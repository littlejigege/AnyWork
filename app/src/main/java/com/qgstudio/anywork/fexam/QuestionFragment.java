package com.qgstudio.anywork.fexam;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.qgstudio.anywork.mvp.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class QuestionFragment extends BaseFragment implements ChoiceAdapter.OnChoiceOptionClickListener {

    @BindView(R.id.tv_type)
    TextView mTVType;

    @BindView(R.id.tv_content)
    TextView mTVContent;

    @BindView(R.id.tv_answer)
    TextView mTVAnswere;

    @BindView(R.id.recycler_option)
    RecyclerView mRecyclerView;

    //1-选择题 2-判断题 3-填空题 4-问答题 5-编程题 6-综合题
    public static final String[] mTypeString = {"(选择题)", "(判断题)", "(填空题)", "(问答题)", "(编程题)", "(综合题)"};

    private Question mQuestion;//该页面显示的一道题

    private Unbinder mUnbinder;

    private OptionAdapter mOptionAdapter;
    private LinearLayoutManager mLinearLayoutManager;


    public StudentAnswer getStudentAnswer() {
        String answer = "";
        switch (mQuestion.getType()) {
            case 1:
            case 2: {
                ChoiceAdapter choiceAdapter = (ChoiceAdapter) mOptionAdapter;
                answer = choiceAdapter.getAnswer();
                break;
            }
            case 3:{//填空
                for (int pos = 0; pos < mLinearLayoutManager.getChildCount(); pos++) {
                    View v =mRecyclerView.getChildAt(pos);
                    FillingAdapter.FillingHolder holder = (FillingAdapter.FillingHolder) mRecyclerView.getChildViewHolder(v);
                    String text = holder.edi_filling.getText().toString();
                    text = text.equals("") ? " " : text;
                    answer = answer + text + "#";
                }
                answer = answer.substring(0, answer.length() - 1);
                break;
            }
            case 4:
            case 5:
            case 6:{
                View v =mRecyclerView.getChildAt(0);
                AskingAdapter.AskingHolder holder = (AskingAdapter.AskingHolder) mRecyclerView.getChildViewHolder(v);
                answer = holder.edi_asking.getText().toString();
                break;
            }
            default:{}
        }
        StudentAnswer studentAnswer = new StudentAnswer();
        studentAnswer.setQuestionId(mQuestion.getQuestionId());
        studentAnswer.setStudentAnswer(answer);
        return studentAnswer;
    }

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
        mTVType.setText(mTypeString[mQuestion.getType() - 1]);

        //设置题目内容
        mTVContent.setText(mQuestion.getContent());

        //设置题目答案
        mTVAnswere.setText("答案：" + mQuestion.getKey());

        //设置题目选项
        mOptionAdapter = OptionFactory.getAdapter(mQuestion.getType(), mActivity, mQuestion);
        int type = mQuestion.getType();
        if (type == 1 || type == 2) {
            ChoiceAdapter choiceAdapter = (ChoiceAdapter) mOptionAdapter;
            choiceAdapter.setOnChoiceOptionClickListener(this);
        }
        mRecyclerView.setAdapter(mOptionAdapter);

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void loadData() {
        //no need
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onChoiceOptionClickListener() {
        ExamActivity ativity = (ExamActivity) mActivity;
        ativity.setNextPage();
    }
}
