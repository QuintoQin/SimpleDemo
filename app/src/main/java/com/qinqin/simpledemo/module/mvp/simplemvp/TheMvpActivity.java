package com.qinqin.simpledemo.module.mvp.simplemvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qinqin.simpledemo.R;
import com.qinqin.common.utils.LogUtils;

public class TheMvpActivity extends AppCompatActivity implements MainView {
    TextView tv;
    ProgressBar pb;
    Button btn;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_mvp);
        tv = (TextView) findViewById(R.id.tv);
        pb = (ProgressBar) findViewById(R.id.pb);
        btn = (Button) findViewById(R.id.btn);

        mainPresenter = new MainPresenter(this);

        btn.setOnClickListener(v -> mainPresenter.loadData("101010100"));
        LogUtils.e("onCreate");
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.unbundling();
        LogUtils.e("onStop");
    }

    @Override
    public void dataSuccess(MainModel mainModel) {
        MainModel.WeatherinfoBean weatherinfo = mainModel.getWeatherinfo();
        String showData = "城市" + weatherinfo.getCity()
                + "风向" + weatherinfo.getWD()
                + "风级" + weatherinfo.getWS()
                + "发布时间" + weatherinfo.getTime();
        tv.setText(showData);
    }

    @Override
    public void dataFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


}
