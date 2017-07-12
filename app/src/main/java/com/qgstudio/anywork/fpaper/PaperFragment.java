package com.qgstudio.anywork.fpaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.mvp.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PaperFragment extends BaseFragment implements PaperAdapter.OnItemClickListener{

    @BindView(R.id.recycler_paper)
    RecyclerView mRecyclerView;

    public static final int TYPE_PRACTICE = 0;
    public static final int TYPE_EXMINATION = 1;

    private int mType;

    private Unbinder mUnbinder;

    private PaperAdapter mPaperAdapter;
    private LinearLayoutManager mLinearLayoutManager;

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
        return R.layout.fragment_paper;
    }

    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this, mRoot);

        mPaperAdapter = new PaperAdapter(new ArrayList<Testpaper>());
        mRecyclerView.setAdapter(mPaperAdapter);

        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mPaperAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (mType) {
            case TYPE_EXMINATION:{
                //// TODO: 2017/7/10 调用接口返回试卷信息,根据mType调用不同
                //假数据
                List<Testpaper> list = new ArrayList<>();
                for (int i=0;i<5;i++) {
                    Testpaper t =new Testpaper();
                    t.setTestpaperTitle("第一章");
                    t.setTestpaperType(0);
                    t.setChapterId(1);
                    list.add(t);
                }
                break;
            }
            case TYPE_PRACTICE:{
                //假数据
                List<Testpaper> list = new ArrayList<>();
                for (int i=0;i<5;i++) {
                    Testpaper t =new Testpaper();
                    t.setTestpaperTitle("第一章");
                    t.setTestpaperType(2);
                    t.setChapterId(1);
                    list.add(t);
                }
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

    @Override
    public void onItemClick() {
        // TODO: 2017/7/11 跳转到考试页面
    }

}
