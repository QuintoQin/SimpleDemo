package com.qinqin.simpledemo.module.bottomtablayout;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.FourFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.OneFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.ThreeFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.TwoFragment;
import com.qinqin.simpledemo.module.bottomtablayout.view.CustomTabView;
import com.qinqin.simpledemo.base.BaseActivity;

import butterknife.BindView;

public class CustomTabActivity extends BaseActivity implements CustomTabView.OnTabCheckListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.custom_container)
    FrameLayout customContainer;
    @BindView(R.id.custom_tab_view)
    CustomTabView customTabView;

    private FragmentManager fragmentManager;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_tab;
    }

    @Override
    protected void initToolbar() {
        toolbar.setTitle("CustomTabActivity");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        CustomTabView.Tab tabHome = new CustomTabView.Tab().setText("首页")
                .setColor(R.color.text_colorgray)
                .setCheckedColor(R.color.text_colorgreen)
                .setNormalIcon(R.drawable.home_gray)
                .setPressedIcon(R.drawable.home_green);
        customTabView.addTab(tabHome);
        CustomTabView.Tab tabDis = new CustomTabView.Tab().setText("定制")
                .setColor(R.color.text_colorgray)
                .setCheckedColor(R.color.text_colorgreen)
                .setNormalIcon(R.drawable.customized_gray)
                .setPressedIcon(R.drawable.customized_green);
        customTabView.addTab(tabDis);
        CustomTabView.Tab tabAttention = new CustomTabView.Tab().setText("购物车")
                .setColor(R.color.text_colorgray)
                .setCheckedColor(R.color.text_colorgreen)
                .setNormalIcon(R.drawable.shoppingcart_gray)
                .setPressedIcon(R.drawable.shoppingcart_green);
        customTabView.addTab(tabAttention);
        CustomTabView.Tab tabProfile = new CustomTabView.Tab().setText("我的")
                .setColor(R.color.text_colorgray)
                .setCheckedColor(R.color.text_colorgreen)
                .setNormalIcon(R.drawable.person_gray)
                .setPressedIcon(R.drawable.person_green);
        customTabView.addTab(tabProfile);
        //设置监听
        customTabView.setOnTabCheckListener(this);
        // 默认选中tab
        customTabView.setCurrentItem(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onTabSelected(View v, int position) {
        setTabSelection(position);
    }


    //tab选项卡选择
    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (oneFragment == null) {
                    // 如果homeFragment为空，则创建一个并添加到界面上
                    oneFragment = new OneFragment();
                    transaction.add(R.id.custom_container, oneFragment);
                } else {
                    // 如果homeFragment不为空，则直接将它显示出来
                    transaction.show(oneFragment);
                }
                break;
            case 1:
                if (twoFragment == null) {
                    // 如果customizedFragment为空，则创建一个并添加到界面上
                    twoFragment = new TwoFragment();
                    transaction.add(R.id.custom_container, twoFragment);
                } else {
                    // 如果orderDetailFragment不为空，则直接将它显示出来
                    transaction.show(twoFragment);
                }
                break;
            case 2:
                if (threeFragment == null) {
                    // 如果mineFragment为空，则创建一个并添加到界面上
                    threeFragment = new ThreeFragment();
                    transaction.add(R.id.custom_container, threeFragment);
                } else {
                    // 如果mineFragment不为空，则直接将它显示出来
                    transaction.show(threeFragment);
                }
                break;
            case 3:
                if (fourFragment == null) {
                    // 如果mineFragment为空，则创建一个并添加到界面上
                    fourFragment = new FourFragment();
                    transaction.add(R.id.custom_container, fourFragment);
                } else {
                    // 如果mineFragment不为空，则直接将它显示出来
                    transaction.show(fourFragment);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏不为空的fragment
    private void hideFragments(FragmentTransaction transaction) {
        if (oneFragment != null) {
            transaction.hide(oneFragment);
        }
        if (twoFragment != null) {
            transaction.hide(twoFragment);
        }
        if (threeFragment != null) {
            transaction.hide(threeFragment);
        }
        if (fourFragment != null) {
            transaction.hide(fourFragment);
        }
    }
}
