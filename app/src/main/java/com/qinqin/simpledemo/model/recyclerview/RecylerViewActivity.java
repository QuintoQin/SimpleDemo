package com.qinqin.simpledemo.model.recyclerview;

import android.os.Bundle;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;
import com.qinqin.simpledemo.model.recyclerview.baserecyleradpter.HomeRyActivity;

import butterknife.OnClick;

public class RecylerViewActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyler_view;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @OnClick(R.id.home_recyler)
    void homeRy() {
        startToActivity(HomeRyActivity.class);
    }

    @OnClick(R.id.multple_recycler)
    void multple() {

    }

    @OnClick(R.id.pullToRefresh_recycler)
    void pullToRefresh() {

    }

    @OnClick(R.id.section_recycler)
    void section() {

    }
}
