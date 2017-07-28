package com.qgstudio.anywork.fgrade;

import android.content.Context;

import com.qgstudio.anywork.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyi on 2017/7/10.
 */

class GradePresenter extends BasePresenterImpl<GradeContract.View> implements GradeContract.Presenter {

    @Override
    public void detachView() {
        mView = new GradeContract.View() {
            @Override
            public void showSuccess() {

            }

            @Override
            public void showError(String s) {

            }

            @Override
            public Context getContext() {
                return null;
            }
        };
    }

    @Override
    public void getDetail(int id) {

    }
}
