package com.qgstudio.anywork.fuser;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qgstudio.anywork.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenyi on 2017/4/12.
 */

public class InfoItem extends LinearLayout {

    @BindView(R.id.img) ImageView img;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.info) TextView info;


    public InfoItem(Context context) {
        super(context);
        setUp(context);
    }

    public InfoItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUp(context);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.InfoItem);
        final int N = ta.getIndexCount();
        for (int i = 0; i<N; i++) {
            int arr = ta.getIndex(i);
            switch (arr) {
                case R.styleable.InfoItem_titlePic:
                    img.setImageResource(ta.getResourceId(arr, 0));
                    break;
                case R.styleable.InfoItem_title:
                    title.setText(ta.getString(arr));
                    break;
                case R.styleable.InfoItem_info:
                    info.setText(ta.getString(arr));
                    break;
            }
        }
        ta.recycle();
    }


    private void setUp(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_info, this);
        ButterKnife.bind(this, view);
    }

    public void setInfo(String str) {
        info.setText(str);
    }
}
