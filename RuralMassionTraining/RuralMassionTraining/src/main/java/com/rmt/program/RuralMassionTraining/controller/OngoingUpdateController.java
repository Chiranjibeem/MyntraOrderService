package com.rmt.program.RuralMassionTraining.controller;

import com.rmt.program.RuralMassionTraining.model.DistTraningPartner;
import com.rmt.program.RuralMassionTraining.model.OngoingUpdate;
import com.rmt.program.RuralMassionTraining.repository.DistPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OngoingUpdateController {

    @Autowired
    DistPartnerRepository distPartnerRepository;

    @GetMapping("/ongoingUpdate")
    public ModelAndView ongoingUpdate(@ModelAttribute("ongoingUpdate") OngoingUpdate ongoingUpdate, BindingResult result, Model model){
        List<Object[]> districtCount = distPartnerRepository.getDistinctDistrictName();
        List<String> districtList = districtCount.stream().map(i->i[0].toString()).collect(Collectors.toList());
        List<Object[]> trainingPartnerCount  = distPartnerRepository.getDistinctTrainingPartnerName();
        List<String> trainingPartnerList = trainingPartnerCount.stream().map(i->i[0].toString()).collect(Collectors.toList());

        model.addAttribute("districtList",districtList);
        model.addAttribute("trainingPartnerList",trainingPartnerList);
        return new ModelAndView("ongoingUpdate");
    }

}
