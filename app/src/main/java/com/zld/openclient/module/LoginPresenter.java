package com.zld.openclient.module;

import com.zld.openclient.base.BasePresenter;
import com.zld.openclient.base.ResultCallback;

/**
 * Created by lingdong on 2018/1/4.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View,LoginModel> implements LoginContract.Presenter{


    @Override
    public void login(String userName, String pwd) {
        mModel.login(userName, pwd, new ResultCallback<String>() {
            @Override
            public void onSuccess(String data) {
                mView.onLoginSuccess(data);
            }

            @Override
            public void onFail(int code, String msg) {
                mView.showMsg(msg);
            }
        });
    }
}
