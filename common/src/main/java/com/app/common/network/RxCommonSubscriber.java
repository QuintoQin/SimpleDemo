package com.app.common.network;


import com.app.common.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Common RxJava订阅者Subscriber封装扩展
 *
 * @author QinQin
 * @version 1.0.0
 */
public abstract class RxCommonSubscriber<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        ToastUtils.showShort(e.getMessage());
    }

    // 忽略操作，需要可覆写该方法
    @Override
    public void onComplete() {
    }

    /**
     * 请求成功回调
     *
     * @param t 最终响应结果
     */
    public abstract void onSuccess(T t);
}
