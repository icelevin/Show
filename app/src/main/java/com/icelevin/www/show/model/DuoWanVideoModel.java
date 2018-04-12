package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/12/13.
 */

public class DuoWanVideoModel extends BaseModel<List<DuoWanVideoModel.Data>> {
    public class Data implements Serializable {
        private String coverUrl;//存储在哪里uri

        private String posterId;//发布者id

        private List<FileOptions> fileOptions;//清晰度

        private String publishDateStr;//发布时间

        private int viewCount;//观看书

        private String url;//页面uri

        private int publishDate;//发布时间

        private String id;//视频id

        private boolean memberOnly;

        private String partList;

        private String title;//标题

        private boolean likeCount;

        private boolean isFree;//是否免费

        private List<String> tags;//标签

        private boolean description;//描述

        private List<String> videoUrls;//视频url

        private String dislikeCount;//不喜欢数

        private String favoriteCount;//喜欢数

        private double durationMin;//总时长

        private boolean commentCount;//回复总数

        private String catPathKey;

        private String posterScreenName;//发布者昵称

        private String mediaType;//视频类型

        private int danmakuCount;//弹幕数量

        private boolean imageUrls;

        private boolean imageURLs;

        private List<String> labels;

        private boolean pDate;

        private boolean subtitle;

        private boolean shareCount;

        private boolean commenterScreenName;

        private boolean rating;

        private boolean sellerScreenName;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getPosterId() {
            return posterId;
        }

        public void setPosterId(String posterId) {
            this.posterId = posterId;
        }

        public List<FileOptions> getFileOptions() {
            return fileOptions;
        }

        public void setFileOptions(List<FileOptions> fileOptions) {
            this.fileOptions = fileOptions;
        }

        public String getPublishDateStr() {
            return publishDateStr;
        }

        public void setPublishDateStr(String publishDateStr) {
            this.publishDateStr = publishDateStr;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(int publishDate) {
            this.publishDate = publishDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isMemberOnly() {
            return memberOnly;
        }

        public void setMemberOnly(boolean memberOnly) {
            this.memberOnly = memberOnly;
        }

        public String getPartList() {
            return partList;
        }

        public void setPartList(String partList) {
            this.partList = partList;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isLikeCount() {
            return likeCount;
        }

        public void setLikeCount(boolean likeCount) {
            this.likeCount = likeCount;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public boolean isDescription() {
            return description;
        }

        public void setDescription(boolean description) {
            this.description = description;
        }

        public List<String> getVideoUrls() {
            return videoUrls;
        }

        public void setVideoUrls(List<String> videoUrls) {
            this.videoUrls = videoUrls;
        }

        public String getDislikeCount() {
            return dislikeCount;
        }

        public void setDislikeCount(String dislikeCount) {
            this.dislikeCount = dislikeCount;
        }

        public String getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(String favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public double getDurationMin() {
            return durationMin;
        }

        public void setDurationMin(double durationMin) {
            this.durationMin = durationMin;
        }

        public boolean isCommentCount() {
            return commentCount;
        }

        public void setCommentCount(boolean commentCount) {
            this.commentCount = commentCount;
        }

        public String getCatPathKey() {
            return catPathKey;
        }

        public void setCatPathKey(String catPathKey) {
            this.catPathKey = catPathKey;
        }

        public String getPosterScreenName() {
            return posterScreenName;
        }

        public void setPosterScreenName(String posterScreenName) {
            this.posterScreenName = posterScreenName;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public int getDanmakuCount() {
            return danmakuCount;
        }

        public void setDanmakuCount(int danmakuCount) {
            this.danmakuCount = danmakuCount;
        }

        public boolean isImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(boolean imageUrls) {
            this.imageUrls = imageUrls;
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

        @Override
        public String toString() {
            return "Data{" +
                    "coverUrl='" + coverUrl + '\'' +
                    ", posterId='" + posterId + '\'' +
                    ", fileOptions=" + fileOptions +
                    ", publishDateStr='" + publishDateStr + '\'' +
                    ", viewCount=" + viewCount +
                    ", url='" + url + '\'' +
                    ", publishDate=" + publishDate +
                    ", id='" + id + '\'' +
                    ", memberOnly=" + memberOnly +
                    ", partList='" + partList + '\'' +
                    ", title='" + title + '\'' +
                    ", likeCount=" + likeCount +
                    ", isFree=" + isFree +
                    ", tags=" + tags +
                    ", description=" + description +
                    ", videoUrls=" + videoUrls +
                    ", dislikeCount='" + dislikeCount + '\'' +
                    ", favoriteCount='" + favoriteCount + '\'' +
                    ", durationMin=" + durationMin +
                    ", commentCount=" + commentCount +
                    ", catPathKey='" + catPathKey + '\'' +
                    ", posterScreenName='" + posterScreenName + '\'' +
                    ", mediaType='" + mediaType + '\'' +
                    ", danmakuCount=" + danmakuCount +
                    ", imageUrls=" + imageUrls +
                    ", imageURLs=" + imageURLs +
                    ", labels=" + labels +
                    ", pDate=" + pDate +
                    ", subtitle=" + subtitle +
                    ", shareCount=" + shareCount +
                    ", commenterScreenName=" + commenterScreenName +
                    ", rating=" + rating +
                    ", sellerScreenName=" + sellerScreenName +
                    '}';
        }
    }

    public class FileOptions {
        private String id;

        private String format;

        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "FileOptions{" +
                    "id='" + id + '\'' +
                    ", format='" + format + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

}
