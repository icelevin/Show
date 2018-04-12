package com.hb.utils.view.recycler;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;

import java.util.List;

public interface RecyclerLoadListener<T> extends OnResultListener<T> {
	BaseRecyclerAdapter setAdapter();
	void  onLoad(ParametersBean parametersBean, OnResultListener<T> listener);
}
