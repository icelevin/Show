package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * 小影视频model
 * Created by ice on 2017/9/28.
 */

public class XyVideoModel extends  BaseModel implements Serializable {

    private List<Data> data;


    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "XyVideoModel{" +
                ", data=" + data +
                '}';
    }

    public static class Data implements Serializable {
        private String catPathKey;

        private String dislikeCount;

        private String coverUrl;

        private int likeCount;

        private int commentCount;

        private String partList;

        private String id;

        private String title;

        private String publishDateStr;

        private String mediaType;

        private String isFree;

        private String location;

        private String favoriteCount;

        private List<String> videoUrls;

        private String posterId;

        private String description;

        private String catName1;

        private List<String> tags;

        private int publishDate;

        private String danmakuCount;

        private int viewCount;

        private String posterScreenName;

        private String url;

        private String memberOnly;

        private String catId1;

        private int durationMin;

        public String getCatPathKey() {
            return catPathKey;
        }

        public void setCatPathKey(String catPathKey) {
            this.catPathKey = catPathKey;
        }

        public String getDislikeCount() {
            return dislikeCount;
        }

        public void setDislikeCount(String dislikeCount) {
            this.dislikeCount = dislikeCount;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getPartList() {
            return partList;
        }

        public void setPartList(String partList) {
            this.partList = partList;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublishDateStr() {
            return publishDateStr;
        }

        public void setPublishDateStr(String publishDateStr) {
            this.publishDateStr = publishDateStr;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public String getIsFree() {
            return isFree;
        }

        public void setIsFree(String isFree) {
            this.isFree = isFree;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(String favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public List<String> getVideoUrls() {
            return videoUrls;
        }

        public void setVideoUrls(List<String> videoUrls) {
            this.videoUrls = videoUrls;
        }

        public String getPosterId() {
            return posterId;
        }

        public void setPosterId(String posterId) {
            this.posterId = posterId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getDanmakuCount() {
            return danmakuCount;
        }

        public void setDanmakuCount(String danmakuCount) {
            this.danmakuCount = danmakuCount;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMemberOnly() {
            return memberOnly;
        }

        public void setMemberOnly(String memberOnly) {
            this.memberOnly = memberOnly;
        }

        public String getCatId1() {
            return catId1;
        }

        public void setCatId1(String catId1) {
            this.catId1 = catId1;
        }

        public int getDurationMin() {
            return durationMin;
        }

        public void setDurationMin(int durationMin) {
            this.durationMin = durationMin;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "catPathKey='" + catPathKey + '\'' +
                    ", dislikeCount='" + dislikeCount + '\'' +
                    ", coverUrl='" + coverUrl + '\'' +
                    ", likeCount=" + likeCount +
                    ", commentCount=" + commentCount +
                    ", partList='" + partList + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", publishDateStr='" + publishDateStr + '\'' +
                    ", mediaType='" + mediaType + '\'' +
                    ", isFree='" + isFree + '\'' +
                    ", location='" + location + '\'' +
                    ", favoriteCount='" + favoriteCount + '\'' +
                    ", videoUrls=" + videoUrls +
                    ", posterId='" + posterId + '\'' +
                    ", description='" + description + '\'' +
                    ", catName1='" + catName1 + '\'' +
                    ", tags=" + tags +
                    ", publishDate=" + publishDate +
                    ", danmakuCount='" + danmakuCount + '\'' +
                    ", viewCount=" + viewCount +
                    ", posterScreenName='" + posterScreenName + '\'' +
                    ", url='" + url + '\'' +
                    ", memberOnly='" + memberOnly + '\'' +
                    ", catId1='" + catId1 + '\'' +
                    ", durationMin=" + durationMin +
                    '}';
        }
    }

}
