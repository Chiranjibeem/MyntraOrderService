package com.freelance.ScrumStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SigmaCertificationController {

    @GetMapping("/sigma-online-self-study")
    public String getOnlineSelfStudyPortal(){
        return "sigma-online-self-study";
    }

}
