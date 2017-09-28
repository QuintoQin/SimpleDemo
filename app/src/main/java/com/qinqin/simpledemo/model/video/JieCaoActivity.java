package com.qinqin.simpledemo.model.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qinqin.simpledemo.R;
import com.qinqin.common.glide.ImageLoader;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class JieCaoActivity extends AppCompatActivity {
    JCVideoPlayerStandard jcVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_cao);
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
        ImageLoader.loadImageView(this,"http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640",jcVideoPlayerStandard.thumbImageView);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
