package com.myntra.order.controller;

import com.myntra.order.model.Customer;
import com.myntra.order.service.CustomerRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class CustomerRegistryController {

    @Autowired
    private CustomerRegistryService service;

    @RequestMapping(value = "/fetchCustomer/{custId}", method = RequestMethod.GET)
    public Customer fetchCustomer(@PathVariable(name = "custId") String custId) throws Exception {
        return service.findCustomerDetails(custId);
    }

    @RequestMapping(value = "/fetchRoles", method = RequestMethod.GET)
    public HashMap<String, String> fetchAllRoles() throws Exception {
        return service.fetchRoles();
    }

    @RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
    public Customer createCustomer(@ModelAttribute(name = "cust") Customer customer) throws Exception {
        return service.createCustomer(customer);

    }


}
