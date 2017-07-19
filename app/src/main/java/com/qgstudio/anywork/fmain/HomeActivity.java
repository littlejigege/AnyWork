package com.qgstudio.anywork.fmain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.dialog.BaseDialog;
import com.qgstudio.anywork.fuser.UserActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation)
    NavigationView mNavigationView;

    private Map<String, Fragment> mFragmentMap;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //1.View初始化
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        mFragmentMap = new HashMap<>();
        mFragmentManager = getSupportFragmentManager();

        //2.添加fragment
        Fragment fragAll = OrganizationFragment.newInstance(OrganizationFragment.TYPE_ALL);
        Fragment fragJoin = OrganizationFragment.newInstance(OrganizationFragment.TYPE_JOIN);

        mFragmentMap.put("ALL", fragAll);
        mFragmentMap.put("JOIN", fragJoin);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.frame, mFragmentMap.get("ALL"));
        transaction.add(R.id.frame, mFragmentMap.get("JOIN"));
        transaction.hide(mFragmentMap.get("ALL"));
        transaction.show(mFragmentMap.get("JOIN"));

        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_all: {
                showFragment("ALL");
                break;
            }
            case R.id.nav_join: {
                showFragment("JOIN");
                break;
            }
            case R.id.nav_inform:{
                Intent intent = new Intent(this, UserActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case R.id.nav_exit: {
                // TODO: 2017/7/12  弹出退出窗口
                break;
            }
            default: {
                return false;
            }
        }
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    // TODO: 修改主界面的个人信息 2017/7/13
                }
                break;
        }
    }

    private void showFragment(String type) {
        //1.隐藏全部fragment
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        for (String key : mFragmentMap.keySet()) {
            transaction.hide(mFragmentMap.get(key));
        }
//        //2.显示所需fragment
//        if (!mFragmentMap.containsKey(type)) {
//            mFragmentMap.put(type, OrganizationFragment.newInstance());
//            transaction.add(R.id.frame, mFragmentMap.get(type));
//        }
        transaction.show(mFragmentMap.get(type));
        transaction.commit();
    }
}
