package com.myntra.order.controller;

import com.myntra.order.exception.OrderException;
import com.myntra.order.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/createAccount")
public class LoginController {

    public static String status = "";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public String showRegistrationForm(Model model) {
        if (status != "") {
            model.addAttribute("status", status);
        }
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
            return "redirect:/createAccount?success";
        } catch (Exception e) {
            status = e.getLocalizedMessage();
            return "redirect:/createAccount?error";
        }
    }

    public Customer getCustomer(@PathVariable(name = "custId") String custId) throws OrderException {
        Customer customer = null;
        try {
            customer = restTemplate.getForObject("http://CUSTOMER-REGISTRY/fetchCustomer/" + custId,
                    Customer.class);
        } catch (Exception e) {
            throw new OrderException("There is an Error While Fetching Customer Details " + e);
        }
        if (customer != null) {
            return customer;
        } else {
            throw new OrderException("Requested Customer Not Available in The Database ,Customer ID " + custId);
        }
    }

}
