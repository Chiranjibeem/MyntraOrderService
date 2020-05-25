package com.example.mailexchange.MailExchange.controller;

import com.example.mailexchange.MailExchange.configure.EmailServiceImpl;
import com.example.mailexchange.MailExchange.exception.MailExchangeException;
import com.example.mailexchange.MailExchange.model.Customer;
import com.example.mailexchange.MailExchange.service.CustomerRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/createAccount")
public class RegistrationController {

    public String registrationStatus = "";
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    CustomerRegistryService customerRegistryService;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationStatus", registrationStatus);
        return "login";
    }

    @PostMapping
    public String createAccount(@ModelAttribute("customer") Customer customer, BindingResult result, Model model) throws MailExchangeException {
        Customer updatedCustomer = null;
        try {
            updatedCustomer = customerRegistryService.createCustomer(customer);
            if (updatedCustomer != null) {
                String email = customer.getEmail();
                String subject = "Registration Success";
                String message = "Hi " + customer.getCustName() + "\n" +
                        subject + "\n" +
                        "Username :" + customer.getEmail() + "\n" +
                        "Password :" + customer.getPassword() + "\n" +
                        "\n" + "\n" + "\n" +
                        "This is a system generated Email, Please don't reply";
                try {
                    emailService.sendEmailWithAttachment(fromEmail, "Admin", subject, message, customer.getEmail(), "Customer");
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
