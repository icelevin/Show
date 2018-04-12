package com.icelevin.www.show.multi_type;

/**
 * Created by ice on 2018/3/28.
 */

public class SpostsContentModel implements Visitable{
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SpostsContentModel{" +
                "content='" + content + '\'' +
                '}';
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
