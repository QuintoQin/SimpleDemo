package com.qinqin.simpledemo.base;

import android.os.Bundle;
import android.view.View;

/**
 * Created by 26050 on 2017/11/15.
 */

public abstract class CommonFragemnt<V, T extends BasePresenter<V>> extends BaseFragment {
    //代理人
    protected T mPresenter;
    //初始化代理人
    protected abstract T createPresenter();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化代理人
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
