package com.qgstudio.anywork.fpaper;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yason on 2017/4/3.
 */

public class FragAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> mFragList;
    private List<String> mTitleList;

    public FragAdapter(FragmentManager fm,
                       @NonNull List<Fragment> fragList, @NonNull List<String> titleList) {
        super(fm);
        mFragList = fragList;
        mTitleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragList.get(position);
    }

    @Override
    public int getCount() {
        return mFragList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
