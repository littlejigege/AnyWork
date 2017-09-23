// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.grade;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GradeActivity_ViewBinding implements Unbinder {
  private GradeActivity target;

  @UiThread
  public GradeActivity_ViewBinding(GradeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GradeActivity_ViewBinding(GradeActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.container = Utils.findRequiredViewAsType(source, R.id.container, "field 'container'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GradeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;
    target.container = null;
  }
}
