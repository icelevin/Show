package com.icelevin.www.show.utils;

import android.text.TextUtils;

import com.hb.utils.tools.LogUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ice on 2017/9/27.
 */

public class DateUtils {
    /**
     * 几天前
     *
     * @param date
     * @return
     */
    public static String getDaysAgo(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long delta = (new Date().getTime() - date1.getTime()) / 1000;
        if (delta <= 0) return date1.toLocaleString();
        if (delta / (60 * 60 * 24 * 365) > 0) return delta / (60 * 60 * 24 * 365) + "年前";
        if (delta / (60 * 60 * 24 * 30) > 0) return delta / (60 * 60 * 24 * 30) + "个月前";
        if (delta / (60 * 60 * 24 * 7) > 0) return delta / (60 * 60 * 24 * 7) + "周前";
        if (delta / (60 * 60 * 24) > 0) return delta / (60 * 60 * 24) + "天前";
        if (delta / (60 * 60) > 0) return delta / (60 * 60) + "小时前";
        if (delta / (60) > 0) return delta / (60) + "分钟前";
        return "刚刚";
    }

    public static String getVideoTime(double time) {

        String date;
        int i = (int) time;
        final BigDecimal bigDecimal = new BigDecimal((time - i) * 60).setScale(0, BigDecimal.ROUND_HALF_UP);
        String newNo;
        int len = String.valueOf(bigDecimal).length();
        newNo = ("0" + String.valueOf(bigDecimal)).substring(len - 1, len + 1);
        date = i + ":" + newNo;
        return date;
    }

    public static String MD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();

    }
}
