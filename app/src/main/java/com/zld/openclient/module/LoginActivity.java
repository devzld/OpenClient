package com.zld.openclient.module;

import android.os.Bundle;

import com.zld.openclient.R;
import com.zld.openclient.base.BaseActivity;
import com.zld.openclient.base.annotation.ActivityFragmentInject;

@ActivityFragmentInject(contentViewId = R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        loadRootFragment(R.id.fl_main, LoginFragment.newInstance());
    }
}
