package com.qgstudio.anywork.fpaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.StartActivity;

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

    private int mOrganizationId;
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

        mTabLayout.setTabTextColors(getColor(R.color.dark_grey_status), getColor(R.color.dark_green_text));

        //获取组织id
        mOrganizationId = getIntent().getIntExtra("ORGANIZATION_ID", -1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //// TODO: 2017/7/12 获取试卷简介信息 
    }

    @OnClick(R.id.btn_back)
    public void back() {
        this.finish();
    }


    public static void startToActivity(Context context, int organizationId) {
        Intent intent = new Intent(context, PaperActivity.class);
        intent.putExtra("ORGANIZATION_ID", organizationId);
        context.startActivity(intent);
    }

}
