package com.qgstudio.anywork.fmain.data;

import com.qgstudio.anywork.data.model.StudentPaper;
import com.qgstudio.anywork.fexam.ExamView;
import com.qgstudio.anywork.fmain.OrganizationFragView;
import com.qgstudio.anywork.mvp.BasePresenter;

/**
 * @author Yason 2017/8/13.
 */

public interface OrganizationPresenter extends BasePresenter<OrganizationFragView> {
    void getAllOrganization();
    void getJoinOrganization();
    void updateJoinOrganization();
    void joinOrganization(int organizationId, String password, int position);
    void leaveOrganization(int organizationId, int position);
}
