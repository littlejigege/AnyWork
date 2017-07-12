package com.qgstudio.anywork.floginandsign.login;

import com.qgstudio.anywork.mvp.BasePresenter;
import com.qgstudio.anywork.mvp.BaseView;

/**
 *  登录界面的接口
 *  Created by chenyi on 2017/3/28.
 */

public class LoginContract {
    interface View extends BaseView {

        void showError(String errorInfo);

        void showSuccess(User user);
    }

    interface  Presenter extends BasePresenter<View> {

        void login(String account, String password);
    }
}
