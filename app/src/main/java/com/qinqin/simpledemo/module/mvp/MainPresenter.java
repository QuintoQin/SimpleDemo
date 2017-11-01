package com.qinqin.simpledemo.module.mvp;

import com.qinqin.simpledemo.bean.MainModel;
import com.qinqin.simpledemo.base.BasePresenter;
import com.qinqin.common.network.RetrofitHelper;
import com.qinqin.common.network.RxSubscriber;
import com.qinqin.common.network.help.RxSchedulersHelper;
import com.qinqin.common.utils.ToastUtils;


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
public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        attachView(view);
    }

    public void loadData(String cityId) {
        RetrofitHelper.createApi(Api.class).loadData(cityId)
                .compose(RxSchedulersHelper.observeOnMainThread())
                .subscribe(new RxSubscriber<MainModel>() {
                    @Override
                    public void onSuccess(MainModel mainModel) {
                        ToastUtils.showLong("成功了" + mainModel.toString());
                    }
                });
    }
}
