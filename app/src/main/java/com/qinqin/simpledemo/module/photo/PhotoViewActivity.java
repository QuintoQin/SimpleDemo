package com.qinqin.simpledemo.module.photo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.qinqin.simpledemo.R;
import com.github.chrisbanes.photoview.PhotoView;

public class PhotoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.ic_test_0);
    }
}
