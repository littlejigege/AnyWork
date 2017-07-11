package com.qgstudio.anywork.fgrade;

import com.qgstudio.anywork.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyi on 2017/7/10.
 */

class GradePresenter extends BasePresenterImpl<GradeContract.View> implements GradeContract.Presenter {
    @Override
    public void getGrade() {
        List<GradeInfo> datas = new ArrayList<>();
        for (int i=0;i<60;i++) {
            GradeInfo gradeInfo;
            if (i % 10 == 0) {
                gradeInfo = new GradeInfo(GradeInfo.TYPE1, "正确率：14/20");
            } else {
                gradeInfo = new GradeInfo(GradeInfo.TYPE2, i+"");
            }
            datas.add(gradeInfo);
        }
        mView.showGrade(datas);
    }
}
