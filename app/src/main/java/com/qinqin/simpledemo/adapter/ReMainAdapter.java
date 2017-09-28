package com.qinqin.simpledemo.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.bean.MainItem;

import java.util.List;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.adapter
 * Date: 2017/4/26
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class ReMainAdapter extends BaseQuickAdapter<MainItem, BaseViewHolder> {
    public ReMainAdapter(int layoutResId, List<MainItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainItem item) {
        helper.setText(R.id.item_main_tv, item.getMain_text());
    }

}
