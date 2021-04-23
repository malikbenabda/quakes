package com.smartrecruiters.mba.demo.service;

import java.util.List;

public interface USGCService {

    List<String> calculateNearestEarthquakes(Double lat, Double lon);
}
