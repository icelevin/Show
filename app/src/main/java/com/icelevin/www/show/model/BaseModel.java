package com.icelevin.www.show.model;

import java.io.Serializable;

/**
 * Created by ice on 2017/9/30.
 */

public class BaseModel<T> implements Serializable{

    private boolean hasNext;//是否有下一页

    private String retcode;//返回的状态码

    private String appCode;//本次查询的api名

    private String dataType;//本次查询的api类型

    private String pageToken;//翻页值

    private String message;

    private int time_sort;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTime_sort() {
        return time_sort;
    }

    public void setTime_sort(int time_sort) {
        this.time_sort = time_sort;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "hasNext=" + hasNext +
                ", retcode='" + retcode + '\'' +
                ", appCode='" + appCode + '\'' +
                ", dataType='" + dataType + '\'' +
                ", pageToken='" + pageToken + '\'' +
                ", message='" + message + '\'' +
                ", time_sort=" + time_sort +
                ", data=" + data +
                '}';
    }
}
