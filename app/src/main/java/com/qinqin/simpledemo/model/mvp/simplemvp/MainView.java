package com.qinqin.simpledemo.model.mvp.simplemvp;

import com.qinqin.simpledemo.model.mvp.simplemvp.base.BaseView;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.model.mvp.simplemvp.base
 * Date: 2017/5/24
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public interface MainView extends BaseView {
    void dataSuccess(MainModel mainModel);

    void dataFail(String msg);
}
