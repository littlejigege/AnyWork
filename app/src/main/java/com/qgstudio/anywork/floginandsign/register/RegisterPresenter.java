package com.qgstudio.anywork.floginandsign.register;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.mvp.BasePresenterImpl;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter{

    private RegisterApi registerApi;

    @Override
    public void register(String email, String password, String name, String phone) {
        if (registerApi == null) {
            registerApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(RegisterApi.class);
        }

        Map<String, String> info = new HashMap<>();
        info.put("valcode", "0");
        info.put("email", email);
        info.put("password", password);
        info.put("userName", name);
        info.put("isWechat", "0");
        info.put("phone", phone);
//        Log.i(TAG, "register: "+GsonUtil.GsonString(info));

        registerApi.register(info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult<Object>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showError("网络连接错误");
                    }

                    @Override
                    public void onNext(ResponseResult<Object> result) {

//                        Log.i(TAG, "onNext: "+result.getStateInfo()+result.getData());
                        if (result.getState() == 111) {
                            mView.showSuccess();
                        } else {
                            mView.showError(result.getStateInfo());
                        }
                    }
                });
    }
}
