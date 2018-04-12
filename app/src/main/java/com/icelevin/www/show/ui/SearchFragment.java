package com.icelevin.www.show.ui;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.recycler.ParametersBean;
import com.hb.utils.view.recycler.RecyclerBaseFragment;
import com.hb.utils.view.recycler.RecyclerLoadListener;
import com.hb.utils.view.recycler.RecyclerViewFragment;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.adapter.SearchCCTVNewsAdapter;
import com.icelevin.www.show.engine.WebEngine;
import com.icelevin.www.show.model.SearchCCTVModel;
import com.icelevin.www.show.net.NetWorkController;
import com.icelevin.www.show.utils.ErrorCode;

import java.util.List;


/**
 * Created by ice on 2017/9/30.
 */

public class SearchFragment extends RecyclerBaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView iv_back;
    private EditText et_search;
    private TextView tv_search;
    private RecyclerViewFragment fragment;
    private SearchCCTVNewsAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    protected void onHanderMessage(Message msg) {

    }

    @Override
    protected void initView() {
        iv_back = (ImageView) getView().findViewById(R.id.iv_back);
        et_search = (EditText) getView().findViewById(R.id.et_search);
        tv_search = (TextView) getView().findViewById(R.id.tv_search);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        iv_back.setOnClickListener(this);
        et_search.setOnClickListener(this);
        tv_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (getActivity() == null || getActivity().isFinishing())
                    return;
                getActivity().finish();
                break;
            case R.id.tv_search:
                String searchKey = et_search.getText().toString().trim();
                if (TextUtils.isEmpty(searchKey)) {
                    showTost("搜索内容不能为空");
                    return;
                }
                initList(searchKey);
                break;
            default:
                break;
        }
    }

    private void initList(final String keyWord) {
        final ParametersBean params = new ParametersBean();
        params.setFragmentParentId(R.id.fragment_parent);
        fragment = RecyclerViewFragment.getInstance(getChildFragmentManager(), params);
        fragment.setHideMessage(null, "暂无搜索结果", null, null);
        fragment.Refresh();
        fragment.setLoadListener(new RecyclerLoadListener<SearchCCTVModel>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                adapter = new SearchCCTVNewsAdapter(getActivity(), null, SearchFragment.this);
                fragment.getRecyclerView().setAdapter(adapter);
                LogUtils.print("设配器设置成功", "设配器设置成功");
                return adapter;
            }

            @Override
            public void onLoad(ParametersBean parametersBean, OnResultListener<SearchCCTVModel> listener) {
                NetWorkController.INSTANCE.SearchCCTVNews(keyWord, String.valueOf(params.getCurPage()), listener);
            }

            @Override
            public void onResult(SearchCCTVModel model) {

                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }
                List<SearchCCTVModel.Data> data = model.getData();
                if (!"000000".equals(model.getRetcode())) {
                    String code = ErrorCode.doErrorCode(model.getRetcode());
                    fragment.onResult(-1, code, data);
                    return;
                }
                if (data == null || data.size() < 1) {
                    fragment.onResult(-1, "暂无数据", data);
                    return;
                }
                fragment.onResult(200, "加载成功", data);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SearchCCTVModel.Data qiHuNewsDataModel = adapter.getList().get(position);
        if (TextUtils.isEmpty(qiHuNewsDataModel.getUrl()))
            return;
        new WebEngine().startWebFragment(getActivity(), qiHuNewsDataModel.getUrl());
    }
}
