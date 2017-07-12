package com.qgstudio.anywork.fuser;


import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by chenyi on 2017/4/13.
 */

public interface UserApi {

    @GET("user/info")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Observable<ResponseResult<User>> getUserInfo();

    @POST("user/update/info")
    @Headers("Content-Type:application/json;charset=UTF-8")
    Observable<ResponseResult<User>> changeInfo(@Body Object o);

    @Multipart
    @POST("user/update/picture")
    Observable<ResponseResult> changePic(@Part("pictureName") RequestBody pictureName, @Part MultipartBody.Part picture);
}
