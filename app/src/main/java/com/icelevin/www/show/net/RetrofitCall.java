package com.icelevin.www.show.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ice on 2017/9/19.
 */

public class RetrofitCall {

    private static Map<String, Retrofit> maps = new HashMap<>();

    public static Retrofit getRetrofit(String baseUrl, boolean isDebug, String cachePath, Interceptor[] interceptors) {

        Retrofit retrofit = maps.get(baseUrl);

        if (retrofit == null) {
            Gson gson = new GsonBuilder().create();
            OkHttpClient okHttpClient = new OkHttpFactory(isDebug, cachePath, interceptors).getOkHttpClient();
            retrofit = new Retrofit.Builder()
                    //设置okHttpClient
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            maps.put(baseUrl, retrofit);
        }

        return retrofit;

    }


}
