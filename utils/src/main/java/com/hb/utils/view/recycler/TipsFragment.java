package com.hb.utils.view.recycler;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hb.utils.R;
import com.hb.utils.view.fragment.BaseFragment;


/**
 * 数据加载状态的显示
 * 
 * @author qian
 * 
 */
public class TipsFragment extends BaseFragment implements OnClickListener {

	public static enum TipType {
		/**
		 * 显示正在加载提示
		 */
		TYPE_LOADING(1),
		/**
		 * 显示失败提示
		 */
		TYPE_FAILE(2),
		/**
		 * 没有数据时的提示
		 */
		TYPE_NULL(3),
		/**
		 * 没有网络时
		 */
		TYPE_NET_ERROR(5),
		/**
		 * 显示自定义提示界面
		 */
		TYPE_CUSTOM_VIEW(4);

		int type = 0;

		private TipType(int type) {
			this.type = type;
		}
	}

	private FrameLayout tip_parent;
	private RefreshListener refreshListener;
	String loadMeg, loadError, loadNull, loadNetError;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tips_fragment, container, false);
		tip_parent = (FrameLayout) view.findViewById(R.id.tip_parent);
		return view;
	}
	


	@Override
	protected void initView() {

	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initListener() {

	}
	
	public void setHind(){
		tip_parent.setVisibility(View.GONE);
	}
	public void setVisiable(int visiable ){
		tip_parent.setVisibility(visiable);
	}
	
	public boolean isShow(){
		return tip_parent.getVisibility() == View.VISIBLE;
	}

	/**
	 * 设置显示消息
	 * 
	 * @param loadMeg
	 * @param loadError
	 * @param loadNull
	 * @param loadNetError
	 */
	public void setHintMessage(String loadMeg, String loadNull, String loadError, String loadNetError) {
		if(getActivity() == null || getActivity().isFinishing()){
			return;
		}

		this.loadMeg = loadMeg;
		this.loadError = loadError;
		this.loadNull = loadNull;
		this.loadNetError = loadNetError;
	}

	/**
	 * 显示空值
	 * 
	 * @param message
	 */
	public void showNull(String message) {
		if(getActivity() == null || getActivity().isFinishing()){
			return;
		}

		tip_parent.setVisibility(View.VISIBLE);
		tip_parent.removeAllViews();
		
		View view = View.inflate(getActivity(), R.layout.layout_load_null, null);
		TextView tv = (TextView) view.findViewById(R.id.tv_load_null);
		if (!TextUtils.isEmpty(loadNull)) {
			tv.setText(loadNull);
		}else if (!TextUtils.isEmpty(message)) {
			tv.setText(message);
		}  
		tip_parent.addView(view);
	}

	/**
	 * 显示失败
	 * 
	 * @param message
	 */
	public void showFaile(TipType type, String message) {
		if(getActivity() == null || getActivity().isFinishing()){
			return;
		}

		tip_parent.setVisibility(View.VISIBLE);
		tip_parent.removeAllViews();
		
		View view = View.inflate(getActivity(), R.layout.layout_load_error, null);
		TextView tv = (TextView) view.findViewById(R.id.tv_error);
		if (!TextUtils.isEmpty(message)) {
			tv.setText(message);
		} else if (type == TipType.TYPE_FAILE) {
			if (!TextUtils.isEmpty(loadError)) {
				tv.setText(loadError);
			}
		} else if (type == TipType.TYPE_NET_ERROR) {
			if (TextUtils.isEmpty(loadNetError)) {
				tv.setText(loadNetError);
			}
		}
		view.setOnClickListener(this);
		tip_parent.addView(view);
	}

	/**
	 * 显示正在加载
	 * 
	 * @param message
	 */
	public void showloading(String message) {
		if(getActivity() == null || getActivity().isFinishing()){
			return;
		}

		tip_parent.setVisibility(View.VISIBLE);
		tip_parent.removeAllViews();
		
		View view = View.inflate(getActivity(), R.layout.layout_loading, null);
		if (!TextUtils.isEmpty(loadMeg)) {
			TextView tv = (TextView) view.findViewById(R.id.tv_loading_tips);
			tv.setText(loadMeg);
		} else if (!TextUtils.isEmpty(message)) {
			TextView tv = (TextView) view.findViewById(R.id.tv_loading_tips);
			tv.setText(message);
		}
		tip_parent.addView(view);
	}

	@Override
	public void onClick(View v) {
		if (refreshListener != null) {
			refreshListener.onRefresh();
		}
	}

	/**
	 * 设置刷新事件
	 * 
	 * @param refreshListener
	 */
	public void setRefreshListener(RefreshListener refreshListener) {
		this.refreshListener = refreshListener;
	}

	/**
	 * 刷新加载
	 * 
	 * @author qian
	 * 
	 */
	public interface RefreshListener {
		void onRefresh();
	}
	
}
