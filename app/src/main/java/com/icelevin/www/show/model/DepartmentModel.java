package com.icelevin.www.show.model;

import java.util.List;

/**
 * Created by ice on 2017/11/9.
 */

public class DepartmentModel {
    private String name;
    private List<DiseaseModel> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DiseaseModel> getList() {
        return list;
    }

    public void setList(List<DiseaseModel> list) {
        this.list = list;
    }
}
