package com.qgstudio.anywork.fuser;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.MVPBaseActivity;
import com.qgstudio.anywork.utils.GlideUtil;
import com.qgstudio.anywork.utils.ToastUtil;
import com.qgstudio.anywork.utils.TransformUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.qgstudio.anywork.R.id.mail;

/**
 *  查看和修改用户信息的 activity
 *  Created by chenyi on 2017/7/12.
 */
public class UserActivity extends MVPBaseActivity<UserContract.View, UserPresenter> implements UserContract.View {

    private boolean isEdit = false;
    private boolean isFinish = true;
    private User user;

    @BindView(R.id.head_pic) CircleImageView pic;

    @BindView(R.id.name) EditText name;

    @BindView(mail) EditText email;

    @BindView(R.id.phone) EditText phone;

    @BindView(R.id.edit) Button edit;

    @OnClick(R.id.exit)
    public void exit() {
        backResult();
    }

    @OnClick(R.id.edit)
    public void edit() {
        if (isFinish) {
            editFocusable(true);
            edit.setText("完成");
            edit.setBackgroundResource(R.drawable.bg_btn_blue);
        } else {
            user.setUserName(name.getText().toString());
            user.setPhone(phone.getText().toString());
            mPresenter.changeInfo(user);
            editFocusable(false);
            edit.setText("编辑");
            edit.setBackgroundResource(R.drawable.bg_btn_yellow);
        }
        isFinish = !isFinish;
    }

    @OnClick(R.id.head_pic)
    public void changePic() {
        // 调用系统图库获取图片
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations();
        }
        user = App.getInstance().getUser();
        setInfo(user);
        editFocusable(false);
    }

    private void setInfo(User user1) {
        name.setText(user1.getUserName());
        email.setText(user1.getEmail());
        phone.setText(user1.getPhone());
        GlideUtil.setPictureWithOutCache(pic, user1.getUserId()+"");
    }

    private void editFocusable(boolean focusable) {
        name.setFocusable(focusable);
        phone.setFocusable(focusable);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(200);
        getWindow().setEnterTransition(enterTransition);
        getWindow().setExitTransition(enterTransition);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为 back 键，并为 back 键设置监听
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backResult();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void backResult() {
        // 实例化 Bundle，设置需要传递的参数
        Bundle bundle = new Bundle();

        if (isEdit) {
            bundle.putParcelable("user", user);
        } else {
            bundle.putParcelable("user", null);
        }
        // 将修改后的用户信息返回给主页面
        setResult(RESULT_OK, this.getIntent().putExtras(bundle));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    if (uri != null) {
                        mPresenter.changePic(TransformUtil.uri2Path(uri));
                    }
                }
                break;
        }
    }

    @Override
    public void showSuccess() {
        App.getInstance().setUser(user);
        ToastUtil.showToast("信息修改完成");
    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void setUser(User user) {
        setInfo(user);
    }

    @Override
    public void changeImg() {
        GlideUtil.setPictureWithOutCache(pic, user.getUserId()+"");
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }
}
