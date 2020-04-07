package com.myntra.order.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myntra.order.model.CoronaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/coronaUpdate")
public class CoronaController {

    private static String coronaStatusAPI = "https://api.covid19india.org/v2/state_district_wise.json";

    private static String coronaStatus = "";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public ModelAndView fetchCoronaStatus(Model model) {
        List<CoronaStatus> statusList= null;
        try {
            String response = restTemplate.getForObject(coronaStatusAPI, String.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            Collection<CoronaStatus> readValues = new ObjectMapper().readValue(
                    response, new TypeReference<Collection<CoronaStatus>>() { }
            );
            statusList = readValues.stream().sorted((i,j)->i.getState().compareTo(j.getState())).collect(Collectors.toList());
            model.addAttribute("statusList",statusList);
        } catch (Exception e) {
            coronaStatus = e.getMessage();
        }
        return new ModelAndView("coronaStatus");

    }
}
