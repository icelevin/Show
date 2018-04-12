package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/10/12.
 */

public class NewsCommentModel extends BaseModel<List<NewsCommentModel.Data>> {

    public class Data implements Serializable {
        private String rating;

        private List<String> tags;

        private String commenterScreenName;

        private long publishDate;

        private String likeCount;

        private String commentCount;

        private String referId;

        private String imageUrls;

        private String id;

        private String commenterId;

        private String url;

        private String publishDateStr;

        private String content;

        private String source;

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getRating() {
            return this.rating;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public List<String> getTags() {
            return this.tags;
        }

        public void setCommenterScreenName(String commenterScreenName) {
            this.commenterScreenName = commenterScreenName;
        }

        public String getCommenterScreenName() {
            return this.commenterScreenName;
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

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getCommentCount() {
            return this.commentCount;
        }

        public void setReferId(String referId) {
            this.referId = referId;
        }

        public String getReferId() {
            return this.referId;
        }

        public void setImageUrls(String imageUrls) {
            this.imageUrls = imageUrls;
        }

        public String getImageUrls() {
            return this.imageUrls;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setCommenterId(String commenterId) {
            this.commenterId = commenterId;
        }

        public String getCommenterId() {
            return this.commenterId;
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

        public void setSource(String source) {
            this.source = source;
        }

        public String getSource() {
            return this.source;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "rating='" + rating + '\'' +
                    ", tags=" + tags +
                    ", commenterScreenName='" + commenterScreenName + '\'' +
                    ", publishDate=" + publishDate +
                    ", likeCount='" + likeCount + '\'' +
                    ", commentCount='" + commentCount + '\'' +
                    ", referId='" + referId + '\'' +
                    ", imageUrls='" + imageUrls + '\'' +
                    ", id='" + id + '\'' +
                    ", commenterId='" + commenterId + '\'' +
                    ", url='" + url + '\'' +
                    ", publishDateStr='" + publishDateStr + '\'' +
                    ", content='" + content + '\'' +
                    ", source='" + source + '\'' +
                    '}';
        }
    }
}
