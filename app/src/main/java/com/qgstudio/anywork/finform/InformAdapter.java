package com.qgstudio.anywork.finform;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Inform;
import com.qgstudio.anywork.fpaper.paper.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by chenyi on 2016/10/8.
 */

public class InformAdapter extends BaseAdapter<Inform, InformAdapter.ListHolder> {

    public InformAdapter(@Nullable List<Inform> list) {
        super(list);
    }


    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inform, parent, false);
        return new ListHolder(layout);
    }

    //绑定View
    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        Inform inform = mList.get(position);
        holder.title.setText(inform.getInformTitle());
        holder.note.setText(inform.getMark());
    }

    //在这里面加载ListView中的每个item的布局
    class ListHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title) TextView title;
        @BindView(R.id.note) TextView note;

        ListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
//        Log.e(TAG, super.getItemCount() + "");
        return super.getItemCount();
    }
}
