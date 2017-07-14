package com.qgstudio.anywork.fpaper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.fexam.ExamActivity;
import com.qgstudio.anywork.ui.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Yason on 2017/7/10.
 */

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.Holder> {

    private List<Testpaper> mPapers;

    public PaperAdapter(List<Testpaper> papers) {
        mPapers = papers;
    }

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
        holder.tv_title.setText(mPapers.get(position).getTestpaperTitle());
        holder.tv_chapter.setText(mPapers.get(position).getChapterName());

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


    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.roundedImageView)
        RoundedImageView img;

        @BindView(R.id.textView5)
        TextView tv_title;

        @BindView(R.id.textView6)
        TextView tv_chapter;

        @BindView(R.id.textView7)
        TextView tv_type;

        @BindView(R.id.textView8)
        TextView tv_date;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    ExamActivity.startToActivity(v.getContext(), mPapers.get(pos).getTestpaperId());
                }
            });
        }
    }

}
