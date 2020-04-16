package com.example.mailexchange.MailExchange.controller;

import com.example.mailexchange.MailExchange.model.Customer;
import com.example.mailexchange.MailExchange.service.CustomerRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    public String forgotPasswordStatus = "";
    @Autowired
    CustomerRegistryService customerRegistryService;

    @GetMapping
    public String forgotPasswordPage(Model model) {
        model.addAttribute("forgotPasswordStatus", forgotPasswordStatus);
        return "login";
    }

    @PostMapping
    public String forgotPassword(@ModelAttribute("forgotPasswordUser") String emailID) {
        if (emailID != null && !"".equals(emailID)) {
            try {
                Customer customer = customerRegistryService.findCustomerDetails(emailID);
                if (customer != null) {
                    forgotPasswordStatus = "Password Reset.Your Password is:" + customer.getPassword();
                    return "redirect:/forgotPassword?success";
                }
            } catch (Exception e) {
                forgotPasswordStatus = "Customer Not Found";
            }
        }
        return "redirect:/forgotPassword?error";
    }
}
