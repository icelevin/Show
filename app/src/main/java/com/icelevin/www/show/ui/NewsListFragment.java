package com.icelevin.www.show.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.hb.utils.view.recycler.ParametersBean;
import com.hb.utils.view.recycler.RecyclerLoadListener;
import com.hb.utils.view.recycler.RecyclerViewFragment;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.adapter.NewsListAdapter;
import com.icelevin.www.show.model.CCTVNewsModel;
import com.icelevin.www.show.net.NetWorkController;

/**
 * 新闻列表页
 * Created by ice on 2017/9/19.
 */

public class NewsListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private String type;

    public String getType() {
        return type;
    }

    private RecyclerViewFragment fragment;
    private NewsListAdapter newsListAdapter;
    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，
    // 在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


    }

    public void initListData(final String catid) {
        final ParametersBean bean = new ParametersBean();
        bean.setFragmentParentId(R.id.fragment_parent);
        fragment = RecyclerViewFragment.getInstance(getChildFragmentManager(), bean);
        fragment.setLoadListener(new RecyclerLoadListener<CCTVNewsModel>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                newsListAdapter = new NewsListAdapter(getActivity(), null, NewsListFragment.this);
                fragment.getRecyclerView().setAdapter(newsListAdapter);
                return newsListAdapter;
            }

            @Override
            public void onLoad(ParametersBean parametersBean, OnResultListener<CCTVNewsModel> listener) {
                NetWorkController.INSTANCE.GetCCTVNews(catid, String.valueOf(parametersBean.getCurPage()), listener);
            }

            @Override
            public void onResult(CCTVNewsModel baiJiaModel) {
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }

                if (!"000000".equals(baiJiaModel.getRetcode())) {
                    fragment.onResult(-1, baiJiaModel.getMessage(), null);
                    return;
                }
                if (baiJiaModel.getData() == null || baiJiaModel.getData().size() < 1) {
                    fragment.onResult(200, baiJiaModel.getMessage(), null);
                    return;
                }
                fragment.getParameters().setPageCount(baiJiaModel.getData().size());

                fragment.onResult(200, baiJiaModel.getMessage(), baiJiaModel.getData());
            }
        });
    }

    @Override
    protected void initListener() {
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CCTVNewsModel.Data data = newsListAdapter.getList().get(position);
//        new WebEngine().startWebFragment(getActivity(),data.getUrl());
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        intent.putExtra(CCTVNewsModel.Data.class.getName(), data);
        intent.putExtra(FragmentContainerActivity.VALUENAME, CCTVNewsInfoFragment.class.getName());
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //懒加载
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        initListData(getType());
    }

    protected void onInvisible() {
    }
}
