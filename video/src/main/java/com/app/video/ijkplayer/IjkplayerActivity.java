package com.app.video.ijkplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.app.video.R;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkplayerActivity extends AppCompatActivity {
    private IjkMediaPlayer mPlayer;
    protected SurfaceView mSurfaceView;

    protected SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            createPlayer();
            mPlayer.setDisplay(mSurfaceView.getHolder());
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            releasePlayer();
        }
    };

    protected void createPlayer() {
        if (mPlayer == null){
            mPlayer = new IjkMediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.setDataSource("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4");
                //mPlayer.setDataSource("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4");
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPlayer.prepareAsync();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mSurfaceView = findViewById(R.id.video_view);
        mSurfaceView.getHolder().addCallback(callback);
    }

    protected int getLayout() {
        return R.layout.activity_ijkplayer;
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    protected void onDestroy() {
        mSurfaceView.getHolder().removeCallback(callback);
        super.onDestroy();
    }


}