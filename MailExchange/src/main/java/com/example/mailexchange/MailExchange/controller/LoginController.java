package com.example.mailexchange.MailExchange.controller;

import com.example.mailexchange.MailExchange.configure.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    EmailServiceImpl service;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @RequestMapping("/login")
    public String homePage() throws Exception{
        service.sendEmail(fromEmail,"chiranjibeem@gmail.com","Important","Somebody Login to Your Account. Gote Khusi Au");
        return "redirect:/";
    }
}
