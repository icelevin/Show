package com.icelevin.www.show.ui.tech;

import android.content.Intent;
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

import com.hb.utils.view.TitleView;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.ui.games.FixedTabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.internal.operators.OnSubscribeLift;

/**
 * Created by ice on 2017/12/29.
 */

public class TechnologyFragment extends BaseFragment implements View.OnClickListener {
    private FixedTabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = new String[]{"人工智能", "网络安全", "机器人", "FINTECH", "智能硬件", "智能驾驶", "AR/VR"};
    private Map<String, String> map = new HashMap<>();
    private List<TechnologyListFragment> fragmentList = new ArrayList<>();
    private TechnologyTitleModel model;
    private TitleView titleView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_technology, container, false);
    }

    @Override
    protected void initView() {
        tabLayout = (FixedTabLayout) getView().findViewById(R.id.tabLayout);
        viewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        titleView = getView().findViewById(R.id.title_view);
        titleView.setTitleText("科技");
        titleView.setRightImgBtn(R.mipmap.search, this);
        titleView.setBackButtVisiable(false);
    }

    @Override
    protected void initData() {

        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        map.put("推荐", "000");
        map.put("人工智能", "132");
        map.put("网络安全", "26");
        map.put("机器人", "130");
        map.put("FINTECH", "131");
        map.put("智能硬件", "40");
        map.put("智能驾驶", "22");
        map.put("AR/VR", "129");
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.bk_title_red));
        tabLayout.setTabTextColors(getResources().getColor(R.color.hint_text_color), getResources().getColor(R.color.bk_title_red));
        viewPager.setAdapter(new TechnologyFragmentAdapter(getChildFragmentManager()));
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.VALUENAME, TechSearchFragment.class.getName());
        startActivity(intent);
    }
    class TechnologyFragmentAdapter extends FragmentPagerAdapter {

        public TechnologyFragmentAdapter(FragmentManager fm) {
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

    private TechnologyListFragment creatFragment(int position) {
        TechnologyListFragment gamesListFragment = new TechnologyListFragment();
        if (position == 0) {
            gamesListFragment.setType("132");
        } else if (position == 1) {
            gamesListFragment.setType("26");
        } else if (position == 2) {
            gamesListFragment.setType("130");
        } else if (position == 3) {
            gamesListFragment.setType("131");
        } else if (position == 4) {
            gamesListFragment.setType("40");
        } else if (position == 5) {
            gamesListFragment.setType("22");
        } else if (position == 6) {
            gamesListFragment.setType("129");
        }


        return gamesListFragment;
    }
}
