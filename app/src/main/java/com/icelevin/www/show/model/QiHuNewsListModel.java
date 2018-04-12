package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/9/30.
 */

public class QiHuNewsListModel extends BaseModel implements Serializable{
    private List<QiHuNewsDataModel> data;

    public List<QiHuNewsDataModel> getData() {
        return data;
    }

    public void setData(List<QiHuNewsDataModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QiHuNewsListModel{" +
                "data=" + data +
                '}';
    }
}
