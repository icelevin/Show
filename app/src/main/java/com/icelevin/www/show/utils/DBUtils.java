package com.icelevin.www.show.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hb.utils.BaseApplication;
import com.hb.utils.tools.LogUtils;
import com.icelevin.www.show.MyApplication;
import com.icelevin.www.show.dao.CacheModleDao;
import com.icelevin.www.show.dao.DaoMaster;
import com.icelevin.www.show.dao.DaoSession;
import com.icelevin.www.show.dao.UserInfoDao;
import com.icelevin.www.show.model.CacheModle;
import com.icelevin.www.show.model.UserInfo;

import cn.bmob.v3.BmobUser;


/**
 * Created by ice on 2017/9/25.
 */

public class DBUtils {
    private static DaoMaster.DevOpenHelper devOpenHelper = null;


    /**
     * 获取数据库操作类
     *
     * @return
     */
    public static DaoSession getDaoSession() {
        if (devOpenHelper == null) {
            devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getAppContext(), "show.db", null);
        }
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        return daoMaster.newSession();
    }

    /**
     * 根据url与指定的javabean类型获取缓存在数据库中的缓存对象
     *
     * @param url
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getCache(String url, Class<T> tClass) {
        T jsonBean = null;
        LogUtils.print(DBUtils.class.getName(), "获取缓存的url=" + url);
        try {
            CacheModleDao dao = getDaoSession().getCacheModleDao();
            CacheModle unique = dao.queryBuilder().where(CacheModleDao.Properties.Url.eq(url)).build().unique();
            if (unique == null || TextUtils.isEmpty(unique.getResponse())) {
                return jsonBean;
            }
            Gson gson = new Gson();
            jsonBean = gson.fromJson(unique.getResponse(), tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.print(DBUtils.class.getName(), "获取缓存的结果jsonBean=" + jsonBean);
        return jsonBean;
    }

    public static void savaCache(CacheModle model) {
        if (model == null || TextUtils.isEmpty(model.getUrl()) || TextUtils.isEmpty(model.getResponse())) {
            return;
        }
        LogUtils.print(DBUtils.class.getName(), "保存缓存CacheModel" + model);
        try {
            CacheModleDao dao = getDaoSession().getCacheModleDao();
            dao.insertOrReplace(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
