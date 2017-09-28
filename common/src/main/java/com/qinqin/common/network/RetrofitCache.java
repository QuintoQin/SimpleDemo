//package com.qinqin.common.network;
//
//import org.reactivestreams.Subscriber;
//
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//
///**
// * Created by 26050 on 2017/9/26.
// */
//
//public class RetrofitCache {
//    /**
//     * @param cacheKey 缓存的Key
//     * @param fromNetwork
//     * @param isSave       是否缓存
//     * @param forceRefresh 是否强制刷新
//     * @param <T>
//     * @return
//     */
//    public static <T> Observable<T> load(final String cacheKey,
//                                         Observable<T> fromNetwork,
//                                         boolean isSave, boolean forceRefresh) {
//        Observable<T> fromCache = Observable.create(new Observable.OnSubscribe<T>() {
//            @Override
//            public void call(Subscriber<? super T> subscriber) {
//                T cache = (T) Hawk.get(cacheKey);
//                if (cache != null) {
//                    subscriber.onNext(cache);
//                } else {
//                    subscriber.onCompleted();
//                }
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//        //是否缓存
//        if (isSave) {
//            /**
//             * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
//             */
//            fromNetwork = fromNetwork.map(new Func1<T, T>() {
//                @Override
//                public T call(T result) {
//                    Hawk.put(cacheKey, result);
//                    return result;
//                }
//            });
//        }
//        //强制刷新
//        if (forceRefresh) {
//            return fromNetwork;
//        } else {
//            return Observable.concat(fromCache, fromNetwork).first();
//        }
//    }
//
//}
