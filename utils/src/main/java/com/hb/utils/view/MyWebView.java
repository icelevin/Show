package com.hb.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ZoomButtonsController;

import com.hb.utils.BaseApplication;
import com.hb.utils.tools.LogUtils;

import java.lang.reflect.Field;

/**
 * Created by txl on 2017/5/25 0025.
 */
public class MyWebView extends WebView {
    private Context context;

    public MyWebView(Context context) {
        super(BaseApplication.getAppContext());
        init(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(BaseApplication.getAppContext(), attrs);
        init(context);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(BaseApplication.getAppContext(), attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;

         getSettings().setJavaScriptEnabled(true);
         setHorizontalScrollBarEnabled(false);
         getSettings().setSupportZoom(true);
         getSettings().setBuiltInZoomControls(true);
         setHorizontalScrollbarOverlay(true);
        // 设置数据库可用
         getSettings().setDatabaseEnabled(true);
         getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        // // 设置可以访问文件
         getSettings().setAllowFileAccess(true);
        // // 设置 缓存模式
         getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // // 开启 DOM storage API 功能 这样你就可以在返回前一个页面的时候不刷新了
         getSettings().setDomStorageEnabled(true);
        // // H5的缓存使用
         getSettings().setAppCacheEnabled(true);
        // // webview自适应
        //  getSettings().setLoadWithOverviewMode(true);
        //  getSettings().setUseWideViewPort(true);// 将图片调整到适合webview的大小
        // //
        // 多窗口，浏览器总在一个新打开、未命名的窗口中载入目标文档，但必须要重写WebChromeClient的onCreateWindow方法
        //  getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        // // 支持内容重新布局

        // 用于判断是否为Android 3.0系统, 然后隐藏缩放控件
        if (android.os.Build.VERSION.SDK_INT >= 11) {
             getSettings().setDisplayZoomControls(false);
        } else {
            setZoomControlGone(this); // Android 3.0(11) 以下使用以下方法
        }
    }

    @Override
    public void loadUrl(String url) {
        LogUtils.print(MyWebView.class.getName(),"url:"+url);
        super.loadUrl(url);
    }

    // 去掉WebView的缩放控件 Android 3.0(11) 以下使用以下方法:
    // 利用java的反射机制
    public void setZoomControlGone(View view) {
        Class<WebView> classType;
        Field field;
        try {
            classType = WebView.class;
            field = classType.getDeclaredField("mZoomButtonsController");
            field.setAccessible(true);
            ZoomButtonsController mZoomButtonsController = new ZoomButtonsController(view);
            mZoomButtonsController.getZoomControls().setVisibility(View.GONE);
            try {
                field.set(view, mZoomButtonsController);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
