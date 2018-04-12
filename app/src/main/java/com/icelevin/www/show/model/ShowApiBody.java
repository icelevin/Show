package com.icelevin.www.show.model;

import java.util.List;

/**
 * Created by ice on 2017/12/15.
 */

public class ShowApiBody<T> extends BaseBean{
    private int ret_code;

    private List<T> data;

    public void setRet_code(int ret_code){
        this.ret_code = ret_code;
    }
    public int getRet_code(){
        return this.ret_code;
    }
    public void setData(List<T> data){
        this.data = data;
    }
    public List<T> getData(){
        return this.data;
    }
}
