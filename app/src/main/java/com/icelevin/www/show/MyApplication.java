package com.icelevin.www.show;


import android.support.multidex.MultiDex;

import com.awen.photo.FrescoImageLoader;
import com.hb.utils.BaseApplication;
import com.hb.utils.tools.Utils;
import com.hb.x5web.InitWebView;
import com.icelevin.www.show.common.Constants;
import com.icelevin.www.show.common.SettingPref;

import cn.bmob.v3.Bmob;
import cn.ittiger.player.Config;
import cn.ittiger.player.PlayerManager;
import cn.ittiger.player.factory.ExoPlayerFactory;

/**
 * Created by ice on 2017/9/4.
 */

public class MyApplication extends BaseApplication {
    private static MyApplication baseApplication;

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        baseApplication = this;
        initParam();
    }

    private void initParam() {
        SettingPref.getInstance().SetVaule("deviceType", Constants.deviceType);
        SettingPref.getInstance().SetVaule("version", Utils.getVersionName(this) + "");

        //友盟统计初始化
//        AnalyticsManager.getInstance(this);
        //分享初始化
//        ShareManager.getInstance(this);
        //x5内核初始化
        Bmob.initialize(this, Constants.BOMB_APPLICATION_ID);
        FrescoImageLoader.init(this);
        //下面是配置toolbar颜色和存储图片地址的
//        FrescoImageLoader.init(this,android.R.color.holo_blue_light);
//        FrescoImageLoader.init(this,android.R.color.holo_blue_light,"/storage/xxxx/xxx");

        InitWebView.init();
        PlayerManager.loadConfig(
                new Config.Builder(this)
                        //使用ExoPlayer内核作为视频播放器，默认使用MediaPlayer
                        .buildPlayerFactory(new ExoPlayerFactory(this))
//                        .enableSmallWindowPlay()//开启小窗口播放，默认不开其
                        .cache(true)//开启缓存功能，默认不开启
                        .build()
        );
    }

    public static MyApplication getAppContext() {
        return baseApplication;
    }
}
