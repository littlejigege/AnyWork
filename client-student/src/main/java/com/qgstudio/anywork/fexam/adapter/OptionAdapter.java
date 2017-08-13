package com.qgstudio.anywork.fexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.qgstudio.anywork.data.model.Question;



/**
 * 答案适配器
 * Created by Yason on 2017/7/10.
 */

public abstract class OptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    protected Context mContext;
    protected Question mQuestion;


    public OptionAdapter(Context context, Question question) {
        mContext = context;
        mQuestion = question;
    }

}
