package com.qgstudio.anywork.fexam;


import com.qgstudio.anywork.common.PreLoading;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswerAnalysis;
import com.qgstudio.anywork.data.model.StudentAnswerResult;
import com.qgstudio.anywork.data.model.StudentTestResult;
import com.qgstudio.anywork.mvp.BaseView;

import java.util.List;

public interface ExamView extends BaseView ,PreLoading{
    void addQuestions(List<Question> questions);
    void startGradeAty(double socre, List<StudentAnswerResult> analysis);
    void destroySelf();
}
