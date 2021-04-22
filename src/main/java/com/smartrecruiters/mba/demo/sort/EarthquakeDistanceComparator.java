package com.smartrecruiters.mba.demo.sort;

import com.smartrecruiters.mba.demo.domain.EarthquakePoint;
import com.smartrecruiters.mba.demo.service.DistanceCalculator;

import java.util.Comparator;

public class EarthquakeDistanceComparator implements Comparator<EarthquakePoint> {

    private EarthquakePoint cityCenter;

    public EarthquakeDistanceComparator(EarthquakePoint cityCenter) {
        this.cityCenter = cityCenter;
    }

    @Override
    public int compare(EarthquakePoint o1, EarthquakePoint o2) {
        Double distance1 = DistanceCalculator.calculateDistance(o1.getSource(), cityCenter.getCityCentre());
        Double distance2 = DistanceCalculator.calculateDistance(o2.getSource(), cityCenter.getCityCentre());
        return distance1.compareTo(distance2);
    }
}
