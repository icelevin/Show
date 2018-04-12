package com.icelevin.www.show.net;


import android.text.TextUtils;
import android.view.TextureView;
import android.widget.TextView;

import com.hb.utils.net.listener.OnResultListener;
import com.icelevin.www.show.common.Constants;
import com.icelevin.www.show.model.BaiJiaModel;
import com.icelevin.www.show.model.BangumiModel;
import com.icelevin.www.show.model.BaseModel;
import com.icelevin.www.show.model.CCTVNewsModel;
import com.icelevin.www.show.model.DuoWanVideoModel;
import com.icelevin.www.show.model.LeiFengListModel;
import com.icelevin.www.show.model.NewsCommentModel;
import com.icelevin.www.show.model.QiongYouScenicModel;
import com.icelevin.www.show.model.SearchCCTVModel;
import com.icelevin.www.show.model.ShowApiBody;
import com.icelevin.www.show.model.QiHuNewsListModel;
import com.icelevin.www.show.ui.sports.QQSportsModel;
import com.icelevin.www.show.ui.sports.QQSportsUserInfoModel;


import retrofit2.Call;


/**
 * Created by ice on 2017/9/18.
 */

public enum NetWorkController {
    INSTANCE;

    /**
     * 获取小影视频
     *
     * @param catid     关键字
     * @param pageToken 翻页值
     * @param listener
     */
    public Call<String> getDuoWan(String catid, String pageToken, OnResultListener<DuoWanVideoModel> listener) {
        if (TextUtils.isEmpty(pageToken)) {
            Api.GetDuoWanVideos api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetDuoWanVideos.class);
            Call<String> call = api.getDuoWans(catid, Constants.IDATA_APIKEY);
            call.enqueue(new APiCallBack<>(call, DuoWanVideoModel.class, listener));
            return call;
        } else {
            Api.GetDuoWanVideo api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetDuoWanVideo.class);
            Call<String> call = api.getDuoWan(catid, pageToken, Constants.IDATA_APIKEY);
            call.enqueue(new APiCallBack<>(call, DuoWanVideoModel.class, listener));
            return call;
        }

    }

    /**
     * 获取搜索新闻
     *
     * @param kw        关键字
     * @param pageToken 翻页值
     * @param listener
     */
    public void GetQiHuNews(String kw, String pageToken, OnResultListener<QiHuNewsListModel> listener) {
        Api.GetQiHuNews api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetQiHuNews.class);
        Call<String> call = api.getQiHuNews(kw, "qq.com", pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, QiHuNewsListModel.class, listener));
    }

    /**
     * 获取cctv新闻
     *
     * @param catid
     * @param pageToken
     * @param listener
     */
    public Call<String> GetCCTVNews(String catid, String pageToken, OnResultListener<CCTVNewsModel> listener) {
        Api.GetCCTVNews api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetCCTVNews.class);
        Call<String> call = api.getCCTVNews(catid, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, CCTVNewsModel.class, listener));
        return call;
    }

    public void GetBaiJiaNews(String catid, String pageToken, OnResultListener<BaiJiaModel> listener) {
        Api.GetBaiJia api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetBaiJia.class);
        Call<String> call = api.getBaiJiaNews(catid, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, BaiJiaModel.class, listener));
    }

    /**
     * 搜索央视新闻
     *
     * @param kw
     * @param pageToken
     * @param listener
     */
    public void SearchCCTVNews(String kw, String pageToken, OnResultListener<SearchCCTVModel> listener) {
        Api.GetSearchCCTVNews api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetSearchCCTVNews.class);
        Call<String> call = api.getCCTVNews(kw, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, SearchCCTVModel.class, listener));
    }

    /**
     * 获取新闻评论
     *
     * @param id        新闻的id
     * @param pageToken 翻页
     * @param listener
     */
    public void GetNewsComment(String id, String pageToken, OnResultListener<NewsCommentModel> listener) {
        Api.GetNewsComment api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetNewsComment.class);
        Call<String> call = api.getNewsComment(id, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, NewsCommentModel.class, listener));
    }

    /**
     * 获取番薯计划
     *
     * @param catid
     * @param pageToken
     * @param listener
     */
    public void GetBangumi(String catid, String pageToken, OnResultListener<BangumiModel> listener) {
        Api.Getbangumi api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.Getbangumi.class);
        Call<String> call = api.getbangumi(catid, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, BangumiModel.class, listener));
    }

    /**
     * 获取bili
     *
     * @param page
     * @param listener
     */
//    public void GetBili(String page, OnResultListener<ShowApiBody> listener) {
//        Api.GetBili api = ApiFactoryUtils.getApi(Constants.SHOW_URL, Api.GetBili.class);
//        String appKey;
//        appKey = "page" + page + "showapi_appid" + Constants.APPID + "showapi_res_gzip" + showapi_res_gzip1 + "showapi_timestamp" + getTime();
//        appKey = appKey + Constants.SHOW_APP_KEY;
//        String sign = DigestUtils.md5Hex(appKey.getBytes());
//        Call<String> call = api.getBili(page, Constants.APPID, sign, getTime(), showapi_res_gzip1);
//        call.enqueue(new APiCallBack<>(call, ShowApiBody.class, listener));
//    }
//
//    public String getTime() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
//        Date date = new Date(System.currentTimeMillis());
//        return format.format(date);
//    }

    /**
     * 雷锋网api
     */
    public Call<String> getLeifeng(String id, String pageToken, OnResultListener<LeiFengListModel> listener) {
        Api.GetLeiFeng api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetLeiFeng.class);
        Call<String> call = api.get(id, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, LeiFengListModel.class, listener));
        return call;
    }

    /**
     * 雷锋网搜索功能api
     */
    public Call<String> searchLeiPhone(String kw, String pageToken, OnResultListener<LeiFengListModel> listener) {
        Api.SearchLeiPhone api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.SearchLeiPhone.class);
        Call<String> call = api.get(kw, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, LeiFengListModel.class, listener));
        return call;
    }

    /**
     * 穷游网景点
     */
    public Call<String> getQiongYouScenic(String cityid, String pageToken, OnResultListener<QiongYouScenicModel> listener) {
        Api.GetQiongYouScenic api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetQiongYouScenic.class);
        Call<String> call = api.get(cityid, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, QiongYouScenicModel.class, listener));
        return call;
    }

    /**
     * 搜索穷游网景点
     */
    public Call<String> searchQiongYouScenic(String kw, String pageToken, OnResultListener<QiongYouScenicModel> listener) {
        Api.SearchQiongYouScenic api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.SearchQiongYouScenic.class);
        Call<String> call = api.get(kw, pageToken, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, QiongYouScenicModel.class, listener));
        return call;
    }

    /**
     * 腾讯体育
     */
    public Call<String> getQQSports(String baid, String pageToken, OnResultListener<QQSportsModel> listener) {

        if (TextUtils.isEmpty(pageToken)) {
            Api.GetQQSport api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetQQSport.class);
            Call<String> call = api.get(baid, Constants.IDATA_APIKEY);
            call.enqueue(new APiCallBack<>(call, QQSportsModel.class, listener));
            return call;
        } else {
            Api.GetQQSports api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetQQSports.class);
            Call<String> call = api.get(baid, pageToken, Constants.IDATA_APIKEY);
            call.enqueue(new APiCallBack<>(call, QQSportsModel.class, listener));
            return call;
        }


    }

    /**
     * 腾讯体育用户信息
     */
    public Call<String> getQQSportsUserInfo(String id, OnResultListener<QQSportsUserInfoModel> listener) {
        Api.GetQQSportUserInfo api = ApiFactoryUtils.getApi(Constants.IDATA_API, Api.GetQQSportUserInfo.class);
        Call<String> call = api.get(id, Constants.IDATA_APIKEY);
        call.enqueue(new APiCallBack<>(call, QQSportsUserInfoModel.class, listener));
        return call;
    }

    /**
     * 获取京东万象天气数据
     * @param city
     * @param listener
     */
    public void getWeatherInfo(String city,  OnResultListener<BaseModel> listener) {
        Api.GetWeatherInfo api = ApiFactoryUtils.getApi(Constants.JD_WX_API, Api.GetWeatherInfo.class);
        Call<String> call = api.get(city,  Constants.JD_WANXAINGD_APPKEY);
        call.enqueue(new APiCallBack<>(call,BaseModel.class,listener));
    }
}
