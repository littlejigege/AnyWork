package com.qgstudio.anywork.fexam;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.Textpaper;
import com.qgstudio.anywork.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 *
 */

public class ExamActivity extends MVPBaseActivity<ExamContract.View, ExamPresenter> implements ExamContract.View {

    @BindView(R.id.tb_test) Toolbar mToolbar;
    @BindView(R.id.vp_test) ViewPager mViewPager;

    private ViewAdapter mAdapter;

    public static final String TEXTPAPER_ID = "textpaperId";

    private int mTextpaperId;
    private boolean mIsFirstLoad = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftest);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("作业");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mTextpaperId = getIntent().getIntExtra(TEXTPAPER_ID, -1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTextpaperId != -1 && mIsFirstLoad) {
            mPresenter.getTextpaper(mTextpaperId);
            mIsFirstLoad = false;
        }
    }

    public static void startToActivity(Context context, int textpaperId) {
        Intent intent = new Intent(context, ExamActivity.class);
        intent.putExtra(TEXTPAPER_ID, textpaperId);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showThrowable(String t) {

    }

    @Override
    public void showTextPaper(Textpaper textpaper) {
        mAdapter = new ViewAdapter(textpaper);
        mAdapter.setOnTextpaperSubmitListener(new ViewAdapter.OnTextpaperSubmitListener() {
            @Override
            public void onTextpaperSubmit(StudentAnswer studentAnswer) {
                mPresenter.submitTextpaper(studentAnswer);
            }
        });

        mViewPager.setOffscreenPageLimit(textpaper.getTopicNum());
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("你还没提交试卷，现在提交将没有成绩。");
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

    @Override
    public void showScore(Double score) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("你的客观题成绩是：");
        builder.setMessage(score+"分");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

}
