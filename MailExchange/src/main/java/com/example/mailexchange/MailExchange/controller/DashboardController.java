package com.example.mailexchange.MailExchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String dashBoardPage(Model model){
        return "home";
    }

    @GetMapping("/home")
    public String dashBoardPageHome(Model model){
        return "home";
    }
}
