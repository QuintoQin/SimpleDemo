package com.qinqin.simpledemo.module.bus.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qinqin.simpledemo.R;
import com.qinqin.simpledemo.module.bus.eventbus.entity.Message;
import com.qinqin.common.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_main;
    Button btn_back;
    Button btn_asy;
    Button btn_post;
    Button btn_go;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_main = (Button) findViewById(R.id.btn_main);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_asy = (Button) findViewById(R.id.btn_asy);
        btn_post = (Button) findViewById(R.id.btn_post);
        btn_go = (Button) findViewById(R.id.btn_go);
        tv = (TextView) findViewById(R.id.tv);
        btn_main.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_asy.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_go.setOnClickListener(this);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main:
                EventBus.getDefault().post(new Message("传递MainMessage"));
                break;
            case R.id.btn_back:
                EventBus.getDefault().post(new Message("传递BackMessage"));
                break;
            case R.id.btn_asy:
                EventBus.getDefault().post(new Message("传递AsyMessage"));
                break;
            case R.id.btn_post:
                EventBus.getDefault().post(new Message("传递PostMessage"));
                break;
            case R.id.btn_go:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 主线程中执行
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(Message msg) {
        LogUtils.e("msg:" + msg + "thread" + Thread.currentThread().getName());
        tv.setText(msg.getMessage());
    }
//    /**
//     * 后台线程
//     *
//     * @param msg
//     */
//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    public void onBackgroundEventBus(Message msg) {
//        LogUtils.e("msg:" + msg + "thread" + Thread.currentThread().getName());
//        tv.setText(msg.getMessage());
//    }
//    /**
//     * 异步线程
//     *
//     * @param msg
//     */
//    @Subscribe(threadMode = ThreadMode.ASYNC)
//    public void onAsyncEventBus(Message msg) {
//        LogUtils.e("msg:" + msg + "thread" + Thread.currentThread().getName());
//        tv.setText(msg.getMessage());
//    }
//    /**
//     * 默认情况，和发送事件在同一个线程
//     *
//     * @param msg
//     */
//    @Subscribe(threadMode = ThreadMode.POSTING)
//    public void onPostEventBus(Message msg) {
//        LogUtils.e("msg:" + msg + "thread" + Thread.currentThread().getName());
//        tv.setText(msg.getMessage());
//    }
}
