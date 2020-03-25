package com.myntra.order.controller;

import com.myntra.order.model.Customer;
import com.myntra.order.service.CustomerRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CustomerRegistryController {

    @Autowired
    private CustomerRegistryService service;

    @RequestMapping(value = "/fetchCustomer/{custId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> fetchCustomer(@PathVariable(name = "custId") String custId) throws Exception {
        Customer customer = null;
        try {
            customer = service.findCustomerDetails(custId);
            return new ResponseEntity<Object>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/fetchCustomer/{custId}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> fetchCustomer(@PathVariable(name = "custId") String custId, @PathVariable(name = "password") String password) throws Exception {
        Customer customer = null;
        try {
            customer = service.findCustomerDetails(custId, password);
            return new ResponseEntity<Object>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/fetchRoles", method = RequestMethod.GET)
    public HashMap<String, String> fetchAllRoles() throws Exception {
        return service.fetchRoles();
    }

    @RequestMapping(value = "/createCustomer", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> createCustomer(@ModelAttribute(name = "cust") Customer customer) throws Exception {
        Customer newCustomer = null;
        try {
            newCustomer = service.createCustomer(customer);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }


}
