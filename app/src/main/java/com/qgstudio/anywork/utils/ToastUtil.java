package com.qgstudio.anywork.utils;

import android.widget.Toast;

import com.qgstudio.anywork.App;

/**
 * Created by chenyi on 2017/7/10.
 */

public class ToastUtil {
    private static Toast toast;

    public static void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
