package com.qinqin.simpledemo.model.bus.eventbus.entity;

/**
 * Created by 26050 on 2017/9/27.
 */

public class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
