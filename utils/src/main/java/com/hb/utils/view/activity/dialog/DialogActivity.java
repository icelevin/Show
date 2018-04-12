package com.hb.utils.view.activity.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hb.utils.R;
import com.hb.utils.view.activity.BaseActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 全局对话框
 * Created by txl on 2017/5/31 0031.
 */
public class DialogActivity extends BaseActivity implements View.OnClickListener {

    public static Map<UUID,MyDialogClickListener> map = new HashMap<>();

    private UUID uuid;
    private MyDialogClickListener listener;
    private DialogMessage dialogMessage;
    private TextView tv_title,dialogactivity_textview, dialogactivity_button, dialogactivity_cancel_button;


    public static enum DialogButEnum{
        cancel_but,ok_but;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         /* 去除Activity的标题栏 */
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_fragment);

        /** 设置宽度为屏幕的0.9*/
        WindowManager windowManager = getWindowManager();
        /* 获取屏幕宽、高 */
        Display display = windowManager.getDefaultDisplay();
        /* 获取对话框当前的参数值 */
        WindowManager.LayoutParams p = getWindow().getAttributes();
        /* 宽度设置为屏幕的1 */
        p.width = (int) (display.getWidth() * 0.9);
        /* 设置透明度,0.0为完全透明，1.0为完全不透明 */
        p.alpha = 0.95f;
        /* 设置布局参数 */
        getWindow().setAttributes(p);

        /* 设置点击弹框外部不可消失 */
        setFinishOnTouchOutside(false);
        init();
    }

    private void init(){
        uuid = (UUID) getIntent().getSerializableExtra(UUID.class.getName());
        dialogMessage = (DialogMessage) getIntent().getSerializableExtra(DialogMessage.class.getName());

        listener = map.get(uuid);
        if(dialogMessage == null){
            throw new RuntimeException(DialogMessage.class.getName()+"不可为null");
        }

        dialogactivity_textview = (TextView) findViewById(R.id.dialogactivity_textview);
        tv_title = (TextView) findViewById(R.id.tv_title);
        dialogactivity_button = (TextView) findViewById(R.id.dialogactivity_button);
        dialogactivity_cancel_button = (TextView) findViewById(R.id.dialogactivity_cancel_button);
        dialogactivity_button.setOnClickListener(this);
        dialogactivity_cancel_button.setOnClickListener(this);

        if(TextUtils.isEmpty(dialogMessage.getTitle())){
            tv_title.setVisibility(View.INVISIBLE);
        }else{
            tv_title.setText(dialogMessage.getTitle());
            tv_title.setVisibility(View.VISIBLE);
        }

        dialogactivity_textview.setText(dialogMessage.getMessage());

        if(dialogMessage.isShowCancelBut()){
            if(!TextUtils.isEmpty(dialogMessage.getCancelButText())){
                dialogactivity_cancel_button.setText(dialogMessage.getCancelButText());
            }
        }else{
            dialogactivity_cancel_button.setVisibility(View.GONE);
        }


        if(dialogMessage.isShowOkBut()){
            if(!TextUtils.isEmpty(dialogMessage.getOkButText())){
                dialogactivity_button.setText(dialogMessage.getOkButText());
            }
        }else{
            dialogactivity_button.setVisibility(View.GONE);
        }
    }

    public void addListener(UUID uuid,MyDialogClickListener listener){
        map.put(uuid,listener);
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if(listener != null){
            if(v == dialogactivity_button){
                listener.onClick(DialogButEnum.ok_but);
            }else{
                listener.onClick(DialogButEnum.cancel_but);
            }
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        if(!dialogMessage.isCancelable()){
            return;
        }
        map.remove(uuid);
        super.onBackPressed();
    }
}


