package com.hb.utils.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.utils.R;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.MyProgressDialog;

import java.util.List;


public abstract class BaseFragment extends StatedFragment {
    public static MyProgressDialog progressDialog = null;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    public BaseFragment() {
    }
    //解决fragment重叠
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();

        }
    }
    //解决fragment重叠
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if(fragments == null || fragments.size() < 1){
            return;
        }

        for(Fragment fragment : fragments){
            if(fragment == null){
                continue;
            }
            fragment.onHiddenChanged(hidden);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if(fragments == null || fragments.size() < 1){
            return;
        }

        for(Fragment fragment : fragments){
            if(fragment == null){
                continue;
            }
            fragment.onResume();
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if(fragments == null || fragments.size() < 1){
            return;
        }

        for(Fragment fragment : fragments){
            if(fragment == null){
                continue;
            }
            fragment.onPause();
        }
    }

    public MyProgressDialog showProgressDialog() {
        return showProgressDialog(null, false, true);
    }

    public void dismisProgressDialog() {
        if(getActivity() == null || getActivity().isFinishing()){
            return;
        }

        if (progressDialog == null) {
            return;
        } else {
            progressDialog.dismissDialog();
        }
    }

    public MyProgressDialog showProgressDialog(String message) {
        return showProgressDialog(message, false, true);
    }

    public MyProgressDialog showProgressDialog(String message, boolean canCancel) {
        return showProgressDialog(message, false, canCancel);
    }

    public MyProgressDialog showProgressDialog(boolean canCancel) {
        return showProgressDialog(null, false, canCancel);
    }

    public MyProgressDialog showProgressDialog(String message, boolean touchOutside,
                                               boolean canCancel) {
        Context context = getActivity();
        if (progressDialog != null) {
            progressDialog.dismissDialog();
            progressDialog = null;
        }
        progressDialog = new MyProgressDialog(context);
        progressDialog.setDialogTouchOutside(touchOutside);
        progressDialog.setDialogCancelable(canCancel);
        if (!TextUtils.isEmpty(message)) {
            progressDialog.setDialogText(message);
        }
        progressDialog.showDialog();
        return progressDialog;
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 将事件传递给正在显示的fragment
        List<Fragment> fragments = getChildFragmentManager().getFragments();

        if(fragments == null){
            return;
        }
        LogUtils.print("BaseFragment-onActivityResult", "手动调用fragment的onActivityResult方法一次");
        for(int i = 0; i < fragments.size(); i++){
            Fragment fragment = fragments.get(i);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public void showTost(String message){
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextSize(14);
        toast.show();
    }

    public void showTost(Context context,String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextSize(14);
        toast.show();
    }

    public void showTost(String message, int time){
        Toast toast = Toast.makeText(getActivity(), message, time);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextSize(14);
        toast.show();
    }

    @Override
    public void onDestroy() {
        if(progressDialog != null){
            progressDialog.dismissDialog();
        }
        super.onDestroy();
    }
}
