package com.icelevin.www.show.ui.tech;

import java.util.List;

/**
 * Created by ice on 2017/12/29.
 */

public class TechnologyTitleModel {
    private List<String> id;

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    private List<String> name;

    @Override
    public String toString() {
        return "TechnologyTitleModel{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
