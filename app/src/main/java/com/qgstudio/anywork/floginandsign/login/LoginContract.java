package com.qgstudio.anywork.floginandsign.login;

import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.BasePresenter;
import com.qgstudio.anywork.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
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
