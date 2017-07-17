package com.qgstudio.anywork.fmain.data;

import com.qgstudio.anywork.data.RetrofitClient;

import retrofit2.Retrofit;

/**
 * Created by Yason on 2017/4/14.
 */

public class GroupRepository {

    private static GroupRepository mGroupRepository;
    private GroupApi mGroupApi;

    private GroupRepository() {
        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
        mGroupApi = retrofit.create(GroupApi.class);
    }

    public static GroupRepository getInstance() {
        if (mGroupRepository == null) {
            synchronized (GroupRepository.class) {
                if (mGroupRepository == null) {
                    mGroupRepository = new GroupRepository();
                }
            }
        }
        return mGroupRepository;
    }


//    public void getAllGroup(int textpaperId,@NonNull LoadDataCallback<Textpaper> loadDataCallback) {
//        mGroupApi.getAllGroup(textpaperId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RetrofitSubscriber<Textpaper>(loadDataCallback) {
//                    @Override
//                    protected int getSuccessState() {
//                        return 401;
//                    }
//                });
//    }

//    public void getJoinGroup(StudentAnswer studentAnswer, LoadDataCallback<Double> loadDataCallback) {
//        mGroupApi.getJoinGroup(studentAnswer)
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
