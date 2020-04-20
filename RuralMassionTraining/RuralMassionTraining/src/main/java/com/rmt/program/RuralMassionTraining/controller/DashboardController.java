package com.rmt.program.RuralMassionTraining.controller;

import com.rmt.program.RuralMassionTraining.model.DistTraningPartner;
import com.rmt.program.RuralMassionTraining.repository.DistPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class DashboardController {

    @Autowired
    DistPartnerRepository distPartnerRepository;

    @GetMapping("/home")
    public ModelAndView homePage(Model model){
        return dashBoardPage(model);
    }

    @GetMapping("/")
    public ModelAndView dashBoardPage(Model model){
        List<Object[]> traningPartnerList = distPartnerRepository.getDistrictPartnerDetails();
        List<DistTraningPartner> distTraningPartnerList = new ArrayList<>();
        for(Object[] obj : traningPartnerList){
            DistTraningPartner traningPartner = new DistTraningPartner();
            traningPartner.setDistrictName(obj[0].toString());
            traningPartner.setTrainingPartner(obj[1].toString());
            if(obj[2] != null)
            traningPartner.setTarget(obj[2].toString());
            distTraningPartnerList.add(traningPartner);
        }

        List<Object[]> districtCount = distPartnerRepository.getDistinctDistrictName();
        long totalDistrictCount  = districtCount.stream().count();
        List<Object[]> trainingPartnerCount  = distPartnerRepository.getDistinctTrainingPartnerName();
        long totalTrainingpartnerCount = trainingPartnerCount.stream().count();

        model.addAttribute("distTraningPartnerList",distTraningPartnerList);
        model.addAttribute("totalDistrictCount",totalDistrictCount);
        model.addAttribute("totalTrainingpartnerCount",totalTrainingpartnerCount);
        return new ModelAndView("home");
    }

}
