package com.qgstudio.anywork.enter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.common.DialogManagerActivity;
import com.qgstudio.anywork.enter.login.LoginFragment;
import com.qgstudio.anywork.enter.register.RegisterFragment;
import com.qgstudio.anywork.utils.ActivityUtil;
import com.qgstudio.anywork.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录注册的 Activity ，切换登录注册的 Fragment ，并为其配置需要的 Presenter
 * Created by chenyi on 2017/3/28.
 */

public class EnterActivity extends DialogManagerActivity {

    @BindView(R.id.container) FrameLayout container;

    //切换帐号标志
    public static final int FLAG_SWITCH_USER = 0;

    @OnClick(R.id.register)
    public void intoRegister() {
        // 根据 tag 从 FragmentManager 中将 fragment 取出
        RegisterFragment registerFragment =
                (RegisterFragment) getSupportFragmentManager().findFragmentByTag(RegisterFragment.ARGUMENT_REGISTER_ID);
        if (registerFragment == null) {
            // 如果当前没有该 fragment ，创建该 fragment 并通过 FragmentManager 进行管理
            registerFragment = RegisterFragment.newInstance();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setTransition(registerFragment);
            }
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), registerFragment,
                                            R.id.container, RegisterFragment.ARGUMENT_REGISTER_ID);
        }
    }

    @OnClick(R.id.login)
    public void intoLogin() {
        // 跳转到登录界面
        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentByTag(LoginFragment.ARGUMENT_LOGIN_ID);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setTransition(loginFragment);
            }
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), loginFragment,
                                            R.id.container, LoginFragment.ARGUMENT_LOGIN_ID);
        }
    }

    @OnClick(R.id.others)
    public void intoOthers() {
//        BaseDialog.Builder builder = new BaseDialog.Builder(EnterActivity.this);
//        BaseDialog baseDialog =
//                builder.cancelTouchout(false)
//                .title("提示")
//                //.titleColor(R.color.theme_yellow_background)
//                .build();
//        baseDialog.show();

        ToastUtil.showToast("暂未开放游客模式。");
        // TODO: 2017/7/10 通过游客模式进入
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);

        int flag = getIntent().getIntExtra("FLAG", -1);
        if (FLAG_SWITCH_USER == flag) {
            intoLogin();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public static void start(Context context, int flag) {
        Intent intent = new Intent(context, EnterActivity.class);
        intent.putExtra("FLAG", flag);
        context.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTransition(Fragment fragment) {
        Slide slideTransition = new Slide(Gravity.END);
        slideTransition.setDuration(300);

        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(300);
        // 为 fragment 设置进出场的动画
        fragment.setEnterTransition(slideTransition);
        fragment.setAllowEnterTransitionOverlap(true);
        fragment.setAllowReturnTransitionOverlap(true);
        fragment.setSharedElementEnterTransition(changeBoundsTransition);
    }
}
