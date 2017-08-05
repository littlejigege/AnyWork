package com.qgstudio.anywork.fmain;


import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.mvp.BaseView;

import java.util.List;

public interface OrganizationFragView extends BaseView{
    void addOrganization(Organization organization);
    void addOrganizations(List<Organization> organizations);
    void joinSuccess(int id, int position);
    void joinFail(String info);
}
