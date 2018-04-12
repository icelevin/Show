package com.hb.utils.view.recycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 解决 RecyclerView 异常
 * java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view
 * holder adapter positionViewHolder{24ccccaa position=9 id=-1, oldPos=9,
 * pLpos:-1 scrap [attachedScrap] tmpDetached no parent}
 *
 * 解决LinearLayoutManager和ScrollView嵌套
 *
 * @author qian
 * 
 */
public class WrapContentLinearLayoutManager extends LinearLayoutManager {
	private static final String TAG = WrapContentLinearLayoutManager.class.getSimpleName();

	public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
		super(context, orientation, reverseLayout);
	}

	public WrapContentLinearLayoutManager(Context context) {
		super(context);
	}

	/**
	 * 解决 RecyclerView 异常的方法
	 * java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view
	 * holder adapter positionViewHolder{24ccccaa position=9 id=-1, oldPos=9,
	 * pLpos:-1 scrap [attachedScrap] tmpDetached no parent}
	 * @param recycler
	 * @param state
     */
	@Override
	public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
		try {
			super.onLayoutChildren(recycler, state);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}
