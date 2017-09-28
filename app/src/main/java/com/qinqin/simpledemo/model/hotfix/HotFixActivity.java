package com.qinqin.simpledemo.model.hotfix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.model.hotfix.tinker.TinkerActivity;

public class HotFixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix);
        findViewById(R.id.tinker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HotFixActivity.this, TinkerActivity.class));
            }
        });
    }
}
