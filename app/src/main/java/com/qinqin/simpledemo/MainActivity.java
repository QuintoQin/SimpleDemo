package com.qinqin.simpledemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qinqin.simpledemo.adapter.ReMainAdapter;
import com.qinqin.simpledemo.base.BaseActivity;
import com.qinqin.simpledemo.bean.MainItem;
import com.qinqin.simpledemo.common.Constant;
import com.qinqin.simpledemo.model.banner.BannerActivity;
import com.qinqin.simpledemo.model.bottomtablayout.BottomTabActivity;
import com.qinqin.simpledemo.model.bus.eventbus.FirstActivity;
import com.qinqin.simpledemo.model.design.DesignActivity;
import com.qinqin.simpledemo.model.encryption.RsaAndAesActivity;
import com.qinqin.simpledemo.model.mvp.simplemvp.TheMvpActivity;
import com.qinqin.simpledemo.model.photo.PhotoViewActivity;
import com.qinqin.simpledemo.model.pick.PickViewActivity;
import com.qinqin.simpledemo.model.recyclerview.RecylerViewActivity;
import com.qinqin.simpledemo.model.retrofit.RetrofitActivity;
import com.qinqin.simpledemo.model.rx.RxJavaActivity;
import com.qinqin.simpledemo.model.sql.LitePalActivity;
import com.qinqin.simpledemo.model.video.VideoActivity;
import com.qinqin.simpledemo.model.webview.WebViewActivity;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Observable;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_recycler)
    RecyclerView mainRecycler;

    private String[] TITLE = {"TabLayout底部导航栏", "Design控件使用",
            "Retrofit使用", "RxJava使用", "banner控件的使用",
            "RSA DES加密", "PickView", "Video播放",
            "PhotoView", "RecyclerView使用", "litepal",
            "WebView","热修复","MVP","Dagger2","EventBus"};
    private ArrayList<MainItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        toolbar.setTitle("主界面");
        setSupportActionBar(toolbar);

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mainRecycler.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    protected void initData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            MainItem mainItem = new MainItem();
            mainItem.setMain_text(TITLE[i]);
            arrayList.add(mainItem);
        }
        initAdapter();
    }

    private void initAdapter() {
        BaseQuickAdapter mainAdapter = new ReMainAdapter(R.layout.item_main, arrayList);
        mainRecycler.setAdapter(mainAdapter);
        View topView = getLayoutInflater().inflate(R.layout.main_top, (ViewGroup) mainRecycler.getParent(), false);
        mainAdapter.addHeaderView(topView);
        mainAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case Constant.MAIN_TABLAYOUT:
                    Intent intent = new Intent(MainActivity.this, BottomTabActivity.class);
                    startActivity(intent);
                    break;
                case Constant.MAIN_DESGIN:
                    DesignActivity.startToDesign(MainActivity.this);
                    break;
                case Constant.MAIN_RETROFIT:
                    RetrofitActivity.startToRetrofit(MainActivity.this);
                    break;
                case Constant.MAIN_RXJAVA:
                    startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
                    break;
                case Constant.MAIN_BANNER:
                    startActivity(new Intent(MainActivity.this, BannerActivity.class));
                    break;
                case Constant.MAIN_REA_AES:
                    Intent intent_rsa_aes = new Intent(MainActivity.this, RsaAndAesActivity.class);
                    startActivity(intent_rsa_aes);
                    break;
                case Constant.MAIN_PICK:
                    startActivity(new Intent(MainActivity.this, PickViewActivity.class));
                    break;
                case Constant.MAIN_VIDEO:
                    startActivity(new Intent(MainActivity.this, VideoActivity.class));
                    break;
                case Constant.MAIN_PHOTO:
                    startActivity(new Intent(MainActivity.this, PhotoViewActivity.class));
                    break;
                case Constant.MAIN_RECYLERVIEW:
                    startActivity(new Intent(MainActivity.this, RecylerViewActivity.class));
                    break;
                case Constant.MAIN_LITEPAL:
                    startActivity(new Intent(MainActivity.this, LitePalActivity.class));
                    break;
                case Constant.MAIN_WEBVIEW:
                    startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                    break;
                case Constant.MAIN_MVP:
                    startActivity(new Intent(MainActivity.this, TheMvpActivity.class));
                    break;
                case Constant.Main_EventBus:
                    startActivity(new Intent(MainActivity.this, FirstActivity.class));
                    break;
                default:
                    break;
            }
        });
    }


    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
