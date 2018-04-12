package com.icelevin.www.show.model;

/**
 * Created by ice on 2017/11/8.
 */

public class ImageInfoModel {
    private String ImgHax;
    private String imagePath; //原图位置
    private String scaleImagePath;//压缩图位置
    private String Name; //名称
    private long Size;
    private String ExtensionName;//扩展名
    private String Url;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getSize() {
        return Size;
    }

    public void setSize(long size) {
        Size = size;
    }

    public String getExtensionName() {
        return ExtensionName;
    }

    public void setExtensionName(String extensionName) {
        ExtensionName = extensionName;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getScaleImagePath() {
        return scaleImagePath;
    }

    public void setScaleImagePath(String scaleImagePath) {
        this.scaleImagePath = scaleImagePath;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImgHax() {
        return ImgHax;
    }

    public void setImgHax(String imgHax) {
        ImgHax = imgHax;
    }

    @Override
    public String toString() {
        return "ImageInfoModel{" +
                ", ImgHax='" + ImgHax + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", scaleImagePath='" + scaleImagePath + '\'' +
                ", Name='" + Name + '\'' +
                ", Size=" + Size +
                ", ExtensionName='" + ExtensionName + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }
}
