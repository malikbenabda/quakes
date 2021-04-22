package com.smartrecruiters.mba.demo.controller;

import com.smartrecruiters.mba.demo.model.Quake;
import com.smartrecruiters.mba.demo.service.EarthQuakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public class EarthQuakeController {

    @Autowired
    EarthQuakeService quakeService;
    @GetMapping("/quakes")
    public String nearbyQuakeList(Model model, @RequestParam Double lat, @RequestParam Double lon){

        model.addAttribute("quakes", quakeService.getEarthquakesAround(lat,lon));
        return "quakes";
    }

    @GetMapping("/")
    public String welcome(Model model){

        return "quakes";
    }
    
}
