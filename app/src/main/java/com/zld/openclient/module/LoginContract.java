package com.zld.openclient.module;

import com.zld.openclient.base.IBasePresenter;
import com.zld.openclient.base.IBaseView;

/**
 * Created by lingdong on 2018/1/4.
 */

public interface LoginContract {

    interface View extends IBaseView{
        void onLoginSuccess(String msg);
    }

    interface Presenter extends IBasePresenter<View>{
        void login(String userName,String pwd);
    }
}
