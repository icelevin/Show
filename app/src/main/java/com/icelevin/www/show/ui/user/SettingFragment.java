package com.icelevin.www.show.ui.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hb.utils.view.TitleView;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;

import cn.bmob.v3.BmobUser;

/**
 * Created by ice on 2017/11/3.
 */

public class SettingFragment extends BaseFragment{
    private TitleView titleView;
    private RelativeLayout rl_pwd,rl_update,rl_advice,rl_clean;
    private Button btn_out;
    private TextView tv_cache;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting,container,false);
    }

    @Override
    protected void initView() {
        titleView= (TitleView) getView().findViewById(R.id.title_view);
        titleView.setTitleText("设置");

        rl_pwd= (RelativeLayout) getView().findViewById(R.id.rl_pwd);
        rl_update= (RelativeLayout) getView().findViewById(R.id.rl_update);
        rl_advice= (RelativeLayout) getView().findViewById(R.id.rl_advice);
        rl_clean= (RelativeLayout) getView().findViewById(R.id.rl_clean);
        btn_out= (Button) getView().findViewById(R.id.btn_out);
        tv_cache= (TextView) getView().findViewById(R.id.tv_cache);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BmobUser.getCurrentUser() == null) {
                    btn_out.setVisibility(View.GONE);
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确定退出?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BmobUser.logOut();
                        showTost("已退出登录");
                        btn_out.setVisibility(View.GONE);
                        rl_pwd.setVisibility(View.GONE);

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        if (BmobUser.getCurrentUser() == null) {
            btn_out.setVisibility(View.GONE);
            rl_pwd.setVisibility(View.GONE);
        }
        rl_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), FragmentContainerActivity.class);
                intent.putExtra(FragmentContainerActivity.VALUENAME,UpdatePwdFragment.class.getName());
                startActivity(intent);
            }
        });
    }
}
