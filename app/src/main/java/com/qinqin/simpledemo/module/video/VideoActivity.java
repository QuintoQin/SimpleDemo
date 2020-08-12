package com.qinqin.simpledemo.module.video;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import com.qinqin.simpledemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.video_jiecao)
    Button videoJiecao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.video_jiecao)
     void jieCao() {
        startActivity(new Intent(VideoActivity.this, JieCaoActivity.class));
    }
}
