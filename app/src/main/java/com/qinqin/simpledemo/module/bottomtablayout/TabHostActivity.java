package com.qinqin.simpledemo.module.bottomtablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.FourFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.OneFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.ThreeFragment;
import com.qinqin.simpledemo.module.bottomtablayout.fragment.TwoFragment;
import com.qinqin.simpledemo.base.BaseActivity;

import butterknife.BindView;

public class TabHostActivity extends BaseActivity implements TabHost.OnTabChangeListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabhost_container)
    FrameLayout tabhostContainer;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private Fragment []mFragments = getFragments();
    private static final String[] mTabTitle = new String[]{"首页", "发现", "关注", "我的"};
    private static final int[] mTabRes = new int[]{R.drawable.home_gray, R.drawable.customized_gray, R.drawable.shoppingcart_gray, R.drawable.person_gray};
    private static final int[] mTabResPressed = new int[]{R.drawable.home_green, R.drawable.customized_green, R.drawable.shoppingcart_green, R.drawable.person_green};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_host;
    }

    @Override
    protected void initToolbar() {
        toolbar.setTitle("TabHostFragment + fragment");
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        // 关联TabHost
        tabhost.setup(this,getSupportFragmentManager(),R.id.tabhost_container);
        //注意，监听要设置在添加Tab之前
        tabhost.setOnTabChangedListener(this);
        //添加Tab
        for (int i=0;i<4;i++){
            //生成TabSpec
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(mTabTitle[i]).setIndicator(getTabView(this,i));
            // 添加Tab 到TabHost，并绑定Fragment
            Bundle bundle = new Bundle();
            bundle.putString("from","FragmentTabHost Tab");
            tabhost.addTab(tabSpec,mFragments[i].getClass(),bundle);
        }


        //去掉Tab 之间的分割线
        tabhost.getTabWidget().setDividerDrawable(null);
        //
        tabhost.setCurrentTab(0);
    }

    @Override
    protected void initData() {

    }

    //得到Fragement实例
    public static Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[4];
        fragments[0] = new OneFragment();
        fragments[1] = new TwoFragment();
        fragments[2] = new ThreeFragment();
        fragments[3] = new FourFragment();
        return fragments;
    }

    @Override
    public void onTabChanged(String tabId) {
        updateTabState();
    }

    private void updateTabState() {
        TabWidget tabWidget = tabhost.getTabWidget();
        for (int i=0;i<tabWidget.getTabCount();i++){
            View view = tabWidget.getChildTabViewAt(i);
            ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
            TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
            if(i == tabhost.getCurrentTab()){
                tabIcon.setImageResource(mTabResPressed[i]);
                tabText.setTextColor(getResources().getColor(android.R.color.black));
            }else{
                tabIcon.setImageResource(mTabRes[i]);
                tabText.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
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
}
