package com.icelevin.www.show.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.hb.utils.view.viewpagerindicator.TabPageIndicator;
import com.icelevin.www.show.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2017/9/18.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private TitleView titleView;
    private TabPageIndicator indicator;
    private ViewPager viewPager;
    private String[] titles = new String[]{"新闻", "本地", "调查", "生活圈", "时讯"};
    private List<NewsListFragment> fragmentList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initView() {
        titleView = (TitleView) getView().findViewById(R.id.title_view);
        titleView.setTitleText("首页");
        indicator = (TabPageIndicator) getView().findViewById(R.id.indicator);
        viewPager = (ViewPager) getView().findViewById(R.id.viewPager);

        titleView.setRightImgBtn(R.mipmap.search, this);
    }

    @Override
    protected void initData() {
        viewPager.setAdapter(new HomeViewPagerAdapter(getChildFragmentManager()));
        indicator.setViewPager(viewPager);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.VALUENAME, SearchFragment.class.getName());
        startActivity(intent);
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
        public CharSequence getPageTitle(int position) {
            return titles[position];
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
            newsListFragment.setType("10135");
        } else if (position == 3) {
            newsListFragment.setType("10063");
        } else if (position == 4) {
            newsListFragment.setType("10115");
        }
        return newsListFragment;
    }
}
