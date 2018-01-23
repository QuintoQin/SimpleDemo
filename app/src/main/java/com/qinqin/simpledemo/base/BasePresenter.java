package com.qinqin.simpledemo.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by QinQin on 2017/11/1.
 */

public abstract class BasePresenter<T> {
    protected CompositeDisposable mCompositeDisposable;
    //View层的引用
    protected WeakReference<T> mViewRef;

    //进行绑定
    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }

    //进行解绑
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        unDisposable();
    }

    //获得当前View
    protected T getView() {
        return mViewRef.get();
    }

    //判断是否绑定
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 将Disposable添加
     *
     * @param subscription
     */
    public void addDisposable(Disposable subscription) {
        //csb 如果解绑了的话添加 sb 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
