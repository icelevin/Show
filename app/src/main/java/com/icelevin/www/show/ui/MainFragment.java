package com.icelevin.www.show.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;

import java.util.ArrayList;
import java.util.List;

import cn.ittiger.player.PlayerManager;

/**
 * Created by ice on 2017/9/15.
 */

public class MainFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private RadioButton rb_home, rb_film, rb_user,rb_discover;
    private RadioGroup radioGroup;
    private FragmentManager fm;
    private Fragment fragment_home, fragment_film, fragment_my, fragment_discovery;
    private FragmentTransaction fragmentTransaction;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void initView() {
        rb_home = (RadioButton) getView().findViewById(R.id.rb_home);
        rb_film = (RadioButton) getView().findViewById(R.id.rb_film);
        rb_user = (RadioButton) getView().findViewById(R.id.rb_user);
        radioGroup = (RadioGroup) getView().findViewById(R.id.ll_bottom);
        rb_discover= (RadioButton) getView().findViewById(R.id.rb_discover);

        fragment_home = getChildFragmentManager().findFragmentById(R.id.fragment_home);
        fragment_film = getChildFragmentManager().findFragmentById(R.id.fragment_film);
        fragment_my = getChildFragmentManager().findFragmentById(R.id.fragment_my);
        fragment_discovery = getChildFragmentManager().findFragmentById(R.id.fragment_discovery);

        fragmentList.add(fragment_home);
        fragmentList.add(fragment_film);
        fragmentList.add(fragment_my);
        fragmentList.add(fragment_discovery);

        fm = getChildFragmentManager();

        fm.beginTransaction().hide(fragment_film).hide(fragment_my).show(fragment_home).hide(fragment_discovery).commit();

        fm.executePendingTransactions();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        fm = getChildFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        switch (checkedId) {
            case R.id.rb_home:
                fragmentTransaction.hide(fragment_film).hide(fragment_my).show(fragment_home).hide(fragment_discovery).commit();
                fm.executePendingTransactions();
                break;
            case R.id.rb_film:
                fragmentTransaction.hide(fragment_home).hide(fragment_my).show(fragment_film).hide(fragment_discovery).commit();
                fm.executePendingTransactions();
                break;
            case R.id.rb_user:
                fragmentTransaction.hide(fragment_film).hide(fragment_home).show(fragment_my).hide(fragment_discovery).commit();
                fm.executePendingTransactions();
                break;
            case R.id.rb_discover:
                fragmentTransaction.hide(fragment_film).hide(fragment_home).show(fragment_discovery).hide(fragment_my).commit();
                fm.executePendingTransactions();
                break;
            default:
                break;
        }
    }
}
