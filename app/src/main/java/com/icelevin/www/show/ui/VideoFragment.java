package com.icelevin.www.show.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.adapter.DuoWanVideoAdapter;
import com.icelevin.www.show.model.DuoWanVideoModel;
import com.icelevin.www.show.net.NetWorkController;
import com.icelevin.www.show.view.MyRecyclerLoadListener;
import com.icelevin.www.show.view.MyRecyclerViewFragment;
import com.icelevin.www.show.view.ParmBean;

import java.util.List;

import cn.ittiger.player.PlayerManager;
import io.vov.vitamio.Vitamio;

/**
 * Created by ice on 2017/9/18.
 */

public class VideoFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private TitleView titleView;
    private MyRecyclerViewFragment fragment;
    private DuoWanVideoAdapter adapter;
    private String pageToken ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film, container, false);
    }


    @Override
    protected void initView() {
        titleView = (TitleView) getView().findViewById(R.id.title_view);
        titleView.setBackButtVisiable(false);
        titleView.setTitleText("视频专区");
        titleView.setBackButtVisiable(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            PlayerManager.getInstance().pause();
        } else {
          initDatas(null);
        }

    }

    private void initDatas(final String token) {
        final ParmBean param = new ParmBean();
        param.setFragmentParentId(R.id.fragment_media);
        fragment = MyRecyclerViewFragment.getInstance(getChildFragmentManager(), param);
        fragment.setLoadListener(new MyRecyclerLoadListener<DuoWanVideoModel>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                adapter = new DuoWanVideoAdapter(getActivity(), null, VideoFragment.this);
                fragment.getRecyclerView().setAdapter(adapter);
                return adapter;
            }

            @Override
            public void onLoad(ParmBean parametersBean, OnResultListener<DuoWanVideoModel> listener) {
                NetWorkController.INSTANCE.getDuoWan("5", parametersBean.getCurPage(), listener);
            }

            @Override
            public void onResult(DuoWanVideoModel model) {
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }

                if (!("000000").equals(model.getRetcode())) {
                    fragment.onResult(-1, model.getMessage(), null);
                    return;
                }
                List<DuoWanVideoModel.Data> data = model.getData();
                if (data == null || data.size() < 1) {
                    fragment.onResult(200, model.getMessage(), null);
                    return;
                }
                fragment.onResult(200, model.getMessage(), data);
                param.setHasNextPage(model.isHasNext());
                fragment.getParameters().setCurPage(model.getPageToken());

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PlayerManager.getInstance().release();
    }

    @Override
    public void onPause() {
        super.onPause();
        PlayerManager.getInstance().pause();
    }

}
