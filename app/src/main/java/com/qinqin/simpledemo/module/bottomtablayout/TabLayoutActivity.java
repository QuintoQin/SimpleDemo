package com.qinqin.simpledemo.module.bottomtablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.FourFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.OneFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.ThreeFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.TwoFragment;
import com.qinqin.simpledemo.base.BaseActivity;

import butterknife.BindView;


public class TabLayoutActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_container)
    FrameLayout tabContainer;
    @BindView(R.id.bottom_tab_layout)
    TabLayout bottomTabLayout;

    private FragmentManager fragmentManager;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private static final int[] mTabRes = new int[]{R.drawable.home_gray, R.drawable.customized_gray, R.drawable.shoppingcart_gray, R.drawable.person_gray};
    private static final int[] mTabResPressed = new int[]{R.drawable.home_green, R.drawable.customized_green, R.drawable.shoppingcart_green, R.drawable.person_green};
    private static final String[] mTabTitle = new String[]{"首页", "定制", "购物车", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void initToolbar() {
        toolbar.setTitle("TabLayout + Fragment");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        initTabLayout();
    }

    @Override
    protected void initData() {

    }

    private void initTabLayout() {
        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabSelection(tab.getPosition());
                //第一种方案
                //改变Tab 状态
//                for (int i = 0; i < bottomTabLayout.getTabCount(); i++) {
//                    if (i == tab.getPosition()) {
//                        bottomTabLayout.getTabAt(i).setIcon(getResources().getDrawable(mTabResPressed[i]));
//                    } else {
//                        bottomTabLayout.getTabAt(i).setIcon(getResources().getDrawable(mTabRes[i]));
//                    }
//                }
                //第二种方案
                // Tab 选中之后，改变各个Tab的状态
                for (int i = 0; i < bottomTabLayout.getTabCount(); i++) {
                    View view = bottomTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    TextView text = (TextView) view.findViewById(R.id.tab_content_text);
                    if (i == tab.getPosition()) { // 选中状态
                        icon.setImageResource(mTabResPressed[i]);
                        text.setTextColor(getResources().getColor(R.color.text_colorgreen));
                    } else {// 未选中状态
                        icon.setImageResource(mTabRes[i]);
                        text.setTextColor(getResources().getColor(R.color.text_colorgray));
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.btn_tab_home_selector)).setText(mTabTitle[0]));
//        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.btn_tab_customized_selector)).setText(mTabTitle[1]));
//        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.btn_tab_service_selector)).setText(mTabTitle[2]));
//        bottomTabLayout.addTab(bottomTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.btn_tab_service_selector)).setText(mTabTitle[3]));
        // 提供自定义的布局添加Tab
        for (int i = 0; i < 4; i++) {
            bottomTabLayout.addTab(bottomTabLayout.newTab().setCustomView(getTabView(this, i)));
        }
    }

    /**
     * 获取Tab 显示的内容
     *
     * @param context
     * @param position
     * @return
     */
    private View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
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
                    transaction.add(R.id.tab_container, oneFragment);
                } else {
                    // 如果homeFragment不为空，则直接将它显示出来
                    transaction.show(oneFragment);
                }
                break;
            case 1:
                if (twoFragment == null) {
                    // 如果customizedFragment为空，则创建一个并添加到界面上
                    twoFragment = new TwoFragment();
                    transaction.add(R.id.tab_container, twoFragment);
                } else {
                    // 如果orderDetailFragment不为空，则直接将它显示出来
                    transaction.show(twoFragment);
                }
                break;
            case 2:
                if (threeFragment == null) {
                    // 如果mineFragment为空，则创建一个并添加到界面上
                    threeFragment = new ThreeFragment();
                    transaction.add(R.id.tab_container, threeFragment);
                } else {
                    // 如果mineFragment不为空，则直接将它显示出来
                    transaction.show(threeFragment);
                }
                break;
            case 3:
                if (fourFragment == null) {
                    // 如果mineFragment为空，则创建一个并添加到界面上
                    fourFragment = new FourFragment();
                    transaction.add(R.id.tab_container, fourFragment);
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
