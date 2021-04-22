package com.smartrecruiters.mba.demo.service;

import com.smartrecruiters.mba.demo.model.Quake;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EarthQuakeService {

    List<Quake> getEarthquakesAround(Double lat, Double lon );
}

