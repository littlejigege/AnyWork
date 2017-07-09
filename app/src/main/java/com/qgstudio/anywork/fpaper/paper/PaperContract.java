package com.qgstudio.anywork.fpaper.paper;

import android.content.Context;

import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Textpaper;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.BasePresenter;
import com.qgstudio.anywork.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface PaperContract {
    interface View extends BaseView {
        void showLoading();
        void hideLoading();
        void showThrowable(Throwable t);
        void showOrganizationList(List<Organization> organizationList);
        void showTextpaperList(List<Textpaper> textpaperList);
        void showPaperTesting(int textpaperId);
    }

    interface  Presenter extends BasePresenter<View> {
        void getOrganizationList();
        void getAllTextpaperList(User user);
        void startPaperTesting(int textpaperId);
    }
}
