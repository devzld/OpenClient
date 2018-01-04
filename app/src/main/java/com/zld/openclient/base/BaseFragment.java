package com.zld.openclient.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zld.openclient.base.annotation.ActivityFragmentInject;
import com.zld.openclient.common.EventBean;
import com.zld.openclient.common.ReflectionUtil;
import com.zld.openclient.common.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lingdong on 2018/1/4.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends SupportFragment implements IBaseView {

    protected int mContentViewId;
    protected boolean mIsOpenEventBus;
    protected View mView;
    protected Context mContext;
    protected T mPresenter;
    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
                ActivityFragmentInject annotation = getClass()
                        .getAnnotation(ActivityFragmentInject.class);
                mContentViewId = annotation.contentViewId();
                mIsOpenEventBus = annotation.isOpenEventBus();
            } else {
                throw new RuntimeException(
                        "Class must add annotations of ActivityFragmentInitParams.class");
            }
            mView = inflater.inflate(mContentViewId, container, false);
            if (mIsOpenEventBus) {
                EventBus.getDefault().register(this);
            }
            unbinder = ButterKnife.bind(this, mView);
            mContext = getActivity();
            initPresenter();
            initView(mView, savedInstanceState);
        }
        return mView;
    }

    private void initPresenter() {
        try {
            Type[] types = ReflectionUtil.getParameterizedTypes(this);
            if (types != null && types.length > 0) {
                mPresenter = (T) ReflectionUtil.newInstance(types[0]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.showToast(msg);
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

    private void handleEvent(EventBean eventBean) {

    }

    protected abstract void initView(View mView, Bundle savedInstanceState);
}
