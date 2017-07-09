package com.qgstudio.anywork.floginandsign.register;

import com.qgstudio.anywork.data.ResponseResult;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by chenyi on 2017/4/11.
 */

public interface RegisterApi {

    @POST("user/chenyibug/register")
    @Headers("Content-Type:application/json")
    Observable<ResponseResult<Object>> register(@Body Object object);
}
