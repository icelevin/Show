package com.hb.utils.net.okhttp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hb.utils.tools.DateFormat;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 传递不同的请求地址，创建不同的RetrofitClient
 * <p/>
 * Created by xianlai on 2017/1/12 0012.
 */
public class RetrofitClient {
    private static Map<String, Retrofit> maps = new HashMap<>();

    public static Retrofit create(String baseUrl,boolean isDebug, String cachePath) {
        Retrofit retrofit = maps.get(baseUrl);

        if (retrofit == null) {
            //自定义Gson对象  调整json里面的一些格式，比如，Date Format。
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.yyyy_MM_dd_HH_mm_ss_1).create();

            OkHttpClient okHttpClient = new OKHttpFactory(isDebug, cachePath).getOkHttpClient();

            retrofit = new Retrofit.Builder()
                    //设置OKHttpClient
                    .client(okHttpClient)

                    //baseUrl
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //gson转化器
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .build();

            maps.put(baseUrl, retrofit);
        }
        return retrofit;
    }

    public static Retrofit create(String baseUrl,boolean isDebug) {
        Retrofit retrofit = maps.get(baseUrl);

        if (retrofit == null) {
            //自定义Gson对象  调整json里面的一些格式，比如，Date Format。
            Gson gson = new GsonBuilder().setDateFormat(DateFormat.yyyy_MM_dd_HH_mm_ss_1).create();

            OkHttpClient okHttpClient = new OKHttpFactory(isDebug).getOkHttpClient();

            retrofit = new Retrofit.Builder()
                    //设置OKHttpClient
                    .client(okHttpClient)

                    //baseUrl
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //gson转化器
                    .addConverterFactory(GsonConverterFactory.create(gson))

                    .build();

            maps.put(baseUrl, retrofit);
        }
        return retrofit;
    }
}
