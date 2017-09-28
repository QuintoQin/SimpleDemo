package com.qinqin.simpledemo.bean;

import org.litepal.crud.DataSupport;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.bean
 * Date: 2017/5/9
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class DEST extends DataSupport{
    private String destId;//目的地ID
    private String cnName;//中文名
    private String enName;//英文名
    private String parentId;
    private String childrenId;
    private long updateTime;

    // 自动生成get、set方法

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(String childrenId) {
        this.childrenId = childrenId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DEST{" +
                "destId='" + destId + '\'' +
                ", cnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", childrenId='" + childrenId + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
