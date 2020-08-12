package com.app.common.utils;

/**
 * Created by ${user} on 2018/8/9.
 * QinQin
 */

public class Operation {
    //除法
    public static Double division(double a, double b) {
        double div = 0;
        if (b != 0) {
            div = a / b;
        } else {
            div = 0;
        }
        return div;
    }
}
