package com.icelevin.www.show.common;

import java.io.Serializable;

/**
 * Created by ice on 2017/10/20.
 */

public class InnerKeyWord implements Serializable{
    private String pageToken;
    private String kw;
    private String beginDate;
    private String endDate;
    private String city;
    private String catid;

    @Override
    public String toString() {
        return "InnerKeyWord{" +
                "pageToken='" + pageToken + '\'' +
                ", kw='" + kw + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", city='" + city + '\'' +
                ", catid='" + catid + '\'' +
                '}';
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }
}
