package com.icelevin.www.show.common;

import android.content.Context;
import android.os.ParcelUuid;
import android.text.TextUtils;

import com.hb.utils.tools.AESOperator;
import com.hb.utils.tools.Utils;
import com.icelevin.www.show.MyApplication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ice on 2017/9/4.
 */

public class SettingPref extends PreferenceOpenHelper {

    private static SettingPref settingPref;

    /**
     * @param context 上下文
     * @param preName pre文件名
     */
    protected SettingPref(Context context, String preName) {
        super(context, preName);
    }

    public synchronized static SettingPref getInstance() {
        if (settingPref == null) {
            Context context = MyApplication.getAppContext();
            String name = context.getPackageName();
            return settingPref = new SettingPref(context, name);
        }
        return settingPref;
    }

    public Map<String, ?> getAll() {
        Map<String, ?> map = super.getAll();
        if (map == null || map.size() < 1) {
            return null;
        }
        Set<String> set = map.keySet();
        if (set == null || set.size() < 1) {
            return null;
        }
        Iterator<String> iterator = set.iterator();
        if (iterator == null) {
            return null;
        }
        Map<String, Object> decryptMap = new HashMap<>();
        while (iterator.hasNext()) {
            String key = iterator.next() + "";
            String value = map.get(key) + "";
            if (!TextUtils.isEmpty(key)) {
                key = AESOperator.getInstance().decrypt(key);
            } else {
                continue;
            }

            if (!TextUtils.isEmpty(value)) {
                value = AESOperator.getInstance().decrypt(value);
            }

            decryptMap.put(key, value);
        }
        return decryptMap;
    }

    public String getSplashBanner() {
        return settingPref.getString(Key.splashBanner, "");
    }

    public void setSplashBanner(String splashBanner) {
        settingPref.putString(Key.splashBanner, splashBanner);
    }

    public boolean isFirstOpen() {
        return settingPref.getBoolean(Key.isFirstOpen, true);
    }

    public void setIsFirstOpen(boolean b) {
        settingPref.putBoolean(Key.isFirstOpen, b);
    }

    public String getSplashImgUrl() {
        return settingPref.getString(Key.splashImageUrl, "");
    }

    public void setSplashImgUrl(String splashImgUrl) {
        settingPref.putString(Key.splashImageUrl, splashImgUrl);
    }

    public void SetVaule(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        if (TextUtils.isEmpty(value)) {
            value = new String();
        }
        key = AESOperator.getInstance().encrypt(key);
        value = AESOperator.getInstance().encrypt(value.toString());
        settingPref.putString(key, value + "");
    }

    public String getValue(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        //加密key
        key = AESOperator.getInstance().encrypt(key);
        //根据加密的key获取值
        String value = settingPref.getString(key, "");
        //解密值
        value = AESOperator.getInstance().decrypt(value);
        //返回值
        return value;
    }


    private interface Key {
        //每一次升级都算是第一次打开
        public static final String isFirstOpen = "isFirstopen" + Utils.getVersionName(MyApplication.getAppContext());
        //        public static final String isFirstOpenDoctorInfoPage = "isFirstOpenDoctorInfoPage";
        public static final String openMusic = "openMusic";
        public static final String openVibrate = "openVibrate";
        public static final String splashImageUrl = "splashImageUrl";  //splash广告图本地保存的文件路径
        public static final String splashBanner = "splashBanner";    //splash广告图json
    }
}
