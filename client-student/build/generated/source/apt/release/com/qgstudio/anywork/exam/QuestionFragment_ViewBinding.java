// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.exam;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QuestionFragment_ViewBinding implements Unbinder {
  private QuestionFragment target;

  @UiThread
  public QuestionFragment_ViewBinding(QuestionFragment target, View source) {
    this.target = target;

    target.mTVType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'mTVType'", TextView.class);
    target.mTVContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'mTVContent'", TextView.class);
    target.mTVAnswere = Utils.findRequiredViewAsType(source, R.id.tv_answer, "field 'mTVAnswere'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_option, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    QuestionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTVType = null;
    target.mTVContent = null;
    target.mTVAnswere = null;
    target.mRecyclerView = null;
  }
}
