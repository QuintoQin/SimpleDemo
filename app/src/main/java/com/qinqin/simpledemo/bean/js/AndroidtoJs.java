package com.qinqin.simpledemo.bean.js;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.qinqin.simpledemo.SimpleApplication;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.bean.js
 * Date: 2017/5/10
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
// 继承自Object类
public class AndroidtoJs extends Object {
    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        //System.out.println("JS调用了Android的hello方法");
        Toast.makeText(SimpleApplication.getInstance(), msg, Toast.LENGTH_LONG).show();
    }
}
