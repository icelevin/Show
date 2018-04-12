package com.icelevin.www.show.web;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.hb.x5web.WebFragment;
import com.hb.x5web.WebViewInitListener;
import com.icelevin.www.show.MyApplication;
import com.icelevin.www.show.R;
import com.icelevin.www.show.common.Constants;
import com.icelevin.www.show.engine.WebEngine;

/**
 * Created by ice on 2017/9/26.
 */

public class TitleWebFragment extends BaseFragment{

    private WebFragment webFragment;
    private String webUrl;
    private TitleView titleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_title_web,container,false);
    }

    @Override
    protected void initView() {
        titleView= (TitleView) getView().findViewById(R.id.title_view);

        webUrl=getActivity().getIntent().getStringExtra(TitleWebFragment.class.getName());

    }

    @Override
    protected void initData() {
        webFragment=new WebFragment();
        if (!Constants.isDebug) {
            webFragment.setHiddenUrl();
        }
        LogUtils.print("webUrl",webUrl);
        webFragment.loadWeb(webUrl);
        getChildFragmentManager().beginTransaction().replace(R.id.fl_web,webFragment).commitNowAllowingStateLoss();

        ImageView backImageView=titleView.getBackImageView();
        if (backImageView != null) {
            backImageView.setImageResource(R.mipmap.big_close);
        }
        titleView.findViewById(R.id.back_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    protected void initListener() {
        webFragment.setWebViewInitListener(new WebViewInitListener() {
            @Override
            public void onInitFinish(Activity actiivty, WebFragment webFragment) {
                WebEngine webEngine=new WebEngine();
                webEngine.setActivity(actiivty);
                webEngine.setWebView(webFragment.getWebView());
                webEngine.setWebFragment(webFragment);
                webFragment.getWebView().addJavascriptInterface(webEngine,"android");
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        return webFragment.onBackPressed();
    }
}
