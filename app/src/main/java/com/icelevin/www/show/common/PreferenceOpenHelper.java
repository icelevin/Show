package com.icelevin.www.show.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


/**
 * Created by ice on 2017/9/4.
 */

public class PreferenceOpenHelper {
    private Context context;
    private String name;
    private int mode;

    /**
     * @param context 上下文
     * @param preName pre文件名
     */
    protected PreferenceOpenHelper(Context context, String preName) {
        this.context = context;
        this.name = preName;
        this.mode = Context.MODE_PRIVATE;
    }

    private SharedPreferences getSharedPreferences() {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp;
    }

    protected Context getContext() {
        return context;
    }

    protected Map<String, ?> getAll() {
        return getSharedPreferences().getAll();
    }

    protected String getString(String key,String defaultValue){
        return getSharedPreferences().getString(key,defaultValue);
    }

    protected int getInt(String key,int defaultValue){
        return getSharedPreferences().getInt(key, defaultValue);
    }

    protected long getLong(String key,long defaultValue){
        return getSharedPreferences().getLong(key, defaultValue);
    }

    protected boolean getBoolean(String key,boolean defaultValue){
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    protected float getFloat(String key,float defaultValue){
        return getSharedPreferences().getFloat(key,defaultValue);
    }

    protected boolean putString(String key,String defaultValue){
        return getSharedPreferences().edit().putString(key, defaultValue).commit();
    }

    protected boolean putInt(String key,int defaultValue){
        return getSharedPreferences().edit().putInt(key,defaultValue).commit();
    }

    protected boolean putLong(String key,long defaultValue){
        return getSharedPreferences().edit().putLong(key, defaultValue).commit();
    }

    protected boolean putBoolean(String key,boolean defaultValue){
        return getSharedPreferences().edit().putBoolean(key, defaultValue).commit();
    }

    protected boolean putFloat(String key,float defaultValue){
        return getSharedPreferences().edit().putFloat(key, defaultValue).commit();
    }

    protected SharedPreferences.Editor getEditor(){
        return getSharedPreferences().edit();
    }

    protected boolean contains(String key){
        return getSharedPreferences().contains(key);
    }

}
