package com.zld.openclient.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zld.openclient.base.annotation.ActivityFragmentInject;
import com.zld.openclient.common.EventBean;
import com.zld.openclient.common.ReflectionUtil;
import com.zld.openclient.common.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lingdong on 2018/1/3.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected int mContentViewId;
    protected boolean mIsOpenEventBus;
    protected Context mContext;
    private Unbinder unbinder;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
            ActivityFragmentInject annotation = getClass()
                    .getAnnotation(ActivityFragmentInject.class);
            mContentViewId = annotation.contentViewId();
            mIsOpenEventBus = annotation.isOpenEventBus();
        } else {
            throw new RuntimeException(
                    "Class must add annotations of ActivityFragmentInitParams.class");
        }

        mContext = this;
        setContentView(mContentViewId);
        if (mIsOpenEventBus) {
            EventBus.getDefault().register(this);
        }
        unbinder = ButterKnife.bind(this);
        initPresenter();

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initView(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * EventBus 全局消息通知
     *
     * @param eventBean 数据
     */
    @Subscribe
    public void onEventMainThread(EventBean eventBean) {
        if (eventBean != null) {
            handleEvent(eventBean);
        }
    }

    protected void handleEvent(EventBean eventBean) {

    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.showToast(msg);
    }

    public void initPresenter() {
        try {
            Type[] types = ReflectionUtil.getParameterizedTypes(this);
            if (types != null && types.length > 0) {
                mPresenter = (T) ReflectionUtil.newInstance(types[0]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected abstract void initView(Bundle savedInstanceState);
}
