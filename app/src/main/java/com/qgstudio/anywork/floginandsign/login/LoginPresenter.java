package com.qgstudio.anywork.floginandsign.login;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.DataBaseUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *  登录界面的 presenter ，处理网络请求的逻辑功能
 *  Created by chenyi on 2017/3/28.
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    private LoginApi loginApi;

    @Override
    public void login(String account, String password) {
        if (loginApi == null) {
            loginApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(LoginApi.class);
        }

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("email", account);
        loginInfo.put("password", password);
        loginInfo.put("valcode", "0");

        loginApi.login(loginInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult<User>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showError("网络连接错误");
                    }

                    @Override
                    public void onNext(ResponseResult<User> result) {

                        if (result.getState() == 2001) {
                            User user = result.getData();
                            mView.showSuccess(user);
                            App.getInstance().setUser(user);
                            DataBaseUtil.getHelper().save(user);
//                            Log.i(TAG, "onNext: "+result.getStateInfo()+result.getData());
                        } else {
                            mView.showError(result.getStateInfo());
                        }
                    }
                });
    }
}
