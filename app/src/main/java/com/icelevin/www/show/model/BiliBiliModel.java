package com.icelevin.www.show.model;

import java.io.Serializable;

/**
 * Created by ice on 2017/12/15.
 */

public class BiliBiliModel extends ShowApiBody<BiliBiliModel.Data> {
    public class Data implements Serializable {
        private int uid;

        private String face;

        private String is_bn;

        private int short_id;

        private String uname;

        private String link;

        private int stream_id;

        private String pic;

        private String area_v2_name;

        private int online;

        private int roomid;

        private String title;

        private String cover;

        private int area;

        private String areaName;

        private String user_cover;

        private int area_v2_id;

        private int is_tv;

        private String area_v2_parent_name;

        private int area_v2_parent_id;

        private String system_cover;

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getUid() {
            return this.uid;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getFace() {
            return this.face;
        }

        public void setIs_bn(String is_bn) {
            this.is_bn = is_bn;
        }

        public String getIs_bn() {
            return this.is_bn;
        }

        public void setShort_id(int short_id) {
            this.short_id = short_id;
        }

        public int getShort_id() {
            return this.short_id;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getUname() {
            return this.uname;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLink() {
            return this.link;
        }

        public void setStream_id(int stream_id) {
            this.stream_id = stream_id;
        }

        public int getStream_id() {
            return this.stream_id;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPic() {
            return this.pic;
        }

        public void setArea_v2_name(String area_v2_name) {
            this.area_v2_name = area_v2_name;
        }

        public String getArea_v2_name() {
            return this.area_v2_name;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public int getOnline() {
            return this.online;
        }

        public void setRoomid(int roomid) {
            this.roomid = roomid;
        }

        public int getRoomid() {
            return this.roomid;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCover() {
            return this.cover;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public int getArea() {
            return this.area;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getAreaName() {
            return this.areaName;
        }

        public void setUser_cover(String user_cover) {
            this.user_cover = user_cover;
        }

        public String getUser_cover() {
            return this.user_cover;
        }

        public void setArea_v2_id(int area_v2_id) {
            this.area_v2_id = area_v2_id;
        }

        public int getArea_v2_id() {
            return this.area_v2_id;
        }

        public void setIs_tv(int is_tv) {
            this.is_tv = is_tv;
        }

        public int getIs_tv() {
            return this.is_tv;
        }

        public void setArea_v2_parent_name(String area_v2_parent_name) {
            this.area_v2_parent_name = area_v2_parent_name;
        }

        public String getArea_v2_parent_name() {
            return this.area_v2_parent_name;
        }

        public void setArea_v2_parent_id(int area_v2_parent_id) {
            this.area_v2_parent_id = area_v2_parent_id;
        }

        public int getArea_v2_parent_id() {
            return this.area_v2_parent_id;
        }

        public void setSystem_cover(String system_cover) {
            this.system_cover = system_cover;
        }

        public String getSystem_cover() {
            return this.system_cover;
        }
    }
}
