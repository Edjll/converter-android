package com.example.converter;

import java.text.DecimalFormat;

public interface Converter {

    static double convertToKilometer(double value) {
        return value / 1000;
    }

    static double convertToCentimeter(double value) {
        return value * 100;
    }

    static double convertToMile(double value) {
        return value * 0.000621371;
    }

    static double convertToFoot(double value) {
        return value * 3.28084;
    }

    static double convertToInch(double value) {
        return value * 39.3701;
    }
}
