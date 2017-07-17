package com.qgstudio.anywork.fexam;


import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.mvp.BaseView;

import java.util.List;

public interface ExamView extends BaseView{
    void addQuestion(Question question);
    void addQuestions(List<Question> questions);
}
