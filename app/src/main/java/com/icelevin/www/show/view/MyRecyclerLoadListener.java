package com.icelevin.www.show.view;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.view.recycler.ParametersBean;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;

/**
 * Created by ice on 2017/12/13.
 */

public interface MyRecyclerLoadListener<T> extends OnResultListener<T> {
    BaseRecyclerAdapter setAdapter();
    void  onLoad(ParmBean parametersBean, OnResultListener<T> listener);
}
