package com.zld.openclient.base;

import com.zld.openclient.common.ReflectionUtil;

import java.lang.reflect.Type;

/**
 * Created by lingdong on 2018/1/4.
 */

public class BasePresenter<V extends IBaseView, M extends BaseModel> implements IBasePresenter<V> {

    protected V mView;
    protected M mModel;

    public BasePresenter() {
        try {
            Type[] types = ReflectionUtil.getParameterizedTypes(this);
            if (types != null && types.length >= 2) {
                mModel = (M) ReflectionUtil.newInstance(types[1]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        if(mModel!=null) {
            mModel.cancelRequest();
        }
        mView = null;
    }
}
