package com.qgstudio.anywork.fexam.data;

import com.qgstudio.anywork.data.model.StudentPaper;
import com.qgstudio.anywork.fexam.ExamView;
import com.qgstudio.anywork.fpaper.PaperFragView;
import com.qgstudio.anywork.mvp.BasePresenter;

/**
 * Created by Yason on 2017/8/13.
 */

public interface ExamPresenter extends BasePresenter<ExamView> {
    void getTestpaper(int textpaperId);
    void submitTestPaper(StudentPaper studentPaper);
}
