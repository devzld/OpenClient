package com.zld.openclient.common;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.zld.openclient.base.MyApp;


public class ToastUtil {
    public static void showToast(Context context, String msg, int duration) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return;
        }

        Toast.makeText(context, msg, duration).show();
    }

    public static void showToast(String msg, int duration) {
        showToast(MyApp.getInstance().getApplicationContext(), msg, duration);
    }

    public static void showToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(String msg) {
        showToast(MyApp.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, int id) {
        if (context == null) return;
        showToast(context, context.getResources().getString(id), Toast.LENGTH_SHORT);
    }
}
