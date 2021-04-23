package com.smartrecruiters.mba.demo;

import com.smartrecruiters.mba.demo.model.EarthquakePoint;
import com.smartrecruiters.mba.demo.model.Location;
import com.smartrecruiters.mba.demo.sort.EarthquakeDistanceComparator;
import com.smartrecruiters.mba.demo.utils.DistanceCalculator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EarthquakeDistanceComparatorTest {
    private EarthquakePoint one;
    private EarthquakePoint two;
    private EarthquakePoint three;
    private EarthquakePoint source;
    private final Location CITY_LOCATION = new Location(41.3965463, 2.1963997);
    private List<EarthquakePoint> ORDER_LIST = new ArrayList<>();

    @Before
    public void setUp() {
        one = new EarthquakePoint("M 5.0 - Prince Edward Islands region", new Location(40.3965463, 20.1963997), CITY_LOCATION);
        two = new EarthquakePoint("M 4.9 - Bouvet Island region", new Location(1.34463, 2.2), CITY_LOCATION);
        three = new EarthquakePoint("M 4.1 - 209km SSE of Ol'ga, Russia", new Location(11.0, 6.7), CITY_LOCATION);

        source = new EarthquakePoint("M 4.5 - 26km ESE of Pamandzi, Mayotte", CITY_LOCATION, CITY_LOCATION);

        ORDER_LIST.add(one);
        ORDER_LIST.add(two);
        ORDER_LIST.add(three);
    }

    @Test
    public void compare() {
        Collections.sort(ORDER_LIST, new EarthquakeDistanceComparator(source));
        assertTrue(ORDER_LIST.get(0) == one);
        assertTrue(ORDER_LIST.get(1) == three);
        assertTrue(ORDER_LIST.get(2) == two);

    }


    @Test
    public void distanceCalculation() {
        Location a1 = new Location(41.3925603, 2.1418532);
        Location a2 = new Location(41.3870194, 2.1678584);

        assertEquals(2.255, DistanceCalculator.calculateDistance(a1, a2), 0.01);
    }


    @Test
    public void isTestRestTemplateOn() {
        String restUrl = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.
                getForEntity(restUrl, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
