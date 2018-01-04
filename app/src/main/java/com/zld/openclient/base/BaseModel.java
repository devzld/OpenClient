package com.zld.openclient.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lingdong on 2018/1/4.
 */

public abstract class BaseModel {

    protected CompositeDisposable mDisposable;

    public BaseModel() {
        mDisposable = new CompositeDisposable();
    }

    protected void cancelRequest(){
        if(mDisposable!=null){
            mDisposable.clear();
        }
    }
}
