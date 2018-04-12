package com.icelevin.www.show.model;

/**
 * Created by ice on 2018/1/3.
 */

public class CityMaoTuYing {
    private String num;
    private String str;

    @Override
    public String toString() {
        return "CityMaoTuYing{" +
                "num='" + num + '\'' +
                ", str='" + str + '\'' +
                '}';
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
