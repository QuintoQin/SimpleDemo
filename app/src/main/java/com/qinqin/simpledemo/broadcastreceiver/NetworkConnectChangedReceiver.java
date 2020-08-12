package com.qinqin.simpledemo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.app.common.utils.NetworkUtils;
import com.app.common.utils.ToastUtils;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.broadcastreceiver
 * Date: 2017/5/12
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class NetworkConnectChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetworkUtils.isConnected()) {
            ToastUtils.showLong("连接上了");
        } else {
            ToastUtils.showLong("断开了");
        }
    }
}
