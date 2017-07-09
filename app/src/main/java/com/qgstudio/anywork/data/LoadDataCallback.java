package com.qgstudio.anywork.data;

/**
 * Created by Yason on 2017/4/14.
 */

public interface LoadDataCallback<T> {
    void onSuccess(T t);

    void onFailure(Throwable t);
}
