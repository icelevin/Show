package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2018/1/2.
 */

public class LeiFengListModel extends BaseModel<List<LeiFengListModel.LeiFengList>> {
    public static class LeiFengList implements Serializable {
        private String posterId;

        private int publishDate;

        private String queryWord;

        private int viewCount;

        private String id;

        private String shareCount;

        private String commentCount;

        private String url;

        private int likeCount;

        private String title;

        private String publishDateStr;

        private String parentPosterScreenName;

        private List<String> tags;

        private String content;

        private int favoriteCount;

        private String posterScreenName;

        private List<String> imageUrls;

        public void setPosterId(String posterId) {
            this.posterId = posterId;
        }

        public String getPosterId() {
            return this.posterId;
        }

        public void setPublishDate(int publishDate) {
            this.publishDate = publishDate;
        }

        public int getPublishDate() {
            return this.publishDate;
        }

        public void setQueryWord(String queryWord) {
            this.queryWord = queryWord;
        }

        public String getQueryWord() {
            return this.queryWord;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getViewCount() {
            return this.viewCount;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setShareCount(String shareCount) {
            this.shareCount = shareCount;
        }

        public String getShareCount() {
            return this.shareCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getCommentCount() {
            return this.commentCount;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getLikeCount() {
            return this.likeCount;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setPublishDateStr(String publishDateStr) {
            this.publishDateStr = publishDateStr;
        }

        public String getPublishDateStr() {
            return this.publishDateStr;
        }

        public void setParentPosterScreenName(String parentPosterScreenName) {
            this.parentPosterScreenName = parentPosterScreenName;
        }

        public String getParentPosterScreenName() {
            return this.parentPosterScreenName;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getTags() {
            return this.tags;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setFavoriteCount(int favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public int getFavoriteCount() {
            return this.favoriteCount;
        }

        public void setPosterScreenName(String posterScreenName) {
            this.posterScreenName = posterScreenName;
        }

        public String getPosterScreenName() {
            return this.posterScreenName;
        }

        public void setImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public List<String> getImageUrls() {
            return this.imageUrls;
        }
    }
}
