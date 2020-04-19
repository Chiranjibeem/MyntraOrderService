package com.rmt.program.RuralMassionTraining.controller;

import com.rmt.program.RuralMassionTraining.exception.RMTException;
import com.rmt.program.RuralMassionTraining.model.Customer;
import com.rmt.program.RuralMassionTraining.service.CustomerRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    CustomerRegistryService customerRegistryService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationStatus", registrationStatus);
        return "login";
    }

    @PostMapping
    public String createAccount(@ModelAttribute("customer") Customer customer, BindingResult result, Model model) throws RMTException {
        Customer updatedCustomer = null;
        try {
            updatedCustomer = customerRegistryService.createCustomer(customer);
            registrationStatus = "Registration Successfull.Login to continue";
            return "redirect:/createAccount?success";
        } catch (Exception e) {
            registrationStatus = e.getMessage();
            return "redirect:/createAccount?error";
        }
    }
}
