package com.hb.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ScrollView;


/**
 * 解决ViewPager在ScrollView中滑动经常失效、无法正常滑动问题。
 * 
 * 解决方法只需要在接近水平滚动时ScrollView不处理事件而交由其子View(即这里的ViewPager)处理即可，重写ScrollView的onInterceptTouchEvent函数
 * @author qian
 *
 */
public class VerticalScrollView extends ScrollView {
	
	  private GestureDetector mGestureDetector;

	public VerticalScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mGestureDetector = new GestureDetector(context, new YScrollDetector());
	}

	public VerticalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(context, new YScrollDetector());
	}

	public VerticalScrollView(Context context) {
		super(context);
		mGestureDetector = new GestureDetector(context, new YScrollDetector());
	}

	 @Override
	    public boolean onInterceptTouchEvent(MotionEvent ev) {
	        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	    }

	    class YScrollDetector extends SimpleOnGestureListener {

	        @Override
	        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
	            /**
	             * if we're scrolling more closer to x direction, return false, let subview to process it
	             */
	            return (Math.abs(distanceY) > Math.abs(distanceX));
	        }
	    }
}
