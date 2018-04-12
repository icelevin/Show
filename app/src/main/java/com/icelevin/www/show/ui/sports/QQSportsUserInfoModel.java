package com.icelevin.www.show.ui.sports;

import com.icelevin.www.show.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2018/1/16.
 */

public class QQSportsUserInfoModel extends BaseModel<List<QQSportsUserInfoModel.Data>> {
    public static class Data implements Serializable {
        private String likeCount;

        private String postCount;

        private String fansCount;

        private String biography;

        private String id;

        private String url;

        private String type;

        private String avatarUrl;

        private String screenName;

        private String gender;

        private String viewCount;

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getLikeCount() {
            return this.likeCount;
        }

        public void setPostCount(String postCount) {
            this.postCount = postCount;
        }

        public String getPostCount() {
            return this.postCount;
        }

        public void setFansCount(String fansCount) {
            this.fansCount = fansCount;
        }

        public String getFansCount() {
            return this.fansCount;
        }

        public void setBiography(String biography) {
            this.biography = biography;
        }

        public String getBiography() {
            return this.biography;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getAvatarUrl() {
            return this.avatarUrl;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

        public String getScreenName() {
            return this.screenName;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return this.gender;
        }

        public void setViewCount(String viewCount) {
            this.viewCount = viewCount;
        }

        public String getViewCount() {
            return this.viewCount;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "likeCount='" + likeCount + '\'' +
                    ", postCount='" + postCount + '\'' +
                    ", fansCount='" + fansCount + '\'' +
                    ", biography='" + biography + '\'' +
                    ", id='" + id + '\'' +
                    ", url='" + url + '\'' +
                    ", type='" + type + '\'' +
                    ", avatarUrl='" + avatarUrl + '\'' +
                    ", screenName='" + screenName + '\'' +
                    ", gender='" + gender + '\'' +
                    ", viewCount='" + viewCount + '\'' +
                    '}';
        }
    }
}
