package com.qgstudio.anywork.finform;

import com.qgstudio.anywork.data.LoadDataCallback;
import com.qgstudio.anywork.data.model.Inform;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.fpaper.data.PaperRepository;
import com.qgstudio.anywork.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class InformPresenter extends BasePresenterImpl<InformContract.View> implements InformContract.Presenter{

    private void getTimeTree(long time, int organId) {
        PaperRepository.getInstance()
                .getTimeTree(time, organId, new LoadDataCallback<Map<String, Map<String, Object>>>() {
                    @Override
                    public void onSuccess(Map<String, Map<String, Object>> dayMap) {
                        getInformList(dayMap);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                        mView.showError("网络连接错误");
                    }
                });
    }

    private void getInformList(Map<String, Map<String, Object>> dayMap) {
        List<Inform> informList = new ArrayList<>(0);
//        for (Map<String, Object> aDay : dayMap.values()) {
//            Object msgObj = aDay.get("message");
//            if (msgObj instanceof List) {
//                List<Object> objList = (List<Object>) msgObj;
//                for (Object obj : objList) {
//                    if (obj instanceof LinkedTreeMap) {
//                        LinkedTreeMap data = (LinkedTreeMap) obj;
//                        if (data.get("name").equals("inform")) {
//                            String dataString = GsonUtil.GsonString(data);
//                            Inform inform = GsonUtil.GsonToBean(dataString, Inform.class);
//                            informList.add(inform);
//                        }
//                    }
//                }
//            }
//        }
        Inform inform1 = new Inform();
        inform1.setInformTitle("系统通知");
        inform1.setMark("欢迎来到 anywork 在线作业平台");
        Inform inform2 = new Inform();
        inform2.setInformTitle("作业发布通知");
        inform2.setMark("近期将发布C语言1-12章的在线作业，请同学按时完成");
        informList.add(inform2);
        informList.add(inform1);
        mView.showInformList(informList);
//        Log.i(TAG, "getInformList: "+informList);
    }

    @Override
    public void getAllInformList(User user) {
//        Set<Organization> organizationSet = user.getOrgans();
//        for (Organization organ : organizationSet) {
//            int organId = organ.getOrganId();
//            getTimeTree(System.currentTimeMillis(), organId);
//        }
        getTimeTree(System.currentTimeMillis(), 36);
    }
}
