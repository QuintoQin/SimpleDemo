package com.qinqin.simpledemo.module.rx;

import com.qinqin.simpledemo.bean.MainModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


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
public interface UserApi {
    //baseUrl
    String API_SERVER_URL = "http://tb.moonvila.com/api/";

    //加载数据
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("User/addUser")
    Call loadData(@Query("wcopenid") String wcopenid,
                          @Query("nickname") String nickname,
                          @Query("password") String password,
                          @Query("sex") String sex,
                          @Query("headurl") String headurl);
}
