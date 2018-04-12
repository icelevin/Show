package com.icelevin.www.show.utils;

/**
 * Created by ice on 2017/10/12.
 */

public class ErrorCode {

    public static String doErrorCode(String code) {
        String codes = "";
        switch (code) {
            case "100000":
                codes = "服务器内部错误";
                break;
            case "100001":
                codes = "网络错误";
                break;
            case "100002":
                codes = "无搜索结果";
                break;
            case "100004":
                codes = "服务器错误";
                break;
            case "100005":
                codes = "输入关键字有误";
                break;
            case "100700":
                codes = "授权失败";
                break;
            case "100701":
                codes = "当前API已停用";
                break;
            case "100702":
                codes = "账户已停用";
                break;
            case "100703":
                codes = "当前用户过多";
                break;
            case "100704":
                codes = "服务器维护中";
                break;
            case "100705":
                codes = "API不存在";
                break;
            case "100706":
                codes = "请先添加api";
                break;
            case "100802":
                codes = "请求路径错误或者缺少\"time\"参数";
                break;
            case "100803":
                codes = "参数pageToken有误";
                break;

            default:
                break;
        }
        return codes;
    }
}
