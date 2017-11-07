package com.qinqin.simpledemo.module.mvp.view;

import com.qinqin.simpledemo.bean.MainModel;

/**
 * Created by 26050 on 2017/11/1.
 */

public interface IDataView {
    void loadData(MainModel mainModel);

    void showProgress();

    void hideProgress();
}
