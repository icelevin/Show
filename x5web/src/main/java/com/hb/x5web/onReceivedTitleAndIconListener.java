package com.hb.x5web;

import android.graphics.Bitmap;

import com.tencent.smtt.sdk.WebView;

/**
 * 当获取网页图标与标题的事件监听器
 *
 * @author txl
 */
public interface onReceivedTitleAndIconListener {
    /**
     * 当获取到图标
     *
     * @param view
     * @param icon
     */
    void onReceivedIcon(WebView view, Bitmap icon);

    /**
     * 当获取到标题
     *
     * @param view
     * @param title
     */
    void onReceivedTitle(WebView view, String title);
}