package com.qgstudio.anywork.fpaper.paper;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Textpaper;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.fpaper.paper.adapter.BaseAdapter;
import com.qgstudio.anywork.fpaper.paper.adapter.OrganizationAdapter;
import com.qgstudio.anywork.fpaper.paper.adapter.TextpaperAdapter;
import com.qgstudio.anywork.fexam.ExamActivity;
import com.qgstudio.anywork.mvp.MVPBaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yason on 2017/4/3.
 */

public class PaperFragment extends MVPBaseFragment<PaperContract.View, PaperPresenter> implements PaperContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String ARGS_FLAG = "FLAG";
    public static final int FLAG_PAPER = 0;
    public static final int FLAG_ORGANIZATION = 1;

    @BindView(R.id.refresh) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler) RecyclerView mRecyclerView;

    private BaseAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int mFlag;

//    public boolean isVisible = false;
    public boolean isFirstLoad = true;
//    public boolean isInitView = false;

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            isVisible = true;
//            lazyLoad();
//        } else {
//            isVisible = false;
//        }
//    }

//    //懒加载数据
//    private void lazyLoad() {
//        if (isVisible && isFirstLoad && isInitView) {
//            try {
//                initData();
//                isFirstLoad = false;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }


    public static PaperFragment newInstance(@NonNull int flag) {
        Bundle args = new Bundle();
        args.putInt(ARGS_FLAG, flag);
        PaperFragment fragment = new PaperFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootId() {
        return R.layout.fragment_fpaper_paper;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, mRoot);

        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(
                getParent(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeRefreshLayout.setOnRefreshListener(this);

        isFirstLoad = true;
//        isInitView = true;
//        lazyLoad();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        mFlag = args.getInt(ARGS_FLAG);
        switch (mFlag) {
            case FLAG_PAPER:
                mAdapter = new TextpaperAdapter(null);
                mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(int textpaperId) {
                        mPresenter.startPaperTesting(textpaperId);
                    }
                });
                break;
            case FLAG_ORGANIZATION:
                mAdapter = new OrganizationAdapter(null);
                break;
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            initData();
            isFirstLoad = false;
        }
    }

    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    public void initData() {
        switch (mFlag) {
            case FLAG_PAPER:
                User user = ((App) (getParent().getApplication())).getUser();
                mPresenter.getAllTextpaperList(user);
                break;
            case FLAG_ORGANIZATION:
                mPresenter.getOrganizationList();
                break;
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showThrowable(Throwable t) {

    }

    @Override
    public void showOrganizationList(List<Organization> organizationList) {
        mAdapter.addData(organizationList);
    }

    @Override
    public void showTextpaperList(List<Textpaper> textpaperList) {
        mAdapter.addData(textpaperList);
    }

    @Override
    public void showPaperTesting(int textpaperId) {
        ExamActivity.startToActivity(getParent(),textpaperId);
    }



}
