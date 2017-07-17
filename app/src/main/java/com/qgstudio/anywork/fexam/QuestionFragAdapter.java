package com.qgstudio.anywork.fexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.ListView;

import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public QuestionFragAdapter(FragmentManager fm, List<Question> questions) {
        super(fm);
        mFragments = new ArrayList<>();
        addAll(questions);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public void add(Question question) {
        mFragments.add(QuestionFragment.newInstance(question));
        notifyDataSetChanged();
    }

    public void addAll(List<Question> questions) {
        for (Question question : questions) {
            mFragments.add(QuestionFragment.newInstance(question));
        }
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return mFragments.size();
    }

}
