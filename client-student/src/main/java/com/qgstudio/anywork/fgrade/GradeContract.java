package com.qgstudio.anywork.fgrade;

import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.mvp.BasePresenter;
import com.qgstudio.anywork.mvp.BaseView;

import java.util.List;

/**
 * Created by chenyi on 2017/7/10.
 */

public interface GradeContract {

    interface View extends BaseView {
        void showSuccess(Question question);
        void showError(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void getDetail(int id);
    }
}
