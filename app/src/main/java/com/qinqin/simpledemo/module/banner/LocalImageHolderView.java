package com.qinqin.simpledemo.module.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.app.common.glide.ImageLoader;

/**
 * Description： SimpleDemo
 * Copyright (c)
 * This program is protected by copyright laws.
 * package: com.qinqin.simpledemo.model.banner
 * Date: 2017/5/8
 * user: user QuintoQin
 *
 * @author 覃勤
 * @version : 1.0
 */
public class LocalImageHolderView implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, String data) {
        ImageLoader.loadImageView(context, data, imageView);
    }
}
