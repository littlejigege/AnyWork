package com.qgstudio.anywork.fexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.qgstudio.anywork.data.model.Question;

import java.util.List;

public class QuestionsAdapter extends FragmentStatePagerAdapter {

    private List<Question> mQuestions;
    private List<Fragment> mFragments;

    public QuestionsAdapter(FragmentManager fm, List<Question> questions) {
        super(fm);
        mQuestions = questions;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments.get(position) != null) {
            Fragment fragment = QuestionFragment.newInstance(mQuestions.get(position));
            mFragments.add(fragment);
        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
