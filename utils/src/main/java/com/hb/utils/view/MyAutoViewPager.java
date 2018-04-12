package com.hb.utils.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * 自动播放的viewpager
 * 
 * @author hbkj-android
 * 
 */
public class MyAutoViewPager extends CustomViewPager {// JazzyViewPager

	private int currentPager = 0;
	private OnItemClickListener clickListener;
	private GestureDetector gestureDetector;
	private AutoScroller autoScroller;
	private long times = 0;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			currentPager = getCurrentItem();
			if (msg.what == 1) {
				if (currentPager++ < getAdapter().getCount()) {
					setCurrentItem(currentPager, true);
				}
			}
		};
	};

	public MyAutoViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public MyAutoViewPager(Context context) {
		super(context);
		initView(context);
	}

	private void initView(Context context) {
		setClickable(true);
		setMyScroller();
		gestureDetector = new GestureDetector(context, new SimpleOnGestureListener() {

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				if (clickListener != null) {
					clickListener.onItemClick(null, null, getCurrentItem(), 0);
				}
				return super.onSingleTapUp(e);
			}
		});
	}

	public void setOnItemClickListener(OnItemClickListener clickListener) {
		this.clickListener = clickListener;
	}

	/**
	 * 需要调用这个方法设置当前显示的页面
	 * 
	 * @param cruPage
	 */
	public void setCurPage(int cruPage) {
		currentPager = cruPage;
	}


	@Override
	protected void onDisplayHint(int hint) {
		super.onDisplayHint(hint);

	}



	@Override
	public void setVisibility(int visibility) {
		super.setVisibility(visibility);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// 通知其父控件，现在进行的是本控件的操作，不允许拦截
//		getParent().requestDisallowInterceptTouchEvent(true);

		int action = arg0.getAction();

		if (action == MotionEvent.ACTION_DOWN) {
			stop();
		} else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
			start();
		}
		gestureDetector.onTouchEvent(arg0);
		return super.onTouchEvent(arg0);
	}

	/**
	 * 启动切换图片
	 */
	public void start() {
		if (autoScroller != null) {
			handler.removeCallbacks(autoScroller);
			autoScroller = null;
		}
		autoScroller = new AutoScroller();
		
		handler.postDelayed(autoScroller, 5000);
		System.out.println("----auto_viewpager_start----");
	}

	/**
	 * 停止切换图片
	 */
	public void stop() {
		if (autoScroller != null) {
			handler.removeCallbacks(autoScroller);
			autoScroller = null;
		}
		System.out.println("----auto_viewpager_stop----");
	}

	private class AutoScroller implements Runnable {

		@Override
		public void run() {
			if(getAdapter() == null || getAdapter().getCount() < 1){
				return;
			}
			handler.sendEmptyMessage(1);
			handler.postDelayed(this, 5000);
		}
	}

	/**
	 * 使用反射来达到viewpager的平滑滚动
	 */
	private void setMyScroller() {
		try {
			Class<?> viewpager = ViewPager.class;
			Field scroller = viewpager.getDeclaredField("mScroller");
			scroller.setAccessible(true);
			scroller.set(this, new MyScroller(getContext()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class MyScroller extends Scroller {
		public MyScroller(Context context) {
			super(context, new DecelerateInterpolator());
		}

		@Override
		public void startScroll(int startX, int startY, int dx, int dy, int duration) {
			super.startScroll(startX, startY, dx, dy, 700 /* 1 secs */);
		}
	}

	@Override
	public void invalidate() {
		super.invalidate();
	}

	@Override
	public void invalidate(int l, int t, int r, int b) {
		super.invalidate(l, t, r, b);
	}
}
