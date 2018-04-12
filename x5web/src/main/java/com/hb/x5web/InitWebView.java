package com.hb.x5web;

import android.util.Log;

import com.hb.utils.BaseApplication;
import com.tencent.smtt.sdk.QbSdk;

/**
 *
 *
 * Created by txl on 2017/7/7 0007.
 */
public class InitWebView {
    public static void init(){
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d(InitWebView.class.getName(), " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(BaseApplication.getAppContext(), cb);
    }
}
