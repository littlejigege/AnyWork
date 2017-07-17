package com.qgstudio.anywork.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义 BaseFragment Template
 * Created by Yason on 2017/4/3.
 */

public abstract class BaseFragment extends Fragment {

    protected View mRoot;

    protected Activity mActivity;

    public abstract int getRootId();
    public abstract void initView();
    public abstract void loadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(getRootId(), container, false);
        initView();
        loadData();
        return mRoot;
    }

}
