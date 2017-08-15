package com.qgstudio.anywork.fpaper.data;


import android.content.Context;
import android.util.Log;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.fpaper.PaperFragView;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author Yason 2017/4/12.
 */

public class PaperRepository extends BasePresenterImpl<PaperFragView> implements PaperPresenter {

    private static final String TAG = "PaperRepository";
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
                        LogUtil.d(TAG, "[getExaminationPaper] " + "onSuccess -> " + data);
                        mView.addExaminationPapers(data);
                    }

                    @Override
                    protected void onFailure(String info) {
                        LogUtil.d(TAG, "[getExaminationPaper] " + "onFailure -> " + info);

                    }

                    @Override
                    protected void onMistake(Throwable t) {
                        LogUtil.d(TAG, "[getExaminationPaper] " + "onMistake -> " + t.getMessage());
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
                        LogUtil.d(TAG, "[getPracticePaper] " + "onSuccess -> " + data);
                        mView.addPracticePapers(data);
                    }

                    @Override
                    protected void onFailure(String info) {
                        LogUtil.d(TAG, "[getPracticePaper] " + "onFailure -> " + info);
                    }

                    @Override
                    protected void onMistake(Throwable t) {
                        LogUtil.d(TAG, "[getPracticePaper] " + "onMistake -> " + t.getMessage());
                    }
                });
    }

}
