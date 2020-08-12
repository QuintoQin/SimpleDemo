package com.qinqin.simpledemo.module.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.app.common.utils.LogUtils;
import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;
import com.qinqin.simpledemo.bean.ActivityCenterInfo;
import com.qinqin.simpledemo.helper.RxHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class RetrofitActivity extends BaseActivity {
    @BindView(R.id.retrofit_btn)
    Button retrofitBtn;
    @BindView(R.id.retrofit_tv)
    TextView retrofitTv;
    private int page = 1;
    private int pagesize = 20;
    private List<ActivityCenterInfo.ListBean> activityCenters = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    public static void startToRetrofit(Context context) {
        Intent intent = new Intent(context, RetrofitActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.retrofit_btn)
    void btn() {
        RxHelper.countdown(10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                LogUtils.e(String.valueOf(integer));
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
