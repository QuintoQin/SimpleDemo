package com.app.common.network.help;

import com.app.common.utils.NetworkUtils;
import com.app.common.utils.ToastUtils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${user} on 2017/9/26.
 * QinQin
 */

public class RxSchedulersHelper {
    public static <T> ObservableTransformer<T, T> observeOnMainThread() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }
}
