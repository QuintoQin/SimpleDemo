package com.qinqin.simpledemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.model.recyclerview.baserecyleradpter.HomeItem;

import java.util.List;

/**
 * Created by 26050 on 2017/9/28.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {
    public HomeAdapter(int layoutResId, List<HomeItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.home_item_tv, item.getTitle());
        helper.setImageResource(R.id.home_item_iv, item.getImageResource());
    }
}
