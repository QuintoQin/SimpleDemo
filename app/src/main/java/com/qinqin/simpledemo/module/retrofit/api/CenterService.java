package com.qinqin.simpledemo.module.retrofit.api;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.model.retrofit.api
 * Date: 2017/5/8
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */


import com.qinqin.simpledemo.bean.ActivityCenterInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by hcc on 2016/10/3 19:04
 * 100332338@qq.com
 * <p>
 * 活动中心数据请求
 * <p>
 * http://api.bilibili.com/event/getlist?device=phone&mobi_app=iphone&page=1&pagesize=20
 */
public interface CenterService {
    @GET("event/getlist?device=phone&mobi_app=iphone")
    Observable<ActivityCenterInfo> getActivityCenterList(@Query("page") int page, @Query("pagesize") int pageSize);
}
