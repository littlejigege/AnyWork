package com.qgstudio.anywork.fpaper.data;


import android.content.Context;
import android.util.Log;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.fpaper.PaperFragView;
import com.qgstudio.anywork.mvp.BasePresenterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Yason on 2017/4/12.
 */

public class PaperRepository extends BasePresenterImpl<PaperFragView> implements PaperPresenter {

    private PaperApi mPaperApi;

    public PaperRepository() {
        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
        mPaperApi = retrofit.create(PaperApi.class);
    }

    public void getExaminationPaper(int organizationId) {
        Map<String, String> map = new HashMap();
        map.put("organizationId", organizationId + "");
        mPaperApi.getExaminationPaper(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<List<Testpaper>>() {
                    @Override
                    protected void onSuccess(List<Testpaper> data) {
                        mView.addExaminationPapers(data);
                    }

                    @Override
                    protected void onFailure(String info) {

                    }

                    @Override
                    protected void onMistake(Throwable t) {

                    }
                });
    }

    public void getPracticePaper(int organizationId) {
        Map<String, String> map = new HashMap();
        map.put("organizationId", organizationId + "");
        mPaperApi.getPracticePaper(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<List<Testpaper>>() {
                    @Override
                    protected void onSuccess(List<Testpaper> data) {
                        mView.addPracticePapers(data);
                    }

                    @Override
                    protected void onFailure(String info) {

                    }

                    @Override
                    protected void onMistake(Throwable t) {

                    }
                });
    }

}
