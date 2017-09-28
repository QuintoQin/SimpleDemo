package com.qinqin.simpledemo.model.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.bean.MainModel;

public class MVPActivity extends AppCompatActivity implements MainView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
    }


    @Override
    public void getDataSuceess(MainModel mainModel) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
