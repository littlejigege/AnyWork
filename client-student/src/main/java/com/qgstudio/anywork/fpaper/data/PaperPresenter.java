package com.qgstudio.anywork.fpaper.data;

import android.view.View;

import com.qgstudio.anywork.fpaper.PaperFragView;
import com.qgstudio.anywork.mvp.BasePresenter;

/**
 * Created by Yason on 2017/8/13.
 */

public interface PaperPresenter extends BasePresenter<PaperFragView> {
    void getExaminationPaper(int organizationId);

    void getPracticePaper(int organizationId);
}
