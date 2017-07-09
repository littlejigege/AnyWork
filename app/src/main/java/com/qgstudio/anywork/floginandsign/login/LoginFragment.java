package com.qgstudio.anywork.floginandsign.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.MainActivity;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.MVPBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginFragment extends MVPBaseFragment<LoginContract.View, LoginPresenter> implements LoginContract.View {

    public static final String ARGUMENT_LOGIN_ID = "LOGIN_ID";

    private boolean hidePass = true;

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;

    @OnClick(R.id.eye)
    public void see() {
//        Log.i(TAG, "see: aaaa");
        if (hidePass) {
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        hidePass = !hidePass;
    }

    @OnClick(R.id.login)
    public void login() {
        if (!email.getText().toString().matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) {
            email.setError("请输入正确的邮箱地址");
        } else {
            mPresenter.login(email.getText().toString(), password.getText().toString());
        }
    }

    public static LoginFragment newInstance() {
        //通过 newInstance 保证 Fragment 不被重复构造，造成 fragment 重叠
        return new LoginFragment();
    }

    public LoginFragment() {
        //防止无参构造器被外部调用
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public int getRootId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void showError(String errorInfo) {
        Toast.makeText(mActivity, errorInfo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(User user) {
        ((App)mActivity.getApplication()).setUser(user);
        Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mActivity, MainActivity.class);
        mActivity.startActivity(intent);
    }
}
