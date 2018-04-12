package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/10/10.
 */

public class SearchCCTVModel extends BaseModel {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SearchCCTVModel{" +
                "data=" + data +
                '}';
    }


    public static class Data implements Serializable {
        private String posterId;

        private String catPathKey;

        private String catName1;

        private List<String> tags;

        private GeoPoint geoPoint;

        private int publishDate;

        private String likeCount;

        private int commentCount;

        private List<String> imageUrls;

        private String id;

        private int viewCount;

        private String district;

        private String posterScreenName;

        private String title;

        private String url;

        private String publishDateStr;

        private String content;

        private int catId1;

        private String state;

        private String shareCount;

        private String location;

        private String city;

        private List<String> videoUrls;

        private double durationMin;

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

        public void setGeoPoint(GeoPoint geoPoint) {
            this.geoPoint = geoPoint;
        }

        public GeoPoint getGeoPoint() {
            return this.geoPoint;
        }

        public void setPublishDate(int publishDate) {
            this.publishDate = publishDate;
        }

        public int getPublishDate() {
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

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDistrict() {
            return this.district;
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

        public void setCatId1(int catId1) {
            this.catId1 = catId1;
        }

        public int getCatId1() {
            return this.catId1;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return this.state;
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

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
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
                    ", tags='" + tags + '\'' +
                    ", geoPoint=" + geoPoint +
                    ", publishDate=" + publishDate +
                    ", likeCount='" + likeCount + '\'' +
                    ", commentCount=" + commentCount +
                    ", imageUrls=" + imageUrls +
                    ", id='" + id + '\'' +
                    ", viewCount=" + viewCount +
                    ", district='" + district + '\'' +
                    ", posterScreenName='" + posterScreenName + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", publishDateStr='" + publishDateStr + '\'' +
                    ", content='" + content + '\'' +
                    ", catId1=" + catId1 +
                    ", state='" + state + '\'' +
                    ", shareCount='" + shareCount + '\'' +
                    ", location='" + location + '\'' +
                    ", city='" + city + '\'' +
                    ", videoUrls=" + videoUrls +
                    ", durationMin=" + durationMin +
                    '}';
        }

        static class GeoPoint {
            private double lat;

            private double lon;

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLat() {
                return this.lat;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }

            public double getLon() {
                return this.lon;
            }

            @Override
            public String toString() {
                return "GeoPoint{" +
                        "lat=" + lat +
                        ", lon=" + lon +
                        '}';
            }
        }
    }
}
