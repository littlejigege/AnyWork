//package com.qgstudio.anywork.finform;
//
//import com.qgstudio.anywork.data.model.User;
//import com.qgstudio.anywork.mvp.BasePresenter;
//import com.qgstudio.anywork.mvp.BaseView;
//
//import java.util.List;
//
///**
// * MVPPlugin
// *  邮箱 784787081@qq.com
// */
//
//public interface InformContract {
//    interface View extends BaseView {
//        void showInformList(List<Inform> dataList);
//        void showError(String s);
//        void showSuccess();
//    }
//
//    interface  Presenter extends BasePresenter<View> {
//        void getAllInformList(User user);
//    }
//}
