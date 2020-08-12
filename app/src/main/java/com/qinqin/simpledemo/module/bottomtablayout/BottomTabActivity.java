package com.qinqin.simpledemo.module.bottomtablayout;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.model.bottomtablayout
 * Date: 2017/4/26
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class BottomTabActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_one)
    Button tabOne;
    @BindView(R.id.tab_two)
    Button tabTwo;
    @BindView(R.id.tab_three)
    Button tabThree;

    @Override
    protected int getLayoutId() {
        return R.layout.bottom_tab;
    }

    @Override
    protected void initToolbar() {
        toolbar.setTitle("BottomTabActivity");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.tab_one)
    void tabOne() {
        startActivity(new Intent(BottomTabActivity.this, RadioGroupActivity.class));
    }

    @OnClick(R.id.tab_two)
    void tabTwo() {
        startActivity(new Intent(BottomTabActivity.this, TabLayoutActivity.class));
    }

    @OnClick(R.id.tab_three)
    void tabThree() {
        startActivity(new Intent(BottomTabActivity.this, BottomNavigationActivity.class));
    }

    @OnClick(R.id.tab_four)
    void tabFour() {
        startActivity(new Intent(BottomTabActivity.this, TabHostActivity.class));
    }

    @OnClick(R.id.tab_five)
    void tabFive() {
        startActivity(new Intent(BottomTabActivity.this, CustomTabActivity.class));
    }
}
