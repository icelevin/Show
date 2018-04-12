package com.icelevin.www.show.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2017/10/23.
 */

public class NewsHomeFragment extends BaseFragment {
    private String type;
    private List<ImageView> mList;
    private int[] mImages=new int[]{};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_home, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
// 准备显示的图片集合
        mList = new ArrayList<>();
        for (int i = 0; i < mImages.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            // 将图片设置到ImageView控件上
            imageView.setImageResource(mImages[i]);

            // 将ImageView控件添加到集合
            mList.add(imageView);
        }

    }

    @Override
    protected void initListener() {

    }

    public void setType(String type) {
        this.type = type;
    }

}
