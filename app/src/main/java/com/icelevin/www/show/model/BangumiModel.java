package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/12/14.
 */

public class BangumiModel extends BaseModel<BangumiModel.Data> {
    public class KeyValues {
        private String value;

        private String key;

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }
    }

    public class Data implements Serializable {
        private List<String> releaseDates;

        private String subtitle;

        private String rank;

        private String videoUrls;

        private String dislikeCount;

        private String propertyMan;

        private String propertyDev;

        private String chargeWays;

        private String titleAliases;

        private String rating;

        private String catPathKey;

        private String viewCount;

        private String id;

        private List<KeyValues> keyValues;

        private String likeCount;

        private String title;

        private String description;

        private String publishDate;

        private String commentCount;

        private String publishDateStr;

        private List<String> osTypes;

        private String imageUrls;

        private String coverUrl;

        private String url;

        private String tags;

        private int ratingCount;

        private String homeUrls;

        public void setReleaseDates(List<String> releaseDates) {
            this.releaseDates = releaseDates;
        }

        public List<String> getReleaseDates() {
            return this.releaseDates;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getSubtitle() {
            return this.subtitle;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getRank() {
            return this.rank;
        }

        public void setVideoUrls(String videoUrls) {
            this.videoUrls = videoUrls;
        }

        public String getVideoUrls() {
            return this.videoUrls;
        }

        public void setDislikeCount(String dislikeCount) {
            this.dislikeCount = dislikeCount;
        }

        public String getDislikeCount() {
            return this.dislikeCount;
        }

        public void setPropertyMan(String propertyMan) {
            this.propertyMan = propertyMan;
        }

        public String getPropertyMan() {
            return this.propertyMan;
        }

        public void setPropertyDev(String propertyDev) {
            this.propertyDev = propertyDev;
        }

        public String getPropertyDev() {
            return this.propertyDev;
        }

        public void setChargeWays(String chargeWays) {
            this.chargeWays = chargeWays;
        }

        public String getChargeWays() {
            return this.chargeWays;
        }

        public void setTitleAliases(String titleAliases) {
            this.titleAliases = titleAliases;
        }

        public String getTitleAliases() {
            return this.titleAliases;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getRating() {
            return this.rating;
        }

        public void setCatPathKey(String catPathKey) {
            this.catPathKey = catPathKey;
        }

        public String getCatPathKey() {
            return this.catPathKey;
        }

        public void setViewCount(String viewCount) {
            this.viewCount = viewCount;
        }

        public String getViewCount() {
            return this.viewCount;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setKeyValues(List<KeyValues> keyValues) {
            this.keyValues = keyValues;
        }

        public List<KeyValues> getKeyValues() {
            return this.keyValues;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getLikeCount() {
            return this.likeCount;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getPublishDate() {
            return this.publishDate;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getCommentCount() {
            return this.commentCount;
        }

        public void setPublishDateStr(String publishDateStr) {
            this.publishDateStr = publishDateStr;
        }

        public String getPublishDateStr() {
            return this.publishDateStr;
        }

        public void setOsTypes(List<String> osTypes) {
            this.osTypes = osTypes;
        }

        public List<String> getOsTypes() {
            return this.osTypes;
        }

        public void setImageUrls(String imageUrls) {
            this.imageUrls = imageUrls;
        }

        public String getImageUrls() {
            return this.imageUrls;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getCoverUrl() {
            return this.coverUrl;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getTags() {
            return this.tags;
        }

        public void setRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
        }

        public int getRatingCount() {
            return this.ratingCount;
        }

        public void setHomeUrls(String homeUrls) {
            this.homeUrls = homeUrls;
        }

        public String getHomeUrls() {
            return this.homeUrls;
        }
    }
}
