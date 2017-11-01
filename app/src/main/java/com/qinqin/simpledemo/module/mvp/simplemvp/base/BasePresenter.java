package com.qinqin.simpledemo.module.mvp.simplemvp.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.model.mvp.simplemvp.base
 * Date: 2017/5/24
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class BasePresenter<V> {
    protected V view;
    private CompositeDisposable disposables2Stop;// 管理Stop取消订阅者者
    private CompositeDisposable disposables2Destroy;// 管理Destroy取消订阅者者

    protected void attachView(V view) {
        this.view = view;
        if (disposables2Stop != null) {
            throw new IllegalStateException("onCreate called multiple times");
        }
        disposables2Stop = new CompositeDisposable();
    }

    protected void detachView() {
        this.view = null;
        remove(disposables2Stop);
    }

    public boolean addRxStop(Disposable disposable) {
        if (disposables2Stop == null) {
            throw new IllegalStateException(
                    "addUtilStop should be called between onStart and onStop");
        }
        disposables2Stop.add(disposable);
        return true;
    }

    public boolean addRxDestroy(Disposable disposable) {
        if (disposables2Destroy == null) {
            throw new IllegalStateException(
                    "addUtilDestroy should be called between onCreate and onDestroy");
        }
        disposables2Destroy.add(disposable);
        return true;
    }

    public void remove(Disposable disposable) {
        if (disposables2Stop == null && disposables2Destroy == null) {
            throw new IllegalStateException("remove should not be called after onDestroy");
        }
        if (disposables2Stop != null) {
            disposables2Stop.remove(disposable);
            disposables2Stop.dispose();
        }
        if (disposables2Destroy != null) {
            disposables2Destroy.remove(disposable);
            disposables2Destroy.dispose();
        }
    }
}
