package com.app.common.bean;

/**
 * Created by ${user} on 2018/4/24.
 * QinQin
 */

public class MainMessage {
    private int msg;//1,刷新 user界面

    public MainMessage(int msg) {
        this.msg = msg;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
