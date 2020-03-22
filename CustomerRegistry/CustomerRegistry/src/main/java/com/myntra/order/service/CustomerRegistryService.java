package com.myntra.order.service;

import com.myntra.order.model.Customer;
import com.myntra.order.repository.CustomerRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CustomerRegistryService {

    @Autowired
    private CustomerRegistryRepository repository;

    public Customer findCustomerDetails(String custID) {
        Customer customer = null;
        try {
            customer = repository.findById(custID).get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return customer;

    }
}
