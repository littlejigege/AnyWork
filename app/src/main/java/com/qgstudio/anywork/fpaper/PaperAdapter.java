package com.qgstudio.anywork.fpaper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.fexam.ExamActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


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
    public void onBindViewHolder(Holder holder, final int position) {
        Testpaper paper = mPapers.get(position);
        holder.tv_title.setText(paper.getTestpaperTitle());
        holder.tv_chapter.setText(paper.getChapterName());
        holder.tv_type.setText(paper.getTestpaperType() + "");
//        holder.tv_date.setText(mPapers.get(position).);

//        holder.civ

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExamActivity.startToActivity(v.getContext(), mPapers.get(position).getTestpaperId());
            }
        });

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

        @BindView(R.id.civ_paper)
        CircleImageView civ;

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
        }
    }

}
