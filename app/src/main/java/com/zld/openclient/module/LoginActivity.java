package com.zld.openclient.module;

import android.os.Bundle;
import android.widget.EditText;

import com.zld.openclient.R;
import com.zld.openclient.base.BaseActivity;
import com.zld.openclient.base.annotation.ActivityFragmentInject;
import com.zld.openclient.common.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

@ActivityFragmentInject(contentViewId = R.layout.activity_login)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @BindView(R.id.et_user)
    EditText mEtUser;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        mPresenter.login(mEtUser.getText().toString().trim(),mEtPwd.getText().toString().trim());
    }

    @Override
    public void onLoginSuccess(String msg) {
        ToastUtil.showToast(msg);
    }
}
