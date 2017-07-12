//package com.qgstudio.anywork.finform;
//
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.widget.Toast;
//
//import com.qgstudio.anywork.App;
//import com.qgstudio.anywork.R;
//import com.qgstudio.anywork.fpaper.paper.adapter.BaseAdapter;
//import com.qgstudio.anywork.mvp.MVPBaseFragment;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
///**
// * MVPPlugin
// *  邮箱 784787081@qq.com
// */
//
//public class InformFragment extends MVPBaseFragment<InformContract.View, InformPresenter> implements InformContract.View, SwipeRefreshLayout.OnRefreshListener {
//
//    private boolean isFirstLoad = true;
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.recycler)
//    RecyclerView recyclerView;
//    @BindView(R.id.refresh)
//    SwipeRefreshLayout mSwipeRefreshLayout;
//
//    private BaseAdapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//
//    public static InformFragment newInstance() {
//        //通过 newInstance 保证 Fragment 不被重复构造，造成 fragment 重叠
//        return new InformFragment();
//    }
//
//    public InformFragment() {
//        //防止无参构造器被外部调用
//    }
//
//    @Override
//    public int getRootId() {
//        return R.layout.fragment_inform;
//    }
//
//    @Override
//    public void initView() {
//        ButterKnife.bind(this, mRoot);
//        isFirstLoad = true;
//
//        toolbar.setTitle("公告");
//
//        mLayoutManager = new LinearLayoutManager(
//                mActivity, LinearLayoutManager.VERTICAL, false);
//
//        recyclerView.setAdapter(mAdapter);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mAdapter = new InformAdapter(null);
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (isFirstLoad) {
//            initData();
//            isFirstLoad = false;
//        }
//    }
//
//    private void initData() {
//        User user = ((App) (getParent().getApplication())).getUser();
//        mPresenter.getAllInformList(user);
//    }
//
//
//    @Override
//    public void onRefresh() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        }, 1000);
//        mAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void showInformList(List<Inform> dataList) {
//        mAdapter.addData(dataList);
//    }
//
//    @Override
//    public void showError(String s) {
//        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void showSuccess() {
//        Toast.makeText(mActivity, "获取信息成功", Toast.LENGTH_SHORT).show();
//    }
//}
