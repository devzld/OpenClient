package com.zld.openclient.module;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zld.openclient.R;
import com.zld.openclient.base.BaseFragment;
import com.zld.openclient.base.annotation.ActivityFragmentInject;
import com.zld.openclient.common.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lingdong on 2018/1/4.
 */
@ActivityFragmentInject(contentViewId = R.layout.fragment_login)
public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_user)
    EditText mEtUser;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View mView, Bundle savedInstanceState) {

    }


    @Override
    public void onLoginSuccess(String msg) {
        ToastUtil.showToast(msg);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        mPresenter.login(mEtUser.getText().toString().trim(), mEtPwd.getText().toString().trim());
    }
}
