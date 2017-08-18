package com.qgstudio.anywork.fexam;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.fexam.adapters.OptionAdapter;
import com.qgstudio.anywork.fexam.adapters.OptionFactory;
import com.qgstudio.anywork.mvp.BaseFragment;
import com.qgstudio.anywork.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 显示试卷中的一道题
 * @author Yason 2017/4/2.
 */
public class QuestionFragment extends BaseFragment {

    public static final String TAG = "QuestionFragment";

    @BindView(R.id.tv_type) TextView mTVType;//题目类型
    @BindView(R.id.tv_content) TextView mTVContent;//题目内容
    @BindView(R.id.tv_answer) TextView mTVAnswere;//题目答案
    @BindView(R.id.recycler_option) RecyclerView mRecyclerView;//题目选项

    //1-选择题 2-判断题 3-填空题 4-问答题 5-编程题 6-综合题
    public static final String[] mTypeString = {"(选择题)", "(判断题)", "(填空题)", "(问答题)", "(编程题)", "(综合题)"};

    private Question mQuestion;//此页的题目
    private int mPosition;//题目的序号

    private OptionAdapter mOptionAdapter;//选项适配器
    private LinearLayoutManager mLinearLayoutManager;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestion = (Question) getArguments().get("QUESTION");
        mPosition = getArguments().getInt("POSITION", -1);
    }

    public static QuestionFragment newInstance(Question question, int position) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("QUESTION", question);
        bundle.putInt("POSITION", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getRootId() {
        return R.layout.fragment_question;
    }

    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this, mRoot);

        //设置题目类型
        mTVType.setText(mTypeString[mQuestion.getType() - 1]);

        //设置题目内容
        mTVContent.setText(mQuestion.getContent());

        //设置题目答案
        String key = mQuestion.getKey();
        mTVAnswere.setText(key == null ? "" : "答案：" + key);

        //设置题目选项
        mOptionAdapter = OptionFactory.getAdapter(mQuestion.getType(), mActivity, mQuestion, mPosition);
        mRecyclerView.setAdapter(mOptionAdapter);

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void loadData() {

    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

}
