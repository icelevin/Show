package com.hb.utils.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hb.utils.R;

/**
 * 菜单dialog
 *
 * @author qian
 */
public class MyMenuDialog extends DialogFragment {
    public final static String title = "title";
    public final static String icos = "icos";
    public final static String items = "items";

    private TextView dialog_title;
    private ListView listview;
    private BaseAdapter adapter;
    private View dialog_view;

    private OnItemClickListener clickListener;
    private OnMenuDialogDismissListener dismissListener;

    private View title_parent;

    // 适配器的数据源
    private List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

    private String[] key = new String[]{"icon", "text"};
    private View view;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.hint_dialog);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_menu, null);
        dialog.setContentView(view);
        windowDeploy(dialog);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        initView();
        initData();
    }

    private void initView() {
        dialog_title = (TextView) view.findViewById(R.id.dialog_title);
        listview = (ListView) view.findViewById(R.id.listview);
        title_parent = view.findViewById(R.id.title_parent);
        dialog_view = view.findViewById(R.id.dialog_view);
    }

    private void initData() {
        String titleStr = getArguments().getString(title);
        int[] icoArray = getArguments().getIntArray(icos);
        String[] itemArray = getArguments().getStringArray(items);

        setTitle(titleStr);
        setData(icoArray, itemArray);

        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (clickListener != null) {
                    clickListener.onItemClick(parent, view, position, id);
                }
                dismissAllowingStateLoss();
            }
        });

        dialog_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });

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
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.BOTTOM;
        window.setAttributes(layoutParams);
//		window.setBackgroundDrawable(null);
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
    private void setTitle(String title) {
        if (TextUtils.isEmpty(title)) {
            setTitleVisible(false);
        } else {
            dialog_title.setText(title);
        }
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 为listView设置数据适配器
     */
    private void setData(int[] icos, String[] items) {
        if (adapter == null) {
            for (int i = 0; i < items.length; i++) {
                HashMap<String, Object> hashMap = new HashMap<String, Object>();
                if (icos != null && icos.length == items.length) {
                    hashMap.put(key[0], icos[i]);
                }
                hashMap.put(key[1], items[i]);
                data.add(hashMap);
            }
            adapter = new SimpleAdapter(getActivity(), data, R.layout.item_menu_list, new String[]{key[0], key[1]}, new int[]{R.id.icon, R.id.text});
        }
        // 设置数据适配器
        listview.setAdapter(adapter);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(dismissListener != null){
            dismissListener.onDismiss();
        }
    }


    /**
     * 当item被点击时的事件
     *
     * @param clickListener
     */
    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnDialogDismissListener(OnMenuDialogDismissListener dismissListener){
        this.dismissListener = dismissListener;
    }

    public interface OnMenuDialogDismissListener{
       void  onDismiss();
    }
}
