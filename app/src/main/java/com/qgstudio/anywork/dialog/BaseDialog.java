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

import com.qgstudio.anywork.utils.DesityUtil;

/**
 * 基础的 Dialog 用于显示自定义自己的 Dialog
 * Created by chenyi on 2017/7/12.
 */

public class BaseDialog extends Dialog {

    private Context context;
    private int height, width;
    private boolean cancelTouchout;
    private View view;

    private BaseDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    private BaseDialog(Builder builder, @StyleRes int themeResId) {
        super(builder.context, themeResId);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);
        setCanceledOnTouchOutside(cancelTouchout);

        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.height = height;
        layoutParams.width = width;
        window.setAttributes(layoutParams);
    }

    public static final class  Builder {

        private  Context context;
        private int height, width;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;

        public Builder(Context context1) {
            context = context1;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder heightpx(int px) {
            height = px;
            return this;
        }

        public Builder widthpx(int px) {
            width = px;
            return this;
        }

        public Builder heightdp(int dp) {
            height = DesityUtil.dp2px(context, dp);
            return this;
        }

        public Builder widthdp(int dp) {
            width = DesityUtil.dp2px(context, dp);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
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
                return new BaseDialog(this);
            }
        }
    }
}
