package com.icelevin.www.show.net;

import com.hb.utils.net.okhttp.RetrofitClient;

import okhttp3.Interceptor;
import retrofit2.Retrofit;

/**
 * Created by ice on 2017/9/19.
 */

public class ApiFactory {
    /**
     * 创建Api
     * @param baseUrl 服务器地址
     * @param isDebug 是否debug
     * @param cachePath 缓存路径
     * @param clz 模型字节码
     * @param <T>
     * @return
     */
    public static <T> T getApi(String baseUrl, boolean isDebug, String cachePath, Class<T> clz){
        Retrofit retrofit= RetrofitClient.create(baseUrl, isDebug, cachePath);
        return retrofit.create(clz);
    }


    public static <T> T getApi(String baseUrl, boolean isDebug, Class<T> clz){
        Retrofit retrofit= RetrofitClient.create(baseUrl, isDebug);
        return retrofit.create(clz);
    }
}
