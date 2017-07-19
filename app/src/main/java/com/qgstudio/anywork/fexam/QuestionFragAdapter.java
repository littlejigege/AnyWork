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

public class QuestionFragAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private FragmentManager mFragmentManager;

    public QuestionFragAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
        mFragmentManager = fm;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = mFragments.get(position);
        mFragmentManager.beginTransaction().hide(fragment).commit();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public void add(Fragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
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
