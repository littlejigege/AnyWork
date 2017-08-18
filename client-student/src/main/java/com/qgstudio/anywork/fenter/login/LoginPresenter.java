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
import com.qgstudio.anywork.utils.MyOpenHelper;

import java.util.HashMap;
import java.util.List;
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
        List<User> users = DataBaseUtil.getHelper().queryAll(User.class);
        return users.get(users.size() - 1);
    }

    @Override
    public void login(final String account, final String password) {
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

                            user.setEmail(account);
                            user.setPassword(password);
                            MyOpenHelper myOpenHelper = DataBaseUtil.getHelper();
                            myOpenHelper.clear(User.class);
                            myOpenHelper.save(user);
                        } else {
                            mView.showError(result.getStateInfo());
                        }
                    }
                });
    }
}
