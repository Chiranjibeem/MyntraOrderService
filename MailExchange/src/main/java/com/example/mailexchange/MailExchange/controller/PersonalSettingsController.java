package com.example.mailexchange.MailExchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonalSettingsController {

    @RequestMapping("/personalSettings")
    public ModelAndView generalSettings(Model model){
        return new ModelAndView("personalSettings");

    }
}
