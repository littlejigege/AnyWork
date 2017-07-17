package com.qgstudio.anywork.fexam.data;

/**
 * Created by Yason on 2017/4/14.
 */

public class ExamRepository {

//    private static ExamRepository mExamRepository;
//    private ExamApi mExamApi;
//
//    private ExamRepository() {
//        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
//        mExamApi = retrofit.create(ExamApi.class);
//    }
//
//    public static ExamRepository getInstance() {
//        if (mExamRepository == null) {
//            synchronized (ExamRepository.class) {
//                if (mExamRepository == null) {
//                    mExamRepository = new ExamRepository();
//                }
//            }
//        }
//        return mExamRepository;
//    }
//
//
//    public void getTextpaper(int textpaperId,@NonNull LoadDataCallback<Testpaper> loadDataCallback) {
//        mExamApi.getTextpaper(textpaperId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RetrofitSubscriber<Testpaper>(loadDataCallback) {
//                    @Override
//                    protected int getSuccessState() {
//                        return 401;
//                    }
//                });
//    }
//
//    public void submitTestPaper(StudentAnswer studentAnswer, LoadDataCallback<Double> loadDataCallback) {
//        mExamApi.submitTextpaper(studentAnswer)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RetrofitSubscriber<Double>(loadDataCallback) {
//                    @Override
//                    protected int getSuccessState() {
//                        return 302;
//                    }
//                });
//    }

}
