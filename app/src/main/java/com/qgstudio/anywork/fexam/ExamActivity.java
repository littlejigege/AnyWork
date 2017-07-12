package com.qgstudio.anywork.fexam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.qgstudio.anywork.R;
import com.qgstudio.anywork.fpaper.PaperActivity;

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

    private int mTestpaperId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        //获取试卷id
        mTestpaperId = getIntent().getIntExtra("TESTPAPER_ID", -1);

        // TODO: 2017/7/11 根据id请求试卷的全部内容
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

    public static void startToActivity(Context context, int testpaperId) {
        Intent intent = new Intent(context, PaperActivity.class);
        intent.putExtra("TESTPAPER_ID", testpaperId);
        context.startActivity(intent);
    }

}
