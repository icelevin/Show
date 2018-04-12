package com.hb.utils.view;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hb.utils.R;


/**
 * 进度框
 * 
 * @author txl
 * 
 */
public class MyProgressDialog {
	private Dialog dialog;
	private TextView textView;

	private View inflate;
	
	private Activity activity;

	public MyProgressDialog(Context context) {
		this.activity = (Activity) context;

		inflate = View.inflate(context, R.layout.view_progress_dialog, null);

		textView = (TextView) inflate.findViewById(R.id.progress_text);


		if (dialog == null) {

			dialog = new Dialog(context, R.style.loading_dialog);
			dialog.setCancelable(true);
			dialog.setCanceledOnTouchOutside(false);

			dialog.setContentView(inflate);
			dialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		}
	}

	public void setBackgroundResource(int resid) {
		inflate.setBackgroundResource(resid);
	}

	/**
	 * 设置是否需要在点击屏幕时对话框消失,默认不消失
	 * 
	 * @param b
	 */
	public void setDialogTouchOutside(boolean b) {
		dialog.setCanceledOnTouchOutside(b);
	}

	/**
	 * 设置是否在按下回退键时对话框是否消失，默认消失
	 * 
	 * @param b
	 */
	public void setDialogCancelable(boolean b) {
		dialog.setCancelable(b);
	}
	
	public Dialog getDialog(){
		return dialog;
	}

	public boolean dialogIsShowing() {
		return dialog != null ? dialog.isShowing() : false;
	}

	public void showDialog() {
		if (dialog != null && !activity.isFinishing()) {
			dialog.show();
		}
	}

	public void dismissDialog() {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

	public void setDialogText(String str) {
		if (textView != null) {
			textView.setText(str);
		}
	}

	public void setDialogText(int resId) {
		if (textView != null) {
			textView.setText(resId);
		}
	}

}
