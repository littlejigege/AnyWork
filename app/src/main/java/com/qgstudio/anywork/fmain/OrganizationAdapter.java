package com.qgstudio.anywork.fmain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.library.SuperTextView;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.fpaper.PaperActivity;

import java.util.List;


/**
 * Created by Yason on 2017/7/10.
 */

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.Holder> {

    private List<Organization> mOrganizations;

    public OrganizationAdapter(List<Organization> organizations) {
        mOrganizations = organizations;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_organization, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Organization organization = mOrganizations.get(position);
        //// TODO: 2017/7/10 绑定数据
        SuperTextView v = holder.stv;
        v.setLeftTopString(organization.getOrganizationName());
        v.setLeftBottomString(organization.getTeacherName());
        v.setLeftBottomString2(organization.getDescription());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaperActivity.startToActivity(v.getContext(), organization.getOrganizationId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrganizations.size();
    }


    public void add(Organization organization) {
        mOrganizations.add(organization);
        notifyItemInserted(mOrganizations.size());
    }

    public void addAll(List<Organization> organizations) {
        int start = mOrganizations.size() + 1;
        int count = organizations.size();
        mOrganizations.addAll(organizations);
        notifyItemRangeInserted(start, count);
    }

    class Holder extends RecyclerView.ViewHolder {

        SuperTextView stv ;

        public Holder(View itemView) {
            super(itemView);
            stv = (SuperTextView) itemView;
        }
    }

}
