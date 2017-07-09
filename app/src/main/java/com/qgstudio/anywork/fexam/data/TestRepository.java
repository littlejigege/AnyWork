package com.qgstudio.anywork.fexam.data;

import android.support.annotation.NonNull;

import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.LoadDataCallback;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.Textpaper;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Yason on 2017/4/14.
 */

public class TestRepository {

    private static TestRepository mTestRepository;
    private TestApi mTestApi;

    private TestRepository() {
        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
        mTestApi = retrofit.create(TestApi.class);
    }

    public static TestRepository getInstance() {
        if (mTestRepository == null) {
            synchronized (TestRepository.class) {
                if (mTestRepository == null) {
                    mTestRepository = new TestRepository();
                }
            }
        }
        return mTestRepository;
    }


    public void getTextpaper(int textpaperId,@NonNull LoadDataCallback<Textpaper> loadDataCallback) {
        mTestApi.getTextpaper(textpaperId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<Textpaper>(loadDataCallback) {
                    @Override
                    protected int getSuccessState() {
                        return 401;
                    }
                });
    }

    public void submitTestPaper(StudentAnswer studentAnswer, LoadDataCallback<Double> loadDataCallback) {
        mTestApi.submitTextpaper(studentAnswer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<Double>(loadDataCallback) {
                    @Override
                    protected int getSuccessState() {
                        return 302;
                    }
                });
    }

}
