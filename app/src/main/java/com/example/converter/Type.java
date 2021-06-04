package com.example.converter;

import androidx.arch.core.util.Function;

public enum Type {
    METER((value) -> value),
    KILOMETER((value) -> value * 1000),
    CENTIMETER((value) -> value / 100),
    FOOT((value) -> value * 0.3048),
    MILE((value) -> value * 1609.34),
    INCH((value) -> value * 0.0254);

    private Function<Double, Double> function;

    Type(Function<Double, Double> function) {
        this.function = function;
    }

    public double convertToMeter(double value) {
        return this.function.apply(value);
    }
}
