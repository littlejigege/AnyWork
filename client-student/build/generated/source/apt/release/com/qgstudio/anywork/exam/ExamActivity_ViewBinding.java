// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.exam;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.widget.ExamPagerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExamActivity_ViewBinding implements Unbinder {
  private ExamActivity target;

  private View view2131558527;

  @UiThread
  public ExamActivity_ViewBinding(ExamActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExamActivity_ViewBinding(final ExamActivity target, View source) {
    this.target = target;

    View view;
    target.mExamPagerView = Utils.findRequiredViewAsType(source, R.id.epv, "field 'mExamPagerView'", ExamPagerView.class);
    view = Utils.findRequiredView(source, R.id.fab, "field 'mSubmitFab' and method 'submit'");
    target.mSubmitFab = Utils.castView(view, R.id.fab, "field 'mSubmitFab'", FloatingActionButton.class);
    view2131558527 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submit();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ExamActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mExamPagerView = null;
    target.mSubmitFab = null;

    view2131558527.setOnClickListener(null);
    view2131558527 = null;
  }
}
