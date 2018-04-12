package com.hb.utils.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hb.utils.R;
import com.hb.utils.view.activity.BaseActivity;

/**
 * Created by txl on 2017/2/6 0006.
 */
public class TitleView extends LinearLayout {
    private BaseActivity activity;
    private View view;

    public TitleView(Context context) {
        super(context);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        if (context instanceof Activity) {
            this.activity = (BaseActivity) context;
        } else if (context instanceof ContextWrapper) {
            this.activity = (BaseActivity) ((ContextWrapper) context).getBaseContext();
        }

        //阴影的开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(getResources().getDimension(R.dimen.title_elevation));
        }
        setId(R.id.title_view);
        setBackgroundResource(R.color.def_colorPrimary);
        view = View.inflate(context, R.layout.titleview, null);
        addView(view);

        View backlayout = view.findViewById(R.id.back_layout);
        backlayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activity != null || !activity.isFinishing()) {
                    activity.onBackPressed();
                }
            }
        });
    }

    public TextView getTitleTextView(){
        if(activity == null || activity.isFinishing()){
            return null;
        }
       return (TextView) view.findViewById(R.id.title_text);
    }

    public void setTitleText(int textId) {
        if(activity == null || activity.isFinishing()){
            return;
        }
        TextView title_text = (TextView) view.findViewById(R.id.title_text);
        title_text.setText(activity.getResources().getString(textId));
    }

    public void setTitleText(String text) {
        if(activity == null || activity.isFinishing()){
            return;
        }
        TextView title_text = (TextView) view.findViewById(R.id.title_text);
        title_text.setText(text);
    }

    public ImageView getBackImageView(){
        if(activity == null || activity.isFinishing()){
            return null;
        }
        ImageView title_back_image = (ImageView) view.findViewById(R.id.title_back_image);
        return title_back_image;
    }

    public void setBackButtVisiable(boolean isVisiable) {
        View backlayout = view.findViewById(R.id.back_layout);
        if (isVisiable) {
            backlayout.setVisibility(View.VISIBLE);
        } else {
            backlayout.setVisibility(View.INVISIBLE);
        }
    }

    public TextView getTextBut(){
        TextView title_text_buttn = (TextView) view.findViewById(R.id.title_text_buttn);
        return title_text_buttn;
    }


    public void setTextBut(String text, OnClickListener listener) {
        ImageView title_image_buttn = (ImageView) view.findViewById(R.id.title_image_buttn);
        TextView title_text_buttn = (TextView) view.findViewById(R.id.title_text_buttn);

        title_image_buttn.setVisibility(View.GONE);
        title_text_buttn.setVisibility(View.VISIBLE);

        title_text_buttn.setText(text);

        if (listener != null) {
            title_text_buttn.setOnClickListener(listener);
        }
    }

    public void setRightImgBtn(int resId,OnClickListener listener){
        ImageView title_image_buttn = (ImageView) view.findViewById(R.id.title_image_buttn);

        title_image_buttn.setVisibility(View.VISIBLE);
        title_image_buttn.setBackgroundResource(R.drawable.ripple_boderless);
        title_image_buttn.setImageResource(resId);
        if (listener != null) {
            title_image_buttn.setOnClickListener(listener);
        }
    }
}
