package com.qgstudio.anywork.fpaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.fpaper.data.PaperPresenter;
import com.qgstudio.anywork.fpaper.data.PaperRepository;
import com.qgstudio.anywork.mvp.BaseFragment;
import com.qgstudio.anywork.mvp.MVPBaseFragment;
import com.qgstudio.anywork.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 显示考试或练习(单个)列表
 * Created by Yason on 2017/4/2.
 */

public class PaperFragment extends MVPBaseFragment<PaperFragView, PaperRepository> implements PaperFragView {

    @BindView(R.id.recycler_paper) RecyclerView mRecyclerView;

    private PaperAdapter mPaperAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private int mType;//fragment类型，考试或练习
    private int mOrganizationId;

    public static final int TYPE_PRACTICE = 0;
    public static final int TYPE_EXMINATION = 1;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("TYPE");
        mOrganizationId = getArguments().getInt("ORGANIZATION_ID");
    }

    public static PaperFragment newInstance(int type, int organizationId) {
        PaperFragment fragment = new PaperFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("TYPE", type);
        bundle.putInt("ORGANIZATION_ID", organizationId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getRootId() {
        return R.layout.fragment_paper;
    }

    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this, mRoot);

        mPaperAdapter = new PaperAdapter(mActivity,new ArrayList<Testpaper>());
        mRecyclerView.setAdapter(mPaperAdapter);

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void loadData() {
        switch (mType) {
            case TYPE_EXMINATION: {
                mPresenter.getExaminationPaper(mOrganizationId);
                break;
            }
            case TYPE_PRACTICE: {
                mPresenter.getPracticePaper(mOrganizationId);
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
    public void addPracticePapers(List<Testpaper> testpapers) {
        addAll(testpapers);
    }

    @Override
    public void addExaminationPapers(List<Testpaper> testpapers) {
        addAll(testpapers);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String s) {
        ToastUtil.showToast(s);
    }

    private void addAll(List<Testpaper> papers){
        mPaperAdapter.addAll(papers);
    }

}
