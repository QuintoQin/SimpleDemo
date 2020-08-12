package com.app.common.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by ${user} on 2018/1/10.
 * QinQin
 * 收集的其他的，不常用的Utils类
 */

public class MyUtils {
    /**
     * 跳转到相应的应用商店
     *
     * @param context
     * @param packageName
     */
    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "抱歉，你没有安装应用市场", Toast.LENGTH_LONG).show();
        }
    }

}
