package com.hb.utils.tools;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by txl on 2017/3/6 0006.
 */
public class CheckUtils {
    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
        if(TextUtils.isEmpty(str)){
            return false;
        }
        return isChinaPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(1[3-9])\\d{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 正则表达式校验邮箱
     * @param emaile 待匹配的邮箱
     * @return 匹配成功返回true 否则返回false;
     */
    public static boolean checkEmaile(String emaile){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(emaile);
        //进行正则匹配
        return m.matches();
    }

    /**
     * 校验密码是否符合规则
     * @param pwd
     * @return
     */
    public static String checkPwd(String pwd){

        if(!pwd.matches(".*\\d+.*")){
            return "密码中必须包含数字";
        }
        if(!pwd.matches(".*[a-zA-Z]+.*")){
            return "密码中必须包含字母";
        }
        if(pwd.length() < 6 || pwd.length() > 16){
            return "密码长度不符合规则";
        }
        return null;
    }

    /**
     * 判断是否包含中文
     * @param str
     * @return
     */
    public static boolean isChineseChar(String str) {
        boolean temp = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            temp = true;
        }
        return temp;
    }
}
