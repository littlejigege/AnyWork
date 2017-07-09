package com.qgstudio.anywork.fpaper.paper;

import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.qgstudio.anywork.data.LoadDataCallback;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Textpaper;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.fpaper.data.PaperRepository;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PaperPresenter extends BasePresenterImpl<PaperContract.View> implements PaperContract.Presenter{

    private void getTimeTree(long time, int organId) {
        PaperRepository.getInstance()
                .getTimeTree(time, organId, new LoadDataCallback<Map<String, Map<String, Object>>>() {
                    @Override
                    public void onSuccess(Map<String, Map<String, Object>> dayMap) {
                        getTextpaperList(dayMap);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                        mView.showThrowable(t);
                    }
                });
    }

    private void getTextpaperList(Map<String, Map<String, Object>> dayMap) {
        List<Textpaper> textpaperList = new ArrayList<>(0);
        for (Map<String, Object> aDay : dayMap.values()) {
            Object msgObj = aDay.get("message");
            if (msgObj instanceof List) {
                List<Object> objList = (List<Object>) msgObj;
                for (Object obj : objList) {
                    if (obj instanceof LinkedTreeMap) {
//                        textpaperList.add((Textpaper) obj);
                        LinkedTreeMap data = (LinkedTreeMap) obj;
                        if (data.get("name").equals("test")) {
                            String dataString = GsonUtil.GsonString(data);
                            Textpaper textpaper = GsonUtil.GsonToBean(dataString, Textpaper.class);
                            textpaperList.add(textpaper);
                        }
                    }
                }
            }
        }
        mView.showTextpaperList(textpaperList);
    }

    @Override
    public void getOrganizationList() {
        PaperRepository.getInstance()
                .getOrganizationList(new LoadDataCallback<List<Organization>>() {

                    @Override
                    public void onSuccess(List<Organization> organizations) {
                        mView.showOrganizationList(organizations);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                        mView.showThrowable(t);
                    }
                });
    }

    @Override
    public void getAllTextpaperList(User user) {
//        Set<Organization> organizationSet = user.getOrgans();
//        for (Organization organ : organizationSet) {
//            int organId = organ.getOrganId();
//            getTimeTree(System.currentTimeMillis(), organId);
//        }
        for (int i=0;i<4;i++) {
            getTimeTree(1492780072298L - i * 3 * 24 * 60 * 60 * 1000, 36);
        }
        Log.i("aaa", "getAllTextpaperList: "+System.currentTimeMillis());
    }

    @Override
    public void startPaperTesting(int textpaperId) {
        mView.showPaperTesting(textpaperId);
    }

}
