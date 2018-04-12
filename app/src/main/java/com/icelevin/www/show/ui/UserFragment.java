package com.icelevin.www.show.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.UserInfo;
import com.icelevin.www.show.ui.user.LoginFragment;
import com.icelevin.www.show.ui.user.SettingFragment;
import com.icelevin.www.show.ui.user.UserInfoFragment;

import cn.bmob.v3.BmobUser;


/**
 * Created by ice on 2017/9/18.
 */

public class UserFragment extends BaseFragment {
    private TextView history, add, msg;
    private ImageView iv_head;
    private RelativeLayout rl_qrcode, rl_setting;
    private static final int REQUEST_CODE = 0x01;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    protected void initView() {
        history = (TextView) getView().findViewById(R.id.history);
        add = (TextView) getView().findViewById(R.id.add);
        msg = (TextView) getView().findViewById(R.id.message);
        iv_head = (ImageView) getView().findViewById(R.id.iv_head);
        rl_qrcode = (RelativeLayout) getView().findViewById(R.id.rl_qrcode);
        rl_setting = (RelativeLayout) getView().findViewById(R.id.rl_setting);

    }

    @Override
    protected void initData() {
        UserInfo currentUser = BmobUser.getCurrentUser(UserInfo.class);
        if (currentUser != null) {
            Glide.with(getActivity()).load(currentUser.getIconHead()).error(R.mipmap.news).into(iv_head);
        }

    }


    @Override
    protected void initListener() {

        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = BmobUser.getCurrentUser();
                if (bmobUser != null) {
                    Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
                    intent.putExtra(FragmentContainerActivity.VALUENAME, UserInfoFragment.class.getName());
                    startActivityForResult(intent,99);

                } else {
                    Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
                    intent.putExtra(FragmentContainerActivity.VALUENAME, LoginFragment.class.getName());
                    startActivity(intent);
                }


            }
        });
        rl_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
                intent.putExtra(FragmentContainerActivity.VALUENAME, SettingFragment.class.getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            showTost(result);
            LogUtils.print("result-->", result);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 99) {
            initData();
        }
    }
}