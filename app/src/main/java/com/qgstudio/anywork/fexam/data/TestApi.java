package com.qgstudio.anywork.fexam.data;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.Textpaper;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Yason on 2017/4/14.
 */

public interface TestApi {

    @GET("test/textpaper/{textpaperId}")
    Observable<ResponseResult<Textpaper>> getTextpaper(@Path("textpaperId") int textpaperId);

    @POST("studentAnswer/add")
    @Headers("Content-Type:application/json")
    Observable<ResponseResult<Double>> submitTextpaper(@Body StudentAnswer studentAnswer);

}
