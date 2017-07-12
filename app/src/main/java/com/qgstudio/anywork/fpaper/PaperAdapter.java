package com.qgstudio.anywork.fpaper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Testpaper;

import java.util.List;


/**
 * Created by Yason on 2017/7/10.
 */

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.Holder> {

    private List<Testpaper> mPapers;

    public PaperAdapter(List<Testpaper> papers) {
        mPapers = papers;
    }

    interface OnItemClickListener {
        void onItemClick();
    }

    private OnItemClickListener mOnItemClickListener;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paper, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Testpaper paper = mPapers.get(position);
        //// TODO: 2017/7/10 绑定数据
    }

    @Override
    public int getItemCount() {
        return mPapers.size();
    }


    public void add(Testpaper paper) {
        mPapers.add(paper);
        notifyItemInserted(mPapers.size());
    }

    public void addAll(List<Testpaper> papers) {
        int start = mPapers.size() + 1;
        int count = papers.size();
        mPapers.addAll(papers);
        notifyItemRangeInserted(start, count);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    class Holder extends RecyclerView.ViewHolder {
        
        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick();
                    }
                }
            });
        }
    }

}
