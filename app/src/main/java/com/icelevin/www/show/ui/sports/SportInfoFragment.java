package com.icelevin.www.show.ui.sports;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hb.utils.view.TitleView;
import com.hb.utils.view.recycler.RecyclerBaseFragment;
import com.icelevin.www.show.R;

/**
 * Created by ice on 2018/1/18.
 */

public class SportInfoFragment extends RecyclerBaseFragment {
    private QQSportsModel.Data data;
    private TitleView titleView;

    @Override
    protected void onHanderMessage(Message msg) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sprot_info, container, false);
    }

    @Override
    protected void initView() {
        super.initView();
        titleView = getView().findViewById(R.id.title_view);
        titleView.setTitleText("");
        data = (QQSportsModel.Data) getActivity().getIntent().getSerializableExtra(QQSportsModel.Data.class.getName());
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
        initData();
    }
}
