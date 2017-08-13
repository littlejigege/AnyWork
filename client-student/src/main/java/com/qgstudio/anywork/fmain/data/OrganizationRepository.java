package com.qgstudio.anywork.fmain.data;

import android.content.Context;
import android.util.Log;

import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.fmain.OrganizationFragView;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.GsonUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by Yason on 2017/4/14.
 */

public class OrganizationRepository extends BasePresenterImpl<OrganizationFragView>{

    @Override
    public void detachView() {
        mView = new OrganizationFragView() {
            @Override
            public void addOrganization(Organization organization) {

            }

            @Override
            public void addOrganizations(List<Organization> organizations) {

            }

            @Override
            public void joinSuccess(int id, int position) {

            }

            @Override
            public void joinFail(String info) {

            }

            @Override
            public Context getContext() {
                return null;
            }
        };
    }

    private OrganizationApi mOrganizationApi;

    public OrganizationRepository() {
        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
        mOrganizationApi = retrofit.create(OrganizationApi.class);
    }

    public void getAllOrganization() {
        Map<String, String> map = new HashMap<>();
        map.put("organizationName", "");
        mOrganizationApi.getAllOrganization(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<List<Organization>>() {
                    @Override
                    protected void onSuccess(List<Organization> data) {
                        mView.addOrganizations(data);
                    }

                    @Override
                    protected void onFailure(String info) {
                    }

                    @Override
                    protected void onMistake(Throwable t) {
                    }
                });
    }

    public void getJoinOrganization() {
        mOrganizationApi.getJoinOrganization()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<List<Organization>>() {
                    @Override
                    protected void onSuccess(List<Organization> data) {
                        mView.addOrganizations(data);
                    }

                    @Override
                    protected void onFailure(String info) {
                    }

                    @Override
                    protected void onMistake(Throwable t) {
                    }
                });
    }

    public void joinOrganization(final int id, String pass, final int position) {
        Map<String, String> info = new HashMap<>();
        info.put("organizationId", id+"");
        info.put("token", pass);

        mOrganizationApi.joinOrganization(info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.joinFail("网络连接错误");
                    }

                    @Override
                    public void onNext(ResponseResult responseResult) {
                        if (responseResult.getState() == 1) {
                            mView.joinSuccess(id, position);
                        } else {
                            mView.joinFail(responseResult.getStateInfo());
                        }
                    }
                });
    }

    public void leaveOrganization(final int id) {
        Map<String, Integer> info = new HashMap<>();
        info.put("organizationId", id);

        Log.i(TAG, "leaveOrganization: "+GsonUtil.GsonString(info));
        mOrganizationApi.leave(info)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: 网络连接错误");
                        mView.joinFail("网络连接错误");
                    }

                    @Override
                    public void onNext(ResponseResult responseResult) {
                        if (responseResult.getState() == 1) {
                            mView.joinSuccess(0, 0);
                        } else {
                            mView.joinFail(responseResult.getStateInfo());
                        }
                    }
                });
    }


}
