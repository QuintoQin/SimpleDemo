package com.qinqin.simpledemo.model.rx;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.OnClick;

public class RxPermissionsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_permissions;
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

    //请求单个权限
    @OnClick(R.id.rx_btn_one)
    void rxBtnOne() {
        //这个请求事件我写在点击事件里面，
        //点击button之后RxPermissions会为我们申请运行时权限
        RxPermissions.getInstance(RxPermissionsActivity.this)
                .request(Manifest.permission.READ_CALENDAR)//这里填写所需要的权限
                .subscribe(aBoolean -> {
                    if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                        Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "获取成功");
                    } else {
                        Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "获取失败");
                    }
                });
    }

    @OnClick(R.id.rx_btn_two)
    void rxBtnTwo() {
        //同时请求多个权限
        RxPermissions.getInstance(RxPermissionsActivity.this)
                .request(Manifest.permission.RECEIVE_MMS,
                        Manifest.permission.READ_CALL_LOG)//多个权限用","隔开
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        //当所有权限都允许之后，返回true
                        Log.i("permissions", "btn_more_sametime：" + true);
                    } else {
                        //只要有一个权限禁止，返回false，
                        //下一次申请只申请没通过申请的权限
                        Log.i("permissions", "btn_more_sametime：" + aBoolean);
                    }
                });
    }

    @OnClick(R.id.rx_btn_three)
    void rxBtnThree() {
        //分别申请多个权限
        RxPermissions.getInstance(RxPermissionsActivity.this)
                //分别申请多个权限时，使用requestEach
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA)
                .subscribe(permission -> {
                    if (permission.name.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        //当ACCESS_FINE_LOCATION权限获取成功时，permission.granted=true
                        Log.i("permissions", Manifest.permission.ACCESS_FINE_LOCATION + "：" + permission.granted);
                    }
                    if (permission.name.equals(Manifest.permission.RECORD_AUDIO)) {
                        //当RECORD_AUDIO 权限获取成功时，permission.granted=true
                        Log.i("permissions", Manifest.permission.RECORD_AUDIO + "：" + permission.granted);
                    }
                    if (permission.name.equals(Manifest.permission.CAMERA)) {
                        //当CAMERA权限获取成功时，permission.granted=true
                        Log.i("permissions", Manifest.permission.CAMERA + "：" + permission.granted);
                    }
                });
    }
}
