package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/9/30.
 */

public class QiHuNewsDataModel  implements Serializable{

        private String posterId;

        private String tags;//标签

        private int publishDate;//发布日期时间戳

        private String commentCount;//评论数

        private List<String> imageUrls;//图片

        private String id;//新闻id

        private String posterScreenName;//发布者名称

        private String title;//标题

        private String url;//新闻链接

        private String publishDateStr;//发布时间（UTC时间

        private String content;//新闻内容

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterScreenName() {
        return posterScreenName;
    }

    public void setPosterScreenName(String posterScreenName) {
        this.posterScreenName = posterScreenName;
    }

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

    public String getPublishDateStr() {
        return publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QiHuNewsDataModel{" +
                "posterId='" + posterId + '\'' +
                ", tags='" + tags + '\'' +
                ", publishDate=" + publishDate +
                ", commentCount='" + commentCount + '\'' +
                ", imageUrls=" + imageUrls +
                ", id='" + id + '\'' +
                ", posterScreenName='" + posterScreenName + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", publishDateStr='" + publishDateStr + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
