package com.zld.openclient.module;

import android.os.Handler;
import android.os.Looper;

import com.zld.openclient.base.BaseModel;
import com.zld.openclient.base.ResultCallback;


/**
 * Created by lingdong on 2018/1/4.
 */

public class LoginModel extends BaseModel {

    private Handler handler;

    public LoginModel() {
        handler = new Handler(Looper.getMainLooper());
    }

    public void login(final String user, final String pwd, final ResultCallback<String> callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    if(user.equals("admin")&&pwd.equals("12345")) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess("登录成功");

                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess("登录失败");

                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
