package com.qinqin.simpledemo.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.base
 * Date: 2017/4/25
 * user: user QuintoQin
 * FRAGMENT基类
 *
 * @author 覃勤
 * @version : 1.0
 */
public abstract class BaseFragment extends RxFragment {
    private View parentView;

    //标志位 fragment是否可见
    protected boolean isVisible;

    //must
    //布局ID
    protected abstract int getLayoutId();

    //结束CreateView所做操作
    protected abstract void initCreateView(Bundle savedInstanceState);

    //初始化数据

    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutId(), container, false);
        return parentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //fragment 绑定控件
        ButterKnife.bind(this, view);
        //结束CreateView所做操作
        initCreateView(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Fragment数据的懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
    }

    protected void onInvisible() {
    }

    //结束当前页面
    protected void finish() {
        getActivity().finish();
    }
}
