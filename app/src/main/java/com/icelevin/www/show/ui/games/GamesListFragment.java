package com.icelevin.www.show.ui.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.view.fragment.BaseFragment;
import com.hb.utils.view.recycler.ParametersBean;
import com.hb.utils.view.recycler.RecyclerLoadListener;
import com.hb.utils.view.recycler.RecyclerViewFragment;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.adapter.BiLiAdapter;
import com.icelevin.www.show.model.ShowApiBody;
import com.icelevin.www.show.net.NetWorkController;

/**
 * Created by ice on 2017/12/14.
 */

public class GamesListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private RecyclerViewFragment fragment;
    private BiLiAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_games_list, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        ParametersBean parmBean = new ParametersBean();
        parmBean.setFragmentParentId(R.id.fragment_parent);
        fragment = RecyclerViewFragment.getInstance(getChildFragmentManager(), parmBean);
        fragment.setLoadListener(new RecyclerLoadListener<ShowApiBody>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                adapter = new BiLiAdapter(getActivity(), null, GamesListFragment.this);
                fragment.getRecyclerView().setAdapter(adapter);
                return adapter;
            }

            @Override
            public void onLoad(ParametersBean parametersBean, OnResultListener<ShowApiBody> listener) {
//                NetWorkController.INSTANCE.GetBili(parametersBean.getCurPage() + "", listener);
            }

            @Override
            public void onResult(ShowApiBody o) {

            }
        });
    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
