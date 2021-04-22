package com.smartrecruiters.mba.demo.controller;

import com.smartrecruiters.mba.demo.service.USGCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EarthQuakeController {

    @Autowired
    USGCService usgcService;
    @GetMapping("/quakes")
    @ResponseBody
    public ModelAndView nearbyQuakeList(ModelAndView model, @RequestParam Double lat, @RequestParam Double lon){

        //model.addAttribute("quakes", usgcService.getEarthquakesAround(lat,lon));
        List<String> listOfPlaces = usgcService.calculateNearestEarthquakes(lat,lon);
        model.addObject("quakes", listOfPlaces);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("quakes.html");
        return modelAndView;
    }

//    @GetMapping("/")
//    @ResponseBody
//    public String welcome(){
//
//        return "index.html";
//    }
    @GetMapping("/")
    @ResponseBody
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
    
}
