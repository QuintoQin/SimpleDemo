package com.app.common.network.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ${user} on 2018/9/6.
 * QinQin
 */

public interface DownloadService {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
