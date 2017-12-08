package com.qinqin.simpledemo.frame.http;

import com.qinqin.simpledemo.bean.MainModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.model.mvp
 * Date: 2017/5/9
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public interface Api {
    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";
    //加载天气
    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadData(@Path("cityId") String cityId);
}
