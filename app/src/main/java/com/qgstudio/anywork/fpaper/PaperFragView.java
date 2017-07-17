package com.qgstudio.anywork.fpaper;


import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.mvp.BaseView;

import java.util.List;

public interface PaperFragView extends BaseView{
    void addPracticePapers(List<Testpaper> testpapers);
    void addExaminationPapers(List<Testpaper> testpapers);
}
