package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2018/1/4.
 */

public class QiongYouScenicModel extends BaseModel<List<QiongYouScenicModel.Data>> {

    public static class KeyValues implements Serializable{
        private String key;

        private String value;

        public void setKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return this.key;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return "KeyValues{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class Data implements Serializable {
        private double rating;

        private int rank;

        private String description;

        private String city;

        private String level;

        private String telephones;

        private List<KeyValues> keyValues;

        private String id;

        private List<String> imageUrls;

        private String country;

        private int commentCount;

        private String url;

        private List<String> tags;

        private String title;

        private String openingHours;

        private String location;

        public void setRating(double rating) {
            this.rating = rating;
        }

        public double getRating() {
            return this.rating;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getRank() {
            return this.rank;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel() {
            return this.level;
        }

        public void setTelephones(String telephones) {
            this.telephones = telephones;
        }

        public String getTelephones() {
            return this.telephones;
        }

        public void setKeyValues(List<KeyValues> keyValues) {
            this.keyValues = keyValues;
        }

        public List<KeyValues> getKeyValues() {
            return this.keyValues;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public List<String> getImageUrls() {
            return this.imageUrls;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountry() {
            return this.country;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getCommentCount() {
            return this.commentCount;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getTags() {
            return this.tags;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setOpeningHours(String openingHours) {
            this.openingHours = openingHours;
        }

        public String getOpeningHours() {
            return this.openingHours;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLocation() {
            return this.location;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "rating=" + rating +
                    ", rank=" + rank +
                    ", description='" + description + '\'' +
                    ", city='" + city + '\'' +
                    ", level='" + level + '\'' +
                    ", telephones='" + telephones + '\'' +
                    ", keyValues=" + keyValues +
                    ", id='" + id + '\'' +
                    ", imageUrls=" + imageUrls +
                    ", country='" + country + '\'' +
                    ", commentCount=" + commentCount +
                    ", url='" + url + '\'' +
                    ", tags=" + tags +
                    ", title='" + title + '\'' +
                    ", openingHours='" + openingHours + '\'' +
                    ", location='" + location + '\'' +
                    '}';
        }
    }


}
