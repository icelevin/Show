package com.hb.utils.view.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.hb.umeng.analytics.AnalyticsManager;
import com.hb.utils.BaseApplication;
import com.hb.utils.R;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.tools.Utils;
import com.hb.utils.view.fragment.BaseFragment;

import cn.ittiger.player.PlayerManager;

/**
 * 装载一个无参fragment的通用activity，以减少activity
 *
 * @author txl
 */
public class FragmentContainerActivity extends SwipeBackActivity {

    public static final String VALUENAME = "AbsolutePath";
    public static final String themeValue = "themeValue";
    private BaseFragment fragment;
    private String path;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        int theme = getIntent().getIntExtra(themeValue,0);
        if(theme > 0){
            setTheme(theme);
        }
        setContentView(R.layout.activity_single_fragment);

        if(theme == R.style.Translucent){
            findViewById(R.id.fragment_parent).setVisibility(View.INVISIBLE);
        }

        initFragment();
    }

    @SuppressLint("Recycle")
    private void initFragment() {
        // 获取fragment的全类名
        path = getIntent().getStringExtra(VALUENAME);

        if (TextUtils.isEmpty(path)) {
            LogUtils.print(FragmentContainerActivity.class.getName(),"path 不能为 null");
            return;
        }

        try {
            fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(path);
            if (fragment != null) {
                LogUtils.print(this.getClass().getName(), "fragment已存在,不再创建");
                return;
            }
            fragment = (BaseFragment) Class.forName(path).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            // 添加到activity上
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_parent, fragment, path).commitAllowingStateLoss();
        } else {
            throw new RuntimeException("指定的绝对路径无法构造fragment");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        AnalyticsManager.getInstance(BaseApplication.getAppContext()).onFragmentResume(this,path);

    }

    @Override
    protected void onPause() {
        super.onPause();
        AnalyticsManager.getInstance(BaseApplication.getAppContext()).onFragmentPause(this,path);
    }

    @Override
    public void onBackPressed() {
        if (!fragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        //侧滑使activity透明，会导致键盘不能在activity销毁的时候自动关闭
        Utils.closeBoard(this);
        super.onDestroy();
    }
}
