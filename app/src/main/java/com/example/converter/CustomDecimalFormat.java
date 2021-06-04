package com.example.converter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public enum CustomDecimalFormat {

   RU((DecimalFormat) NumberFormat.getNumberInstance(Locale.FRANCE)), EN((DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH));

   public DecimalFormat decimalFormat;

    CustomDecimalFormat(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
        this.decimalFormat.applyPattern("#.###");
    }

    public String format(double value) {
        return this.decimalFormat.format(value);
    }
}
