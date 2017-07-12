package com.qgstudio.anywork.fpaper.data;

import com.qgstudio.anywork.data.ResponseResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Yason on 2017/4/11.
 */

public interface PaperApi {

    @GET("timeline/{time}/timeline/{organId}")
    Observable<ResponseResult<List<Textpaper>>> getPracticePaper(@Path("time") long time, @Path("organId") int organId);

    @GET("relation/allorgan")
    Observable<ResponseResult<List<Textpaper>>> getExaminationPaper();

}
