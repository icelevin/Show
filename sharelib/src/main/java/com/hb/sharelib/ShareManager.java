package com.hb.sharelib;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hb.utils.view.MyMenuDialog;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.List;

/**
 * Created by txl on 2017/4/18 0018.
 */
public class ShareManager {

    private String[] items = new String[]{"微信好友","微信朋友圈","QQ好友","QQ空间","新浪微博"};
    private int[] icons = new int[]{
            R.drawable.umeng_socialize_wechat,
            R.drawable.umeng_socialize_wxcircle,
            R.drawable.umeng_socialize_qq,
            R.drawable.umeng_socialize_qzone,
            R.drawable.umeng_socialize_sina};


    static {
        PlatformConfig.setWeixin("wx012fd48cad7e4810", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1104739493", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("2428843117", "89af2a856353a3ef38658ec421df9f0d", "http://www.hbjk114.com");
    }

    private static ShareManager shareManager;

    public static ShareManager getInstance(Context context){
        if(shareManager == null){
            shareManager = new ShareManager(context);
        }
        return shareManager;
    }

    /**
     * 友盟分享初始化
     * @param context
     */
    private ShareManager(Context context){
        UMShareAPI.get(context);
    }


    public void showDialog(FragmentActivity activity,AdapterView.OnItemClickListener listener){
        Bundle bundle = new Bundle();
        bundle.putString(MyMenuDialog.title,"分享到");
        bundle.putStringArray(MyMenuDialog.items,items);
        bundle.putIntArray(MyMenuDialog.icos,icons);

        MyMenuDialog dialog = new MyMenuDialog();
        dialog.setArguments(bundle);
        dialog.setOnItemClickListener(listener);
        dialog.show(activity.getSupportFragmentManager(),"sharedialig");
    }

    public void share(Activity activity,ShareModel shareModel){
        if(activity == null || activity.isFinishing() || shareModel == null || TextUtils.isEmpty(shareModel.getPageUrl())){
            return;
        }

        SHARE_MEDIA media;
        int position = shareModel.getSharePosition();

        if(position == 0){
            media = SHARE_MEDIA.WEIXIN;
        }else if(position == 1){
            media = SHARE_MEDIA.WEIXIN_CIRCLE;
        }else if(position == 2){
            media = SHARE_MEDIA.QQ;
        }else if(position == 3){
            media = SHARE_MEDIA.QZONE;
        }else {
            media = SHARE_MEDIA.SINA;
        }

        if(media == SHARE_MEDIA.WEIXIN || media == SHARE_MEDIA.WEIXIN_CIRCLE ){
            boolean install = UMShareAPI.get(activity).isInstall(activity, SHARE_MEDIA.WEIXIN);
            if(!install){
                Toast.makeText(activity,"您尚未安装微信",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if( media == SHARE_MEDIA.QQ || media == SHARE_MEDIA.QZONE){
            boolean install = UMShareAPI.get(activity).isInstall(activity, SHARE_MEDIA.QQ);
            if(!install){
                Toast.makeText(activity,"您尚未安装QQ",Toast.LENGTH_SHORT).show();
                return;
            }
        }


        UMWeb  web = new UMWeb(shareModel.getPageUrl());
        if(!TextUtils.isEmpty(shareModel.getTitle())){
            web.setTitle(shareModel.getTitle());//标题
        }

        if(!TextUtils.isEmpty(shareModel.getImageUrl())){
            UMImage image = new UMImage(activity, shareModel.getImageUrl());//资源文件
            web.setThumb(image);  //缩略图
        }

        if(!TextUtils.isEmpty(shareModel.getDescription())){
            web.setDescription(shareModel.getDescription());//描述
        }
        new ShareAction(activity).setPlatform(media).withMedia(web)
                .setCallback(new MYUMShareListener(activity,shareModel.getListener()))
                .share();
    }


    private class MYUMShareListener implements UMShareListener{

        public Activity activity;
        private UMShareListener listener;

        public MYUMShareListener(Activity activity,UMShareListener listener){
            this.activity = activity;
            this.listener = listener;
        }

        @Override
        public void onStart(final SHARE_MEDIA share_media) {
            if(activity == null || activity.isFinishing()){
                return;
            }

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(listener != null){
                        listener.onStart(share_media);
                    }
                }
            });
//            Toast.makeText(activity,share_media+"分享开始",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(final SHARE_MEDIA share_media) {
            if(activity == null || activity.isFinishing()){
                return;
            }

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(listener != null){
                        listener.onResult(share_media);
                    }
                    Toast.makeText(activity,"分享成功",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
            System.out.println("分享错误throwable="+throwable);
            if(activity == null || activity.isFinishing()){
                return;
            }

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(listener != null){
                        listener.onError(share_media,throwable);
                    }
                    Toast.makeText(activity,"分享失败",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onCancel(final SHARE_MEDIA share_media) {
            if(activity == null || activity.isFinishing()){
                return;
            }

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(listener != null){
                        listener.onCancel(share_media);
                    }
                    Toast.makeText(activity,"分享已取消",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
