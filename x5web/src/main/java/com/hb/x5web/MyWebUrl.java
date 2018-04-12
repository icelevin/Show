package com.hb.x5web;

import java.io.Serializable;

/**
 * Created by txl on 2017/7/7 0007.
 */
public class MyWebUrl implements Serializable{
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MyWebUrl{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
