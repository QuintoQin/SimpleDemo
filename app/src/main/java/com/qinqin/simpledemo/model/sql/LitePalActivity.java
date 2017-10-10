package com.qinqin.simpledemo.model.sql;

import android.os.Bundle;
import android.widget.TextView;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.bean.DEST;
import com.qinqin.simpledemo.base.BaseActivity;

import org.litepal.crud.DataSupport;

import butterknife.BindView;

public class LitePalActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lite_pal;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        DEST mDest = new DEST();
        mDest.setDestId("12345");
        mDest.setChildrenId("222");
        mDest.setCnName("name");
        mDest.setParentId("12233");
        mDest.update(1);

        find();
    }

    @Override
    protected void initData() {

    }

    private void find() {
        DEST mDest = DataSupport.find(DEST.class,1);

        tv.setText(mDest.toString());
    }
}
