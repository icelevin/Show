package com.hb.utils.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hb.utils.R;

/**
 * 底部按钮
 * 
 * @author tangxianlai
 * 
 */
public class Buttom_button extends RelativeLayout {

	private boolean checked = false;
	private boolean touchCheck = false;

	private int image;
	private int check_image;
	private String text;
	private ImageView imageView;
	private TextView textView;


	public Buttom_button(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public Buttom_button(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray obtainStyledAttributes = context.obtainStyledAttributes(
				attrs, R.styleable.buttom_button);

		text = obtainStyledAttributes.getString(R.styleable.buttom_button_text);

		image = obtainStyledAttributes.getResourceId(
				R.styleable.buttom_button_image, 0);

		check_image = obtainStyledAttributes.getResourceId(
				R.styleable.buttom_button_check_image, 0);

		obtainStyledAttributes.recycle();

		init(context);
	}

	public Buttom_button(Context context) {
		super(context);
		init(context);
	}

	
	
	
	public void init(Context context) {
		View inflate = View.inflate(context, R.layout.view_buttom_button, null);

		imageView = (ImageView) inflate.findViewById(R.id.botton_image);
		textView = (TextView) inflate.findViewById(R.id.button_text);

		textView.setText(text);
		imageView.setImageResource(image);
		changeView();
		addView(inflate);
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (!getChecked()) {
				touchCheck = true;
				changeView();
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			float x2 = event.getX();
			float y2 = event.getY();

			touchCheck = false;

			//如果最后离开的位置在按钮之内
			if ((x2 >= 0 && x2 <= getWidth()) && (y2 >= 0 && y2 <= getHeight())) {
				
				//这里当按下按钮后有对话框弹出来时，按钮接收不到up事件了，需要复原
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						if (!getChecked()) {
							changeView();
						}
					}
				}, 100);
				return super.onTouchEvent(event);
			}

			if (!getChecked()) {
				changeView();
			}
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}

	public void setChecked(boolean b) {
		if (checked != b) {
			checked = b;
			changeView();
		}
	}

	public void setChecked() {

		checked = !checked;
		changeView();
	}

	public boolean getChecked() {
		return checked;
	}

	public void changeView() {

		if (checked || touchCheck) {
			imageView.setImageResource(check_image);
			textView.setTextColor(getResources().getColor(R.color.def_colorAccent));
		} else {
			imageView.setImageResource(image);
			textView.setTextColor(getResources().getColor(R.color.dimgrey_text_normal));
		}
	}
}
