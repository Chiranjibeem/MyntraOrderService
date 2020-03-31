package com.myntra.order.controller;

import com.myntra.order.model.Customer;
import com.myntra.order.util.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    public String forgotPasswordStatus = "";
    @Autowired
    CustomerUtil customerUtil;

    @GetMapping
    public String forgotPasswordPage(Model model) {
        model.addAttribute("forgotPasswordStatus", forgotPasswordStatus);
        return "login";
    }

    @PostMapping
    public String forgotPassword(@ModelAttribute("forgotPasswordUser") String forgotPasswordUser) {
        if (forgotPasswordUser != null && !"".equals(forgotPasswordUser)) {
            try {
                Customer customer = customerUtil.getCustomer(forgotPasswordUser);
                if (customer != null) {
                    forgotPasswordStatus = "Password Reset.Your Password is:" + customer.getPassword();
                    return "redirect:/forgotPassword?success";
                } else {
                    forgotPasswordStatus = "Customer Not Found";
                }
            } catch (Exception e) {
                forgotPasswordStatus = e.getMessage();
            }
        }
        return "redirect:/forgotPassword?error";
    }
}
