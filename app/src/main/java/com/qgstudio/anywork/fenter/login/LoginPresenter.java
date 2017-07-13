package com.qgstudio.anywork.fenter.login;

import android.util.Log;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.DataBaseUtil;
import com.qgstudio.anywork.utils.GsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    private LoginApi loginApi;

    @Override
    public User getUser() {

        return null;
    }

    @Override
    public void login(String account, String password) {
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

                        if (result.getState() == 2001) {
                            User user = result.getData();
                            App.getInstance().setUser(user);

                            mView.showSuccess();

                            DataBaseUtil.getHelper().save(user);
                            List<User> list = DataBaseUtil.getHelper().queryAll(User.class);
                            for (User u : list) {
                                Log.d("tag", "onNext: "+ GsonUtil.GsonString(u));
                            }
                        } else {
                            mView.showError(result.getStateInfo());
                        }
                    }
                });
    }
}
