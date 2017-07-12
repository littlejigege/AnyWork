package com.qgstudio.anywork.fexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by Yason on 2017/7/11.
 */

public class ExamActivity extends AppCompatActivity {


//    private List<Question> mChoiceQuestions;
//    private List<Question> mJudgeQuestions;
//    private List<Question> mFillingQuestions;
//    private List<Question> mCodeQuestions;
//    private List<Question> mComprehensives;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2017/7/11 获取试卷的全部内容
    }

    @OnClick(R.id.card_choice)
    public void choice() {
    }

    @OnClick(R.id.card_judge)
    public void judge() {

    }

    @OnClick(R.id.card_filling)
    public void filling() {

    }

    @OnClick(R.id.card_asking)
    public void asking() {

    }

    @OnClick(R.id.card_code)
    public void code() {

    }

    @OnClick(R.id.card_comprehensive)
    public void comprehensive() {

    }


}
