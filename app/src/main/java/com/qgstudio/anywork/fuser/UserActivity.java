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

//    private static final int GET_PHOTO = 1;
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.image)
//    CircleImageView img;
//    @BindView(R.id.name)
//    TextView name;
//    @BindView(R.id.progress)
//    ProgressBar progress;
//    @BindView(R.id.toolLayout)
//    RelativeLayout relativeLayout;
//
//    @OnClick(R.id.phone)
//    public void editPhone() {
//        dialog("修改电话", "phone");
//    }
//    @OnClick(R.id.name)
//    public void editName() {
//        dialog("修改姓名", "userName");
//    }
//    @OnClick(R.id.school)
//    public void editSchool() {
//        dialog("修改学校", "school");
//    }
//    @OnClick(R.id.image)
//    public void editPic() {
//        //调用系统图库获取图片
//        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, GET_PHOTO);
//    }
//    @OnClick(R.id.logout)
//    public void logout() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setTitle("是否退出登录");
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                mActivity.finish();
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
//    }
//
//    /**
//     * 通过intent调用系统图库后获取返回数据
//     * 通过相机和图库获取图片,并加载到imageView上
//     */
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case GET_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    Uri uri = data.getData();
//                    if (uri != null) {
//                        mPresenter.changePic(uri);
//                        showProgressDialog();
//                    } else {
//                        Toast.makeText(mActivity, "获取图片失败", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                break;
//        }
//    }
//
//
//
//    public static UserFragment newInstance() {
//        //通过 newInstance 保证 Fragment 不被重复构造，造成 fragment 重叠
//        return new UserFragment();
//    }
//
//    public UserFragment() {
//        //防止无参构造器被外部调用
//    }
//
//    @Override
//    public int getRootId() {
//        return 0;
//    }
//
//    @Override
//    public void initView() {}
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_fpaper_content, container, false);
//        ButterKnife.bind(this, view);
//
//        toolbar.setTitle("个人信息");
//        mPresenter.getInfo();
//        return view;
//    }
//
//
//    @Override
//    public void showSuccess() {
//    }
//
//    @Override
//    public void showError(String s) {
//        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void setEmail(String email) {
//        this.email.setInfo(email);
//    }
//
//    @Override
//    public void setUserName(String name) {
//        this.name.setText(name);
//    }
//
//    @Override
//    public void setPhone(String phone) {
//        this.phone.setInfo(phone);
//    }
//
//    @Override
//    public void setSchool(String school) {
//        this.school.setInfo(school);
//    }
//
//    @Override
//    public void setImg(String imgUrl) {
//        Glide.with(mActivity)
//                .load(ApiStores.API_SERVER_URL+"picture/"+imgUrl)
//                .skipMemoryCache(true)
//                .into(img);
//    }
//
//    @Override
//    public void changeImg(Uri uri) {
//        Glide.with(mActivity)
//                .load(uri)
//                .skipMemoryCache(true)
//                .into(img);
//    }
//
//    @Override
//    public void showProgressDialog() {
//        progress.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hidProgressDialog() {
//        progress.setVisibility(View.INVISIBLE);
//    }
//
//    protected void dialog(String title, final String info) {
//        final EditText editText = new EditText(mActivity);
//        editText.setTextColor(getResources().getColor(R.color.colorAccent));
//        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setTitle(title);
//        builder.setView(editText);
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                mPresenter.changeInfo(info, editText.getText().toString());
//                dialog.dismiss();
//                showProgressDialog();
//
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
//    }
//
//    @Override
//    public String uri2Path (Uri uri) {
//        String imagePath = null;
//
//        if (DocumentsContract.isDocumentUri(mActivity, uri)) {
//            String docId = DocumentsContract.getDocumentId(uri);
//            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                String id = docId.split(":")[1];
//                String selection = MediaStore.Images.Media._ID + "=" + id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
//                Uri contentUri = ContentUris.withAppendedId(
//                        Uri.parse("content://downloads/public_downloads"),
//                        Long.valueOf(docId));
//                imagePath = getImagePath(contentUri, null);
//            }
//        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            imagePath = getImagePath(uri, null);
//        }
//        return imagePath;
//    }
//
//    private String getImagePath(Uri uri, String selection) {
//        String path = null;
//        Cursor cursor = mActivity.getContentResolver().query(uri, null, selection, null, null);
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//
//            cursor.close();
//        }
//        return path;
//    }

}
