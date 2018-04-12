package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/10/10.
 */

public class BaiJiaDataModel implements Serializable{
    private String posterId;

    private String catPathKey;

    private String catName1;

    private List<String> tags;

    private int publishDate;

    private boolean commentCount;

    private List<String> imageUrls;

    private String id;

    private int viewCount;

    private String posterScreenName;

    private String title;

    private String url;

    private String publishDateStr;

    private String content;

    private String catId1;

    private boolean imageURLs;

    private List<String> labels;

    private boolean pDate;

    private boolean subtitle;

    private boolean likeCount;

    private boolean shareCount;

    private boolean commenterScreenName;

    private boolean rating;

    private boolean sellerScreenName;

    private boolean coverUrl;

    private boolean description;

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getCatPathKey() {
        return catPathKey;
    }

    public void setCatPathKey(String catPathKey) {
        this.catPathKey = catPathKey;
    }

    public String getCatName1() {
        return catName1;
    }

    public void setCatName1(String catName1) {
        this.catName1 = catName1;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }

    public boolean isCommentCount() {
        return commentCount;
    }

    public void setCommentCount(boolean commentCount) {
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
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

    public String getCatId1() {
        return catId1;
    }

    public void setCatId1(String catId1) {
        this.catId1 = catId1;
    }

    public boolean isImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(boolean imageURLs) {
        this.imageURLs = imageURLs;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public boolean ispDate() {
        return pDate;
    }

    public void setpDate(boolean pDate) {
        this.pDate = pDate;
    }

    public boolean isSubtitle() {
        return subtitle;
    }

    public void setSubtitle(boolean subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isLikeCount() {
        return likeCount;
    }

    public void setLikeCount(boolean likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isShareCount() {
        return shareCount;
    }

    public void setShareCount(boolean shareCount) {
        this.shareCount = shareCount;
    }

    public boolean isCommenterScreenName() {
        return commenterScreenName;
    }

    public void setCommenterScreenName(boolean commenterScreenName) {
        this.commenterScreenName = commenterScreenName;
    }

    public boolean isRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    public boolean isSellerScreenName() {
        return sellerScreenName;
    }

    public void setSellerScreenName(boolean sellerScreenName) {
        this.sellerScreenName = sellerScreenName;
    }

    public boolean isCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(boolean coverUrl) {
        this.coverUrl = coverUrl;
    }

    public boolean isDescription() {
        return description;
    }

    public void setDescription(boolean description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BaiJiaDataModel{" +
                "posterId='" + posterId + '\'' +
                ", catPathKey='" + catPathKey + '\'' +
                ", catName1='" + catName1 + '\'' +
                ", tags=" + tags +
                ", publishDate=" + publishDate +
                ", commentCount=" + commentCount +
                ", imageUrls=" + imageUrls +
                ", id='" + id + '\'' +
                ", viewCount=" + viewCount +
                ", posterScreenName='" + posterScreenName + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", publishDateStr='" + publishDateStr + '\'' +
                ", content='" + content + '\'' +
                ", catId1='" + catId1 + '\'' +
                ", imageURLs=" + imageURLs +
                ", labels=" + labels +
                ", pDate=" + pDate +
                ", subtitle=" + subtitle +
                ", likeCount=" + likeCount +
                ", shareCount=" + shareCount +
                ", commenterScreenName=" + commenterScreenName +
                ", rating=" + rating +
                ", sellerScreenName=" + sellerScreenName +
                ", coverUrl=" + coverUrl +
                ", description=" + description +
                '}';
    }
}
