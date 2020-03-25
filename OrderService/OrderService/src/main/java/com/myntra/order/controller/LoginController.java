package com.myntra.order.controller;

import com.myntra.order.exception.OrderException;
import com.myntra.order.model.Customer;
import com.myntra.order.model.Login;
import com.myntra.order.util.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/validateLogin")
public class LoginController {

    public String loginStatus = "";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerUtil customerUtil;

    @GetMapping
    public String showLoginPage(Model model) {
        model.addAttribute("loginStatus", loginStatus);
        if (loginStatus.equals("SUCCESS")) {
            return "redirect:/index.html";
        }
        return "login";
    }

    @PostMapping
    public String validateLogin(@ModelAttribute("signUp") Login signUp, BindingResult result, Model model) throws OrderException {
        Customer customer = null;
        if (signUp.getCustUserName() != null && !"".equals(signUp.getCustUserName())) {
            if (signUp.getCustUserPassword() != null && !"".equals(signUp.getCustUserPassword())) {
                try {
                    customer = customerUtil.getCustomer(signUp.getCustUserName(), signUp.getCustUserPassword());
                    loginStatus = "SUCCESS";
                    return "redirect:/validateLogin?success";
                } catch (Exception e) {
                    loginStatus = e.getMessage();
                }

            } else {
                loginStatus = "Password Can't be Empty";
            }
        } else {
            loginStatus = "Username Can't be Empty";
        }
        return "redirect:/validateLogin?error";
    }

}
