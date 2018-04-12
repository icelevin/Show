package com.icelevin.www.show.engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.x5web.WebFragment;
import com.icelevin.www.show.web.TitleWebFragment;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by ice on 2017/9/26.
 */

public class WebEngine {
    private Activity activity;
    private WebView webView;
    private WebFragment fragment;
    private static String jumpUrl;

    public static String getJumpUrl() {
        return jumpUrl;
    }

    public static void resetJumpUrl() {
        jumpUrl = "";
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public void setWebFragment(WebFragment fragment) {
        this.fragment = fragment;
    }

    public void startWebFragment(Context context, String webUrl) {
        Intent intent = new Intent(context, FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.VALUENAME, TitleWebFragment.class.getName());
        intent.putExtra(TitleWebFragment.class.getName(), webUrl);
        context.startActivity(intent);
    }
}
