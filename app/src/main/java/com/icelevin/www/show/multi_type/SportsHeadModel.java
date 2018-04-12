package com.icelevin.www.show.multi_type;

/**
 * Created by ice on 2018/3/28.
 */

public class SportsHeadModel implements Visitable{
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SportsHeadModel{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
