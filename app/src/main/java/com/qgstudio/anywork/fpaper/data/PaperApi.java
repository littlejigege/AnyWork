package com.qgstudio.anywork.fpaper.data;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.model.Testpaper;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Yason on 2017/4/11.
 */

public interface PaperApi {

    @POST("test/practiceList")
    Observable<ResponseResult<List<Testpaper>>> getPracticePaper(@Body Map<String, String> organizationId);

    @POST("test/testList")
    Observable<ResponseResult<List<Testpaper>>> getExaminationPaper(@Body Map<String, String> organizationId);

}
