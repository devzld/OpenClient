package com.zld.openclient.base;

import android.app.Application;

/**
 * Created by lingdong on 2018/1/3.
 */

public class MyApp extends Application {


    private static MyApp mInstance;

    public static MyApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
