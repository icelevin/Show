package com.hb.utils.tools;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xianlai on 2017/1/12 0012.
 */
public class DateFormat {
    public static final String M_dd = "M-dd";
    public static final String M__dd = "M/dd";
    public static final String yyyyMdd = "yyyyMdd";
    public static final String yyyy_M_dd_ = "yyyy/M/dd";
    public static final String yyyy_M_dd = "yyyy-M-dd";
    public static final String yyyyMddHHmmss = "yyyyMddHHmmss";
    public static final String yyyy_M_dd_HH_mm_ss_1 = "yyyy-M-dd HH:mm:ss";
    public static final String yyyy_M_dd_HH_mm_ss = "yyyy/M/dd HH:mm:ss";

    public static final String MM_dd = "MM-dd";
    public static final String MM__dd = "MM/dd";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd_ = "yyyy/MM/dd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyy_MM_dd_HH_mm_ss_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";



    public static String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};


    /**
     * 使用指定的时间获取格式化时间字符串
     *
     * @param template
     * @param date
     * @return
     */
    public static String getFormatDate(String template, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(template, Locale.CHINA);
        return dateFormat.format(date);
    }

    /**
     * 使用当前的时间获取格式化时间字符串
     *
     * @param template
     * @return
     */
    public static String getFormatDate(String template) {
        return getFormatDate(template, new Date());
    }

    /**
     * 使用时间格式字符串来从字符串解析出date类型
     * @param DateValue
     * @param format
     * @return
     */
    public static Date parseString(String DateValue, String format) {
        if(TextUtils.isEmpty(DateValue)){
            return null;
        }
        if(DateValue.contains("T")){
            DateValue = DateValue.replace("T"," ");
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(format,Locale.getDefault());
        Date parse = null;
        try {
            parse = dateFormat.parse(DateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
