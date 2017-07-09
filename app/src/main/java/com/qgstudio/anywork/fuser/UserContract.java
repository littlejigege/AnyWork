package com.qgstudio.anywork.fuser;

import android.net.Uri;

import com.qgstudio.anywork.mvp.BasePresenter;
import com.qgstudio.anywork.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UserContract {
    interface View extends BaseView {
        void showSuccess();
        void showError(String s);
        void setEmail(String email);
        void setUserName(String name);
        void setPhone(String phone);
        void setSchool(String school);
        void setImg(String imgUrl);
        void changeImg(Uri imgUrl);
        void showProgressDialog();
        void hidProgressDialog();
        String uri2Path (Uri picUri);
    }

    interface Presenter extends BasePresenter<View> {
        void getInfo();
        void changeInfo(String title, String string);
        void changePic(Uri picUri);
    }
}
