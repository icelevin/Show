package com.icelevin.www.show.net;

import com.hb.utils.BaseApplication;
import com.hb.utils.tools.SDCardUtil;
import com.icelevin.www.show.MyApplication;
import com.icelevin.www.show.common.Constants;


/**
 * Created by ice on 2017/9/19.
 */

public class ApiFactoryUtils {
    private static String cachePath = SDCardUtil.getInstance(BaseApplication.getAppContext()).CACHE_FOLDER;

//    public static <T> T getApi(String baseUrl,Class<T>  clz){
//        return ApiFactory.getApi(baseUrl, Constants.isDebug,cachePath,clz);
//    }
//    public static <T> T getApi(Class<T> clz){
//        return ApiFactory.getApi(Constants.NEWS_URL,Constants.isDebug,cachePath,clz);
//    }

    public static <T> T getApi(String baseUrl, Class<T> clz) {
        return ApiFactory.getApi(baseUrl, Constants.isDebug, cachePath, clz);
    }
}
