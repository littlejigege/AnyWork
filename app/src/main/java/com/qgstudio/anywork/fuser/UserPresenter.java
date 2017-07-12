package com.qgstudio.anywork.fuser;

import android.net.Uri;
import android.util.Log;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.GsonUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UserPresenter extends BasePresenterImpl<UserContract.View> implements UserContract.Presenter{

    private UserApi userApi;
    private User mUser;

    @Override
    public void getInfo() {
        if (userApi == null) {
            userApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(UserApi.class);
        }

        userApi.getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult<User>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
//                        Log.i(TAG, "onError: 网络连接错误");
                        mView.showError("网络连接错误");
                    }

                    @Override
                    public void onNext(ResponseResult<User> result) {

                        assert result != null;

                        if (result.getState() == 161) {
                            mUser = result.getData();
                            mView.setEmail(mUser.getEmail());
                            mView.setPhone(mUser.getPhone());
//                            mView.setSchool(mUser.getSchool());
//                            mView.setImg(mUser.getPicture());
                            mView.setUserName(mUser.getUserName());
//                            Log.i(TAG, "onNext: "+result.getStateInfo()+result.getData());
                        } else {
                            mView.showError("信息修改格式错误");
                        }
                    }
                });
    }

    @Override
    public void changeInfo(final String title, final String string) {
        if (userApi == null) {
            userApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(UserApi.class);
        }

        Map<String, String> info = new HashMap<>();
        info.put("userName", mUser.getUserName());
        info.put("phone", mUser.getPhone());
//        info.put("school", mUser.getSchool()==null?" ":mUser.getSchool());
        info.put(title, string);

        Log.i(TAG, "changeInfo: "+ GsonUtil.GsonString(info));
        userApi.changeInfo(info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult<User>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hidProgressDialog();
//                        Log.i(TAG, "onError: 网络连接错误");
                    }

                    @Override
                    public void onNext(ResponseResult<User> result) {
                        assert result != null;

                        if (result.getState() == 141) {
                            switch (title) {
                                case "phone":
                                    mUser.setPhone(string);
                                    break;
                                case "userName":
                                    mUser.setUserName(string);
                                    break;
                                case "school":
//                                    mUser.setSchool(string);
                                    break;
                            }
                            mView.setPhone(mUser.getPhone());
//                            mView.setSchool(mUser.getSchool());
                            mView.setUserName(mUser.getUserName());
                            mView.hidProgressDialog();
                        } else {

                        }
                    }
                });
    }

    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    @Override
    public void changePic(final Uri picUri) {
        RequestBody pictureNameBody = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), "picture");
        File picture = new File(mView.uri2Path(picUri));
        final RequestBody requestFile = RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), picture);
        // MultipartBody.Part 借助文件名上传
        MultipartBody.Part picturePart = MultipartBody.Part.createFormData("file", picture.getName(), requestFile);
        //调接口
        if (userApi == null) {
            userApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(UserApi.class);
        }

        userApi.changePic(pictureNameBody, picturePart)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hidProgressDialog();
                    }

                    @Override
                    public void onNext(ResponseResult responseResult) {
                        if (responseResult.getState() == 151) {
                            mView.changeImg(picUri);
                        }
                        mView.hidProgressDialog();
                    }
                });
    }
}
