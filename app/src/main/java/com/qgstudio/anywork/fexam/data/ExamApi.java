package com.qgstudio.anywork.fexam.data;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.StudentAnswerAnalysis;
import com.qgstudio.anywork.data.model.StudentPaper;
import com.qgstudio.anywork.data.model.Testpaper;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Yason on 2017/4/14.
 */

public interface ExamApi {

    @POST("test")
    Observable<ResponseResult<List<Question>>> getTestpaper(@Body() Map<String, String> testpaperId);

    @POST("test/submit")
    @Headers("Content-Type:application/json")
    Observable<ResponseResult<StudentAnswerAnalysis>> submitTestpaper(@Body StudentPaper studentPaper);

}
