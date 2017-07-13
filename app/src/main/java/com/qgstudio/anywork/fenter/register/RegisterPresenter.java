package com.qgstudio.anywork.fenter.register;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.BasePresenterImpl;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter{

    private RegisterApi registerApi;

    @Override
    public void register(String account, String password, String name, String phone) {
        if (registerApi == null) {
            registerApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(RegisterApi.class);
        }

        final User user = new User(-1, name, account, password, phone, 0);

        registerApi.register(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult<Integer>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showError("网络连接错误");
                    }

                    @Override
                    public void onNext(ResponseResult<Integer> result) {

                        if (result.getState() == 1001) {
                            mView.showSuccess();
                        } else {
                            mView.showError(result.getStateInfo());
                        }
                    }
                });
    }
}
