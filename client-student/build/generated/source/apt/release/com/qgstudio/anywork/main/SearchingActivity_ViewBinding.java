// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchingActivity_ViewBinding implements Unbinder {
  private SearchingActivity target;

  private View view2131558533;

  @UiThread
  public SearchingActivity_ViewBinding(SearchingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchingActivity_ViewBinding(final SearchingActivity target, View source) {
    this.target = target;

    View view;
    target.searching = Utils.findRequiredViewAsType(source, R.id.edi_searching, "field 'searching'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_back, "method 'back'");
    view2131558533 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.searching = null;

    view2131558533.setOnClickListener(null);
    view2131558533 = null;
  }
}
