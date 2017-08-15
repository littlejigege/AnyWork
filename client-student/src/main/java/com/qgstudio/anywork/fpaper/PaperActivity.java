package com.qgstudio.anywork.fpaper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.StartActivity;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.dialog.BaseDialog;
import com.qgstudio.anywork.fmain.OrganizationFragView;
import com.qgstudio.anywork.fmain.data.OrganizationRepository;
import com.qgstudio.anywork.mvp.MVPBaseActivity;
import com.qgstudio.anywork.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 考试和练习试卷列表的容器
 * @author Yason 2017/4/2.
 */

public class PaperActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tab) TabLayout mTabLayout;
    @BindView(R.id.pager) ViewPager mViewPager;
//    @BindView(R.id.fab) FloatingActionButton fab;

    private int mOrganizationId;
    private PaperFragAdapter mPaperFragAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        mOrganizationId = getIntent().getIntExtra("ORGANIZATION_ID", -1);
        initView();
    }

    @OnClick(R.id.btn_back)
    public void back() {
        finishAty();
    }

    public static void start(Context context, int organizationId) {
        Intent intent = new Intent(context, PaperActivity.class);
        intent.putExtra("ORGANIZATION_ID", organizationId);
        context.startActivity(intent);
    }

    private void initView() {
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPaperFragAdapter = new PaperFragAdapter(getSupportFragmentManager(), mOrganizationId);

        mViewPager.setAdapter(mPaperFragAdapter);
        mViewPager.setOffscreenPageLimit(mPaperFragAdapter.getCount());

        //自动和viewpager的title关联
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabTextColors(ResourcesCompat.getColor(getResources(), R.color.dark_grey_status, null),
                ResourcesCompat.getColor(getResources(), R.color.dark_green_text, null));

        //        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                View view1 = LayoutInflater.from(PaperActivity.this).inflate(R.layout.dialog_point, null);
//                TextView tv = (TextView) view1.findViewById(R.id.point);
//                tv.setText("确认退出当前的班级吗？");
//                BaseDialog.Builder builder = new BaseDialog.Builder(PaperActivity.this);
//                builder.cancelTouchout(false)
//                        .title("提示")
//                        .view(view1)
//                        .setNegativeListener("确认", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                mPresenter.leaveOrganization(mOrganizationId);
//                            }
//                        })
//                        .setPositiveListener("取消", null)
//                        .build()
//                        .show();
//            }
//        });
    }

    private void finishAty(){
        this.finish();
    }

}
