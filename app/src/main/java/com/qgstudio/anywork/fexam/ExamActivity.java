package com.qgstudio.anywork.fexam;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.StudentAnswerAnalysis;
import com.qgstudio.anywork.data.model.StudentAnswerResult;
import com.qgstudio.anywork.data.model.StudentPaper;
import com.qgstudio.anywork.data.model.StudentTestResult;
import com.qgstudio.anywork.fexam.data.ExamRepository;
import com.qgstudio.anywork.fgrade.GradeActivity;
import com.qgstudio.anywork.mvp.MVPBaseActivity;
import com.qgstudio.anywork.ui.ExamPagerView;
import com.qgstudio.anywork.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;


public class ExamActivity extends MVPBaseActivity<ExamView, ExamRepository> implements ExamView, ViewPager.OnPageChangeListener {

    @BindView(R.id.epv)
    ExamPagerView mExamPagerView;//类似ViewPager

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private int mTestPaperId;

    private boolean isSubmit;

    //    private List<Question> mQuestions;//数据源
    private QuestionFragAdapter mQuestionFragAdapter;//适配器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        //1.获取试卷id
        mTestPaperId = getIntent().getIntExtra("TESTPAPER_ID", -1);

        //2.View初始化
        ButterKnife.bind(this);

        mQuestionFragAdapter = new QuestionFragAdapter(getSupportFragmentManager(), new ArrayList<Fragment>());
        mExamPagerView.setViewPagerAdapter(mQuestionFragAdapter);

        mExamPagerView.setViewPagerListener(this);

        //3.向服务器申请数据
        mPresenter.getTestpaper(mTestPaperId);

    }

//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("你还未完成选择题，确认返回？");
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
//    }

    public void setNextPage() {
        mExamPagerView.setViewPagerNextItem();
    }

    public static void startToActivity(Context context, int testpaperId) {
        Intent intent = new Intent(context, ExamActivity.class);
        intent.putExtra("TESTPAPER_ID", testpaperId);
        context.startActivity(intent);
    }

    @Override
    public void addQuestion(Question question) {
        mQuestionFragAdapter.add(QuestionFragment.newInstance(question));
    }

    @Override
    public void addQuestions(List<Question> questions) {
        List<Fragment> fragments = new ArrayList<>();
        for (Question question : questions) {
            fragments.add(QuestionFragment.newInstance(question));
        }
        mQuestionFragAdapter.addAll(fragments);
        mExamPagerView.setTitleCenterTextString(1 + "/" + questions.size());
    }

    @Override
    public void skipToGradeAty(double socre, List<StudentAnswerResult> results) {
        GradeActivity.startToActivity(this, socre, GsonUtil.GsonString(results));
        finish();
    }

    @OnClick(R.id.fab)
    public void submit() {
        if (!isSubmit) {
            //获取填写的答案
            List<StudentAnswer> studentAnswers = new ArrayList<>();
            for (int pos = 0; pos < mQuestionFragAdapter.getCount(); pos++) {
                QuestionFragment fragment = (QuestionFragment) mQuestionFragAdapter.getItem(pos);
                studentAnswers.add(fragment.getStudentAnswer());
            }
            //提交
            StudentPaper studentPaper = new StudentPaper();
            studentPaper.setStudentId(((App) getApplication()).getUser().getUserId());
            studentPaper.setTestpaperId(mTestPaperId);
            studentPaper.setStudentAnswer(studentAnswers);

            mPresenter.submitTestPaper(studentPaper);
            isSubmit = true;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //每页标题显示页数变化
        int total = mQuestionFragAdapter.getCount();
        int pos = position + 1;
        mExamPagerView.setTitleCenterTextString(pos + "/" + total);

        //最后一页显示提交按钮
        mFab.setVisibility(pos == total ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
