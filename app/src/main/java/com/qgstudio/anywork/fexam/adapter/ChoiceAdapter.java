package com.qgstudio.anywork.fexam.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
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

    private String mAnswer;//填写的答案

    private OnChoiceOptionClickListener mOnChoiceOptionClickListener;

    public interface OnChoiceOptionClickListener {
        void onChoiceOptionClickListener();
    }

    //选择
    private String[] choice = {"A", "B", "C", "D"};
    private String[] choice_content = {mQuestion.getA(), mQuestion.getB(), mQuestion.getC(), mQuestion.getD()};
    //判断
    private int[] judge = {1, 0};
    private int[] judge_content = {R.drawable.ic_right_normal, R.drawable.ic_right_selected};

    public ChoiceAdapter(Context context, Question question) {
        super(context, question);
    }

    public void setOnChoiceOptionClickListener(OnChoiceOptionClickListener onChoiceOptionClickListener) {
        mOnChoiceOptionClickListener = onChoiceOptionClickListener;
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
        ChoiceHolder c = (ChoiceHolder) holder;
        switch (mQuestion.getType()) {
            case 1:{//选择
                c.img_choice.setText(choice[position]);
                c.tv_choice.setText(choice_content[position]);
                if (mOnChoiceOptionClickListener != null) {
                    c.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAnswer = choice[position];
                            mOnChoiceOptionClickListener.onChoiceOptionClickListener();
                        }
                    });
                }
                break;
            }case 2:{//判断
                c.img_choice.setText("");
                c.img_choice.setBackground(ContextCompat.getDrawable(mContext, judge_content[position]));
                if (mOnChoiceOptionClickListener != null) {
                    c.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mAnswer = judge[position] + "";
                            mOnChoiceOptionClickListener.onChoiceOptionClickListener();
                        }
                    });
                }
                break;
            }
            default:{}
        }
    }

    @Override
    public int getItemCount() {
        switch (mQuestion.getType()) {
            case 1:{
                return 4;
            }
            case 2:{
                return 2;
            }
            default:{}
        }
        return 0;
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
