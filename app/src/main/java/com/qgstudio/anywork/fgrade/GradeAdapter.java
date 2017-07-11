package com.qgstudio.anywork.fgrade;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenyi on 2017/7/10.
 */

public class GradeAdapter extends Adapter<RecyclerView.ViewHolder> {

    private final int ITEM_TYPE_TITLE = 1;
    private final int ITEM_TYPE_CONTENT = 2;

    private List<GradeInfo> mDatas;
    private Context mContext;

    public void setmDatas(List<GradeInfo> mDatas) {
        this.mDatas = mDatas;
    }

    public GradeAdapter(Context context) {
        super();
        mDatas = new ArrayList<>();
        mContext = context;
    }

    public GradeAdapter(List<GradeInfo> datas, Context context) {
        super();
        mDatas = datas;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (getDatasType(position) == GradeInfo.TYPE1) {
            return ITEM_TYPE_TITLE;
        } else {
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int viewId;
        if (viewType == ITEM_TYPE_TITLE) {
            viewId = R.layout.item_grade_title;
        } else {
            viewId = R.layout.item_grade_content;
        }
        View layout = LayoutInflater.from(mContext).inflate(viewId, parent, false);
        return new ItemHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GradeInfo gInfo = mDatas.get(position);
        if (getItemViewType(position) == ITEM_TYPE_TITLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((ItemHolder) holder).text.setLetterSpacing(0.2f);
            }
            ((ItemHolder) holder).text.setText(gInfo.getInfo());
        } else {
            ((ItemHolder) holder).text.setText(gInfo.getInfo());
            ((ItemHolder) holder).text.setBackground(ContextCompat.getDrawable(mContext,
                                                                        R.drawable.ic_fiber_green));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    int getDatasType(int position) {
        return mDatas.get(position).getType();
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text) TextView text;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
