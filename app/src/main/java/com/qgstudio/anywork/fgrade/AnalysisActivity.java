package com.qgstudio.anywork.fgrade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.fexam.adapter.AskingAdapter;
import com.qgstudio.anywork.fexam.adapter.ChoiceAdapter;
import com.qgstudio.anywork.fexam.adapter.FillingAdapter;
import com.qgstudio.anywork.fexam.adapter.OptionAdapter;
import com.qgstudio.anywork.fexam.adapter.OptionFactory;
import com.qgstudio.anywork.utils.GsonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenyi on 17-7-29.
 */

public class AnalysisActivity extends AppCompatActivity {
    @BindView(R.id.tv_type)
    TextView mTVType;

    @BindView(R.id.tv_content)
    TextView mTVContent;

    @BindView(R.id.tv_answer)
    TextView mTVAnswere;

    @BindView(R.id.recycler_option)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_question);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mQuestion = GsonUtil.GsonToBean(intent.getStringExtra("question"), Question.class);
        initView();
    }

    //1-选择题 2-判断题 3-填空题 4-问答题 5-编程题 6-综合题
    public static final String[] mTypeString = {"(选择题)", "(判断题)", "(填空题)", "(问答题)", "(编程题)", "(综合题)"};

    private Question mQuestion;//该页面显示的一道题

    private Unbinder mUnbinder;

    private OptionAdapter mOptionAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private void initView() {

        //设置题目类型
        mTVType.setText(mTypeString[mQuestion.getType() - 1]);

        //设置题目内容
        mTVContent.setText(mQuestion.getContent());

        //设置题目答案
        mTVAnswere.setText("答案：" + mQuestion.getKey());

        //设置题目选项
        mOptionAdapter = OptionFactory.getAdapter(mQuestion.getType(), this, mQuestion);
        mRecyclerView.setAdapter(mOptionAdapter);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    public static void startActivity(Context context, String question) {
        Intent intent = new Intent(context, AnalysisActivity.class);
        intent.putExtra("question", question);
        context.startActivity(intent);
    }
}
