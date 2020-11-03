package com.qinqin.simpledemo.module.photo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.common.glide.ImageLoader;
import com.qinqin.simpledemo.R;
import com.github.chrisbanes.photoview.PhotoView;

public class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
//        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
//        photoView.setImageResource(R.drawable.ic_test_0);
        ImageView iv = findViewById(R.id.iv);
        ImageLoader.loadImageView(this,"https://t8.baidu.com/it/u=2247852322,986532796&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1604299406&t=8aeb4325ce3517fd8cd26fabeda0b941",iv);
    }
}
