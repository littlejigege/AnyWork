package com.qgstudio.anywork.fuser;


import android.net.Uri;

import com.qgstudio.anywork.mvp.MVPBaseFragment;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class UserFragment extends MVPBaseFragment<UserContract.View, UserPresenter> implements UserContract.View {
    @Override
    public void showSuccess() {

    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void setUserName(String name) {

    }

    @Override
    public void setPhone(String phone) {

    }

    @Override
    public void setSchool(String school) {

    }

    @Override
    public void setImg(String imgUrl) {

    }

    @Override
    public void changeImg(Uri imgUrl) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }

    @Override
    public String uri2Path(Uri picUri) {
        return null;
    }

    @Override
    public int getRootId() {
        return 0;
    }

    @Override
    public void initView() {

    }

//    private static final int GET_PHOTO = 1;
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.email)
//    InfoItem email;
//    @BindView(R.id.phone)
//    InfoItem phone;
//    @BindView(R.id.school)
//    InfoItem school;
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
