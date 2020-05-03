package com.rmt.program.RuralMassionTraining.controller;

import com.rmt.program.RuralMassionTraining.model.DistTraningPartner;
import com.rmt.program.RuralMassionTraining.model.OngoingUpdate;
import com.rmt.program.RuralMassionTraining.repository.DistPartnerRepository;
import com.rmt.program.RuralMassionTraining.repository.OngoingUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.util.*;

@Controller
public class DashboardController {

    @Autowired
    DistPartnerRepository distPartnerRepository;

    @Autowired
    OngoingUpdateRepository updateRepository;

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

        OngoingUpdate ongoingUpdate = new OngoingUpdate();
        List<Object[]> dashBoard = updateRepository.getDashboardDetails();
        Object[] object =  dashBoard.get(0);
        ongoingUpdate.setBatchCommenced(((BigInteger)object[0]).intValue());
        ongoingUpdate.setOngoingBatchUpdate(((BigInteger)object[1]).intValue());
        ongoingUpdate.setBatchesCompleted(((BigInteger)object[2]).intValue());
        ongoingUpdate.setCompletedLevel(((BigInteger)object[3]).intValue());
        ongoingUpdate.setEnrolledCandidates(((BigInteger)object[4]).intValue());
        ongoingUpdate.setHouseTagged(((BigInteger)object[5]).intValue());
        ongoingUpdate.setRRL(((BigInteger)object[6]).intValue());
        ongoingUpdate.setSTT(((BigInteger)object[7]).intValue());
        ongoingUpdate.setPlinthLevel(((BigInteger)object[8]).intValue());
        ongoingUpdate.setLintelLevel(((BigInteger)object[9]).intValue());
        ongoingUpdate.setRoofLevel(((BigInteger)object[10]).intValue());


        model.addAttribute("distTraningPartnerList",distTraningPartnerList);
        model.addAttribute("totalDistrictCount",totalDistrictCount);
        model.addAttribute("totalTrainingpartnerCount",totalTrainingpartnerCount);
        model.addAttribute("ongoingUpdtae",ongoingUpdate);

        return new ModelAndView("home");
    }

}
