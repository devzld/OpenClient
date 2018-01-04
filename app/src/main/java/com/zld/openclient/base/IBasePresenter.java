package com.zld.openclient.base;

/**
 * Created by lingdong on 2018/1/4.
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);
    void detachView();
}
