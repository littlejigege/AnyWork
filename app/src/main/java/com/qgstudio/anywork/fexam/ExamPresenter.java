package com.qgstudio.anywork.fexam;

import com.qgstudio.anywork.data.LoadDataCallback;
import com.qgstudio.anywork.data.model.StudentAnswer;
import com.qgstudio.anywork.data.model.Textpaper;
import com.qgstudio.anywork.fexam.data.TestRepository;
import com.qgstudio.anywork.mvp.BasePresenterImpl;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class ExamPresenter extends BasePresenterImpl<ExamContract.View> implements ExamContract.Presenter{

    @Override
    public void getTextpaper(int textpaperId) {
//        Log.e(TAG, "getTextpaper: "+textpaperId);
        TestRepository.getInstance()
                .getTextpaper(textpaperId, new LoadDataCallback<Textpaper>() {

            @Override
            public void onSuccess(Textpaper textpaper) {
//                Log.i(TAG, "onSuccess: "+textpaper);
                mView.showTextPaper(textpaper);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                mView.showThrowable("网络连接错误");
            }
        });
    }

    @Override
    public void submitTextpaper(StudentAnswer studentAnswer) {
//        Log.i(TAG, "submitTextpaper: "+ GsonUtil.GsonString(studentAnswer));
        TestRepository.getInstance()
                .submitTestPaper(studentAnswer, new LoadDataCallback<Double>() {
                    @Override
                    public void onSuccess(Double score) {
                        mView.showScore(score);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                        mView.showThrowable("网络连接错误");
                    }
                });

    }
}
