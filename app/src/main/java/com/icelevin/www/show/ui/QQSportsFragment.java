package com.icelevin.www.show.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lianghanzhen.LazyViewPager;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.ui.games.FixedTabLayout;
import com.icelevin.www.show.ui.sports.SportFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ice on 2017/12/14.
 */

public class QQSportsFragment extends BaseFragment {
    private FixedTabLayout tabLayout;
    private ViewPager viewPager;
    private TitleView titleView;
    private String[] titles = new String[]{"热议NBA", "中国足球", "中国篮球", "热议足球", "电子竞技", "运动装备", "操场等我", "跑步健身"};
    private Map<String, String> map = new HashMap<>();
    private List<SportFragment> fragmentList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qqsports, container, false);
    }

    @Override
    protected void initView() {
        tabLayout = getView().findViewById(R.id.tabLayout);
        viewPager = getView().findViewById(R.id.viewPager);
        titleView = getView().findViewById(R.id.title_view);
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.bk_title_red));
        tabLayout.setTabTextColors(getResources().getColor(R.color.hint_text_color), getResources().getColor(R.color.bk_title_red));
        viewPager.setAdapter(new QQSportsPagerAdapter(getChildFragmentManager()));
    }

    @Override
    protected void initData() {
        titleView.setTitleText("体育");
        map.put("热议NBA", "69");
        map.put("中国足球", "65");
        map.put("中国篮球", "63");
        map.put("热议足球", "145");
        map.put("电子竞技", "96");
        map.put("运动装备", "101");
        map.put("操场等我", "135");
        map.put("跑步健身", "90");
    }

    @Override
    protected void initListener() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    private class QQSportsPagerAdapter extends FragmentPagerAdapter {

        public QQSportsPagerAdapter(FragmentManager fm) {
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

    private Fragment creatFragment(int position) {
        SportFragment sportFragment = new SportFragment();
        switch (position){
            case 0:
                sportFragment.setType(map.get(titles[position]));
                break;
            case 1:
                sportFragment.setType(map.get(titles[position]));
                break;
            case 2:
                sportFragment.setType(map.get(titles[position]));
                break;
            case 3:
                sportFragment.setType(map.get(titles[position]));
                break;
            case 4:
                sportFragment.setType(map.get(titles[position]));
                break;
            case 5:
                sportFragment.setType(map.get(titles[position]));
                break;
            case 6:
                sportFragment.setType(map.get(titles[position]));
                break;
            case 7:
                sportFragment.setType(map.get(titles[position]));
                break;
        }
        return sportFragment;
    }
}
