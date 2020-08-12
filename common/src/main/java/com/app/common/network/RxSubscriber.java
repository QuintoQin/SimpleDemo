package com.app.common.network;


import android.net.ParseException;
import android.text.TextUtils;

import com.app.common.R;
import com.app.common.bean.MainMessage;
import com.app.common.network.entity.BasicResponse;
import com.app.common.network.help.CodeException;
import com.app.common.utils.ActivityUtils;
import com.app.common.utils.ToastUtils;
import com.google.gson.JsonParseException;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.EventBus;
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
public abstract class RxSubscriber<T> implements Observer<BasicResponse<T>> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    //status==200
    @Override
    public void onNext(BasicResponse<T> response) {
        if (response.getCode() == 200) {
            //请求成功
            onSuccess(response.getData().getBussData());
        } else {
            //请求失败
            onFail(response.getData().getErrMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
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
     * @param message 服务器返回的数据
     */
    public void onFail(String message) {
        if (TextUtils.isEmpty(message)) {
            ToastUtils.showShort(R.string.response_return_error);
        } else if (message.equals("未登录或token失效")) {
            ToastUtils.showShort("未登录或token失效");
            Hawk.deleteAll();
//            if (ActivityUtils.getTopActivity() != null) {
//                if (!ActivityUtils.getTopActivity().getComponentName().getClassName().equals("com.jrsj.spongebuyer.module.account.LoginHelloActivity")) {
//                    ActivityUtils.launchActivity("com.jrsj.spongebuyer", "com.jrsj.spongebuyer.module.account.LoginHelloActivity");
//                }
//            }
        } else {
            ToastUtils.showShort(message);
        }
    }

    /**
     * 请求异常 所有请求
     * 异常原因
     *
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
                ToastUtils.showShort(R.string.unkown_error);
                break;
            default:
                break;
        }
    }
}
