package com.myntra.order.service;

import com.myntra.order.model.Customer;
import com.myntra.order.model.UserRole;
import com.myntra.order.repository.CustomerRegistryRepository;
import com.myntra.order.repository.RoleRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerRegistryService {

    @Autowired
    private CustomerRegistryRepository customerRepository;

    @Autowired
    private RoleRegistryRepository roleRepository;

    public Customer findCustomerDetails(String... str) throws Exception {
        Customer customer = null;
        try {
            if (str.length > 1) {
                List<Customer> customerList = customerRepository.findCustomerByIdAndPassword(str[0], str[1]);
                if (customerList != null && customerList.size() > 0) {
                    customer = customerList.get(0);
                } else {
                    throw new Exception("Customer Does Not Exist or Bad Credentials");
                }
            } else {
                customer = customerRepository.findById(str[0]).get();
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Unable to find Customer " + e);
        }
        return customer;

    }

    public HashMap<String, String> fetchRoles() {
        HashMap<String, String> roleMap = new HashMap<String, String>();
        List<UserRole> userRoleList = roleRepository.findAll();
        if (userRoleList != null) {
            return userRoleList.stream().collect(Collectors.toMap(UserRole::getRoleName, UserRole::getRoleID,
                    (oldValue, newValue) -> oldValue,
                    HashMap::new));
        }
        return roleMap;
    }

    public Customer createCustomer(Customer customer) throws Exception {
        Customer updatedCustomer = null;
        try {
            updatedCustomer = findCustomerDetails(customer.getCustId());
        } catch (Exception e) {
        }
        if (updatedCustomer != null) {
            throw new Exception("Customer Already Exist :" + customer.getCustId());
        } else {
            try {
                UserRole role = roleRepository.findById(customer.getRoleID()).get();
                customer.setRole(role);
                updatedCustomer = customerRepository.saveAndFlush(customer);
            } catch (Exception e) {
                throw new Exception("Unable to create customer " + e);
            }
            return updatedCustomer;
        }
    }
}
