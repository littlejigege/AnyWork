package com.qgstudio.anywork.data;

import android.support.annotation.NonNull;

import rx.Subscriber;


/**
 * Created by Yason on 2017/4/12.
 */

public abstract class RetrofitSubscriber<T> extends Subscriber<ResponseResult<T>> {

    protected abstract void onSuccess(T data);//服务器返回成功
    protected abstract void onFailure(String info);//服务器返回失败
    protected abstract void onMistake(Throwable t);//网络库请求数据失败

    public static final int mSuccessState = 1;

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable t) {
        onMistake(t);
    }

    @Override
    public void onNext(ResponseResult<T> tResponseResult) {
        if (tResponseResult.getState() == mSuccessState) {
            onSuccess(tResponseResult.getData());
        } else {
            onFailure(tResponseResult.getStateInfo());
        }
    }

}
