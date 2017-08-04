package com.qgstudio.anywork.fenter.login;

import android.content.Context;
import android.util.Log;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.DataBaseUtil;
import com.qgstudio.anywork.utils.GsonUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    @Override
    public void detachView() {
        mView = new LoginContract.View() {
            @Override
            public void showError(String errorInfo) {

            }

            @Override
            public void showSuccess() {

            }

            @Override
            public Context getContext() {
                return null;
            }
        };

    }

    private LoginApi loginApi;

    @Override
    public User getUser() {
        User user = DataBaseUtil.getHelper().queryById(User.class, 1);
        return user;
    }

    @Override
    public void login(String account, final String password) {
        if (loginApi == null) {
            loginApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(LoginApi.class);
        }

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("valcode", "0");
        loginInfo.put("email", account);
        loginInfo.put("password", password);

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

                        if (result.getState() == 1) {
                            User user = result.getData();
                            App.getInstance().setUser(user);
                            Log.i(TAG, "onNext: login" + GsonUtil.GsonString(user));
                            mView.showSuccess();

                            user.setPassword(password);
                            DataBaseUtil.getHelper().save(user);
                        } else {
                            mView.showError(result.getStateInfo());
                        }
                    }
                });
    }
}
