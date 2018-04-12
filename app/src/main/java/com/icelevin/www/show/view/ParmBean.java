package com.icelevin.www.show.view;

import java.util.List;

/**
 * Created by ice on 2017/12/13.
 */

public class ParmBean {
    private int fragmentParentId;
    private String curPage;
    private boolean hasNextPage = false;

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    private int pageSize = 20;
    private int pageCount = 1;
    private List list;


    public int getFragmentParentId() {
        return fragmentParentId;
    }

    public void setFragmentParentId(int fragmentParentId) {
        this.fragmentParentId = fragmentParentId;
    }

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ParametersBean{" +
                "fragmentParentId=" + fragmentParentId +
                ", curPage=" + curPage +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", list=" + list +
                '}';
    }
}
