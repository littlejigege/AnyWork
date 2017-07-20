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

public class JudgeAdapter extends ChoiceAdapter {

    //判断
    private String[] judge = {"1", "0"};
    private int[] content_normal = {R.drawable.ic_right_normal, R.drawable.ic_wrong_normal};
    private int[] content_selected = {R.drawable.ic_right_selected, R.drawable.ic_wrong_selected};


    public JudgeAdapter(Context context, Question question) {
        super(context, question);
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
        c.img_choice.setText("");
        c.img_choice.setBackground(ContextCompat.getDrawable(mContext, content_normal[position]));
        //选项点击监听
        c.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != mAnswerPos) {
                        notifyItemChanged(mAnswerPos);
                    }

                    mAnswer = judge[position];
                    mAnswerPos = position;

                    c.img_choice.setBackground(ContextCompat.getDrawable(mContext, content_selected[position]));
                }
            });

    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
