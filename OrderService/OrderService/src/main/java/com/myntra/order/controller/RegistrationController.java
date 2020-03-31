package com.myntra.order.controller;

import com.myntra.order.exception.OrderException;
import com.myntra.order.model.Customer;
import com.myntra.order.util.EmailServiceImpl;
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

    @Autowired
    EmailServiceImpl emailService;


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
            if (updatedCustomer != null) {
                String fromEmail = "chiranjibeemohapatra@gmail.com";
                String email = customer.getAddress();
                String subject = "Registration Success";
                String message = "Hi " + customer.getCustId() + "\n" +
                        subject + "\n" +
                        "Username :" + customer.getCustId() + "\n" +
                        "Password :" + customer.getPassword() + "\n" +
                        "\n" + "\n" + "\n" +
                        "This is a system generated Email, Please don't reply";
                try {
                    emailService.sendEmail(fromEmail, email, subject, message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            registrationStatus = "Registration Successfull.Login to continue";
            return "redirect:/createAccount?success";
        } catch (Exception e) {
            registrationStatus = e.getMessage();
            return "redirect:/createAccount?error";
        }
    }

}
