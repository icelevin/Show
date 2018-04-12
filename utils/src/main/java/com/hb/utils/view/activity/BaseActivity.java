package com.hb.utils.view.activity;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.utils.R;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.tools.Utils;
import com.hb.utils.view.MyProgressDialog;

import java.util.List;

/**
 * Activity 基类
 */
public class BaseActivity extends AppCompatActivity {
    private MyProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public MyProgressDialog showProgressDialog() {
        return showProgressDialog(null, false, true);
    }

    public void dismisProgressDialog() {
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
        if (progressDialog != null) {
            progressDialog.dismissDialog();
            progressDialog = null;
        }
        progressDialog = new MyProgressDialog(this);
        progressDialog.setDialogTouchOutside(touchOutside);
        progressDialog.setDialogCancelable(canCancel);
        if (!TextUtils.isEmpty(message)) {
            progressDialog.setDialogText(message);
        }
        progressDialog.showDialog();
        return progressDialog;
    }

    public void showTost(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextSize(14);
        toast.show();
    }

    public void showTost(String message, int time) {
        Toast toast = Toast.makeText(this, message, time);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextSize(14);
        toast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 将事件传递给正在显示的fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return;
        }
        LogUtils.print("BaseActivity-onActivityResult", "手动调用fragment的onActivityResult方法一次");

        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (fragment != null) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
