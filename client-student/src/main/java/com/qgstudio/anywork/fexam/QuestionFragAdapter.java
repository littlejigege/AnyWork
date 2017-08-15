package com.qgstudio.anywork.fexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public QuestionFragAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public void addAll(List<Fragment> fragments) {
        mFragments.addAll(fragments);
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return mFragments.size();
    }

}
