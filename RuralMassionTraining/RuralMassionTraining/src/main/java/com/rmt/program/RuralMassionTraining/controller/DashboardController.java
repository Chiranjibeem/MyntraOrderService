package com.rmt.program.RuralMassionTraining.controller;

import com.rmt.program.RuralMassionTraining.model.DistTraningPartner;
import com.rmt.program.RuralMassionTraining.repository.DistPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    DistPartnerRepository distPartnerRepository;

    @GetMapping("/")
    public ModelAndView dashBoardPage(Model model){
        List<DistTraningPartner> distTraningPartnerList = distPartnerRepository.findAll();
        model.addAttribute("distTraningPartnerList",distTraningPartnerList);
        return new ModelAndView("home");
    }

}
