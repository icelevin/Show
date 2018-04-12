package com.icelevin.www.show.net;

import android.support.v4.view.ViewCompat;

import com.icelevin.www.show.model.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ice on 2017/9/18.
 */

public class Api {
    /**
     * 聚合新闻
     */
    public interface GetNews {
        @Headers({"isCache: true"})
        @GET("toutiao/index")
        Call<String> getNews(@Query("type") String type, @Query("key") String key);
    }

    /**
     * 小影视频
     */
    public interface GetDuoWanVideo {
        @Headers({"isCache: true"})
        @GET("/video/duowan")
        Call<String> getDuoWan(@Query("catid") String catid, @Query("pageToken") String pageToken, @Query("apikey") String apikey);
    }

    /**
     * 小影视频
     */
    public interface GetDuoWanVideos {
        @Headers({"isCache: true"})
        @GET("/video/duowan")
        Call<String> getDuoWans(@Query("catid") String catid, @Query("apikey") String apikey);
    }

    /**
     * 360新闻搜索
     */
    public interface GetQiHuNews {
        @GET("/news/qihoo")
        Call<String> getQiHuNews(@Query("kw") String kw, @Query("site") String site, @Query("pageToken") String pageToken, @Query("apikey") String apikey);
    }

    /**
     * cctv新闻
     */
    public interface GetCCTVNews {
        @GET("/news/cctvplus")
        Call<String> getCCTVNews(@Query("catid") String catId, @Query("pageToken") String pageToken, @Query("apikey") String apiKey);
    }

    /**
     * 百度百家新闻
     */
    public interface GetBaiJia {
        @GET("/news/baijia")
        Call<String> getBaiJiaNews(@Query("catid") String catId, @Query("pageToken") String pageToken, @Query("apikey") String apiKey);
    }

    /**
     * 搜索央视新闻
     */
    public interface GetSearchCCTVNews {
        @GET("/news/cctvplus")
        Call<String> getCCTVNews(@Query("kw") String kw, @Query("pageToken") String pageToken, @Query("apikey") String apiKey);
    }

    /**
     * 获取新闻评论
     */
    public interface GetNewsComment {
        @GET("/comment/cctvplus")
        Call<String> getNewsComment(@Query("id") String id, @Query("pageToken") String pageToken, @Query("apikey") String apikey);
    }

    /**
     * 获取番薯游戏
     */
    public interface Getbangumi {
        @GET("/game/bangumi")
        Call<String> getbangumi(@Query("catid") String id, @Query("pageToken") String pageToken, @Query("apikey") String apikey);
    }

    /**
     * 获取bili视频
     */
    public interface GetBili {
        @FormUrlEncoded
        @POST("/1367-1")
        Call<String> getBili(@Field("page") String page, @Field("showapi_appid") String showapi_appid, @Field("showapi_sign") String showapi_sign, @Field("showapi_timestamp") String showapi_timestamp, @Field("showapi_res_gzip") String showapi_res_gzip);
    }

    /**
     * 雷锋网
     */
    public interface GetLeiFeng {
        @GET("post/leifeng")
        Call<String> get(@Query("baid") String id, @Query("pageToken") String pageToken, @Query("apikey") String apikey);

    }

    /**
     * 雷锋网搜索功能api
     */
    public interface SearchLeiPhone {
        @GET("post/leifeng")
        Call<String> get(@Query("kw") String kw, @Query("pageToken") String pageToken, @Query("apikey") String apikey);

    }


    /**
     * 穷游网景点
     */
    public interface GetQiongYouScenic {
        @GET("sight/qyer")
        Call<String> get(@Query("cityid") String id, @Query("pageToken") String pageToken, @Query("apikey") String apikey);
    }

    /**
     * 搜索穷游
     */
    public interface SearchQiongYouScenic {
        @GET("sight/qyer")
        Call<String> get(@Query("kw") String kw, @Query("pageToken") String pageToken, @Query("apikey") String apikey);
    }

    /**
     * 腾讯体育
     */
    public interface GetQQSports {
        @GET("post/qqsport")
        Call<String> get(@Query("baid") String baid, @Query("pageToken") String pageToken, @Query("apikey") String apikey);

    }

    /**
     * 腾讯体育
     */
    public interface GetQQSport {
        @GET("post/qqsport")
        Call<String> get(@Query("baid") String baid, @Query("apikey") String apikey);

    }

    /**
     * 腾讯体育用户信息
     */
    public interface GetQQSportUserInfo {
        @GET("profile/qqsport")
        Call<String> get(@Query("id") String id, @Query("apikey") String apikey);
    }

    /**
     * 京东万象天气数据
     */
    public interface GetWeatherInfo {
        @FormUrlEncoded
        @POST("he/freecity")
        Call<String> get(@Field("city") String city, @Field("appkey") String appkey);
    }
}
