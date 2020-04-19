package com.rmt.program.RuralMassionTraining.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
