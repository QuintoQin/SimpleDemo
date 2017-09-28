package com.qinqin.simpledemo.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

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
public class Album extends DataSupport {

    @Column(unique = true, defaultValue = "unknown")
    private String name;

    private float price;

    private byte[] cover;

    private List<Song> songs = new ArrayList<>();
}
