package com.smartrecruiters.mba.demo.service;

import com.smartrecruiters.mba.demo.model.Quake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class EarthQuakeServiceImpl implements EarthQuakeService{
    private final String URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Quake> getEarthquakesAround(Double lat, Double lon) {
           ResponseEntity r =  restTemplate.getForEntity(URL,String.class);
        return null;
    }
}
