/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pr.corina.lab2pr.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import net.sf.jsefa.common.converter.SimpleTypeConverter;

public class DoubleConverter implements SimpleTypeConverter {

    private static final DoubleConverter INSTANCE = new DoubleConverter();

    public static DoubleConverter create() {
        return INSTANCE;
    }

    private DoubleConverter() {
    }

    @Override
    public Object fromString(String s) {
        Double d=new Double(s);
        d=round(d, 2);
        return d; 
    }

    @Override
    public String toString(Object d) {
        return d.toString();
    }
    
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
