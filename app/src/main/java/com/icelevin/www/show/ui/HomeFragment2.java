package com.icelevin.www.show.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.tools.Utils;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.BaseModel;
import com.icelevin.www.show.net.NetWorkController;
import com.icelevin.www.show.ui.games.FixedTabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ice on 2017/12/8.
 */

public class HomeFragment2 extends BaseFragment{
    private TitleView titleView;
    private FixedTabLayout mTablayout;
    private ViewPager viewPager;
    private String[] titles = new String[]{"新闻", "本地", "科教", "生活圈", "时讯"};
    private List<NewsListFragment> fragmentList = new ArrayList<>();
    private String ip;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home2, container, false);
    }

    @Override
    protected void initView() {
        titleView = (TitleView) getView().findViewById(R.id.title_view);
        titleView.setBackButtVisiable(false);
        mTablayout = (FixedTabLayout) getView().findViewById(R.id.tabLayout);
        viewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        titleView.setTitleText("首页");

    }


    @Override
    protected void initData() {
        for (int i = 0; i < titles.length; i++) {
            mTablayout.addTab(mTablayout.newTab().setText(titles[i]));
        }
        mTablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        mTablayout.setTabTextColors(getResources().getColor(R.color.hint_text_color), getResources().getColor(R.color.white));

        viewPager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
        loadWeather();
    }

    private void loadWeather() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ip = Utils.GetNetIp();
            }
        }).start();
        if (TextUtils.isEmpty(ip)) {
            return;
        }
        NetWorkController.INSTANCE.getWeatherInfo(ip, new OnResultListener<BaseModel>() {
            @Override
            public void onResult(BaseModel baseModel) {
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }

            }
        });
    }

    @Override
    protected void initListener() {
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTablayout));
        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    public class HomeViewPagerAdapter extends FragmentPagerAdapter {

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (fragmentList.size() > position) {
                fragment = fragmentList.get(position);
            }
            if (fragment == null) {
                fragment = creatFragment(position);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

    private NewsListFragment creatFragment(int position) {
        NewsListFragment newsListFragment = new NewsListFragment();
        if (position == 0) {
            newsListFragment.setType("10003");
        } else if (position == 1) {
            newsListFragment.setType("10009");
        } else if (position == 2) {
            newsListFragment.setType("10098");
        } else if (position == 3) {
            newsListFragment.setType("10063");
        } else if (position == 4) {
            newsListFragment.setType("10115");
        }
        return newsListFragment;

    }
}
