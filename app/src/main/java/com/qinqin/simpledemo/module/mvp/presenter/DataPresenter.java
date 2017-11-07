package com.qinqin.simpledemo.module.mvp.presenter;

import com.qinqin.simpledemo.bean.MainModel;
import com.qinqin.simpledemo.module.mvp.base.BasePresenter;
import com.qinqin.simpledemo.module.mvp.model.DataModelImpl;
import com.qinqin.simpledemo.module.mvp.model.IDataModel;
import com.qinqin.simpledemo.module.mvp.view.IDataView;

/**
 * Created by 26050 on 2017/11/1.
 */

public class DataPresenter extends BasePresenter<IDataView> {
    private DataModelImpl dataModelImpl;
    private IDataView mView;

    public DataPresenter() {
        dataModelImpl = new DataModelImpl();
    }

    public void load(String cityId) {
        mView = getView();
        mView.showProgress();
        dataModelImpl.load(cityId, mainModel -> {
            mView.loadData(mainModel);
            mView.hideProgress();
        });
    }
}
