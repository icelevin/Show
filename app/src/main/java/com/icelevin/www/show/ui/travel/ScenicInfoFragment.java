package com.icelevin.www.show.ui.travel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.QiongYouScenicModel;

/**
 * Created by ice on 2018/1/4.
 */

public class ScenicInfoFragment extends BaseFragment {
    private QiongYouScenicModel.Data data;
    private ImageView iv_title;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView tv_discrip, tv_tips, tv_openTime, tv_price, tv_url, tv_phone,tv_arrive;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scenic_info, container, false);
    }

    @Override
    protected void initView() {
        data = (QiongYouScenicModel.Data) getActivity().getIntent().getSerializableExtra(QiongYouScenicModel.Data.class.getName());
        iv_title = getView().findViewById(R.id.iv_title);
        collapsingToolbarLayout = getView().findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(data.getTitle());
        tv_discrip = getView().findViewById(R.id.tv_discrip);
        tv_tips = getView().findViewById(R.id.tv_tips);
        tv_openTime = getView().findViewById(R.id.tv_openTime);
        tv_price = getView().findViewById(R.id.tv_price);
        tv_url = getView().findViewById(R.id.tv_url);
        tv_phone = getView().findViewById(R.id.tv_phone);
        tv_arrive = getView().findViewById(R.id.tv_arrive);

    }

    @Override
    protected void initData() {
        if (data.getImageUrls() == null || data.getImageUrls().size() < 1) {
            iv_title.setImageResource(R.color.bk_title_red);
        } else {
            Glide.with(getActivity()).load(data.getImageUrls().get(0)).into(iv_title);
        }
        tv_discrip.setText(data.getDescription());
        for (int i = 0; i < data.getKeyValues().size(); i++) {
            QiongYouScenicModel.KeyValues keyValues = data.getKeyValues().get(i);
            if (keyValues.getKey().equals("Tips")) {
                tv_tips.setText(keyValues.getValue());
                continue;
            }
            if (keyValues.getKey().equals("到达方式")) {
                tv_arrive.setText(keyValues.getValue());
                continue;
            }
            if (keyValues.getKey().equals("门票")) {
                tv_price.setText(keyValues.getValue());
                continue;
            }
            if (keyValues.getKey().equals("电话")) {
                tv_phone.setText(keyValues.getValue());
                continue;
            }
            if (keyValues.getKey().equals("网址")) {
                tv_url.setText(keyValues.getValue());
                continue;
            }
            if (keyValues.getKey().equals("开放时间")) {
                tv_openTime.setText(keyValues.getValue());
            }
        }
    }

    @Override
    protected void initListener() {

    }
}
