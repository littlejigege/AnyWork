package com.qgstudio.anywork.data;

import android.support.annotation.NonNull;

import rx.Subscriber;


/**
 * Created by Yason on 2017/4/12.
 */

public abstract class RetrofitSubscriber<T> extends Subscriber<ResponseResult<T>> {

//    class ApiException extends Exception {
//        public ApiException(String message) {
//            super(message);
//        }
//    }

    protected abstract int getSuccessState();

    protected LoadDataCallback<T> mLoadDataCallback;

    public RetrofitSubscriber(@NonNull LoadDataCallback<T> LoadDataCallback){
        mLoadDataCallback = LoadDataCallback;
    }

    @Override
    public void onCompleted() {
        unsubscribeCallback();
    }

    @Override
    public void onError(Throwable t) {
        mLoadDataCallback.onFailure(t);
        unsubscribeCallback();
    }

    @Override
    public void onNext(ResponseResult<T> tResponseResult) {
        if (tResponseResult.getState() == getSuccessState()) {
            mLoadDataCallback.onSuccess(tResponseResult.getData());
        } else {
//            onError(new ApiException(tResponseResult.getStateInfo()));
        }
    }

    public void unsubscribeCallback() {
        mLoadDataCallback = null;
    }

}
