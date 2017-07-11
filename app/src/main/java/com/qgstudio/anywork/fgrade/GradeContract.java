package com.qgstudio.anywork.fgrade;

import com.qgstudio.anywork.mvp.BasePresenter;
import com.qgstudio.anywork.mvp.BaseView;

import java.util.List;

/**
 * Created by chenyi on 2017/7/10.
 */

public interface GradeContract {

    interface View extends BaseView {
        void showSuccess();
        void showError(String s);
        void showGrade(List<GradeInfo> datas);
        void showProgressDialog();
        void hidProgressDialog();
    }

    interface Presenter extends BasePresenter<View> {
        void getGrade();
    }
}
