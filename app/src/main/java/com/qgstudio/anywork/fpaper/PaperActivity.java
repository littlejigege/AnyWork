package com.qgstudio.anywork.fpaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.qgstudio.anywork.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yason on 2017/4/2.
 */

public class PaperActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.tab)
    TabLayout mTabLayout;

    @BindView(R.id.pager)
    ViewPager mViewPager;

    private PaperFragAdapter mPaperFragAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPaperFragAdapter = new PaperFragAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPaperFragAdapter);
        mViewPager.setOffscreenPageLimit(mPaperFragAdapter.getCount());

        //自动和viewpager的title关联
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.setTabTextColors(getColor(R.color.status_dark), getColor(R.color.text_green));

    }

    @OnClick(R.id.btn_back)
    public void back() {
        this.finish();
    }


}
