package com.qinqin.simpledemo.module.mvp.model;

import com.qinqin.common.network.RetrofitHelper;
import com.qinqin.common.network.RxCommonSubscriber;
import com.qinqin.common.network.RxSubscriber;
import com.qinqin.common.network.help.RxSchedulersHelper;
import com.qinqin.simpledemo.bean.MainModel;
import com.qinqin.simpledemo.module.mvp.simplemvp.Api;

/**
 * Created by 26050 on 2017/11/1.
 */

public class DataModelImpl implements IDataModel {


    @Override
    public void load(String id,IDataModel.onLoadListener listener) {
        RetrofitHelper.createApi(Api.class).loadData(id)
                .compose(RxSchedulersHelper.observeOnMainThread())
                .subscribe(new RxCommonSubscriber<MainModel>() {
                    @Override
                    public void onSuccess(MainModel mainModel) {
                        //ToastUtils.showLong("成功了" + mainModel.toString());
                        listener.onSuccess(mainModel);
                    }
                });
    }
}
