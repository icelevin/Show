package com.icelevin.www.show.ui.sports;

import com.icelevin.www.show.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2018/1/15.
 */

public class QQSportsModel extends BaseModel<List<QQSportsModel.Data>> {
    public static class Data implements Serializable {
        private String posterScreenName;

        private String posterId;

        private String id;

        private int commentCount;

        private List<String> imageUrls;

        private String shareCount;

        private int viewCount;

        private String title;

        private String content;

        private int publishDate;

        private int likeCount;

        private String publishDateStr;

        private String url;

        public void setPosterScreenName(String posterScreenName) {
            this.posterScreenName = posterScreenName;
        }

        public String getPosterScreenName() {
            return this.posterScreenName;
        }

        public void setPosterId(String posterId) {
            this.posterId = posterId;
        }

        public String getPosterId() {
            return this.posterId;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
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

        public void setShareCount(String shareCount) {
            this.shareCount = shareCount;
        }

        public String getShareCount() {
            return this.shareCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getViewCount() {
            return this.viewCount;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setPublishDate(int publishDate) {
            this.publishDate = publishDate;
        }

        public int getPublishDate() {
            return this.publishDate;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getLikeCount() {
            return this.likeCount;
        }

        public void setPublishDateStr(String publishDateStr) {
            this.publishDateStr = publishDateStr;
        }

        public String getPublishDateStr() {
            return this.publishDateStr;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }
    }
}
