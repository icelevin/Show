package com.icelevin.www.show.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.ui.games.GamesFragment;
import com.icelevin.www.show.ui.tech.TechnologyFragment;
import com.icelevin.www.show.ui.travel.TravelFragment;

/**
 * Created by ice on 2017/12/14.
 */

public class DiscoveryFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_travel, tv_games, tv_technology, tv_basketball;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discovery, container, false);
    }

    @Override
    protected void initView() {
        tv_travel = getView().findViewById(R.id.tv_travel);
        tv_games = getView().findViewById(R.id.tv_games);
        tv_technology = getView().findViewById(R.id.tv_technology);
        tv_basketball = getView().findViewById(R.id.tv_basketball);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        tv_travel.setOnClickListener(this);
        tv_games.setOnClickListener(this);
        tv_technology.setOnClickListener(this);
        tv_basketball.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        switch (v.getId()) {
            case R.id.tv_travel:
                intent.putExtra(FragmentContainerActivity.VALUENAME, TravelFragment.class.getName());
                break;
            case R.id.tv_games:
                intent.putExtra(FragmentContainerActivity.VALUENAME, GamesFragment.class.getName());
                break;
            case R.id.tv_technology:
                intent.putExtra(FragmentContainerActivity.VALUENAME, TechnologyFragment.class.getName());
                break;
            case R.id.tv_basketball:
                intent.putExtra(FragmentContainerActivity.VALUENAME, QQSportsFragment.class.getName());

                break;

        }
        startActivity(intent);

    }
}
