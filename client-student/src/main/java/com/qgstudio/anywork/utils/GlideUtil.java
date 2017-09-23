package com.qgstudio.anywork.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.ApiStores;

/**
 * Created by chenyi on 2017/7/12.
 */

public class GlideUtil {

    public static void setPicture(ImageView img, String path) {
        Glide.with(App.getInstance())
                .load(path)
                .error(R.drawable.ic_icon)
                .fitCenter()
                .crossFade(500)
                .into(img);
    }

    public static void setPictureWithOutCache(ImageView img, int id, int def) {
        Glide.with(App.getInstance())
                .load(ApiStores.API_DEFAULT_URL + "picture/" + id + ".jpg")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(def == -1 ? R.drawable.ic_icon : def)
                .fitCenter()
                .crossFade(500)
                .into(img);
    }
}
