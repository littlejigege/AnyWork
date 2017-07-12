package com.qgstudio.anywork.fmain;

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

    private Map<String,Fragment> mFragmentMap;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        mFragmentMap = new HashMap<>();
        mFragmentManager = getSupportFragmentManager();

        //添加fragment
        Fragment fragAll = OrganizationFragment.newInstance(OrganizationFragment.TYPE_ALL);
        Fragment fragJoin = OrganizationFragment.newInstance(OrganizationFragment.TYPE_JOIN);

        mFragmentMap.put("ALL", fragAll);
        mFragmentMap.put("JOIN", fragJoin);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.frame, mFragmentMap.get("ALL"));
        transaction.add(R.id.frame, mFragmentMap.get("JOIN"));
        transaction.hide(mFragmentMap.get("JOIN"));
        transaction.show(mFragmentMap.get("ALL"));

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
    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("是否退出登录");
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_all:{
                showFragment("ALL");
                break;
            }
            case R.id.nav_join:{
                showFragment("JOIN");
                break;
            }
            case R.id.nav_inform:{
                // TODO: 2017/7/12  启动通知aty
                break;
            }
            case R.id.nav_exit:{
                // TODO: 2017/7/12  弹出退出窗口
                break;
            }
            default:{
                return false;
            }
        }
        mDrawerLayout.closeDrawers();
        return true;
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
