package com.app.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by 26050 on 2017/10/23.
 */

public class PackageUtils {
    public static String getPackageVersion(Context context) {
        String version = "1.0";
        //获取PackageManager
        PackageManager manager = context.getPackageManager();

        try {
            //获取PackageInfo
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);

            //获取当前app版本号
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
