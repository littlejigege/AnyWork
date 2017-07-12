package com.qgstudio.anywork.floginandsign.login;

import com.qgstudio.anywork.data.ResponseResult;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenyi on 2017/3/31.
 */

interface LoginApi {

    @POST("user/login")
    @Headers("Content-Type:application/json")
    Observable<ResponseResult<User>> login(@Body Object object);
}
