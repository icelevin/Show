package com.hb.utils.view.recycler.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hb.utils.R;


public class RecyclerFootView extends RelativeLayout implements OnClickListener {

	private boolean hasLoadMore;
	private LoadMoreRecyclerView.LoadMoreListener listener;
//	private Context context;
	private ProgressBar progressbar;
	private TextView tv_state;
	private View loadingView;
	private String loadStr = "正在努力加载~";

	public RecyclerFootView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}

	public RecyclerFootView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public RecyclerFootView(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		loadingView = LayoutInflater.from(context).inflate(R.layout.foot_loading, this, false);
		progressbar = (ProgressBar) loadingView.findViewById(R.id.progressbar);
		tv_state = (TextView) loadingView.findViewById(R.id.tv_state);

		loadingView.setOnClickListener(this);

		addView(loadingView);
	}

	@Override
	public void onClick(View v) {
		
		if (hasLoadMore && progressbar.getVisibility() == View.GONE && listener != null) {
			showloading();
			listener.onLoadMore();
		}
	}
	
	public void showDef(){
		this.hasLoadMore = true;
		progressbar.setVisibility(View.GONE);
		tv_state.setText("点击加载更多");
	}
	
	/**
	 * 显示正在加载
	 */
	public void showloading() {
		progressbar.setVisibility(View.VISIBLE);
		tv_state.setText(loadStr);
	}

	/**
	 * 显示消息
	 * 
	 * @param message
	 */
	public void showError(final String message, boolean hasLoadMore) {
		this.hasLoadMore = hasLoadMore;
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				progressbar.setVisibility(View.GONE);
				tv_state.setText(message);
			}
		}, 300);
	}

	public void setLoadMoreListener(LoadMoreRecyclerView.LoadMoreListener listener) {
		this.listener = listener;
	}
}
