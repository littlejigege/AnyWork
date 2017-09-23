// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.enter.login;

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

public class LoginFragment_ViewBinding implements Unbinder {
  private LoginFragment target;

  private View view2131558576;

  private View view2131558549;

  @UiThread
  public LoginFragment_ViewBinding(final LoginFragment target, View source) {
    this.target = target;

    View view;
    target.account = Utils.findRequiredViewAsType(source, R.id.account, "field 'account'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    view = Utils.findRequiredView(source, R.id.sign_in, "method 'login'");
    view2131558576 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
    view = Utils.findRequiredView(source, R.id.cancel, "method 'cancel'");
    view2131558549 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cancel();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.account = null;
    target.password = null;

    view2131558576.setOnClickListener(null);
    view2131558576 = null;
    view2131558549.setOnClickListener(null);
    view2131558549 = null;
  }
}
