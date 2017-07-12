package com.qgstudio.anywork.fmain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.fpaper.PaperFragment;
import com.qgstudio.anywork.mvp.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrganizationFragment extends BaseFragment {

    @BindView(R.id.recycler_all)
    RecyclerView mRecyclerView;

    public static final int TYPE_ALL = 0;
    public static final int TYPE_JOIN = 1;

    private int mType;

    private Unbinder mUnbinder;

    private OrganizationAdapter mOrganizationAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    public static OrganizationFragment newInstance() {
        OrganizationFragment fragment = new OrganizationFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = (int) getArguments().get("TYPE");
    }

    public static PaperFragment newInstance(int type) {
        PaperFragment fragment = new PaperFragment();
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
    public void onResume() {
        super.onResume();
        //// TODO: 2017/7/10 调用接口返回全部的组织数据
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
