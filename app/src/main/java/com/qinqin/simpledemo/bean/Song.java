package com.qinqin.simpledemo.bean;

import org.litepal.annotation.Column;

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
public class Song {
    @Column(nullable = false)
    private String name;

    private int duration;

    @Column(ignore = true)
    private String uselessField;

    private Album album;
}
