package com.qgstudio.anywork.fpaper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.fpaper.paper.PaperFragment;
import com.qgstudio.anywork.mvp.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yason on 2017/4/2.
 */

public class FPaperFragment extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tb_paper) Toolbar mToolbar;
//    @BindView(R.id.tab) TabLayout mTabLayout;
//    @BindView(R.id.vp_paper) ViewPager mViewPager;
//    @BindView(R.id.drawer) DrawerLayout mDrawerLayout;
//    @BindView(R.id.nav) NavigationView mNavigationView;

//    private FragAdapter mFragAdapter;

    public static FPaperFragment newInstance() {
        return new FPaperFragment();
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.fpaper_setting, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        List<Fragment> fragList = new ArrayList<>();
//        fragList.add(PaperFragment.newInstance(PaperFragment.FLAG_PAPER));
//        fragList.add(PaperFragment.newInstance(PaperFragment.FLAG_ORGANIZATION));
//
//        List<String> titleList = new ArrayList<>();
//        titleList.add("试题");
//        titleList.add("组织");
//
//        mFragAdapter = new FragAdapter(getChildFragmentManager(), fragList, titleList);
    }

    @Override
    public int getRootId() {
        return R.layout.fragment_fpaper_home;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, mRoot);

        mToolbar.setTitle("试卷");
        setHasOptionsMenu(true);
        ((AppCompatActivity) getParent()).setSupportActionBar(mToolbar);

//        mViewPager.setAdapter(mFragAdapter);

//        mTabLayout.setupWithViewPager(mViewPager);

        getChildFragmentManager().beginTransaction()
                .add(R.id.content_frame, PaperFragment.newInstance(PaperFragment.FLAG_PAPER)).commit();

//        ActionBarDrawerToggle mActionBarDrawerToggle =
//                new ActionBarDrawerToggle(getParent(), mDrawerLayout, mToolbar, R.string.open, R.string.close);
//        //设置出现 Toolbar 最左边的导航按钮
//        mActionBarDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
//
//        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        mDrawerLayout.closeDrawers();
        return true;
    }

}
