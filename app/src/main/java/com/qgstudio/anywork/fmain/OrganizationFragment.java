package com.qgstudio.anywork.fmain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.qgstudio.anywork.fpaper.PaperActivity;
import com.qgstudio.anywork.mvp.MVPBaseFragment;
import com.qgstudio.anywork.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrganizationFragment extends MVPBaseFragment<OrganizationFragView, OrganizationRepository> implements OrganizationFragView {

    @BindView(R.id.recycler_all)
    RecyclerView mRecyclerView;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

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

        mOrganizationAdapter = new OrganizationAdapter(mType, mActivity, new ArrayList<Organization>());
        mOrganizationAdapter.setJoinListener(new OrganizationAdapter.JoinListener() {
            @Override
            public void join(int id, String pass, int position) {
                mPresenter.joinOrganization(id, pass, position);
            }
        });
        mRecyclerView.setAdapter(mOrganizationAdapter);

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        refresh.setEnabled(false);
    }

    @Override
    public void loadData() {
        refresh.setRefreshing(true);
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
        refresh.setRefreshing(false);
    }

    @Override
    public void addOrganizations(List<Organization> organizations) {
        mOrganizationAdapter.addAll(organizations);
        refresh.setRefreshing(false);
    }

    @Override
    public void joinSuccess(int id, int positon) {
        ToastUtil.showToast("成功加入");
        mOrganizationAdapter.updatemOrganizations(positon);
        PaperActivity.startToActivity(mActivity, id);
    }

    @Override
    public void joinFail(String info) {
        ToastUtil.showToast(info);
    }
}

