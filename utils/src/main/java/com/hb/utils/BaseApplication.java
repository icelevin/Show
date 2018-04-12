package com.hb.utils;


import android.support.multidex.MultiDexApplication;

import com.hb.utils.tools.MyBaseExceptionHandler;

/**
 * Created by txl on 2017/5/26 0026.
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.application = this;
        Thread.setDefaultUncaughtExceptionHandler(new MyBaseExceptionHandler());
    }

    public static BaseApplication getAppContext(){
        return BaseApplication.application;
    }

}
