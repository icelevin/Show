package com.hb.utils.tools;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * Created by txl on 2017/3/6 0006.
 */
public class DialogUtils {

    public static Dialog showMessageDialog(Context context, String title, String message) {
        return showMessageDialog(context,0,title,message,null,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    public static Dialog showMessageDialog(Context context, String title, String message,DialogInterface.OnClickListener listener) {
        return showMessageDialog(context,0,title,message,null,listener);
    }

    public static Dialog showMessageDialog(Context context, int iconResId, String title, String message, String butText,DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        if(!TextUtils.isEmpty(title)){
            builder.setTitle(title);
        }
        if(iconResId > 0){
            builder.setIcon(iconResId);
        }

        if(TextUtils.isEmpty(butText)){
            butText = "确定";
        }

        builder.setPositiveButton(butText,listener);
        return builder.create();
    }
}
