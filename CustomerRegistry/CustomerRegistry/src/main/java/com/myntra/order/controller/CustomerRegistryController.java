package com.myntra.order.controller;

import com.myntra.order.model.Customer;
import com.myntra.order.service.CustomerRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRegistryController {

    @Autowired
    private CustomerRegistryService service;

    @RequestMapping(value = "/fetchCustomer/{custId}",method = RequestMethod.GET)
    public Customer fetchCustomer(@PathVariable(name = "custId") String custId) throws Exception {
        return service.findCustomerDetails(custId);
    }

}
