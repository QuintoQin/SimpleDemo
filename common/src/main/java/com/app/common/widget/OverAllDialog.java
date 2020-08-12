package com.app.common.widget;

import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

/**
 * Created by ${user} on 2018/9/6.
 * QinQin
 */

public class OverAllDialog extends AlertDialog {
    private static AlertDialog overAllDialog = null;

    protected OverAllDialog(@NonNull Context context) {
        super(context);
    }

    public static AlertDialog getInstance(Context context) {
        if (overAllDialog == null) {
            synchronized (OverAllDialog.class) {
                if (overAllDialog == null) {
                    overAllDialog = new OverAllDialog.Builder(context)
                            .setTitle("温馨提示")
                            .setMessage("身份过期，请重新登录")
                            .setPositiveButton("确定", new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setNegativeButton("取消", new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create();
                }
            }
        }
        return overAllDialog;
    }
}
