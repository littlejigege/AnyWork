package com.qgstudio.anywork.floginandsign.register;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.mvp.MVPBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterFragment extends MVPBaseFragment<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {
    public static final String ARGUMENT_REGISTER_ID = "REGISTER_ID";
    private boolean hidePass;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;

    @OnClick(R.id.register)
    public void register() {
        String e = email.getText().toString();
        String n = name.getText().toString();
        String p = phone.getText().toString();
        if (!n.matches("[a-z0-9A-Z\\u4e00-\\u9fa5]{1,15}")) {
            name.setError("请输入1-15个字符的姓名");
        } else if (!p.matches("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$")) {
            phone.setError("请输入正确的电话号码");
        } else if (!e.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) {
            email.setError("请输入正确的邮箱地址");
        } else if (!password.getText().toString().matches("[a-z0-9A-Z]{6,15}")) {
            password.setError("请输入6-15位的密码");
        } else {
            mPresenter.register(e, password.getText().toString(), n, p);
        }
    }
    @OnClick(R.id.eye)
    public void seePass() {
        if (hidePass) {
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        hidePass = !hidePass;
    }

    public static RegisterFragment newInstance() {
        //通过 newInstance 保证 Fragment 不被重复构造，造成 fragment 重叠
        return new RegisterFragment();
    }

    public RegisterFragment() {
        //防止无参构造器被外部调用
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showError(String errorInfo) {
        Toast.makeText(mActivity, errorInfo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess() {
        Toast.makeText(mActivity, "注册成功", Toast.LENGTH_SHORT).show();
        mActivity.onBackPressed();
    }
}
