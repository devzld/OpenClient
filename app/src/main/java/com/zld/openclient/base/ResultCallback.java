package com.zld.openclient.base;

/**
 * Created by lingdong on 2018/1/4.
 */

public abstract class ResultCallback<T> {

    public abstract void onSuccess(T data);

    public abstract void onFail(int code,String msg);

}
