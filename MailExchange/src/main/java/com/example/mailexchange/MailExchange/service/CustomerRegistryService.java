package com.example.mailexchange.MailExchange.service;

import com.example.mailexchange.MailExchange.exception.MailExchangeException;
import com.example.mailexchange.MailExchange.model.Customer;
import com.example.mailexchange.MailExchange.model.UserRole;
import com.example.mailexchange.MailExchange.repository.CustomerRegistryRepository;
import com.example.mailexchange.MailExchange.repository.RoleRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerRegistryService {

    @Autowired
    private CustomerRegistryRepository customerRepository;

    @Autowired
    private RoleRegistryRepository roleRepository;

    public Customer findCustomerDetails(String... str) throws MailExchangeException {
        Customer customer = null;
        try {
            if (str.length > 1) {
                List<Customer> customerList = customerRepository.findCustomerByEmailIdAndPassword(str[0], str[1]);
                if (customerList != null && customerList.size() > 0) {
                    customer = customerList.get(0);
                } else {
                    throw new MailExchangeException("Customer Does Not Exist or Bad Credentials");
                }
            } else {
                customer = customerRepository.findCustomerByEmailId(str[0]).get(0);
            }
        } catch (Exception e) {
            throw new MailExchangeException("Customer Does Not Exist " + e);
        }
        return customer;
    }

    public Customer createCustomer(Customer customer) throws MailExchangeException {
        Customer updatedCustomer = null;
        try {
            updatedCustomer = findCustomerDetails(customer.getEmail());
        } catch (Exception e) {
        }
        if (updatedCustomer != null) {
            throw new MailExchangeException("Customer Already Exist :" + customer.getEmail());
        } else {
            try {
                UserRole role = roleRepository.findById(Integer.parseInt(customer.getRoleID())).get();
                customer.setRole(role);
                updatedCustomer = customerRepository.saveAndFlush(customer);
            } catch (Exception e) {
                throw new MailExchangeException("Unable to create customer " + e);
            }
            return updatedCustomer;
        }
    }
}
