package com.qgstudio.anywork.fuser;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.User;
import com.qgstudio.anywork.mvp.MVPBaseActivity;
import com.qgstudio.anywork.utils.GlideUtil;
import com.qgstudio.anywork.utils.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.os.Environment.getExternalStorageDirectory;
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

    @BindView(mail) TextView email;

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
            String n = name.getText().toString();
            String p = phone.getText().toString();
            if (!n.matches("[a-z0-9A-Z\\u4e00-\\u9fa5]{1,15}")) {
                ToastUtil.showToast("请输入1-15个字符的姓名");
                return ;
            }
            if (!p.matches("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$")) {
                ToastUtil.showToast("请输入正确的电话号码");
                return ;
            }

            User nUser = user.clone();
            nUser.setUserName(n);
            nUser.setPhone(p);
            mPresenter.changeInfo(nUser);
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
        GlideUtil.setPictureWithOutCache(pic, user1.getUserId());
    }

    private void editFocusable(boolean focusable) {
        name.setEnabled(focusable);
        phone.setEnabled(focusable);
        if (focusable) {
            name.requestFocus();
            phone.requestFocus();
        }
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
                        // 将图片设置到头像中
                        Glide.with(this)
                                .load(uri)
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>(250, 250) {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        saveHeadPic(resource);
                                    }
                                });
                    }
                }
                break;
        }
    }

    /**
     * 将获取的图片进行压缩并保存
     */
    private void saveHeadPic(Bitmap bitmap) {
        //将bitmap保存到本地，spath :生成图片取个名字和路径包含类型
        String path = getExternalStorageDirectory()
                .getAbsolutePath() + "/AnyWork/picture/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = path + user.getUserId() +".jpg";
        saveImage(bitmap, filePath);
        mPresenter.changePic(filePath);
    }

    /**
     * bitmap后存到对应路径
     */
    public void saveImage(Bitmap photo, String spath) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(spath, false));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showSuccess(User user) {
        App.getInstance().setUser(user);
        ToastUtil.showToast("信息修改完成");
    }

    @Override
    public void showError(String s) {
        setInfo(user);
        ToastUtil.showToast(s);
    }

    @Override
    public void setUser(User user) {
        setInfo(user);
    }

    @Override
    public void changeImg() {
        GlideUtil.setPictureWithOutCache(pic, user.getUserId());
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }
}
