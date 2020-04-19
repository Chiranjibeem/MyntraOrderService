package com.rmt.program.RuralMassionTraining.controller;

import com.rmt.program.RuralMassionTraining.exception.RMTException;
import com.rmt.program.RuralMassionTraining.model.Customer;
import com.rmt.program.RuralMassionTraining.model.Login;
import com.rmt.program.RuralMassionTraining.service.CustomerRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/validateLogin")
public class LoginController {

    public String loginStatus = "";

    public String customerName = "";

    @Autowired
    CustomerRegistryService customerRegistryService;

    @GetMapping
    public String showLoginPage(Model model) {
        model.addAttribute("loginStatus", loginStatus);
        if (loginStatus.equals("SUCCESS")) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping
    public String validateLogin(@ModelAttribute("signUp") Login signUp, BindingResult result, Model model, HttpSession session) throws RMTException {
        Customer customer = null;
        if (signUp.getCustUserName() != null && !"".equals(signUp.getCustUserName())) {
            if (signUp.getCustUserPassword() != null && !"".equals(signUp.getCustUserPassword())) {
                try {
                    customer = customerRegistryService.findCustomerDetails(signUp.getCustUserName(), signUp.getCustUserPassword());
                    loginStatus = "SUCCESS";
                    customerName = signUp.getCustUserName();

                    if (!customerName.equalsIgnoreCase("")) {
                        String[] arr = customerName.split("@");
                        session.setAttribute("customername", arr[0]);
                    }
                    return "redirect:/validateLogin?success";
                } catch (Exception e) {
                    loginStatus = "Bad Credentials";
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
