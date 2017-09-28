package com.qinqin.simpledemo.base;

/**
 * 覃勤
 */
public class BasePresenter<V> {
    protected V mvpView;

    //绑定View
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    //解绑View
    public void detachView() {
        this.mvpView = null;
    }
}
