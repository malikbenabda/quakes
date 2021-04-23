package com.smartrecruiters.mba.demo.controller;

import com.smartrecruiters.mba.demo.service.USGCService;
import com.smartrecruiters.mba.demo.service.USGCServiceImpl;
import com.smartrecruiters.mba.demo.utils.ErrorEnum;
import com.smartrecruiters.mba.demo.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EarthQuakeController {

    @Autowired
    USGCService usgcService;
    @GetMapping("/quakes")
    @ResponseBody
    public ModelAndView nearbyQuakeList(Model model1, @RequestParam Double lat, @RequestParam Double lon){
        if (Tools.isValidCoords( lat, lon)){
            List<String> listOfPlaces = usgcService.calculateNearestEarthquakes(lat,lon);
            model1.addAttribute("quakes", listOfPlaces);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("index.html");
            return modelAndView;
        }else{
            model1.addAttribute("error", ErrorEnum.WRONG_COORD_FORMAT.toString());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("index.html");
            return  modelAndView;
        }


    }
    
}
