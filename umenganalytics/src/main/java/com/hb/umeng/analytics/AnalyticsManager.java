package com.hb.umeng.analytics;

import android.app.Activity;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import java.util.Map;

/**
 * Created by txl on 2017/5/28 0028.
 */
public class AnalyticsManager {
    private static AnalyticsManager analyticsManager;
    private Context appContext;

    public static AnalyticsManager getInstance(Context appContext){
        if(analyticsManager == null){
            analyticsManager = new AnalyticsManager(appContext);
        }
        return analyticsManager;
    }

    private AnalyticsManager(Context appContext){
        this.appContext = appContext;
        //禁止默认的页面统计方式，这样将不会再自动统计Activity。
        MobclickAgent.openActivityDurationTrack(false);
    }

    public static void onFragmentResume(Activity context, String fragmentName){
        MobclickAgent.onPageStart(fragmentName); //统计页面启动
        MobclickAgent.onResume(context); //统计页面时长
    }

    public static void onFragmentPause(Activity context,String fegamentName){
        MobclickAgent.onPageEnd(fegamentName);//统计页面关闭
        MobclickAgent.onPause(context);//统计页面时长
    }

    /**
     * 自定义事件
     * @param event
     */
    public void click(EventEnum event){
        click(event,null);
    }

    /**
     * 自定义事件及参数
     * @param event
     */
    public void click(EventEnum event,Map<String,String> map){
        if(map == null || map.size() < 1){
            MobclickAgent.onEvent(appContext, event.getId());
        }else{
            MobclickAgent.onEvent(appContext, event.getId(), map);
        }
    }
}
