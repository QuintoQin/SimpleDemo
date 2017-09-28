package com.qinqin.simpledemo.model.mvp;

import com.qinqin.simpledemo.bean.MainModel;
import com.qinqin.simpledemo.base.BaseView;

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
public interface MainView extends BaseView {

    void getDataSuceess(MainModel mainModel);

    void getDataFail(String msg);
}
