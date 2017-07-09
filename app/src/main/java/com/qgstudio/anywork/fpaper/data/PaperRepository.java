package com.qgstudio.anywork.fpaper.data;

import android.support.annotation.NonNull;

import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.LoadDataCallback;
import com.qgstudio.anywork.data.model.Organization;

import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Yason on 2017/4/12.
 */

public class PaperRepository {

    private static PaperRepository mPaperRepository;
    private PaperApi mPaperApi;

    private PaperRepository() {
        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
        mPaperApi = retrofit.create(PaperApi.class);
    }

    public static PaperRepository getInstance() {
        if (mPaperRepository == null) {
            synchronized (PaperRepository.class) {
                if (mPaperRepository == null) {
                    mPaperRepository = new PaperRepository();
                }
            }
        }
        return mPaperRepository;
    }

    public void getTimeTree(long time, int organId, @NonNull LoadDataCallback<Map<String, Map<String, Object>>> LoadDataCallback) {
        mPaperApi.getTimeTree(time, organId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<Map<String, Map<String, Object>>>(LoadDataCallback) {
                    @Override
                    protected int getSuccessState() {
                        return 201;
                    }
                });
    }

    public void getOrganizationList(@NonNull LoadDataCallback<List<Organization>> LoadDataCallback) {
        mPaperApi.getOrganizationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<List<Organization>>(LoadDataCallback) {
                    @Override
                    protected int getSuccessState() {
                        return 176;
                    }
                });
    }


}
