package com.qgstudio.anywork.fexam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 答案适配器
 * Created by Yason on 2017/7/10.
 */

public class OptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Question mQuestion;

    public OptionAdapter(Question question) {
        mQuestion = question;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (mQuestion.getType()) {
            case 1:
            case 2: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_choice, parent, false);
                return new ChoiceHolder(v);
            }
            case 3:{
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_filling, parent, false);
                return new FillingHolder(v);
            }
            case 4:
            case 5:
            case 6: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_asking, parent, false);
                return new AskingHolder(v);
            }
            default:{}
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mQuestion.getType()) {
            case 1:{//选择
                ChoiceHolder choice = (ChoiceHolder) holder;
                switch (position) {
                    case 0:{
                        choice.img_choice.setImageResource(R.mipmap.ic_launcher);
                        choice.tv_choice.setText(mQuestion.getA());
                        break;
                    }
                    case 1:{
                        choice.img_choice.setImageResource(R.mipmap.ic_launcher);
                        choice.tv_choice.setText(mQuestion.getB());
                        break;
                    }
                    case 2:{
                        choice.img_choice.setImageResource(R.mipmap.ic_launcher);
                        choice.tv_choice.setText(mQuestion.getC());
                        break;
                    }
                    case 3:{
                        choice.img_choice.setImageResource(R.mipmap.ic_launcher);
                        choice.tv_choice.setText(mQuestion.getD());
                        break;
                    }
                }
                break;
            }
            case 2:{//判断
                ChoiceHolder choice = (ChoiceHolder) holder;
                choice.img_choice.setImageResource(position == 0 ? R.drawable.ic_right_normal:R.drawable.ic_wrong_normal);
                break;
            }
            case 3:{//填空
                FillingHolder filling = (FillingHolder) holder;
                int pos = position + 1;
                filling.tv_filling.setText(pos + ".");
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
            case 3:{
                return mQuestion.getOther();
            }
            case 4:
            case 5:
            case 6: {
                return 1;
            }
            default:{}
        }
        return 0;
    }




    class ChoiceHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_choice) ImageView img_choice;
        @BindView(R.id.tv_choice) TextView tv_choice;

        public ChoiceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2017/7/11 下一页
                }
            });
        }

    }

    class FillingHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_filling) TextView tv_filling;
        @BindView(R.id.edi_filling) EditText edi_filling;

        public FillingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AskingHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.edi_asking) EditText edi_asking;

        public AskingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
