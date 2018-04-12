package com.hb.utils.view.recycler;

import java.util.List;

/**
 * Created by txl on 2017/7/5 0005.
 */
public class ResultListBean<T> {
    private int code;
    private String message;
    private List<T> resultList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "ResultListBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", resultList=" + resultList +
                '}';
    }
}
