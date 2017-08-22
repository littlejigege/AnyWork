package com.qgstudio.anywork.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qgstudio.anywork.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


/**
 * Created by Yason on 2017/7/10.
 */

public class ExamPagerView extends LinearLayout {

    private Context mContext;

    private Toolbar mToolbar;
    private TextView mTitleCenterTV;
    private ImageView mTitleRightIcon;
    private ViewPager mViewPager;
//    private RecyclerView mRecycler;
    private LinearLayout mBottomLinear;
    private Button mLeftBottomBtn;
    private Button mRightBottomBtn;

    private String mTitleCenterTextString;
    private int mTitleCenterTextColor;
    private int mTitleRightIconResId;
    private int mTitleBackgroundColor;
    private String mLeftBottomTextString;
    private int mLeftBottomTextColor;
    private int mLeftBottomBackgroundColor;
    private float mLeftBottomAlpha;
    private String mRightBottomTextString;
    private int mRightBottomTextColor;
    private int mRightBottomBackgroundColor;
    private float mRightBottomAlpha;

    private int mDefaultTextColor;
    private int mDefaultTitleBackgroundColor;
    private int mDefaultBottomBackgroundColor;

    @IdRes
    int ID_VIEWPAGER = 1000;


//    private OnTitleRightIconClickListener mOnTitleRightIconClickListener;
//    private OnLeftBottomBtnClickListener mOnLeftBottomBtnClickListener;
//    private OnRightBottomBtnClickListener mOnRightBottomBtnClickListener;
//
//    public interface OnTitleRightIconClickListener {
//        void onTitleRightIconClick ();
//    }
//
//    public interface OnLeftBottomBtnClickListener {
//        void onLeftBottomBtnClick ();
//    }
//
//    public interface OnRightBottomBtnClickListener {
//        void onRightBottomBtnClick ();
//    }
//
//    public void setOnTitleRightIconClick(OnTitleRightIconClickListener onTitleRightIconClick) {
//        mOnTitleRightIconClickListener = onTitleRightIconClick;
//    }
//
//    public void setOnLeftBottomBtnClickListener(OnLeftBottomBtnClickListener onLeftBottomBtnClickListener) {
//        mOnLeftBottomBtnClickListener = onLeftBottomBtnClickListener;
//    }
//
//    public void setOnRightBottomBtnClickListener(OnRightBottomBtnClickListener onRightBottomBtnClickListener) {
//        mOnRightBottomBtnClickListener = onRightBottomBtnClickListener;
//    }

    public void setViewPagerNextItem() {
        int cp = mViewPager.getCurrentItem();
        if (cp < mViewPager.getAdapter().getCount()) {
            mViewPager.setCurrentItem(cp + 1);
        }
    }

    public void setViewPagerLastItem() {
        int cp = mViewPager.getCurrentItem();
        if (cp > 0) {
            mViewPager.setCurrentItem(cp - 1);
        }
    }

    public void setTitleCenterTextString(String text) {
        if (mTitleCenterTV != null) {
            mTitleCenterTV.setText(text);
        }
    }

    public void setViewPagerAdapter(PagerAdapter pagerAdapter) {
        mViewPager.setAdapter(pagerAdapter);
//        mViewPager.setOffscreenPageLimit(fragmentPagerAdapter.getCount());
    }

    public void setViewPagerListener(ViewPager.OnPageChangeListener onPageChangeLitener) {
        mViewPager.setOnPageChangeListener(onPageChangeLitener);
    }

    public ExamPagerView(Context context) {
        super(context);
    }

    public ExamPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setDefault();
        getAttr(attrs);
        //位置
        ViewGroup.LayoutParams params = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
        this.setLayoutParams(params);
        //性质
        this.setOrientation(VERTICAL);
        initLayout();
    }

    private void setDefault() {
        TypedValue value = new TypedValue();

        mContext.getTheme().resolveAttribute(R.attr.colorAccent,value,true);
        mDefaultTextColor = value.data;

        mContext.getTheme().resolveAttribute(R.attr.colorPrimaryDark,value,true);
        mDefaultTitleBackgroundColor = value.data;

        mContext.getTheme().resolveAttribute(R.attr.colorPrimary,value,true);
        mDefaultBottomBackgroundColor = value.data;

    }

    private void getAttr(AttributeSet attrs) {
        //获取属性，没有则用默认值
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.ExamPagerView);
        try {
            mTitleCenterTextString = a.getString(R.styleable.ExamPagerView_sTitleCenterTextString);
            mTitleCenterTextColor = a.getColor(R.styleable.ExamPagerView_sTitleCenterTextColor, mDefaultTextColor);
            mTitleRightIconResId = a.getResourceId(R.styleable.ExamPagerView_sTitleRightIconResId, -1);
            mTitleBackgroundColor = a.getColor(R.styleable.ExamPagerView_sTitleBackgroundColor, mDefaultTitleBackgroundColor);
            mLeftBottomTextString = a.getString(R.styleable.ExamPagerView_sLeftBottomTextString);
            mLeftBottomTextColor = a.getColor(R.styleable.ExamPagerView_sLeftBottomTextColor,mDefaultTextColor);
            mLeftBottomBackgroundColor = a.getColor(R.styleable.ExamPagerView_sLeftBottomBackgroundColor, mDefaultBottomBackgroundColor);
            mLeftBottomAlpha = a.getFloat(R.styleable.ExamPagerView_sLeftBottomAlpha,0.8f);
            mRightBottomTextString = a.getString(R.styleable.ExamPagerView_sRightBottomTextString);
            mRightBottomTextColor = a.getColor(R.styleable.ExamPagerView_sRightBottomTextColor, mDefaultTextColor);
            mRightBottomBackgroundColor = a.getColor(R.styleable.ExamPagerView_sRightBottomBackgroundColor, mDefaultBottomBackgroundColor);
            mRightBottomAlpha = a.getFloat(R.styleable.ExamPagerView_sRightBottomAlpha, 0.8f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }
    }

    private void initLayout() {
        initToolbar();
        initViewPager();
        initBottomLinear();
    }

    private void initViewPager() {
        mViewPager = new ViewPager(mContext);

        LayoutParams params = new LayoutParams(MATCH_PARENT, 0);
        params.weight = 1;
        mViewPager.setLayoutParams(params);

        mViewPager.setId(ID_VIEWPAGER);

        addView(mViewPager);

    }

    private void initBottomLinear() {
        mBottomLinear = new LinearLayout(mContext);
        //先位置，后性质
        LayoutParams params = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        mBottomLinear.setLayoutParams(params);
        mBottomLinear.setOrientation(HORIZONTAL);

        mLeftBottomBtn = new Button(mContext);
        LinearLayout.LayoutParams left_params = new LayoutParams(0,WRAP_CONTENT);
        left_params.weight = 1;
        mLeftBottomBtn.setLayoutParams(left_params);
        mLeftBottomBtn.setAlpha(mLeftBottomAlpha);
        mLeftBottomBtn.setBackgroundColor(mLeftBottomBackgroundColor);
        mLeftBottomBtn.setText(mLeftBottomTextString);
        mLeftBottomBtn.setTextColor(mLeftBottomTextColor);
        mLeftBottomBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewPagerLastItem();
            }
        });
        mBottomLinear.addView(mLeftBottomBtn);

        mRightBottomBtn = new Button(mContext);
        LinearLayout.LayoutParams right_params = new LayoutParams(0,WRAP_CONTENT);
        right_params.weight = 1;
        mRightBottomBtn.setLayoutParams(right_params);
        mRightBottomBtn.setAlpha(mRightBottomAlpha);
        mRightBottomBtn.setBackgroundColor(mRightBottomBackgroundColor);
        mRightBottomBtn.setText(mRightBottomTextString);
        mRightBottomBtn.setTextColor(mRightBottomTextColor);
        mRightBottomBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewPagerNextItem();
            }
        });
        mBottomLinear.addView(mRightBottomBtn);

        this.addView(mBottomLinear);
    }

    private void initToolbar() {
        //1.创建view
        mToolbar = new Toolbar(mContext);

        //2.设置view默认属性
        LayoutParams params = new LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.gravity = Gravity.TOP;
        mToolbar.setLayoutParams(params);

        if (mTitleCenterTextString != null) {
            mTitleCenterTV = new TextView(mContext);
            //位置属性
            Toolbar.LayoutParams tv_params = new Toolbar.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            tv_params.gravity = Gravity.CENTER;
            mTitleCenterTV.setLayoutParams(tv_params);
            //性质属性
            mTitleCenterTV.setText(mTitleCenterTextString);
            mTitleCenterTV.setTextColor(mTitleCenterTextColor);
            mToolbar.addView(mTitleCenterTV);
        }
        if (mTitleRightIconResId != -1) {
            mTitleRightIcon = new ImageView(mContext);
            Toolbar.LayoutParams icon_params = new Toolbar.LayoutParams(dp2px(mContext, 20.0f), dp2px(mContext, 20.0f));
            icon_params.gravity = Gravity.RIGHT;
            icon_params.rightMargin = dp2px(mContext, 16.0f);
            mTitleRightIcon.setLayoutParams(icon_params);
            mTitleRightIcon.setImageResource(mTitleRightIconResId);
            mTitleRightIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            mTitleRightIcon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof Activity) {
                        Activity activity = (Activity) mContext;
                        activity.onBackPressed();
                    }
                }
            });
            mToolbar.addView(mTitleRightIcon);
        }

        mToolbar.setBackgroundColor(mTitleBackgroundColor);

        //3.将view添加到容器
        this.addView(mToolbar);

    }

    private int dp2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    private int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

}
