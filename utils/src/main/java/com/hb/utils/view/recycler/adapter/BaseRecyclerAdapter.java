package com.hb.utils.view.recycler.adapter;

import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;

public class BaseRecyclerAdapter<T extends Serializable, V extends ViewHolder> extends Adapter<V> {

	protected Context context;
	protected List<T> list;
	protected OnItemClickListener itemClickListener;

	public BaseRecyclerAdapter(Context context, List<T> list, OnItemClickListener itemClickListener) {
		this.context = context;
		this.list = list;
		this.itemClickListener = itemClickListener;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void update(List<T> list) {
		this.list = list;
	}

	public void add(List<T> list) {
		if (this.list == null) {
			this.list = list;
		} else {
			this.list.addAll(list);
		}
	}
	
	public T getItem(int index){
		if(list == null || list.size() <= index){
			return null;
		}
		return list.get(index);
	}

	@Override
	public int getItemCount() {
		int size = list == null ? 0 : list.size();
		return size;
	}

	public List<T> getList() {
		return list;
	}

	public void setOnItemClickListener(OnItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}


	@Override
	public void onBindViewHolder(final V arg0, final int position) {
		if(itemClickListener != null){
			arg0.itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					itemClickListener.onItemClick(null, arg0.itemView, position, 0);
				}
			});
		}
	}

	@Override
	public V onCreateViewHolder(ViewGroup arg0, int arg1) {
		return null;
	}
}
