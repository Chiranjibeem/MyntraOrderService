package com.example.mailexchange.MailExchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SendMailController {

    @RequestMapping("/sendMailList")
    public ModelAndView sendMailList(Model model){
        return new ModelAndView("sendMailList");
    }

    @RequestMapping("/sendNewEmail")
    public ModelAndView sendNewEmail(Model model){
        return new ModelAndView("sendNewEmail");
    }
}
