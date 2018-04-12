package com.icelevin.www.show.model;

import java.io.Serializable;

/**
 * Created by ice on 2018/1/3.
 */

public class CityQiongYou implements Serializable {
    private String city_name;
    private String city_id;

    @Override
    public String toString() {
        return "CityMaoTuYing{" +
                "city_name='" + city_name + '\'' +
                ", city_id='" + city_id + '\'' +
                '}';
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
