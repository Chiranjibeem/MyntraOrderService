package com.example.mailexchange.MailExchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AllAccountsController {

    @RequestMapping("/accountList")
    public ModelAndView getAccountList(Model model) {
        return new ModelAndView("accountList");
    }

    @RequestMapping("/addAccount")
    public ModelAndView addAccount(Model model) {
        return new ModelAndView("addAccount");
    }

    @RequestMapping("/importContacts")
    public ModelAndView importAccounts(Model model) {
        return new ModelAndView("importAccounts");
    }

}
