package com.qgstudio.anywork.exam;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswerResult;
import com.qgstudio.anywork.data.model.StudentPaper;
import com.qgstudio.anywork.dialog.BaseDialog;
import com.qgstudio.anywork.exam.adapters.AnswerBuffer;
import com.qgstudio.anywork.exam.data.ExamRepository;
import com.qgstudio.anywork.grade.GradeActivity;
import com.qgstudio.anywork.mvp.MVPBaseActivity;
import com.qgstudio.anywork.utils.GsonUtil;
import com.qgstudio.anywork.utils.ToastUtil;
import com.qgstudio.anywork.widget.ExamPagerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 整套试卷的容器
 * @author Yason 2017/4/2.
 */

public class ExamActivity extends MVPBaseActivity<ExamView, ExamRepository> implements ExamView,
        ViewPager.OnPageChangeListener {

    @BindView(R.id.epv) ExamPagerView mExamPagerView;
    @BindView(R.id.fab) FloatingActionButton mSubmitFab;

    private int mTestPaperId;
    private int mTestPaperType;//1为考试，0为练习
    private QuestionFragAdapter mQuestionFragAdapter;//数据适配器

    private BaseDialog mBaseDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        mTestPaperId = getIntent().getIntExtra("TESTPAPER_ID", -1);
        mTestPaperType = getIntent().getIntExtra("TESTPAPER_TYPE", -1);
        initView();
        loadData();
    }

    private void loadData() {
        mPresenter.getTestpaper(mTestPaperId);
    }

    private void initView() {
        ButterKnife.bind(this);

        mQuestionFragAdapter = new QuestionFragAdapter(getSupportFragmentManager(), new ArrayList<Fragment>());
        mExamPagerView.setViewPagerAdapter(mQuestionFragAdapter);

        mExamPagerView.setViewPagerListener(this);
    }

    @Override
    protected void onDestroy() {
        //退出前一定要清空缓存
        AnswerBuffer.getInstance().clear();

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mTestPaperType == 1) {
            checkExit();
            return;
        }
        super.onBackPressed();
    }

    public void checkExit() {
        if (mBaseDialog == null) {
            mBaseDialog = new BaseDialog.Builder(this)
                    .title("提示")
                    .content("您还未提交试卷，退出将不保存作答内容！！！")
                    .setNegativeListener("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finishAty();
                        }
                    })
                    .setPositiveListener("取消", null)
                    .cancelTouchout(true)
                    .build();
        }
        if (!mBaseDialog.isShowing()) {
            mBaseDialog.show();
        }
    }

    @OnClick(R.id.fab)
    public void submit() {
        StudentPaper studentPaper = new StudentPaper();
        studentPaper.setStudentId(((App) getApplication()).getUser().getUserId());
        studentPaper.setStudentAnswer(AnswerBuffer.getInstance().getResult());
        studentPaper.setTestpaperId(mTestPaperId);
        mPresenter.submitTestPaper(studentPaper);
    }

    @Override
    public void onPageSelected(int position) {
        int total = mQuestionFragAdapter.getCount();
        int pos = total != 0 ? position + 1 : 0;
        //每页显示当前页序
        mExamPagerView.setTitleCenterTextString(pos + "/" + total);
        //末页显示提交按钮
        mSubmitFab.setVisibility(pos == total ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static void start(Context context, int testpaperId, int testpaperType) {
        Intent intent = new Intent(context, ExamActivity.class);
        intent.putExtra("TESTPAPER_ID", testpaperId);
        intent.putExtra("TESTPAPER_TYPE", testpaperType);
        context.startActivity(intent);
    }

    @Override
    public void addQuestions(List<Question> questions) {
        List<Fragment> fragments = new ArrayList<>();
        int position = 0;
        for (Question question : questions) {
            //将每道题传入每个fragment中
            fragments.add(QuestionFragment.newInstance(question, position));
            position++;
        }
        mQuestionFragAdapter.addAll(fragments);
        mExamPagerView.setTitleCenterTextString((position != 0 ? 1 : 0) + "/" + questions.size());
        //如若当前作业只有一题，则在第一页(即也是最后一页)就显示提交按钮
        if (mQuestionFragAdapter.getCount() == 1) {
            mSubmitFab.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void startGradeAty(double socre, List<StudentAnswerResult> results) {
        GradeActivity.start(this, socre, GsonUtil.GsonString(results));
        finishAty();
    }

    @Override
    public void destroySelf() {
        finishAty();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String s) {
        ToastUtil.showToast(s);
    }


    private void finishAty() {
        this.finish();
    }

}
