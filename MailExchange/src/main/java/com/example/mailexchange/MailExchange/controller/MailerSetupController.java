package com.example.mailexchange.MailExchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailerSetupController {

    @RequestMapping("/mailerList")
    public ModelAndView getMailerList(Model model){
        return new ModelAndView("/mailerList");
    }

    @RequestMapping("/addMailer")
    public ModelAndView addMailer(){
        return new ModelAndView("addMailer");
    }
}
