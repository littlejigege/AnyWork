package com.qgstudio.anywork.fpaper.paper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;

import java.util.List;

/**
 * Created by Yason on 2017/4/4.
 */

public class OrganizationAdapter extends BaseAdapter<Organization,OrganizationAdapter.Holder> {

    public OrganizationAdapter(List<Organization> list) {
        super(list);
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fpaper_textpaper, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
    }

}
