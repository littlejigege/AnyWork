package com.qgstudio.anywork.fpaper.data;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Textpaper;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Yason on 2017/4/11.
 */

public interface PaperApi {

    @GET("timeline/{time}/timeline/{organId}")
    Observable<ResponseResult<Map<String, Map<String, Object>>>> getTimeTree(@Path("time") long time, @Path("organId") int organId);

    @GET("relation/allorgan")
    Observable<ResponseResult<List<Organization>>> getOrganizationList();

}
