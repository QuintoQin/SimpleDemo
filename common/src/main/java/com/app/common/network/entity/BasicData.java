package com.app.common.network.entity;

public class BasicData<T> {
    private T bussData;
    private int resultCode;
    private String errMsg;

    public T getBussData() {
        return bussData;
    }

    public void setBussData(T bussData) {
        this.bussData = bussData;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
