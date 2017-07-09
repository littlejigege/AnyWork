package com.qgstudio.anywork.floginandsign;

import android.animation.Animator;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.floginandsign.login.LoginFragment;
import com.qgstudio.anywork.floginandsign.register.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录注册的 Activity ，切换登录注册的 Fragment ，并为其配置需要的 Presenter
 * Created by chenyi on 2017/3/28.
 */

public class EnterActivity extends AppCompatActivity {

    public static final int ANIMATE_OUT = 0;

    public static final int ANIMATE_IN = 1;

    @BindView(R.id.register) ImageView register;

    @OnClick(R.id.register)
    public void startRegister() {
        animateOutandIn(register, ANIMATE_OUT);
        addNextFragment();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ButterKnife.bind(this);

        // 添加 Fragment
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LoginFragment.ARGUMENT_LOGIN_ID);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            //将新建 Fragment 加入到布局中
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, loginFragment, LoginFragment.ARGUMENT_LOGIN_ID)
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        animateOutandIn(register, ANIMATE_IN);
        super.onBackPressed();
    }

    //  view 的隐藏和显示动画
    private void animateOutandIn(final View view, int tag) {
        ViewPropertyAnimator animator = view.animate()
                    .setStartDelay(500)
                    .setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in));
        if (tag == ANIMATE_IN) {
            animator.alpha(1)
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

        } else {
            animator.alpha(0)
                    .scaleX(0f)
                    .scaleY(0f)
                    .setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    view.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    private void addNextFragment() {

        //切换到注册的 Fragment 中
        RegisterFragment registerFragment =
                (RegisterFragment) getSupportFragmentManager().findFragmentByTag(RegisterFragment.ARGUMENT_REGISTER_ID);
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
            setTransition(registerFragment);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, registerFragment, RegisterFragment.ARGUMENT_REGISTER_ID)
                    .addToBackStack(RegisterFragment.ARGUMENT_REGISTER_ID)
                    .commit();
        }
    }

    private void setTransition(Fragment fragment) {
        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(300);

        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(300);

        fragment.setEnterTransition(slideTransition);
        fragment.setAllowEnterTransitionOverlap(true);
        fragment.setAllowReturnTransitionOverlap(true);
        fragment.setSharedElementEnterTransition(changeBoundsTransition);
    }
}
