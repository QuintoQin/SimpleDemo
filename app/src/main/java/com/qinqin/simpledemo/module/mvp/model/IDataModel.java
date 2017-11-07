package com.qinqin.simpledemo.module.mvp.model;

import com.qinqin.simpledemo.bean.MainModel;

/**
 * Created by 26050 on 2017/11/1.
 */

public interface IDataModel {
    void load(String id, onLoadListener listener);

    interface onLoadListener {
        void onSuccess(MainModel mainModel);
    }
}
