package com.example.mailexchange.MailExchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeneralSettingsController {

    @RequestMapping("/generalSettings")
    public ModelAndView generalSettings(Model model){
        return new ModelAndView("generalSettings");

    }
}
