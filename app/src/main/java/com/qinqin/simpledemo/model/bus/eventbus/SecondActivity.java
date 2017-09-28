package com.qinqin.simpledemo.model.bus.eventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.model.bus.eventbus.entity.Message;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.click_post).setOnClickListener(v -> EventBus.getDefault().post(new Message("传递SecondActivity")));
    }
}
