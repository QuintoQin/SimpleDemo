package com.qinqin.simpledemo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qinqin.simpledemo.R;
import com.qinqin.common.utils.LogUtils;
import com.qinqin.common.utils.ToastUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.base
 * Date: 2017/4/25
 * user: user QuintoQin
 * ACTIVITY基类
 * @author 覃勤
 * @version : 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {
    //布局id
    protected abstract int getLayoutId();

    //初始化toolbar
    protected abstract void initToolbar();

    //初始视图
    protected abstract void initViews(Bundle savedInstanceState);

    //初始化数据
    protected abstract void initData();

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(getLayoutId());
        //绑定ButterKnife
        ButterKnife.bind(this);
        //初始化toolbar
        initToolbar();
        //初始视图
        initViews(savedInstanceState);
        //初始化数据
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 启动Activity
     *
     * @param context：Context
     * @param cls：Class<?>
     * @param isFinish：是否关闭Activity
     */
    protected void startActivityByIntent(Context context, Class<?> cls, Boolean isFinish) {
        startActivity(new Intent(context, cls));
        if (isFinish) {
            finish();
        }
    }

    /**
     * 弹出toast 显示时长short
     * @param msg
     */
    protected void toast(String msg) {
        ToastUtils.showShort(msg);
    }

    /**
     * 根据传入的类(class)打开指定的activity
     * @param pClass
     */
    protected void startToActivity(Class<?> pClass) {
        Intent Intent = new Intent();
        Intent.setClass(this, pClass);
        startActivity(Intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

}
