package com.qinqin.simpledemo.model.bottomtablayout;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.base.BaseActivity;
import com.qinqin.simpledemo.model.bottomtablayout.fragment.FourFragment;
import com.qinqin.simpledemo.model.bottomtablayout.fragment.OneFragment;
import com.qinqin.simpledemo.model.bottomtablayout.fragment.ThreeFragment;
import com.qinqin.simpledemo.model.bottomtablayout.fragment.TwoFragment;
import com.qinqin.common.utils.ToastUtils;

import butterknife.BindView;

public class BottomNavigationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_container)
    FrameLayout navContainer;
    @BindView(R.id.tab_nav)
    BottomNavigationView tabNav;

    private FragmentManager fragmentManager;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FourFragment fourFragment;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bottom_navigation;
    }

    @Override
    protected void initToolbar() {
        toolbar.setTitle("BottomNavigation + Fragment");

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();

        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();

        tabNav.setOnNavigationItemSelectedListener(item -> {
            //setTabSelection(item.getItemId());
            switch (item.getItemId()) {
                case R.id.tab_menu_home:
                    switchFragment(oneFragment);
                    break;
                case R.id.tab_menu_discovery:
                    switchFragment(twoFragment);
                    break;
                case R.id.tab_menu_attention:
                    switchFragment(threeFragment);
                    break;
                case R.id.tab_menu_profile:
                    switchFragment(fourFragment);
                    break;
                default:
                    ToastUtils.showShort(item.getItemId() + "");
                    break;
            }
            return true;
        });

        // 由于第一次进来没有回调onNavigationItemSelected，因此需要手动调用一下切换状态的方法
        //setTabSelection(R.id.tab_menu_home);
        switchFragment(oneFragment);
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
                    transaction.add(R.id.nav_container, oneFragment);
                } else {
                    // 如果homeFragment不为空，则直接将它显示出来
                    transaction.show(oneFragment);
                }
                break;
            case 1:
                if (twoFragment == null) {
                    // 如果customizedFragment为空，则创建一个并添加到界面上
                    twoFragment = new TwoFragment();
                    transaction.add(R.id.nav_container, twoFragment);
                } else {
                    // 如果orderDetailFragment不为空，则直接将它显示出来
                    transaction.show(twoFragment);
                }
                break;
            case 2:
                if (threeFragment == null) {
                    // 如果mineFragment为空，则创建一个并添加到界面上
                    threeFragment = new ThreeFragment();
                    transaction.add(R.id.nav_container, threeFragment);
                } else {
                    // 如果mineFragment不为空，则直接将它显示出来
                    transaction.show(threeFragment);
                }
                break;
            case 3:
                if (fourFragment == null) {
                    // 如果mineFragment为空，则创建一个并添加到界面上
                    fourFragment = new FourFragment();
                    transaction.add(R.id.nav_container, fourFragment);
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

    /**
     * 动态添加fragment，不会重复创建fragment
     *
     * @param fragment 将要加载的fragment
     */
    public void switchFragment(Fragment fragment) {
        if (mCurrentFragment != fragment) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!fragment.isAdded()) {// 如果to fragment没有被add则增加一个fragment
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment);
                }
                transaction.add(R.id.nav_container, fragment);
            } else {
                transaction.hide(mCurrentFragment).show(fragment); // 隐藏当前的fragment，显示下一个
            }
            mCurrentFragment = fragment;
            transaction.commit();
        }
    }
}
