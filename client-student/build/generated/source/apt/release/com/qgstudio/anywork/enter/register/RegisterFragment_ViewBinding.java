// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.enter.register;

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

public class RegisterFragment_ViewBinding implements Unbinder {
  private RegisterFragment target;

  private View view2131558522;

  private View view2131558549;

  @UiThread
  public RegisterFragment_ViewBinding(final RegisterFragment target, View source) {
    this.target = target;

    View view;
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", EditText.class);
    target.account = Utils.findRequiredViewAsType(source, R.id.account, "field 'account'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.password2 = Utils.findRequiredViewAsType(source, R.id.password2, "field 'password2'", EditText.class);
    view = Utils.findRequiredView(source, R.id.register, "method 'register'");
    view2131558522 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.register();
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
    RegisterFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.account = null;
    target.phone = null;
    target.password = null;
    target.password2 = null;

    view2131558522.setOnClickListener(null);
    view2131558522 = null;
    view2131558549.setOnClickListener(null);
    view2131558549 = null;
  }
}
