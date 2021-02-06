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

    String constan = "select deptid,count(employee) from employee " +
            "having sal > 1000 group by deptid";


    @RequestMapping("/addMailer")
    public ModelAndView addMailer(){
        return new ModelAndView("addMailer");
    }
}
