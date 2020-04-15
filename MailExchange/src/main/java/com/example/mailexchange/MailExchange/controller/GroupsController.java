package com.example.mailexchange.MailExchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GroupsController {

    @RequestMapping("/groupList")
    public ModelAndView getAccountList(Model model) {
        return new ModelAndView("groupList");
    }

    @RequestMapping("/addGroup")
    public ModelAndView addAccount(Model model) {
        return new ModelAndView("addGroup");
    }

}
