package com.freelance.ScrumStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScrumCertificationController {

    @GetMapping("/scrum-online-self-study")
    public String getOnlineSelfStudyPortal(){
        return "scrum-online-self-study";
    }
}
