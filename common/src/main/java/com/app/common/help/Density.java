package com.app.common.help;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.DisplayMetrics;

import com.app.common.utils.Operation;

import java.text.DecimalFormat;

/**
 * Created by ${user} on 2018/8/9.
 * QinQin
 * 通过修改系统参数来适配android设备
 */

public class Density {
    private static float appDensity;
    private static float appScaledDensity;
    private static DisplayMetrics appDisplayMetrics;
    private final static String WIDTH = "width";
    private final static String HEIGHT = "height";

    public static void setDensity(@NonNull Application application) {
        //获取application的DisplayMetrics
        appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (appDensity == 0) {
            //初始化的时候赋值
            appDensity = appDisplayMetrics.density;
            appScaledDensity = appDisplayMetrics.scaledDensity;

            //添加字体变化的监听
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体改变后,将appScaledDensity重新赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }
    }

    //此方法在BaseActivity中做初始化(如果不封装BaseActivity的话,直接用下面那个方法就好了)
    public static void setDefault(Activity activity) {
        setAppOrientation(activity, Density.WIDTH);
    }

    public static void setHeight(Activity activity) {
        setAppOrientation(activity, Density.HEIGHT);
    }

    //此方法用于在某一个Activity里面更改适配的方向
    public static void setOrientation(Activity activity, String orientation) {
        setAppOrientation(activity, orientation);
    }

    /**
     * targetDensity
     * targetScaledDensity
     * targetDensityDpi
     * 这三个参数是统一修改过后的值
     * <p>
     * orientation:方向值,传入width或height
     */
    private static void setAppOrientation(@Nullable Activity activity, String orientation) {

        float targetDensity = 0;
        try {
            Double division;
            //根据带入参数选择不同的适配方向
            if (orientation.equals("height")) {
                division = Operation.division(appDisplayMetrics.heightPixels, 667);
            } else {
                division = Operation.division(appDisplayMetrics.widthPixels, 360);
            }
            //由于手机的长宽不尽相同,肯定会有除不尽的情况,有失精度,所以在这里把所得结果做了一个保留两位小数的操作
            DecimalFormat df = new DecimalFormat("0.00");
            String s = df.format(division);
            targetDensity = Float.parseFloat(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        /**
         *
         * 最后在这里将修改过后的值赋给系统参数
         *
         * 只修改Activity的density值
         */

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
