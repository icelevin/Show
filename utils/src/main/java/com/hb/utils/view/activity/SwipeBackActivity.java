package com.hb.utils.view.activity;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.hb.utils.view.swipeBack.SwipeBackLayout;

public abstract class SwipeBackActivity extends BaseActivity {

	private SwipeBackLayout mSwipeBackLayout;

	private boolean mOverrideExitAniamtion = true;

	private boolean mIsFinishing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setBackgroundDrawable(new ColorDrawable(0));
		getWindow().getDecorView().setBackgroundColor(getResources().getColor(android.R.color.transparent));
		mSwipeBackLayout = new SwipeBackLayout(this);
		setOverrideExitAniamtion(false);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		mSwipeBackLayout.attachToActivity(this);
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public View findViewById(int id) {
		View v = super.findViewById(id);
		if (v != null)
			return v;
		return mSwipeBackLayout.findViewById(id);
	}

	public SwipeBackLayout getSwipeBackLayout() {
		return mSwipeBackLayout;
	}

	public void setSwipeBackEnable(boolean enable) {
		mSwipeBackLayout.setEnableGesture(enable);
	}

	public void setEdgeFromLeft() {
		final int edgeFlag = SwipeBackLayout.EDGE_LEFT;
		mSwipeBackLayout.setEdgeTrackingEnabled(edgeFlag);
	}

	public void setOverrideExitAniamtion(boolean override) {
		mOverrideExitAniamtion = override;
	}

	public void scrollToFinishActivity() {
		mSwipeBackLayout.scrollToFinishActivity();
	}

	@Override
	public void finish() {
		if (mOverrideExitAniamtion && !mIsFinishing) {
			scrollToFinishActivity();
			mIsFinishing = true;
			return;
		}
		mIsFinishing = false;
		super.finish();
	}
}
