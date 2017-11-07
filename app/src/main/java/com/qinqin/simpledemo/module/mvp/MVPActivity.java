package com.qinqin.simpledemo.module.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.bean.MainModel;
import com.qinqin.simpledemo.module.mvp.base.BaseActivity;
import com.qinqin.simpledemo.module.mvp.presenter.DataPresenter;
import com.qinqin.simpledemo.module.mvp.view.IDataView;

import butterknife.BindView;

public class MVPActivity extends BaseActivity<IDataView, DataPresenter> implements IDataView {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.pb)
    ProgressBar pb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mPresenter.load("101010100");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected DataPresenter createPresenter() {
        return new DataPresenter();
    }


    @Override
    public void loadData(MainModel mainModel) {
        tv.setText(mainModel.getWeatherinfo().toString());
    }

    @Override
    public void showProgress() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pb.setVisibility(View.GONE);
    }

}
