package com.hb.utils.view.recycler;

import java.util.List;

public class ParametersBean {

    private int fragmentParentId;
    private int curPage = 1;
    private int pageSize = 20;
    private int pageCount = 1;
    private List list;


    public int getFragmentParentId() {
        return fragmentParentId;
    }

    public void setFragmentParentId(int fragmentParentId) {
        this.fragmentParentId = fragmentParentId;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
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
