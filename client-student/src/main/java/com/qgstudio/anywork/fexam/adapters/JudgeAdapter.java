package com.qgstudio.anywork.fexam.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswer;

/**
 * @author Yason 2017/7/17.
 */

public class JudgeAdapter extends ChoiceAdapter {

    //判断的内容
    private String[] judge = {"1", "0"};
    private int[] content_normal = {R.drawable.ic_right_normal, R.drawable.ic_wrong_normal};
    private int[] content_selected = {R.drawable.ic_right_selected, R.drawable.ic_wrong_selected};

    public JudgeAdapter(Context context, Question question, int position) {
        super(context, question, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_choice, parent, false);
        return new ChoiceHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ChoiceHolder jh = (ChoiceHolder) holder;

        //选项内容初始化
        jh.tv_choice.setText("");
        jh.tv_choice.setBackground(ContextCompat.getDrawable(mContext, content_normal[position]));

        if (mIsReadOnly) {
            jh.itemView.setClickable(false);
            return;
        }

        //选项点击事件监听
        jh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != mAnswerPos) {
                        notifyItemChanged(mAnswerPos);
                    }

                    mAnswer = judge[position];
                    mAnswerPos = position;

                    jh.tv_choice.setBackground(ContextCompat.getDrawable(mContext, content_selected[position]));

                    storeAnswer();
                }
            });

        //fragment回退时恢复已答的内容
        StudentAnswer origin = restoreAnswer();
        if (origin != null) {
            String os = origin.getStudentAnswer();
            if (os.equals(judge[position])) {
                mAnswer = os;
                mAnswerPos = position;

                jh.tv_choice.setBackground(ContextCompat.getDrawable(mContext, content_selected[position]));
            }
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
