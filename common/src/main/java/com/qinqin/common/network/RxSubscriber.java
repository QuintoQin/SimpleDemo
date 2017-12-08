package com.qinqin.common.network;


import com.qinqin.common.network.help.ApiException;
import com.qinqin.common.utils.LogUtils;
import com.qinqin.common.utils.NetworkUtils;
import com.qinqin.common.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * RxJava订阅者Subscriber封装扩展
 *
 * @author huchiwei
 * @version 1.0.0
 */
public abstract class RxSubscriber<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
//        if (t instanceof ResponseData) {
//            ResponseData response = (ResponseData) t;
//            // 判断是否请求错误，出错直接转到onError()
//            if (response.isError()) {
//                Throwable e = new Throwable("错误");
//                this.onError(e);
//                return;
//            }
//        }
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {

        LogUtils.e(e);
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showLong("网络已断开");
        } else if (e instanceof ApiException) {
            ToastUtils.showLong(e.getMessage());
        } else {
            ToastUtils.showLong("请求失败，请稍候重试...");
        }

        // TODO: 处理其它通用错误
        // 覆写此方法自行处理异常，通用异常使用super.onError(e)保留
        //e.printStackTrace();
    }

    @Override
    public void onComplete() {
        // 忽略操作，需要可覆写该方法
    }

    /**
     * 请求成功回调
     *
     * @param t 最终响应结果
     */
    public abstract void onSuccess(T t);
}
