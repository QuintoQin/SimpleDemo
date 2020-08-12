package com.app.common.network.entity;

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
    private int code;
    private String status;
    private BasicData<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BasicData<T> getData() {
        return data;
    }

    public void setData(BasicData<T> data) {
        this.data = data;
    }
}
