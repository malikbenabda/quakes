package com.smartrecruiters.mba.demo.controller;

import com.smartrecruiters.mba.demo.service.EarthQuakeService;
import com.smartrecruiters.mba.demo.service.USGCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EarthQuakeController {

    @Autowired
    EarthQuakeService quakeService;
    @Autowired
    USGCService usgcService;
    @GetMapping("/quakes")
    public String nearbyQuakeList(ModelAndView model, @RequestParam Double lat, @RequestParam Double lon){

        //model.addAttribute("quakes", usgcService.getEarthquakesAround(lat,lon));
        List<String> listOfPlaces = usgcService.calculateNearestEarthquakes(lat,lon);
        model.addObject("quakes", listOfPlaces);
        return "quakes";
    }

    @GetMapping("/")
    public String welcome(Model model){

        return "quakesddd";
    }
    
}