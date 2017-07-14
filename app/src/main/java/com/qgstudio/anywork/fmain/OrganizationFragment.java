package com.qgstudio.anywork.fmain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.fmain.data.OrganizationRepository;
import com.qgstudio.anywork.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrganizationFragment extends MVPBaseFragment<OrganizationFragView, OrganizationRepository> implements OrganizationFragView {

    @BindView(R.id.recycler_all)
    RecyclerView mRecyclerView;

    public static final int TYPE_ALL = 0;
    public static final int TYPE_JOIN = 1;

    private int mType;

    private Unbinder mUnbinder;

    private OrganizationAdapter mOrganizationAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = (int) getArguments().get("TYPE");
    }

    public static OrganizationFragment newInstance(int type) {
        OrganizationFragment fragment = new OrganizationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getRootId() {
        return R.layout.fragment_organization;
    }

    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this, mRoot);

        mOrganizationAdapter = new OrganizationAdapter(new ArrayList<Organization>());
        mRecyclerView.setAdapter(mOrganizationAdapter);

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void loadData() {
        switch (mType) {
            case TYPE_ALL: {
                mPresenter.getAllOrganization();
                break;
            }
            case TYPE_JOIN: {
                mPresenter.getJoinOrganization();
                break;
            }
            default: {
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void addOrganization(Organization organization) {
        mOrganizationAdapter.add(organization);
    }

    @Override
    public void addOrganizations(List<Organization> organizations) {
        mOrganizationAdapter.addAll(organizations);
    }
}

