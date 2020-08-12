package com.app.common.network;


import android.text.TextUtils;

import com.app.common.R;
import com.app.common.network.api.Api;
import com.app.common.utils.JsonUtil;
import com.app.common.utils.LogUtils;
import com.app.common.utils.NetworkUtils;
import com.app.common.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.orhanobut.hawk.Hawk;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Description： RetofitHelper
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.app.base.network
 * Date: 2017/5/2
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class RetrofitHelper {
    private volatile static Retrofit retrofitInstance = null;

    /**
     * 创建Retrofit请求Api
     *
     * @param clazz Retrofit Api接口
     * @return api实例
     */
    public static <T> T createApi(Class<T> clazz) {
        return getInstance().create(clazz);
    }


    // ===============================================================
    // private methods =================================================

    /**
     * 获取Retrofit实例
     *
     * @return Retrofit
     */
    private static Retrofit getInstance() {
        if (null == retrofitInstance) {
            synchronized (Retrofit.class) {
                if (null == retrofitInstance) { // 双重检验锁,仅第一次调用时实例化
                    retrofitInstance = new Retrofit.Builder()
                            // baseUrl总是以/结束，@URL不要以/开头
                            .baseUrl(Api.HOST)
                            // 使用OkHttp Client
                            .client(buildOKHttpClient())
                            // 集成RxJava处理
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            // 集成Gson转换器
                            .addConverterFactory(buildGsonConverterFactory())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }

    /**
     * 构建OkHttpClient
     *
     * @return OkHttpClient
     */
    public static OkHttpClient buildOKHttpClient() {
        // 添加日志拦截器，非debug模式不打印任何日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)                       // 添加日志拦截器
                .addInterceptor(buildTokenInterceptor())                   // 添加token拦截器
                .addInterceptor(ReceivedCookiesInterceptor())
                .addInterceptor(AddCookiesInterceptor())
                .addNetworkInterceptor(buildCacheInterceptor())           // 添加网络缓存拦截器
                .cache(getCache())                                        // 设置缓存文件
                .retryOnConnectionFailure(true)                           // 自动重连
                .connectTimeout(15, TimeUnit.SECONDS)                     // 15秒连接超时
                .readTimeout(20, TimeUnit.SECONDS)                        // 20秒读取超时
                .writeTimeout(20, TimeUnit.SECONDS)                       // 20秒写入超时
                .build();
    }

    private static class HttpLogger implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {
                message = JsonUtil.formatJson(message);
            }
            mMessage.append(message.concat("\n"));
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                LogUtils.e(mMessage.toString());
            }
            //LogUtils.e(message);
            //Timber.e(message);
        }
    }

    /**
     * 获取缓存对象
     *
     * @return Cache
     */
    private static Cache getCache() {
        // 获取缓存目标,SD卡
        File cacheFile = new File(Utils.getContext().getCacheDir(), Utils.getContext().getResources().getString(R.string.cache));
        // 创建缓存对象,最大缓存100m
        return new Cache(cacheFile, 1024 * 1024 * 100);
    }

    /**
     * 构建缓存拦截器
     *
     * @return Interceptor
     */
    private static Interceptor buildCacheInterceptor() {
        return chain -> {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            //CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            // 无网络连接时请求从缓存中读取
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtils.e("okhttp", "no network");
            }

            // 响应内容处理
            // 在线时缓存5分钟
            // 离线时缓存4周
            Response response = chain.proceed(request);
            if (NetworkUtils.isConnected()) {
                //在线
                int maxAge = 300;
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();

            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        };
    }

    /**
     * 构建token拦截器
     */
    private static Interceptor buildTokenInterceptor() {
        return chain -> {
            String sessionId = Hawk.get("sessionId");
            if (TextUtils.isEmpty(sessionId)) {
                Request originalRequest = chain.request();
                Request request = originalRequest
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("consumes", "application/json")
                        .addHeader("produces", "*/*")
                        .build();
                return chain.proceed(request);
            } else {
                Request originalRequest = chain.request();
                Request newRequest = originalRequest
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("consumes", "application/json")
                        .addHeader("produces", "*/*")
                        .addHeader("sessionId", sessionId)
                        .build();
                return chain.proceed(newRequest);
            }
        };
    }

    /**
     * 首次请求cookie为空，需要从响应报文中获取，并保存到客户端的首选项中。
     * 非首次请求时，从首选中中取出cookie并添加到请求头中。
     * 首次请求的处理
     */
    private static Interceptor ReceivedCookiesInterceptor() {
        return chain -> {
            Response originalResponse = chain.proceed(chain.request());

            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>();

                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                }
                Hawk.put("cookie", cookies);
            }
            return originalResponse;
        };
    }

    /**
     * 非首次请求
     */
    private static Interceptor AddCookiesInterceptor() {
        return chain -> {
            Request.Builder builder = chain.request().newBuilder();
            HashSet<String> preferences = (HashSet) Hawk.get("cookie");
            if (preferences != null) {
                for (String cookie : preferences) {
                    builder.addHeader("Cookie", cookie);
                }
            }
            return chain.proceed(builder.build());
        };
    }

    /**
     * 构建GSON转换器
     *
     * @return GsonConverterFactory
     */
    private static GsonConverterFactory buildGsonConverterFactory() {
        GsonBuilder builder = new GsonBuilder();
        builder.setLenient();

        // 注册类型转换适配器
        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> null == json ? null : new Date(json.getAsLong()));

        Gson gson = builder.create();
        return GsonConverterFactory.create(gson);
    }
}
