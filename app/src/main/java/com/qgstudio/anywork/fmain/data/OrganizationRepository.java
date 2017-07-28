package com.qgstudio.anywork.fmain.data;

import android.content.Context;

import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.fmain.OrganizationFragView;
import com.qgstudio.anywork.mvp.BasePresenterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Model+Presenter
 * 对外提供的接口用public，否则private
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
        Map<String, String> map = new HashMap();
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

}
