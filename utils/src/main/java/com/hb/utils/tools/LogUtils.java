package com.hb.utils.tools;

import android.text.TextUtils;
import android.util.Log;


/**
 * Created by txl on 2017/2/6 0006.
 */
public class LogUtils {
    public static void print(String tag,String message){
        if(TextUtils.isEmpty(tag)){
            tag = "";
        }
        if(TextUtils.isEmpty(message)){
            return;
        }
            Log.e(tag,message);
    }
}
