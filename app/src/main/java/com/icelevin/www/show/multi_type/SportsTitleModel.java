package com.icelevin.www.show.multi_type;

/**
 * Created by ice on 2018/3/28.
 */

public class SportsTitleModel implements Visitable {
    private String head;
    private String name;
    private String time;

    @Override
    public String toString() {
        return "SportsTitleModel{" +
                "head='" + head + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
