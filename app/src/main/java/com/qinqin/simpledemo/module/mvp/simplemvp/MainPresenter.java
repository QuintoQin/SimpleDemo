package com.qinqin.simpledemo.module.mvp.simplemvp;

import com.qinqin.common.network.RxCommonSubscriber;
import com.qinqin.simpledemo.module.mvp.simplemvp.base.BasePresenter;
import com.qinqin.common.network.RetrofitHelper;
import com.qinqin.common.network.RxSubscriber;
import com.qinqin.common.network.help.RxSchedulersHelper;
import com.qinqin.common.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
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
public class MainPresenter extends BasePresenter<MainView> {


    public MainPresenter(MainView mainView) {
        attachView(mainView);
    }

    public void loadData(String cityId) {
        RetrofitHelper.createApi(ApiStores.class)
                .loadDataByRetrofitRxjava(cityId)
                .compose(RxSchedulersHelper.observeOnMainThread())
                .subscribe(new RxCommonSubscriber<MainModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.onSubscribe(d);
                        view.showLoading();
                        addRxStop(d);
                    }

                    @Override
                    public void onNext(@NonNull MainModel mainModel) {
                        super.onNext(mainModel);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        view.hideLoading();
                    }

                    @Override
                    public void onSuccess(MainModel mainModel) {
                        view.dataSuccess(mainModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        view.hideLoading();
                        view.dataFail(e.getMessage());
                    }
                });
    }

    public void test() {
        Observable.interval(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxCommonSubscriber<Long>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        super.onSubscribe(d);
                        LogUtils.e("addRxStop");
                        addRxStop(d);
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        super.onNext(aLong);
                        LogUtils.e(String.valueOf(aLong));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        LogUtils.e("onError");
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        LogUtils.e("onComplete");
                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        LogUtils.e("onSuccess");
                    }
                });
    }

    public void unbundling() {
        detachView();
    }
}
