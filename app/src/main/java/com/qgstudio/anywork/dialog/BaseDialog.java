package com.qgstudio.anywork.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.utils.DesityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基础的 Dialog 用于显示自定义自己的 Dialog
 * Created by chenyi on 2017/7/12.
 */

public class BaseDialog extends Dialog {

    private Builder builder;

    @BindView(R.id.title_container) FrameLayout titleContainer;
    @BindView(R.id.close) ImageView close;
    @BindView(R.id.title_text) TextView title;
    @BindView(R.id.view_container) FrameLayout viewContainer;
    @BindView(R.id.confirm) Button confirm;
    @BindView(R.id.cancel) Button cancel;

    private BaseDialog(Builder builder1) {
        super(builder1.context);
        builder = builder1;
    }

    private BaseDialog(Builder builder1, @StyleRes int themeResId) {
        super(builder1.context, themeResId);
        builder = builder1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_dialog);
        setCanceledOnTouchOutside(builder.cancelTouchout);
        ButterKnife.bind(this);

        View.OnClickListener dialogClose = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDialog.this.cancel();
            }
        };
        cancel.setOnClickListener(dialogClose);
        confirm.setOnClickListener(dialogClose);
        close.setOnClickListener(dialogClose);

        if (builder.titleColor != -1) {
            titleContainer.setBackgroundResource(builder.titleColor);
        }
        if (builder.title != null) {
            title.setText(builder.title);
        }
        if (!builder.isClose) {
            close.setVisibility(View.INVISIBLE);
        }
        if (builder.view != null) {
            viewContainer.addView(builder.view);
        }
        if (builder.positiveListener != null) {
            cancel.setVisibility(View.GONE);
            confirm.setOnClickListener(builder.positiveListener);
        }
        if (builder.negativeListener != null) {
            confirm.setVisibility(View.GONE);
            cancel.setOnClickListener(builder.negativeListener);
        }

        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.height = builder.height;
        layoutParams.width = builder.width;
        window.setAttributes(layoutParams);
    }

    public static final class  Builder {

        private  Context context;
        private int height, width;
        private boolean cancelTouchout;

        private int titleColor = -1;
        private String title;
        private boolean isClose = true;
        private View view;
        private View.OnClickListener negativeListener;
        private View.OnClickListener positiveListener;
        private int resStyle = -1;

        public Builder(Context context1) {
            context = context1;
            height = 650;
            width = 850;
        }

        public Builder titleColor(int colorRes) {
            titleColor = colorRes;
            return this;
        }

        public Builder title(String text) {
            title = text;
            return this;
        }

        public Builder isClose(boolean is) {
            isClose = is;
            return this;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public void setNegativeListener(View.OnClickListener negativeListener) {
            this.negativeListener = negativeListener;
        }

        public void setPositiveListener(View.OnClickListener positiveListener) {
            this.positiveListener = positiveListener;
        }



        public Builder sizepx(int hpx, int wpx) {
            height = hpx;
            width = wpx;
            return this;
        }

        public Builder sizedp(int hdp, int wdp) {
            height = DesityUtil.dp2px(context, hdp);
            width = DesityUtil.dp2px(context, wdp);
            return this;
        }

        public Builder sizeRes(int hDimenRes, int wDimenRes) {
            height = context.getResources().getDimensionPixelOffset(hDimenRes);
            height = context.getResources().getDimensionPixelOffset(wDimenRes);
            return this;
        }

        public Builder style(int resStyle1) {
            resStyle = resStyle1;
            return this;
        }

        public Builder cancelTouchout(boolean cancelTouchout1) {
            cancelTouchout = cancelTouchout1;
            return this;
        }

        public Builder addViewOnClick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }

        public BaseDialog build() {
            if (resStyle != -1) {
                return new BaseDialog(this, resStyle);
            } else {
                return new BaseDialog(this, R.style.AnyWork_Theme_Dialog);
            }
        }
    }
}
