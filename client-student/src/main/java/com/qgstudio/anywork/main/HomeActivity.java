package com.qgstudio.anywork.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.common.DialogManagerActivity;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.dialog.BaseDialog;
import com.qgstudio.anywork.enter.EnterActivity;
import com.qgstudio.anywork.user.UserActivity;
import com.qgstudio.anywork.utils.GlideUtil;
import com.qgstudio.anywork.utils.SessionMaintainUtil;
import com.qgstudio.anywork.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends DialogManagerActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "HomeActivity";
    public static final String ACTION = TAG + "$Receiver";//广播action

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.drawer) DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation) NavigationView mNavigationView;

    CircleImageView headIv;
    TextView name;
    TextView mail;

    private FragmentManager mFragmentManager;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        registerBroadcast();
    }

    @Override
    protected void onDestroy() {
        unregisterBroadcast();
        super.onDestroy();
    }

    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        mReceiver = new Receiver();
        registerReceiver(mReceiver, intentFilter);
    }

    private void unregisterBroadcast() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    private void initView() {
        ButterKnife.bind(this);

        View navHeaderView = mNavigationView.getHeaderView(0);
        headIv = (CircleImageView) navHeaderView.findViewById(R.id.civ_headIv);
        name = (TextView) navHeaderView.findViewById(R.id.tv_name);
        mail = (TextView) navHeaderView.findViewById(R.id.tv_mail);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        setDrawerInfo();

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.frame, OrganizationFragment.newInstance(OrganizationFragment.TYPE_JOIN))
                .commit();

    }

    private void setDrawerInfo() {
        User user = App.getInstance().getUser();
        GlideUtil.setPictureWithOutCache(headIv, user.getUserId(), R.drawable.ic_user_default);
        name.setText(user.getUserName());
        mail.setText("邮箱：" + user.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_searching:{
                SearchingActivity.start(this);
                return true;
            }
            default:{}
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_join:{//我的班级
                break;
            }
            case R.id.nav_inform:{//个人信息
                Intent intent = new Intent(this, UserActivity.class);
                startActivityForResult(intent, 0);
                break;
            }
            case R.id.nav_feedback:{//意见反馈
                // TODO: 2017/8/16 意见反馈
                ToastUtil.showToast("此功能暂未开放！");
                break;
            }
            case R.id.nav_exit: {//退出登入
                BaseDialog.Builder builder = new BaseDialog.Builder(this);
                BaseDialog baseDialog = builder.cancelTouchout(false)
                        .title("提示")
                        .content("确定要退出当前账号吗？")
                        .setNegativeListener("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //暂停定时任务
                                SessionMaintainUtil.stop();
                                //跳转切换帐号
                                EnterActivity.start(HomeActivity.this, EnterActivity.FLAG_SWITCH_USER);
                                finish();
                            }
                        })
                        .setPositiveListener("取消", null)
                        .build();
                baseDialog.show();
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
    public void onBackPressed() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        BaseDialog baseDialog = builder.cancelTouchout(false)
                .title("提示")
                .content("确定要退出当前账号吗？")
                .setNegativeListener("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HomeActivity.super.onBackPressed();
                    }
                })
                .setPositiveListener("取消", null)
                .build();
        baseDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0: {
                if (resultCode == RESULT_OK) {
                    setDrawerInfo();
                }
                break;
            }
            default:{}
        }
    }

    /**
     * 监听SearchingActivity页面的加入或退出通知
     * 并及时更新自己的页面
     */
    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            OrganizationFragment ofm = (OrganizationFragment) mFragmentManager.getFragments().get(0);
            ofm.loadData();
        }
    }

}
