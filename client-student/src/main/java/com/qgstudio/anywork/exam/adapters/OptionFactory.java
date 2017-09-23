package com.qgstudio.anywork.exam.adapters;

import android.content.Context;

import com.qgstudio.anywork.data.model.Question;

/**
 * @author Yason 2017/7/17.
 */

public class OptionFactory {

    public static OptionAdapter getAdapter(int type, Context context, Question question, int position) {
        OptionAdapter optionAdapter = null;
        switch (type) {
            case 1://选择
            {
                optionAdapter = new ChoiceAdapter(context, question, position);
                break;
            }
            case 2://判断
            {
                optionAdapter = new JudgeAdapter(context, question, position);
                break;
            }
            case 3://填空
            {
                optionAdapter = new FillingAdapter(context, question, position);
                break;
            }
            case 4://问答
            case 5://编程
            case 6://综合
            {
                optionAdapter = new AskingAdapter(context, question, position);
                break;
            }
            default: {
            }
        }
        return optionAdapter;
    }
}
