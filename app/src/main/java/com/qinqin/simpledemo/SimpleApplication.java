package com.qinqin.simpledemo;

import android.os.StrictMode;

import com.qinqin.common.base.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

import org.litepal.LitePal;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo
 * Date: 2017/5/3
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class SimpleApplication extends BaseApplication {
    private static SimpleApplication instance;
    public static String cacheDir = "";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //集成LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.

            return;
        }
        LeakCanary.install(this);
        //init litepal
        LitePal.initialize(this);
        //initStrict();
    }

    private void initStrict() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectCustomSlowCalls() //API等级11，使用StrictMode.noteSlowCode
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyDialog() //弹出违规提示对话框
                .penaltyLog() //在Logcat 中打印违规异常信息
                .penaltyFlashScreen() //API等级11
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects() //API等级11
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    public static SimpleApplication getInstance() {
        return instance;
    }
}
