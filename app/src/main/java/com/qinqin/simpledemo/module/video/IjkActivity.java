package com.qinqin.simpledemo.module.video;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.video.ijkplayer.IjkplayerActivity;
import com.qinqin.simpledemo.R;

public class IjkActivity extends IjkplayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        createPlayer();
    }
}