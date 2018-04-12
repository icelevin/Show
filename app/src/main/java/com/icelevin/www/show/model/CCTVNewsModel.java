package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/10/10.
 */

public class CCTVNewsModel extends BaseModel<List<CCTVNewsModel.Data>> {
    public class Data implements Serializable {
        private String posterId;

        private String catPathKey;

        private String catName1;

        private List<String> tags;

        private long publishDate;

        private String likeCount;

        private int commentCount;

        private List<String> imageUrls;

        private String id;

        private int viewCount;

        private String posterScreenName;

        private String title;

        private String url;

        private String publishDateStr;

        private String content;

        private String catId1;

        private String shareCount;

        private String location;

        private List<String> videoUrls;

        private double durationMin;

        private String city;
        private String district;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public void setPosterId(String posterId) {
            this.posterId = posterId;
        }

        public String getPosterId() {
            return this.posterId;
        }

        public void setCatPathKey(String catPathKey) {
            this.catPathKey = catPathKey;
        }

        public String getCatPathKey() {
            return this.catPathKey;
        }

        public void setCatName1(String catName1) {
            this.catName1 = catName1;
        }

        public String getCatName1() {
            return this.catName1;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getTags() {
            return this.tags;
        }

        public void setPublishDate(long publishDate) {
            this.publishDate = publishDate;
        }

        public long getPublishDate() {
            return this.publishDate;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getLikeCount() {
            return this.likeCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getCommentCount() {
            return this.commentCount;
        }

        public void setImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public List<String> getImageUrls() {
            return this.imageUrls;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getViewCount() {
            return this.viewCount;
        }

        public void setPosterScreenName(String posterScreenName) {
            this.posterScreenName = posterScreenName;
        }

        public String getPosterScreenName() {
            return this.posterScreenName;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        public void setPublishDateStr(String publishDateStr) {
            this.publishDateStr = publishDateStr;
        }

        public String getPublishDateStr() {
            return this.publishDateStr;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setCatId1(String catId1) {
            this.catId1 = catId1;
        }

        public String getCatId1() {
            return this.catId1;
        }

        public void setShareCount(String shareCount) {
            this.shareCount = shareCount;
        }

        public String getShareCount() {
            return this.shareCount;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLocation() {
            return this.location;
        }

        public void setVideoUrls(List<String> videoUrls) {
            this.videoUrls = videoUrls;
        }

        public List<String> getVideoUrls() {
            return this.videoUrls;
        }

        public void setDurationMin(double durationMin) {
            this.durationMin = durationMin;
        }

        public double getDurationMin() {
            return this.durationMin;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "posterId='" + posterId + '\'' +
                    ", catPathKey='" + catPathKey + '\'' +
                    ", catName1='" + catName1 + '\'' +
                    ", tags=" + tags +
                    ", publishDate=" + publishDate +
                    ", likeCount='" + likeCount + '\'' +
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
                    ", shareCount='" + shareCount + '\'' +
                    ", location='" + location + '\'' +
                    ", videoUrls=" + videoUrls +
                    ", durationMin=" + durationMin +
                    ", city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    '}';
        }
    }
}
