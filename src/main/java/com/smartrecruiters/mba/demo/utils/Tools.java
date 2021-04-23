package com.smartrecruiters.mba.demo.utils;

public class Tools {
    public static boolean isValidCoords(Double lat, Double lon) {
        if (lat.isNaN() || lat == null) return false;
        else if (lon.isNaN() || lon == null) return false;
        else if (lon.doubleValue() == 0 && lat.doubleValue() == 0) return false;
        else return true;
    }

}
