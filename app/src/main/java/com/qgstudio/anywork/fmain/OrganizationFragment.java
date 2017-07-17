package com.qgstudio.anywork.fmain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.mvp.BaseFragment;

import java.util.ArrayList;
import java.util.List;

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
    public void onResume() {
        super.onResume();
        //// TODO: 2017/7/10 调用接口返回全部的组织数据
        switch (mType) {
            case TYPE_ALL:{
                //假数据
                List<Organization> list = new ArrayList<>();
                for (int i=0;i<10;i++) {
                    Organization o =new Organization();
                    o.setOrganizationName("C语言");
                    o.setTeacherName("梨廉洁");
                    o.setDescription("C语言是一门综合性学科，能很好考察学生计算机专业素养...");
                    list.add(o);
                }
                mOrganizationAdapter.addAll(list);
                break;
            }
            case TYPE_JOIN:{
                //假数据
                List<Organization> list = new ArrayList<>();
                for (int i=0;i<5;i++) {
                    Organization o =new Organization();
                    o.setOrganizationName("数据结构");
                    o.setTeacherName("皇飞虹");
                    o.setDescription("数据结构是一门综合性学科，能很好考察学生计算机专业素养...");
                    list.add(o);
                }
                mOrganizationAdapter.addAll(list);
                break;
            }
            default:{}
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
