package com.qgstudio.anywork.fexam.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yason on 2017/7/17.
 */

public class ChoiceAdapter extends OptionAdapter {

    //填写的答案
    protected String mAnswer;
    protected int mAnswerPos;

    //选项的内容
    private String[] choice = {"A", "B", "C", "D"};
    private String[] content = {mQuestion.getA(), mQuestion.getB(), mQuestion.getC(), mQuestion.getD()};

    public ChoiceAdapter(Context context, Question question) {
        super(context, question);
    }

    public String getAnswer() {
        return mAnswer + "";
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_choice, parent, false);
        return new ChoiceHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ChoiceHolder c = (ChoiceHolder) holder;
        //选项内容初始化
        c.img_choice.setText(choice[position]);
        c.img_choice.setBackgroundResource(R.drawable.bg_choice_normal);
        c.img_choice.setTextColor(ContextCompat.getColor(mContext, R.color.dark_grey_text));
        c.tv_choice.setText(content[position]);
        //选项点击监听
        c.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != mAnswerPos) {
                    notifyItemChanged(mAnswerPos);
                }

                mAnswer = choice[position];
                mAnswerPos = position;

                c.img_choice.setBackgroundResource(R.drawable.bg_choice_selected);
                c.img_choice.setTextColor(ContextCompat.getColor(mContext, R.color.dark_green_text));

            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ChoiceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_choice)
        TextView img_choice;
        @BindView(R.id.tv_choice)
        TextView tv_choice;

        public ChoiceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
