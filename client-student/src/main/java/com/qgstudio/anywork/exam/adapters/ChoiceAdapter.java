package com.qgstudio.anywork.exam.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Yason 2017/7/17.
 */

public class ChoiceAdapter extends OptionAdapter {

    //学生的答案
    protected String mAnswer;
    protected int mAnswerPos;

    //选项的内容
    private String[] choice = {"A", "B", "C", "D"};
    private String[] content = {mQuestion.getA(), mQuestion.getB(), mQuestion.getC(), mQuestion.getD()};

    public ChoiceAdapter(Context context, Question question, int position) {
        super(context, question, position);
    }


    @Override
    public String getAnswer() {
        return mAnswer == null ? "" : mAnswer;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_choice, parent, false);
        return new ChoiceHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ChoiceHolder ch = (ChoiceHolder) holder;

        //选项内容初始化
        ch.tv_choice.setText(choice[position]);
        ch.tv_choice.setBackgroundResource(R.drawable.bg_choice_normal);
        ch.tv_choice.setTextColor(ContextCompat.getColor(mContext, R.color.dark_grey_text));
        ch.tv_content.setText(content[position]);

        if (mIsReadOnly) {
            ch.itemView.setClickable(false);
            return;
        }

        //选项点击事件监听
        ch.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != mAnswerPos) {
                    notifyItemChanged(mAnswerPos);
                }
                mAnswer = choice[position];
                mAnswerPos = position;

                ch.tv_choice.setBackgroundResource(R.drawable.bg_choice_selected);
                ch.tv_choice.setTextColor(ContextCompat.getColor(mContext, R.color.dark_green_text));

                storeAnswer();
            }
        });

        //fragment回退时恢复已答的内容
        StudentAnswer origin = restoreAnswer();
        if (origin != null) {
            String os = origin.getStudentAnswer();
            if (os.equals(choice[position])) {
                mAnswer = os;
                mAnswerPos = position;

                ch.tv_choice.setBackgroundResource(R.drawable.bg_choice_selected);
                ch.tv_choice.setTextColor(ContextCompat.getColor(mContext, R.color.dark_green_text));

            }
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    protected class ChoiceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_choice) TextView tv_choice;
        @BindView(R.id.tv_content) TextView tv_content;

        public ChoiceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
