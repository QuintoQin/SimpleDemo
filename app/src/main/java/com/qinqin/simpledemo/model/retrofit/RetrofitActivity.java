package com.qinqin.simpledemo.model.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;
import com.qinqin.simpledemo.bean.ActivityCenterInfo;
import com.qinqin.simpledemo.model.retrofit.api.CenterService;
import com.qinqin.common.network.RetrofitHelper;
import com.qinqin.common.network.help.RxSchedulersHelper;
import com.qinqin.common.utils.LogUtils;
import com.qinqin.common.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


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
//        RetrofitHelper.createApi(CenterService.class)
//                .getActivityCenterList(page, pagesize)
//                .compose(bindToLifecycle())
//                .compose(RxSchedulersHelper.observeOnMainThread())
//                .map(activityCenterInfo -> activityCenterInfo.getList())
//                .flatMap(new Func1<List<ActivityCenterInfo.ListBean>, Observable<ActivityCenterInfo.ListBean>>() {
//                    @Override
//                    public Observable<ActivityCenterInfo.ListBean> call(List<ActivityCenterInfo.ListBean> listBeen) {
//                        return Observable.from(listBeen);
//                    }
//                })
//                .subscribe(new Subscriber<ActivityCenterInfo.ListBean>() {
//                    @Override
//                    public void onCompleted() {
//                        retrofitTv.setText(sb.toString());
//                        LogUtils.e("onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastUtils.showShort("网络错误");
//                    }
//
//                    @Override
//                    public void onNext(ActivityCenterInfo.ListBean listBean) {
//                        activityCenters.add(listBean);
//                        sb.append(listBean.toString());
//                    }
//                });
//        .subscribe(new RxSubscriber<ActivityCenterInfo.ListBean>() {
//            @Override
//            public void onSuccess(ActivityCenterInfo.ListBean listBean) {
//                activityCenters.add(listBean);
//            }
//        });

//                .subscribe(activityCenterInfo -> {
//                    activityCenters.addAll(activityCenterInfo.getList());
//                    for (ActivityCenterInfo.ListBean data : activityCenters) {
//                        sb.append(data.toString());
//                    }
//                    retrofitTv.setText(sb.toString());
//                });
    }
}
