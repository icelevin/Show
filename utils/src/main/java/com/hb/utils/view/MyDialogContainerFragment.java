package com.hb.utils.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hb.utils.R;

/**
 * Created by txl on 2017/5/12 0012.
 */
public class MyDialogContainerFragment extends DialogFragment {
    private View view;
    private View title_parent,dialog_view;
    private TextView dialog_title;
    private Dialog dialog;
    private Runnable runnable;
    private String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_container, null);
        return view;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_container, null);
        dialog = new Dialog(getActivity(), R.style.hint_dialog);
        dialog.setContentView(view);
        windowDeploy(dialog);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    protected void initView() {
        dialog_title = (TextView) view.findViewById(R.id.dialog_title);
        title_parent = view.findViewById(R.id.title_parent);
        dialog_view = view.findViewById(R.id.dialog_view);
        windowDeploy(dialog);
    }

    protected void initData() {
        dialog_title.setText(title);
        if(runnable != null){
            runnable.run();
        }
    }

    protected void initListener() {
        dialog_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
    }

    public void post(Runnable runnable){
        this.runnable = runnable;
    }

    /**
     * 设置是否显示标题
     *
     * @param visible
     */
    public void setTitleVisible(boolean visible) {
        title_parent.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
       this.title = title;
    }

    // 设置窗口显示
    private void windowDeploy(Dialog dialog) {

        // 设置触摸对话框以外的地方取消对话框
        setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow(); // 得到对话框
        window.setWindowAnimations(R.style.dialogAnim); //设置窗口弹出动画
        window.setBackgroundDrawableResource(R.color.transparent); // 设置对话框背景为透明
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        /*
		 * 将对话框的大小按屏幕大小的百分比设置
		 */
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.BOTTOM;
        window.setAttributes(layoutParams);
//		window.setBackgroundDrawable(null);
    }
}
