package com.qgstudio.anywork.fexam.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.StudentAnswerAnalysis;
import com.qgstudio.anywork.data.model.StudentAnswerResult;
import com.qgstudio.anywork.data.model.StudentPaper;
import com.qgstudio.anywork.data.model.StudentTestResult;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.fexam.ExamView;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.GsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Yason on 2017/4/14.
 */

public class ExamRepository extends BasePresenterImpl<ExamView>{

    @Override
    public void detachView() {
        mView = new ExamView() {
            @Override
            public void addQuestion(Question question) {

            }

            @Override
            public void addQuestions(List<Question> questions) {

            }

            @Override
            public void skipToGradeAty(double socre, List<StudentAnswerResult> analysis) {

            }

            @Override
            public Context getContext() {
                return null;
            }
        };
    }

    private ExamApi mExamApi;

    public ExamRepository() {
        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
        mExamApi = retrofit.create(ExamApi.class);
    }


    public void getTestpaper(int textpaperId) {
        Map<String, String> map = new HashMap<>();
        map.put("testpaperId", textpaperId + "");
        mExamApi.getTestpaper(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<List<Question>>() {
                    @Override
                    protected void onSuccess(List<Question> data) {
                        mView.addQuestions(data);
                    }

                    @Override
                    protected void onFailure(String info) {
                    }

                    @Override
                    protected void onMistake(Throwable t) {
                    }
                });
    }

    public void submitTestPaper(StudentPaper studentPaper) {
        mExamApi.submitTestpaper(studentPaper)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<StudentTestResult>() {
                    @Override
                    protected void onSuccess(StudentTestResult data) {
                        double socre = data.getSocre();
                        List<StudentAnswerResult> results = new ArrayList<>();
                        List<StudentAnswerAnalysis> analysis = data.getStudentAnswerAnalysis();
                        for (StudentAnswerAnalysis analysi : analysis) {
                            results.add(new StudentAnswerResult(analysi));
                        }
                        mView.skipToGradeAty(socre, results);
                    }

                    @Override
                    protected void onFailure(String info) {
                        Log.i("tag", "onFailure: " +info);
                    }

                    @Override
                    protected void onMistake(Throwable t) {

                    }
                });
    }

}
