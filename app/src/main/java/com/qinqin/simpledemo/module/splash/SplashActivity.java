package com.qinqin.simpledemo.module.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.qinqin.simpledemo.MainActivity;
import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;
import com.qinqin.simpledemo.helper.RxHelper;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.model.splash
 * Date: 2017/4/25
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.splash_img)
    ImageView splashImg;
    @BindView(R.id.countdown_tv)
    TextView countdown_tv;

    private String countStr;
    private Disposable disposable;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //ImageLoader.loadImageView(this,"http://oi984847e.bkt.clouddn.com/4f965df4edd6a9754600073d.jpg",splashImg);
        disposable = Observable.timer(5000, TimeUnit.MILLISECONDS)
//                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Long -> {
                    goToActivity();
                });

        RxHelper.countdown(5).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                countStr = String.valueOf(integer) + "秒";
                countdown_tv.setText(countStr);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void initData() {

    }

    private void goToActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        //跳转动画，右进左出
        overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        finish();
        disposable.dispose();
    }

    @OnClick(R.id.countdown_tv)
    void skip() {
        goToActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
