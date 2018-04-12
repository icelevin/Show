package com.icelevin.www.show.ui.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ice on 2017/12/14.
 */

public class GamesFragment extends BaseFragment {
    private List<Map<String, String>> mapList = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();
    private String[] titles = new String[]{"PC",
            "Mac OS",
            "PS4",
            "Xbox One",
            "Wii U",
            "PS3",
            "Xbox360",
            "Wii",
            "PS Vita",
            "3DS",
            "iOS",
            "Android",
            "街机",
            "NDS",
            "PSP",
            "PS2",
            "XBOX",
            "ameCube",
            "Dreamcast",
            "Nintendo 64",
            "PlayStation",
            "SFC",
            "FC",
            "WonderSwan",
            "WonderSwan Color",
            "NEOGEO Pocket Color",
            "GBA",
            "GB",
            "Virtual Boy"
    };
    private List<GamesListFragment> fragmentList = new ArrayList<>();
    private FixedTabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games, container, false);
    }

    @Override
    protected void initView() {
        tabLayout = (FixedTabLayout) getView().findViewById(R.id.tabLayout);
        viewPager = (ViewPager) getView().findViewById(R.id.viewPager);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.bk_title_red));
        tabLayout.setTabTextColors(getResources().getColor(R.color.hint_text_color), getResources().getColor(R.color.bk_title_red));

        viewPager.setAdapter(new GamesPageAdapter(getChildFragmentManager()));
        map.put("PC", "pc");
        map.put("Mac OS", "mac");
        map.put("PS4", "ps4");
        map.put("Xbox One", "xbox_one");
        map.put("Wii U", "wii_u");
        map.put("PS3", "ps3");
        map.put("Xbox360", "xbox360");
        map.put("Wii", "wii");
        map.put("PS Vita", "psv");
        map.put("3DS", "3ds");
        map.put("iOS", "iphone");
        map.put("Android", "android");
        map.put("街机", "arc");
        map.put("NDS", "nds");
        map.put("PSP", "psp");
        map.put("PS2", "ps2");
        map.put("XBOX", "xbox");
        map.put("GameCube", "gamecube");
        map.put("Dreamcast", "dreamcast");
        map.put("Nintendo 64", "n64");
        map.put("PlayStation", "ps");
        map.put("SFC", "sfc");
        map.put("FC", "fc");
        map.put("WonderSwan", "ws");
        map.put("WonderSwan Color", "wsc");
        map.put("NEOGEO Pocket Color", "ngp");
        map.put("GBA", "GBA");
        map.put("GB", "GB");
        map.put("Virtual Boy", "vb");


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

    class GamesPageAdapter extends FragmentStatePagerAdapter {


        public GamesPageAdapter(FragmentManager fm) {
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
    }

    private GamesListFragment creatFragment(int position) {
        GamesListFragment gamesListFragment = null;
        gamesListFragment = new GamesListFragment();
        gamesListFragment.setType(map.get(titles[position]));
        return gamesListFragment;
    }
}
