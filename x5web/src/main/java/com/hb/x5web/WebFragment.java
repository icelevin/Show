package com.hb.x5web;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.utils.tools.LogUtils;
import com.hb.utils.tools.Utils;
import com.hb.utils.view.MyWebView;
import com.hb.utils.view.fragment.BaseFragment;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;

/**
 * Created by txl on 2017/7/7 0007.
 */
public class WebFragment extends BaseFragment {
    private boolean isDebug = false;

    private View test_layout;
    private EditText edit_text;
    private ProgressBar progressBar;
    private FrameLayout web_parent;

    private X5WebView mWebView;
    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;
    private static final int uploadFilesRequestCode = 0x111;

    public static final String urlKey = "webviewurkkey";
    private String mIntentUrl;
    private String mHomeUrl = "http://www.hbjk114.com";
    private onReceivedTitleAndIconListener listener;
    private WebViewInitListener initListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    protected void initView() {
//        mIntentUrl = getActivity().getIntent().getStringExtra(urlKey);

        web_parent = (FrameLayout) getView().findViewById(R.id.web_x5_parent);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
        edit_text = (EditText) getView().findViewById(R.id.edit_text);
        test_layout = getView().findViewById(R.id.test_layout);

        if(!isDebug){
            setHiddenUrl();
        }
    }

    public void setHiddenUrl(){
        if(test_layout == null){
            return;
        }
        test_layout.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        mWebView = new X5WebView(getActivity(), null);

        web_parent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void initListener() {
        // 用来控制webView的页面显示替换
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                LogUtils.print(WebFragment.class.getName(), "url=" + url);
                if (URLUtil.isHttpUrl(url) || URLUtil.isHttpsUrl(url)) {
                    if (edit_text != null) {
                        edit_text.setText(url);
                    }
                    webView.loadUrl(url);
                }
                return true;
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);

                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
                if (edit_text != null) {
                    edit_text.setText(webView.getUrl());
                }
            }

            @Override
            public void onReceivedIcon(WebView webView, Bitmap bitmap) {
                super.onReceivedIcon(webView, bitmap);
                if (listener != null) {
                    listener.onReceivedIcon(webView, bitmap);
                }
            }

            @Override
            public void onReceivedTitle(WebView webView, String title) {
                super.onReceivedTitle(webView, title);
                // 判断只有包含了中文才会认为是比较合适的标题，这个标题可能将会显示在错误页面之上
                if (Utils.isChineseChar(title)) {
                    if (listener != null) {
                        listener.onReceivedTitle(webView, title);
                    }
                }
            }

            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2,
                                       JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            View myVideoView;
            View myNormalView;
            IX5WebChromeClient.CustomViewCallback callback;

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                FrameLayout normalView = mWebView;
                ViewGroup viewGroup = (ViewGroup) normalView.getParent();
                viewGroup.removeView(normalView);
                viewGroup.addView(view);
                myVideoView = view;
                myNormalView = normalView;
                callback = customViewCallback;
            }

            @Override
            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                if (myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                    viewGroup.removeView(myVideoView);
                    viewGroup.addView(myNormalView);
                }
            }

            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2, JsResult arg3) {
                /**
                 * 这里写入你自定义的window alert
                 */
                return super.onJsAlert(null, arg1, arg2, arg3);
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                Log.i("test", "openFileChooser 1");
                WebFragment.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsgs) {
                Log.i("test", "openFileChooser 2");
                WebFragment.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android  > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.i("test", "openFileChooser 3");
                WebFragment.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            // For Android  >= 5.0
            public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {
                Log.i("test", "openFileChooser 4:" + filePathCallback.toString());
                WebFragment.this.uploadFiles = filePathCallback;
                openFileChooseProcess();
                return true;
            }
        });

        mWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String arg0, String arg1, String arg2,
                                        String arg3, long arg4) {
                TbsLog.d(WebFragment.class.getName(), "url: " + arg0);
                new AlertDialog.Builder(getActivity())
                        .setTitle("allow to download？")
                        .setPositiveButton("yes",
                                           new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                   Toast.makeText(getActivity(), "fake message: i'll download...", Toast.LENGTH_LONG).show();
                                               }
                                           })
                        .setNegativeButton("no",
                                           new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                   // TODO Auto-generated method stub
                                                   Toast.makeText(getActivity(), "fake message: refuse download...", Toast.LENGTH_SHORT).show();
                                               }
                                           })
                        .setOnCancelListener(
                                new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        Toast.makeText(getActivity(), "fake message: refuse download...", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
            }
        });

        edit_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String url = edit_text.getText().toString();
                    if (TextUtils.isEmpty(url)) {
                        Toast.makeText(getActivity(), "请输入url", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    if (!URLUtil.isHttpUrl(url)) {
                        Toast.makeText(getActivity(), "url错误", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    if (mWebView != null) {
                        mWebView.loadUrl(url);
                    }

					/* 隐藏软键盘 */
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });


        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(getActivity().getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(getActivity().getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(getActivity().getDir("geolocation", 0).getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        long time = System.currentTimeMillis();
        if (TextUtils.isEmpty(mIntentUrl)) {
            mWebView.loadUrl(mHomeUrl);
        } else {
            mWebView.loadUrl(mIntentUrl);
        }
        TbsLog.d("time-cost", "cost time: " + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(getActivity());
        CookieSyncManager.getInstance().sync();

        if(initListener != null){
            initListener.onInitFinish(getActivity(),this);
        }
    }


    private void openFileChooseProcess() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i, "test"), uploadFilesRequestCode);
    }

    public WebView getWebView(){
        return mWebView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        TbsLog.d(WebFragment.class.getName(), "onActivityResult, requestCode:" + requestCode + ",resultCode:" + resultCode);

        if (requestCode == uploadFilesRequestCode) {
            if (resultCode == Activity.RESULT_OK) {
                switch (requestCode) {
                    case 0:
                        if (null != uploadFile) {
                            Uri result = data == null || resultCode != Activity.RESULT_OK ? null
                                    : data.getData();
                            uploadFile.onReceiveValue(result);
                            uploadFile = null;
                        }
                        if (null != uploadFiles) {
                            Uri result = data == null || resultCode != Activity.RESULT_OK ? null
                                    : data.getData();
                            uploadFiles.onReceiveValue(new Uri[]{result});
                            uploadFiles = null;
                        }
                        break;
                    default:
                        break;
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                if (null != uploadFile) {
                    uploadFile.onReceiveValue(null);
                    uploadFile = null;
                }

            }
        }
    }

    /**
     * 设置当获取到网页的图标与标题的监听
     *
     * @param listener
     */
    public void setOnReceivedTitleAndIconListener(onReceivedTitleAndIconListener listener) {
        this.listener = listener;
    }

    public void setWebViewInitListener(WebViewInitListener listener){
        this.initListener = listener;
    }


    public void loadWeb(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        this.mIntentUrl = url;
        if (mWebView != null) {
            mWebView.loadUrl(mIntentUrl);
        }
    }

    @Override
    public boolean onBackPressed() {
        if (goBack()) {
            return true;
        }
        return super.onBackPressed();
    }

    public boolean canGoBack() {
        return mWebView.canGoBack();
    }

    public boolean goBack() {
        if (mWebView != null) {
            // 自己处理回退事件
            if (canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        super.onDestroy();
    }
}
