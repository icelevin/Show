package com.icelevin.www.show.net;


import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ice on 2017/9/19.
 */

public class OkHttpFactory {

    private OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;
    private static final int TIMEOUT_WRITE = 25;

    public OkHttpFactory(boolean isDebug, String cachePath, Interceptor[] interceptors) {
        //打印请求log
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (isDebug) {
            //debug模式下开启日志
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            //缓存目录
            File cacheFile = new File(cachePath, "netWorkCache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    //必须是设置cache目录
                    .cache(cache)
                    //失败连接
                    .retryOnConnectionFailure(true)
                    //读取超时设定
                    .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                    //写入超时设定
                    .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                    //连接超时设定
                    .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);
            //设置拦截器
            if (interceptors != null && interceptors.length > 0) {
                for (int i = 0; i < interceptors.length; i++) {
                    Interceptor interceptor = interceptors[i];
                    if (interceptor == null) {
                        continue;
                    }
                    builder.addInterceptor(interceptor);
                }
            }
            //打印请求log
            builder.addInterceptor(loggingInterceptor);
            okHttpClient = builder.build();
        }
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
