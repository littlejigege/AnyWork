package com.qgstudio.anywork.fpaper.paper.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yason on 2017/4/12.
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public interface OnItemClickListener {
        void onItemClick(int textpaperId);
    }

    protected List<T> mList;
    protected OnItemClickListener mOnItemClickListener;

    public BaseAdapter(@Nullable List<T> list) {
        if (list != null) {
            mList = list;
        } else {
            mList = new ArrayList<>(0);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<T> list) {
        mList.addAll(list);
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
