package com.icelevin.www.show.ui.sports;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.view.recycler.RecyclerBaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.net.NetWorkController;


/**
 * Created by ice on 2018/1/16.
 */

public class SportUserInfoFragment extends RecyclerBaseFragment {
    private QQSportsModel.Data model;
    private ImageView iv_head, iv_sexy;
    private TextView tv_name, biography;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sport_userinfo, container, false);
    }

    @Override
    protected void onHanderMessage(Message msg) {

    }

    @Override
    protected void initView() {
        super.initView();
        iv_head = getView().findViewById(R.id.iv_head);
        iv_sexy = getView().findViewById(R.id.iv_sexy);
        tv_name = getView().findViewById(R.id.tv_name);
        biography = getView().findViewById(R.id.biography);
        model = (QQSportsModel.Data) getActivity().getIntent().getSerializableExtra(QQSportsModel.Data.class.getName());
        Refresh();
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void Refresh() {
        super.Refresh();
        initDatas();
    }

    private void initDatas() {
        NetWorkController.INSTANCE.getQQSportsUserInfo(model.getPosterId(), new OnResultListener<QQSportsUserInfoModel>() {
            @Override
            public void onResult(QQSportsUserInfoModel qqSportsUserInfoModel) {
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }
                if (!"000000".equals(qqSportsUserInfoModel.getRetcode())) {
                    sendMessage(MSG_UI_LOAD_FAILE, qqSportsUserInfoModel.getMessage());
                    return;
                }
                sendMessage(MSG_UI_LOAD_SUCCESS, qqSportsUserInfoModel.getMessage());
                QQSportsUserInfoModel.Data data = qqSportsUserInfoModel.getData().get(0);

                if (!TextUtils.isEmpty(data.getScreenName())) {
                    tv_name.setText(data.getScreenName());
                }
                if (!TextUtils.isEmpty(data.getBiography())) {
                    biography.setText(data.getBiography());
                }
                Glide.with(getActivity()).load(data.getAvatarUrl()).into(iv_head);

                if ("女".equals(data.getGender())) {
                    iv_sexy.setImageResource(R.mipmap.male);
                } else if ("男".equals(data.getGender())) {
                    iv_sexy.setImageResource(R.mipmap.female);
                } else {
                    iv_sexy.setImageResource(0);
                }
            }
        });
    }
}
