package com.smartrecruiters.mba.demo.service;

import com.smartrecruiters.mba.demo.domain.EarthQuakeInfoResponse;
import com.smartrecruiters.mba.demo.domain.EarthquakePoint;
import com.smartrecruiters.mba.demo.domain.Location;
import com.smartrecruiters.mba.demo.utils.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByKey;

@Service
public class USGCService {



    private final EarthQuakeInfoResponse earthQuakeInfoResponse;

    @Autowired
    public USGCService(EarthQuakeInfoResponse earthQuakeInfoResponse) {

        this.earthQuakeInfoResponse = earthQuakeInfoResponse;
    }

    public List<String> calculateNearestEarthquakes(Double latitude, Double longitude) {
        Location source = new Location(longitude, latitude);
        List<EarthquakePoint> pointList = new ArrayList<>();
        saveLocationDataToList(source, pointList, earthQuakeInfoResponse);
        Map<Double, EarthquakePoint> distanceMapSorted = sortLocationsByIncreasingDistance(source, pointList);
        return consolePrintTop10Results(distanceMapSorted);
    }

    private void saveLocationDataToList(Location source, List<EarthquakePoint> pointList, EarthQuakeInfoResponse earthQuakeInfoResponse) {
        earthQuakeInfoResponse.getFeatures().forEach(feature -> {
            String title = feature.getFeature().getProperties().getTitle();
            Double lo = feature.getFeature().getGeometry().getCoordinates().get(0);
            Double la = feature.getFeature().getGeometry().getCoordinates().get(1);
            Location eqPoint = new Location(lo, la);
            EarthquakePoint point = new EarthquakePoint(title, eqPoint, source);
            pointList.add(point);
        });
    }

    private Map<Double, EarthquakePoint> sortLocationsByIncreasingDistance(Location source, List<EarthquakePoint> pointList) {
        Map<Double, EarthquakePoint> distanceMapUnsorted = pointList.stream()
                .parallel()
                .collect(Collectors.toMap(point -> DistanceCalculator.calculateDistance(
                        source, point.getSource()), Function.identity(), (a1, a2) -> a1
                ));
        return distanceMapUnsorted.entrySet().stream()
                .sorted(comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private List<String> consolePrintTop10Results(Map<Double, EarthquakePoint> distanceMapSorted) {
        return distanceMapSorted.entrySet().stream().limit(10)
                .map(point -> point.getValue().getTitle() + " || " + Math.round(point.getKey() * 100) / 100)
                .collect(Collectors.toList());
    }

}
