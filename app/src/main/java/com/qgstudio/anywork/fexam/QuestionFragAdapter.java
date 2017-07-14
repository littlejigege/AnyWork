package com.qgstudio.anywork.fexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragAdapter extends FragmentStatePagerAdapter {

    private List<Question> mQuestions;
    private List<Fragment> mFragments;

    public QuestionFragAdapter(FragmentManager fm, List<Question> questions) {
        super(fm);
        mQuestions = questions;
        mFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments.get(position) != null) {
            Fragment fragment = QuestionFragment.newInstance(mQuestions.get(position));
            mFragments.add(fragment);
        }
        return mFragments.get(position);
    }

    public void add(Question question) {
        mQuestions.add(question);
        notifyDataSetChanged();
    }

    public void addAll(List<Question> organizations) {
        mQuestions.addAll(organizations);
        notifyDataSetChanged();
    }
    
    @Override
    public int getCount() {
        return mFragments.size();
    }

}
