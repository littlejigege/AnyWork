package com.qgstudio.anywork.fexam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.mvp.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class QuestionFragment extends BaseFragment {

    @BindView(R.id.tv_type)
    TextView mTVType;

    @BindView(R.id.tv_content)
    TextView mTVContent;

    @BindView(R.id.tv_answer)
    TextView mTVAnswere;

    @BindView(R.id.recycler_all)
    RecyclerView mRecyclerView;

    //1-选择题 2-判断题 3-填空题 4-问答题 5-编程题 6-综合题
    public static final String[] mType = {"(选择题)", "判断题", "填空题", "问答题", "编程题", "综合题"};

    private Question mQuestion;//该页面显示的一道题

    private Unbinder mUnbinder;

    private OptionAdapter mOptionAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestion = (Question) getArguments().get("QUESTION");
    }

    public static QuestionFragment newInstance(Question question) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("QUESTION", question);
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
        mTVType.setText(mType[mQuestion.getType() - 1]);

        //设置题目内容
        mTVContent.setText(mQuestion.getContent());

        //设置题目答案
        mTVAnswere.setText(mQuestion.getKey());

        //设置题目选项
        mOptionAdapter = new OptionAdapter(mQuestion);
        mRecyclerView.setAdapter(mOptionAdapter);

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
