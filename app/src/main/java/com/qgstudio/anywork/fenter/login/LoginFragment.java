package com.qgstudio.anywork.fenter.login;

import android.content.Intent;
import android.widget.EditText;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.fmain.HomeActivity;
import com.qgstudio.anywork.mvp.MVPBaseFragment;
import com.qgstudio.anywork.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  登录界面的 fragment
 *  Created by chenyi on 2017/3/28.
 */

public class LoginFragment extends MVPBaseFragment<LoginContract.View, LoginPresenter> implements LoginContract.View {

    public static final String ARGUMENT_LOGIN_ID = "LOGIN_ID";

    @BindView(R.id.account) EditText account;

    @BindView(R.id.password) EditText password;

    @OnClick(R.id.sign_in)
    public void login() {
        String acc = account.getText().toString();
        String pass = password.getText().toString();
        if (acc.equals("") || pass.equals("")) {
            ToastUtil.showToast("请输入帐号和密码");
        } else {
            // 通过网络进行登录
            mPresenter.login(acc, pass);
        }
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        mActivity.onBackPressed();
    }

    public static LoginFragment newInstance() {
        //通过 newInstance 保证 Fragment 不被重复构造，造成 fragment 重叠
        return new LoginFragment();
    }

    public LoginFragment() {
        //防止无参构造器被外部调用
    }



    @Override
    public int getRootId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, mRoot);
    }

    @Override
    public void showError(String errorInfo) {
        ToastUtil.showToast(errorInfo);
    }

    @Override
    public void showSuccess() {
        ToastUtil.showToast("登录成功");
        Intent intent = new Intent(mActivity, HomeActivity.class);
        mActivity.startActivity(intent);
    }
}
