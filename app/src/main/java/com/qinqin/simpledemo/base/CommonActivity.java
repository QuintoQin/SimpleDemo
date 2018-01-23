package com.qinqin.simpledemo.base;

/**
 * Created by 26050 on 2017/11/15.
 */

public abstract class CommonActivity<V, T extends BasePresenter<V>> extends BaseActivity{
    //代理人
    protected T mPresenter;
    //初始化代理人
    protected abstract T createPresenter();

//    @Override
//    protected void initPresenter() {
//        //初始化代理人
//        mPresenter = createPresenter();
//        mPresenter.attachView((V) this);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
