package com.hb.sharelib;

import com.umeng.socialize.UMShareListener;

import java.io.Serializable;

/**
 * 分享model
 *
 * Created by txl on 2017/5/28 0028.
 */
public class ShareModel implements Serializable{
    private String imageUrl;
    private String pageUrl;
    private int sharePosition;
    private String title;
    private String description;
    private UMShareListener listener;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public int getSharePosition() {
        return sharePosition;
    }

    public void setSharePosition(int sharePosition) {
        this.sharePosition = sharePosition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UMShareListener getListener() {
        return listener;
    }

    public void setListener(UMShareListener listener) {
        this.listener = listener;
    }

    @Override
    public String toString() {
        return "ShareModel{" +
                "imageUrl='" + imageUrl + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", sharePosition=" + sharePosition +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", listener=" + listener +
                '}';
    }
}
