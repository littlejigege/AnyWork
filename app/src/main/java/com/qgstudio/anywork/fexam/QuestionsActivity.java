package com.qgstudio.anywork.fexam;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.ui.ExamPagerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuestionsActivity extends AppCompatActivity {

    @BindView(R.id.epv)
    ExamPagerView mExamPagerView;//类似ViewPager

    private List<Question> mQuestions;//数据源
    private QuestionsAdapter mQuestionsAdapter;//适配器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);

        mQuestionsAdapter = new QuestionsAdapter(getSupportFragmentManager(), mQuestions);
        mExamPagerView.setViewPagerAdapter(mQuestionsAdapter);

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("你还未完成选择题，确认返回？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public static void startToActivity(Context context) {
        Intent intent = new Intent(context, QuestionsActivity.class);
//        intent.putParcelableArrayListExtra("",)
        context.startActivity(intent);
    }

}
