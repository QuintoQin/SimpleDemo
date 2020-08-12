package com.app.common;

import android.app.Application;

import com.app.common.tools.log.TimberUtils;
import com.app.common.utils.LogUtils;
import com.app.common.utils.Utils;
import com.orhanobut.hawk.Hawk;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //工具类初始化
        Utils.init(this);
        //Hawk
        Hawk.init(this).build();
        //Timer初始化
        TimberUtils.setLogDebug();
    }
}
