package com.icelevin.www.show.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ice on 2017/9/20.
 */
@Entity
public class UserInfo extends BmobUser{
    @Property
    private String sexName;
    @Property
    private int Sex;
    @Property
    private String qqNum;
    @Property
    private String nickName;
    @Property
    private String birthday;
    @Property
    private String address;
    @Property
    private String iconHead;
    @Property
    private boolean isLogin;
    @Generated(hash = 61380613)
    public UserInfo(String sexName, int Sex, String qqNum, String nickName,
            String birthday, String address, String iconHead, boolean isLogin) {
        this.sexName = sexName;
        this.Sex = Sex;
        this.qqNum = qqNum;
        this.nickName = nickName;
        this.birthday = birthday;
        this.address = address;
        this.iconHead = iconHead;
        this.isLogin = isLogin;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public String getSexName() {
        return this.sexName;
    }
    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
    public int getSex() {
        return this.Sex;
    }
    public void setSex(int Sex) {
        this.Sex = Sex;
    }
    public String getQqNum() {
        return this.qqNum;
    }
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getIconHead() {
        return this.iconHead;
    }
    public void setIconHead(String iconHead) {
        this.iconHead = iconHead;
    }
    public boolean getIsLogin() {
        return this.isLogin;
    }
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    
}
