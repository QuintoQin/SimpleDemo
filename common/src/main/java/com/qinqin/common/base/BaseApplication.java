package com.qinqin.common.base;

import android.app.Application;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.NoEncryption;
import com.qinqin.common.utils.LogUtils;
import com.qinqin.common.utils.Utils;


/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.common.base
 * Date: 2017/5/3
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
        Hawk.init(this)
                .build();
        new LogUtils.Builder().setBorderSwitch(true);
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
