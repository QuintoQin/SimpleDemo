package com.qinqin.simpledemo.helper;

import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



/**
 * Created by Rukey7 on 2017/1/22.
 * RxJava 帮助类
 */
public final class RxHelper {

    private RxHelper() {
        throw new AssertionError();
    }

    /**
     * 倒计时
     * @param time
     * @return
     */
    public static Observable countdown(int time) {
        if (time < 0) {

            time = 0;
        }
        final int countTime = time;

        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(increaseTime -> countTime - increaseTime.intValue())
                .take(countTime + 1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
