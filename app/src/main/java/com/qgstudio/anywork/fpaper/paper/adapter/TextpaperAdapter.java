package com.qgstudio.anywork.fpaper.paper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Textpaper;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yason on 2017/4/4.
 */

public class TextpaperAdapter extends BaseAdapter<Textpaper,TextpaperAdapter.Holder> {

    public TextpaperAdapter(List<Textpaper> list) {
        super(list);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_textpaper_title) TextView title;
        @BindView(R.id.tv_textpaper_create) TextView create;
        @BindView(R.id.tv_textpaper_ending) TextView ending;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fpaper_textpaper, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        holder.title.setText(mList.get(position).getTextpaperTitle());
        holder.create.setText("开始："+sdf.format(mList.get(position).getCreateTime()));
        holder.ending.setText("结束："+sdf.format(mList.get(position).getEndingTime()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(
                            mList.get(position).getTextpaperId());
                }
            }
        });
    }

    @Override
    public void addData(List<Textpaper> list) {
        mList.addAll(list);
        Collections.sort(mList, new Comparator<Textpaper>() {
            @Override
            public int compare(Textpaper o1, Textpaper o2) {
                return o1.getCreateTime() - o2.getCreateTime() > 0 ? 1 : -1;
            }
        });
        notifyDataSetChanged();
    }
}
