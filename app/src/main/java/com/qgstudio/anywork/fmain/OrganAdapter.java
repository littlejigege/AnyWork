package com.qgstudio.anywork.fmain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgstudio.anywork.R;

import java.util.List;


/**
 * Created by Yason on 2017/7/10.
 */

public class OrganAdapter extends RecyclerView.Adapter<OrganAdapter.Holder> {

    private List<Organ> mOrgans;

    public OrganAdapter(List<Organ> organs) {
        mOrgans = organs;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_organ, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Organ organ = mOrgans.get(position);
        //// TODO: 2017/7/10 绑定数据

    }

    @Override
    public int getItemCount() {
        return mOrgans.size();
    }


    public void add(Organ group) {
        mOrgans.add(group);
        notifyItemInserted(mOrgans.size());
    }

    public void addAll(List<Organ> groups) {
        int start = mOrgans.size() + 1;
        int count = groups.size();
        mOrgans.addAll(groups);
        notifyItemRangeInserted(start, count);
    }

    class Holder extends RecyclerView.ViewHolder {



        public Holder(View itemView) {
            super(itemView);
        }
    }

}
