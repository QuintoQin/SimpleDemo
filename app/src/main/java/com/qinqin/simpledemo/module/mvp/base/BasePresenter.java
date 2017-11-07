package com.qinqin.simpledemo.module.mvp.base;

import java.lang.ref.WeakReference;

/**
 * Created by 26050 on 2017/11/1.
 */

public class BasePresenter<T> {
    //View层的引用
    protected WeakReference<T> mViewRef;

    //进行绑定
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    //进行解绑
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    //获得当前View
    protected T getView() {
        return mViewRef.get();
    }

    //判断是否绑定
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }
}
