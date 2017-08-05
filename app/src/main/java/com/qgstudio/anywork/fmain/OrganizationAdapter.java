package com.qgstudio.anywork.fmain;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.dialog.BaseDialog;
import com.qgstudio.anywork.fexam.QuestionFragment;
import com.qgstudio.anywork.fpaper.PaperActivity;
import com.qgstudio.anywork.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.qgstudio.anywork.data.ApiStores.API_DEFAULT_URL;
import static com.qgstudio.anywork.fmain.OrganizationFragment.TYPE_ALL;


/**
 * Created by Yason on 2017/7/10.
 */

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.Holder> {

    private int mType;

    private Context mContext;

    private List<Organization> mOrganizations;

    private BaseDialog baseDialog;

    public void updatemOrganizations(int position) {
        baseDialog.cancel();
        mOrganizations.get(position).setIsJoin(1);
        notifyDataSetChanged();
    }

    public OrganizationAdapter(int type, Context context, List<Organization> organizations) {
        mType = type;
        mContext = context;
        mOrganizations = organizations;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.item_organization, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final Organization organization = mOrganizations.get(position);
        holder.tv_name.setText(organization.getOrganizationName());
        holder.tv_teacher.setText(organization.getTeacherName());
        holder.tv_description.setText(organization.getDescription());

        final String url = API_DEFAULT_URL + "picture/organization/" + organization.getOrganizationId() + ".jpg";
        Glide.with(mContext).load(url).into(holder.civ);

        if (mType == TYPE_ALL) {
            holder.tv_status.setText(organization.getIsJoin() == 1 ? "已加入" : "未加入");
            holder.tv_status.setTextColor(organization.getIsJoin() == 1 ?
                    ContextCompat.getColor(mContext,R.color.status_join_true_text) : ContextCompat.getColor(mContext,R.color.status_join_false_text));
            holder.tv_status.setBackgroundColor(organization.getIsJoin() == 1 ?
                    ContextCompat.getColor(mContext, R.color.status_join_true_bg) : ContextCompat.getColor(mContext, R.color.status_join_false_bg));
        }else {

            holder.tv_status.setVisibility(View.GONE);

        }

        if (mType == TYPE_ALL && organization.getIsJoin() != 1) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_origanization, null);
                    CircleImageView imageView = (CircleImageView) view.findViewById(R.id.cimg);
                    TextView textView = (TextView) view.findViewById(R.id.text);
                    final EditText et = (EditText) view.findViewById(R.id.et);
                    Glide.with(mContext).load(url).into(imageView);
                    textView.setText(organization.getTeacherName()+" "+organization.getDescription());

                    baseDialog = new BaseDialog.Builder(mContext)
                            .title(organization.getOrganizationName())
                            .view(view)
                            .setPositiveListener("加入", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    joinListener.join(organization.getOrganizationId(),
                                            et.getText().toString(), position);
                                }
                            })
                            .build();
                    baseDialog.show();
                }
            });
        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PaperActivity.startToActivity(v.getContext(), mOrganizations.get(position).getOrganizationId());
                }
            });
        }

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

        @BindView(R.id.civ_organization) CircleImageView civ;
        @BindView(R.id.tv_name) TextView tv_name;
        @BindView(R.id.tv_teacher) TextView tv_teacher;
        @BindView(R.id.tv_description) TextView tv_description;
        @BindView(R.id.tv_status) TextView tv_status;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    interface JoinListener {
        void join(int id, String pass, int position);
    }

    private JoinListener joinListener;

    public void setJoinListener(JoinListener joinListener) {
        this.joinListener = joinListener;
    }
}
