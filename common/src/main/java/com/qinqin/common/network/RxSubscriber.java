package com.qinqin.common.network;


import android.net.ParseException;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.qinqin.common.R;
import com.qinqin.common.network.entity.BasicResponse;
import com.qinqin.common.network.help.CodeException;
import com.qinqin.common.utils.LogUtils;
import com.qinqin.common.utils.ToastUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * RxJava订阅者Subscriber封装扩展
 *
 * @author QinQin
 * @version 1.0.0
 */
public abstract class RxSubscriber<T extends BasicResponse<T>> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        if (response.getCode() == 200) {
            //请求成功
            onSuccess(response);
        } else {
            //请求失败
            onFail(response);
        }

    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e("Retrofit", e.getMessage());

        if (e instanceof HttpException) {     //HTTP错误
            onException(CodeException.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //连接错误
            onException(CodeException.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //连接超时
            onException(CodeException.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //解析错误
            onException(CodeException.PARSE_ERROR);
        } else {
            onException(CodeException.UNKNOWN_ERROR);     //未知错误
        }
    }

    // 忽略操作，需要可覆写该方法
    @Override
    public void onComplete() {

    }

    /**
     * 请求成功回调 且响应码为200
     *
     * @param response 最终响应结果
     */
    public abstract void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {
        String message = response.getMessage();
        if (TextUtils.isEmpty(message)) {
            ToastUtils.showShort(R.string.response_return_error);
        } else {
            ToastUtils.showShort(message);
        }
    }

    /**
     * 请求异常 所有请求
     * 异常原因
     * @param reason
     */
    public void onException(int reason) {
        switch (reason) {
            case CodeException.CONNECT_ERROR:
                ToastUtils.showShort(R.string.bad_network);
                break;

            case CodeException.CONNECT_TIMEOUT:
                ToastUtils.showShort(R.string.connect_error);
                break;

            case CodeException.BAD_NETWORK:
                ToastUtils.showShort(R.string.connect_timeout);
                break;

            case CodeException.PARSE_ERROR:
                ToastUtils.showShort(R.string.parse_error);
                break;

            case CodeException.UNKNOWN_ERROR:
            default:
                ToastUtils.showShort(R.string.unkown_error);
                break;
        }
    }
}
