package com.icelevin.www.show.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/9/19.
 */

public class Result  implements Serializable{
    private String stat;

    private List<Data> data ;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "stat='" + stat + '\'' +
                ", data=" + data +
                '}';
    }
}
