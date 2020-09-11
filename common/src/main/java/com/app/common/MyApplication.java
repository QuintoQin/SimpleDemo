package com.app.common;

import android.app.Application;

import com.app.common.tools.log.TimberUtils;
import com.app.common.utils.LogUtils;
import com.app.common.utils.Utils;
import com.orhanobut.hawk.Hawk;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //工具类初始化
        Utils.init(this);
        //Hawk
        Hawk.init(this).build();
        //Timer初始化
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag("App");
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, @NotNull String message, Throwable t) {
            //线上环境所做的log处理
            //null
        }
    }
}
