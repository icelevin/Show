package com.icelevin.www.show.model;

/**
 * Created by ice on 2017/11/9.
 */

public class DiseaseModel {
    private int id;
    private String name;
    private boolean isCheck=false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "DiseaseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
