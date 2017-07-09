package com.qgstudio.anywork.fexam;

import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.Textpaper;
import com.qgstudio.anywork.mvp.BasePresenter;
import com.qgstudio.anywork.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExamContract {
    interface View extends BaseView {
        void showLoading();
        void hideLoading();
        void showThrowable(String t);
        void showTextPaper(Textpaper textpaper);
        void showScore(Double score);
    }

    interface  Presenter extends BasePresenter<View> {
        void getTextpaper(int textpaperId);
        void submitTextpaper(StudentAnswer studentAnswer);
    }
}
