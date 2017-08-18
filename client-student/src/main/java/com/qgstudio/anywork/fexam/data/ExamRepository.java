package com.qgstudio.anywork.fexam.data;

import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswerAnalysis;
import com.qgstudio.anywork.data.model.StudentAnswerResult;
import com.qgstudio.anywork.data.model.StudentPaper;
import com.qgstudio.anywork.data.model.StudentTestResult;
import com.qgstudio.anywork.fexam.ExamView;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.GsonUtil;
import com.qgstudio.anywork.utils.LogUtil;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Yason 2017/4/14.
 */

public class ExamRepository extends BasePresenterImpl<ExamView> implements ExamPresenter {

    public static final String TAG = "ExamRepository";

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
                        LogUtil.d(TAG, "[getTestpaper] " + "onSuccess -> " + data);
                        mView.addQuestions(data);
                    }

                    @Override
                    protected void onFailure(String info) {
                        LogUtil.d(TAG, "[getTestpaper] " + "onFailure -> " + info);
                        handleGetPaperContentFailure();
                    }

                    @Override
                    protected void onMistake(Throwable t) {
                        LogUtil.d(TAG, "[getTestpaper] " + "onMistake -> " + t.getMessage());

                        if (t instanceof ConnectException) {
                            mView.showToast("无法连接到服务器");
                            mView.destroySelf();
                            return;
                        }

                        handleGetPaperContentFailure();
                    }
                });
    }

    public void submitTestPaper(StudentPaper studentPaper) {
        LogUtil.d(TAG, "[submitTestPaper] " + "studentPaper -> " + GsonUtil.GsonString(studentPaper));
        mExamApi.submitTestpaper(studentPaper)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<StudentTestResult>() {
                    @Override
                    protected void onSuccess(StudentTestResult data) {
                        LogUtil.d(TAG, "[submitTestPaper] " + "onSuccess -> " + data);

                        double socre = data.getSocre();
                        List<StudentAnswerResult> results = new ArrayList<>();

                        List<StudentAnswerAnalysis> analysis = data.getStudentAnswerAnalysis();
                        for (StudentAnswerAnalysis analysi : analysis) {
                            results.add(new StudentAnswerResult(analysi));
                        }

                        mView.startGradeAty(socre, results);
                    }

                    @Override
                    protected void onFailure(String info) {
                        LogUtil.d(TAG, "[submitTestPaper] " + "onFailure -> " + info);
                        mView.showToast("提交试卷失败");
                    }

                    @Override
                    protected void onMistake(Throwable t) {
                        LogUtil.d(TAG, "[submitTestPaper] " + "onMistake -> " + t);

                        if (t instanceof ConnectException) {
                            mView.showToast("无法连接到服务器");
                            return;
                        }

                        mView.showToast("提交试卷失败");
                    }
                });
    }

    private void handleGetPaperContentFailure(){
        mView.showToast("获取试卷内容失败");
        mView.destroySelf();
    }

}
