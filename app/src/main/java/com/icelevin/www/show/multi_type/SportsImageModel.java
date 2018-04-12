package com.icelevin.www.show.multi_type;

import java.util.List;

/**
 * Created by ice on 2018/3/28.
 */

public class SportsImageModel implements Visitable{
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
