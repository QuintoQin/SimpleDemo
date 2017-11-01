package com.qinqin.simpledemo.module.rx;

import android.content.Intent;
import android.os.Bundle;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;

import butterknife.OnClick;

public class RxJavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rax_java;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.rx_one)
    void rxOne() {
        startActivity(new Intent(RxJavaActivity.this, RxPermissionsActivity.class));
    }
}
