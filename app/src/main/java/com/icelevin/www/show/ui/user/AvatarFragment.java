package com.icelevin.www.show.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hb.utils.tools.ImageUtils;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.BuildConfig;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.ImageInfoModel;
import com.icelevin.www.show.model.UserInfo;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by ice on 2017/12/11.
 */

public class AvatarFragment extends BaseFragment {
    private TitleView titleView;
    private ImageView iv_icon;
    private String imagePath;
    private static final int getImageRequestCode = 3;
    private static final int getImageZoomRequestCode = 4;
    private ImageInfoModel imageBean;
    private String imgUrl;
    private BmobException BmobException;
    private BmobException exception;

    public AvatarFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_avatar, container, false);
    }

    @Override
    protected void initView() {
        titleView = (TitleView) getView().findViewById(R.id.title_view);
        iv_icon = (ImageView) getView().findViewById(R.id.iv_icon);
        titleView.setTitleText("头像");
    }

    @Override
    protected void initData() {
        iv_icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv_icon.post(new Runnable() {
            @Override
            public void run() {
                int width = iv_icon.getWidth();
                ViewGroup.LayoutParams layoutParams = iv_icon.getLayoutParams();
                layoutParams.height = width;
                iv_icon.setLayoutParams(layoutParams);

            }
        });
    }

    @Override
    protected void initListener() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("scale", true);
        startActivityForResult(intent, getImageRequestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            getActivity().finish();
            return;
        }
        if (requestCode == getImageRequestCode) {
            if (resultCode != Activity.RESULT_OK) {
                showTost("照片获取失败");
                getActivity().finish();
                return;
            }
            imagePath = new ImageUtils().getImageUrlByIntent(getActivity(), data);
            if (TextUtils.isEmpty(imagePath)) {
                showTost("获取照片失败");
                getActivity().finish();
                return;
            }
            File file = new File(imagePath);
            if (!file.exists()) {
                showTost("照片不存在,请重新选择");
                getActivity().finish();
                return;
            }

            imagePath = ImageUtils.startPhotoZoom(getActivity(), BuildConfig.APPLICATION_ID, file, getImageZoomRequestCode);

            if (TextUtils.isEmpty(imagePath)) {
                showTost("照片裁剪失败,系统不支持");
                getActivity().finish();
            }
        } else if (requestCode == getImageZoomRequestCode) {
            if (resultCode != Activity.RESULT_OK) {
                showTost("照片裁剪失败,请重试");
                getActivity().finish();
                return;
            }
            LogUtils.print("选中图片地址", imagePath + "");
            resetUserIcon(imagePath);


        }
    }


    private void resetUserIcon(String file) {
        final File file1 = new File(file);
        if (!file1.exists()) {
            showTost("裁剪图片失败");
            getActivity().finish();
            return;
        }
        final BmobFile bmobFile = new BmobFile(file1);
        showProgressDialog("正在上传");
        bmobFile.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    showTost(bmobFile.getFileUrl());
                    LogUtils.print("url", bmobFile.getFileUrl());
                    UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
                    userInfo.setIconHead(bmobFile.getFileUrl());
                    userInfo.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                showTost("上传成功");
                            } else {
                                showTost(e.getMessage());
                                LogUtils.print("信息结果", e.getMessage());
                            }
                        }
                    });
                    dismisProgressDialog();
                    getActivity().finish();
                } else {
                    showTost(e.getMessage());
                    LogUtils.print("上传结果", e.getMessage());
                    dismisProgressDialog();
                    getActivity().finish();
                }

            }
        });

    }


}
