package com.qinqin.common.network.entity;

/**
 * Created by 26050 on 2017/12/8.
 * QinQin
 * 数据基本返回类型
 */

/**
 * {
 * "status": 200,
 * "message": "成功",
 * "data": {}
 * }
 */
public class BasicResponse<T> {
    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
