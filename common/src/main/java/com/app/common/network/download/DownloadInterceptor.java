package com.app.common.network.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ${user} on 2018/9/6.
 * QinQin
 */

public class DownloadInterceptor implements Interceptor {
    private DownloadListener downloadListener;

    public DownloadInterceptor(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new DownloadResponseBody(response.body(), downloadListener)).build();
    }
}
