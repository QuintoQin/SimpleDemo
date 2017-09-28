package com.qinqin.simpledemo.model.recyclerview.baserecyleradpter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.adapter.HomeAdapter;

import java.util.ArrayList;

public class HomeRyActivity extends AppCompatActivity {
    private RecyclerView ry_home;
    private static final String[] TITLE = {"阿库娅1", "阿库娅2", "阿库娅3",
            "阿库娅4", "阿库娅5", "阿库娅6", "阿库娅7", "阿库娅8", "阿库娅9", "阿库娅10"};
    private static final int[] IMG = {R.drawable.resoure, R.drawable.resoure, R.drawable.resoure,
            R.drawable.resoure, R.drawable.resoure, R.drawable.resoure, R.drawable.resoure,
            R.drawable.resoure, R.drawable.resoure, R.drawable.resoure};
    private ArrayList<HomeItem> mDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ry);
        initView();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item_view, mDataList);
        homeAdapter.openLoadAnimation();
        ry_home.setAdapter(homeAdapter);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }

    private void initView() {
        ry_home = (RecyclerView) findViewById(R.id.ry_home);
        ry_home.setLayoutManager(new LinearLayoutManager(this));
    }
}
