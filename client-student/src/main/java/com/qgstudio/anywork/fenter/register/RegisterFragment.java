package com.qgstudio.anywork.fenter.register;


import android.widget.EditText;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.mvp.MVPBaseFragment;
import com.qgstudio.anywork.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 注册的 Fragment 并为其配置需要的 Presenter
 * Created by chenyi on 2017/3/28.
 */

public class RegisterFragment extends MVPBaseFragment<RegisterContract.View, RegisterPresenter> implements RegisterContract.View {

    public static final String ARGUMENT_REGISTER_ID = "REGISTER_ID";

    @BindView(R.id.name) EditText name;

    @BindView(R.id.account) EditText account;

    @BindView(R.id.phone) EditText phone;

    @BindView(R.id.password) EditText password;

    @BindView(R.id.password2) EditText password2;

    @OnClick(R.id.register)
    public void register() {
        String a = account.getText().toString();
        String n = name.getText().toString();
        String p = phone.getText().toString();
        String pass1 = password.getText().toString();
        String pass2 = password2.getText().toString();

        if (!n.matches("[a-z0-9A-Z\\u4e00-\\u9fa5]{1,15}")) {
            name.setError("请输入1-15个字符的姓名");
        } else if (!a.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) {
            account.setError("请输入正确的邮箱地址");
        } else if (!p.matches("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$")) {
            phone.setError("请输入正确的电话号码");
        } else if (!pass1.matches("[a-z0-9A-Z]{6,15}")) {
            password.setError("请输入6-15位的密码");
        } else if (pass1.length() != pass2.length() && !pass1.equals(pass2)) {
            ToastUtil.showToast("请输入相同的确认密码！");
        } else {
            mPresenter.register(a, pass1, n, p);
        }

    }

    @OnClick(R.id.cancel)
    public void cancel() {
        mActivity.onBackPressed();
    }

    public static RegisterFragment newInstance() {
        //可通过 newInstance 为 Fragment 添加参数，保证 Fragment 重建时参数字段不被销毁
        return new RegisterFragment();
    }

    @Override
    public int getRootId() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, mRoot);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void showError(String errorInfo) {
        ToastUtil.showToast(errorInfo);
        password.setText("");
        password2.setText("");
    }

    @Override
    public void showSuccess() {
        ToastUtil.showToast("注册完成");
        mActivity.onBackPressed();
    }
}
