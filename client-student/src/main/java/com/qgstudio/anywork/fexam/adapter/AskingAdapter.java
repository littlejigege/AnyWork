package com.qgstudio.anywork.fexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yason on 2017/7/17.
 */

public class AskingAdapter extends OptionAdapter {

    public AskingAdapter(Context context, Question question) {
        super(context, question);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_asking, parent, false);
        return new AskingHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class AskingHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.edi_asking)
        public EditText edi_asking;

        public AskingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
