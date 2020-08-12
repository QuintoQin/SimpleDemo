package com.qinqin.simpledemo.module.bottomtablayout;


import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.FourFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.OneFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.ThreeFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.TwoFragment;
import com.qinqin.simpledemo.base.BaseActivity;

import butterknife.BindView;

import static com.qinqin.simpledemo.R.id.rg_groups;


public class RadioGroupActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rg_container)
    FrameLayout rgContainer;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_customized)
    RadioButton rbCustomized;
    @BindView(R.id.rb_shoppingcart)
    RadioButton rbShoppingcart;
    @BindView(R.id.rb_person)
    RadioButton rbPerson;
    @BindView(rg_groups)
    RadioGroup rgGroups;

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
        return R.layout.activity_radio_group;
    }

    @Override
    protected void initToolbar() {
        toolbar.setTitle("RadioGroup+Fragment");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        //初始化底部Tab按钮
        initTab();
    }

    @Override
    protected void initData() {

    }

    private void initTab() {
        //默认选中第一个按钮
        rgGroups.check(R.id.rb_home);
        //默认显示第一个fragment
        setTabSelection(0);
        rgGroups.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        setTabSelection(0);
                        break;
                    case R.id.rb_customized:
                        setTabSelection(1);
                        break;
                    case R.id.rb_shoppingcart:
                        setTabSelection(2);
                        break;
                    case R.id.rb_person:
                        setTabSelection(3);
                        break;
                }
            }
        });

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
                    transaction.add(R.id.rg_container, oneFragment);
                } else {
                    // 如果homeFragment不为空，则直接将它显示出来
                    transaction.show(oneFragment);
                }
                break;
            case 1:
                if (twoFragment == null) {
                    // 如果customizedFragment为空，则创建一个并添加到界面上
                    twoFragment = new TwoFragment();
                    transaction.add(R.id.rg_container, twoFragment);
                } else {
                    // 如果orderDetailFragment不为空，则直接将它显示出来
                    transaction.show(twoFragment);
                }
                break;
            case 2:
                if (threeFragment == null) {
                    // 如果mineFragment为空，则创建一个并添加到界面上
                    threeFragment = new ThreeFragment();
                    transaction.add(R.id.rg_container, threeFragment);
                } else {
                    // 如果mineFragment不为空，则直接将它显示出来
                    transaction.show(threeFragment);
                }
                break;
            case 3:
                if (fourFragment == null) {
                    // 如果mineFragment为空，则创建一个并添加到界面上
                    fourFragment = new FourFragment();
                    transaction.add(R.id.rg_container, fourFragment);
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
