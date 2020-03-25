package com.myntra.order.controller;

import com.myntra.order.exception.OrderException;
import com.myntra.order.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/createAccount")
public class RegistrationController {

    public String registrationStatus = "";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationStatus", registrationStatus);
        return "login";
    }

    @PostMapping
    public String createAccount(@ModelAttribute("customer") Customer customer, BindingResult result, Model model) throws OrderException {
        Customer updatedCustomer = null;

        String url = "?cust=&custId=" + customer.getCustId() +
                "&custName=" + customer.getCustId() +
                "&address=" + customer.getAddress() +
                "&phoneNumber=" + customer.getPhoneNumber() +
                "&password=" + customer.getPassword() +
                "&roleID=" + customer.getRoleId();
        try {
            updatedCustomer = restTemplate.getForObject("http://CUSTOMER-REGISTRY/createCustomer" + url, Customer.class);
            registrationStatus = "Registration Successfull.Login to continue";
            return "redirect:/createAccount?success";
        } catch (Exception e) {
            registrationStatus = e.getMessage();
            return "redirect:/createAccount?error";
        }
    }

}
