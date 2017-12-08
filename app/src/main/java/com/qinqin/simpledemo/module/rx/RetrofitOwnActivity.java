package com.qinqin.simpledemo.module.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qinqin.common.network.RetrofitHelper;
import com.qinqin.common.network.RxSubscriber;
import com.qinqin.common.network.help.RxSchedulersHelper;
import com.qinqin.common.utils.LogUtils;
import com.qinqin.simpledemo.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitOwnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_own);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserApi.API_SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Call call = retrofit.create(UserApi.class).loadData("58fd6b7c1d868b1a89a767257c4cdb60", "1111111", "123456", "1", "url/image/demo.jpg");
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                LogUtils.e(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                LogUtils.e(t);
            }
        });
//        RetrofitHelper.createApi(UserApi.class)
//                .loadData("58fd6b7c1d868b1a89a767257c4cdb60", "1111111", "123456", "1", "url/image/demo.jpg")
//                .compose(RxSchedulersHelper.observeOnMainThread())
//                .subscribe(new RxSubscriber<String>() {
//                    @Override
//                    public void onSuccess(String s) {
//                        //ToastUtils.showLong("成功了" + mainModel.toString());
//                        LogUtils.e(s);
//                    }
//                });
    }
}
