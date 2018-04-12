package com.icelevin.www.show.view;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.recycler.ParametersBean;
import com.hb.utils.view.recycler.RecyclerLoadListener;
import com.hb.utils.view.recycler.RecyclerViewFragment;
import com.hb.utils.view.recycler.ResultListBean;
import com.hb.utils.view.recycler.WrapContentLinearLayoutManager;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.hb.utils.view.recycler.view.LoadMoreRecyclerView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ice on 2017/12/13.
 */

public class MyRecyclerViewFragment<T extends Serializable> extends MyRecyclerBaseFragment {
    private SwipeRefreshLayout swipe_refresh;
    private LoadMoreRecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;

    private MyRecyclerLoadListener<T> recyclerLoadListener;
    //	private BaseRecyclerAdapter<T,?> adapter;

    @Override
    protected void onHanderMessage(Message msg) {
        String message = "";
        if (msg.obj != null) {
            message = msg.obj.toString().trim();
        }

        switch (msg.what) {
            case MSG_UI_START_LOADING:
                if (swipe_refresh.getVisibility() == View.VISIBLE && TextUtils.isEmpty(loadPage)) {
                    swipe_refresh.setRefreshing(true);
                }
                LogUtils.print("ListViewWorkerFragment", "刷新数据=================");
                loadData(loadPage, parametersBean.getPageSize());
                break;
            case MSG_UI_LOAD_FAILE:// 加载失败
                swipe_refresh.setRefreshing(false);
                mRecyclerView.notifyMoreFinish(parametersBean.isHasNextPage(), true, message);
                break;
            case MSG_UI_LOAD_NO_DATA:// 没有数据
                swipe_refresh.setRefreshing(false);
                mRecyclerView.notifyMoreFinish(parametersBean.isHasNextPage(), false, message);
                swipe_refresh.setVisibility(View.GONE);
                break;
            case MSG_UI_LOAD_SUCCESS:// 加载成功有更多数据
                swipe_refresh.setEnabled(true);
                swipe_refresh.setVisibility(View.VISIBLE);
                swipe_refresh.setRefreshing(false);
                mRecyclerView.notifyMoreFinish(parametersBean.isHasNextPage(), false, null);
                break;
            case MSG_ALL_DATA_HAVE_LOADED:// 加载成功没有更多数据
                swipe_refresh.setEnabled(true);
                swipe_refresh.setVisibility(View.VISIBLE);
                swipe_refresh.setRefreshing(false);
                mRecyclerView.notifyMoreFinish(false, false, "没有更多了~");
                break;
            case DATABASE_LOED_SUCCESS:// 数据库获取到数据时
                swipe_refresh.setVisibility(View.VISIBLE);
            default:
                break;
        }
    }

    /**
     * 获取fragment实例，fragment_parent大于0时才会自动添加到fragment
     */
    public static <T extends Serializable> MyRecyclerViewFragment<T> getInstance(FragmentManager fm, ParmBean parametersBean) {
        MyRecyclerViewFragment<T> fragment = null;
        if (parametersBean.getFragmentParentId() > 0) {
            fragment = (MyRecyclerViewFragment<T>) fm.findFragmentById(parametersBean.getFragmentParentId());

        }
        if (fragment == null) {
            fragment = new MyRecyclerViewFragment<T>();
            if (parametersBean.getFragmentParentId() > 0) {
                fm.beginTransaction().replace(parametersBean.getFragmentParentId(), fragment).commitAllowingStateLoss();
            }
        }
        LogUtils.print("parametersBean", "parametersBean=" + parametersBean);
        fragment.reSetParameters(parametersBean);
        LogUtils.print(RecyclerViewFragment.class.getName(), "返回fragment");
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sendEmptyMessage(MSG_UI_START_LOADING);
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.hb.utils.R.layout.fragment_recyclerview, container, false);
    }

    @Override
    protected void initView() {
        super.initView();

        mRecyclerView = (LoadMoreRecyclerView) getView().findViewById(com.hb.utils.R.id.recyclerView);
        swipe_refresh = (SwipeRefreshLayout) getView().findViewById(com.hb.utils.R.id.swipe_refresh);

        // 设置可以加载更多
        mRecyclerView.setAutoLoadMoreEnable(true);
    }

    /**
     * 设置布局管理器，不设默认为WrapContentLinearLayoutManager
     */
    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    protected void initData() {
        super.initData();

        // :设置进度动画的颜色。
        swipe_refresh.setColorSchemeResources(com.hb.utils.R.color.bk_title_red);
        // 设置进度圈的大小，只有两个值：DEFAULT、LARGE
        swipe_refresh.setSize(SwipeRefreshLayout.DEFAULT);
        // 设置下拉高度
        // swipe_refresh.setProgressViewEndTarget(true,300);
        // swipe_refresh.setProgressViewOffset(false, 0, 30);
        // swipe_refresh.setRefreshing(true);

        if (layoutManager == null) {
            layoutManager = new WrapContentLinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
        } else {
            mRecyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }
        });

        mRecyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadNextPage();
            }
        });
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public SwipeRefreshLayout getSwipe_refresh() {
        return swipe_refresh;
    }

    public void reSetParameters(ParmBean parametersBean) {
        this.parametersBean = parametersBean;
    }

    public ParmBean getParameters() {
        return parametersBean;
    }

    public BaseRecyclerAdapter<T, ?> getAdapter() {
        return (BaseRecyclerAdapter<T, ?>) mRecyclerView.getRecyclerAdapter();
    }

    protected void loadData(final String loadPage, int pageSize) {
        parametersBean.setCurPage(loadPage);
        parametersBean.setPageSize(pageSize);

        if (recyclerLoadListener != null) {
            recyclerLoadListener.onLoad(parametersBean, this.recyclerLoadListener);
        }
    }

    public void onResult(ResultListBean<T> bean) {
        onResult(bean.getCode(), bean.getMessage(), bean.getResultList());
    }

    public void onResult(int resultCode, String message, List<T> list) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }

        if (parametersBean == null) {
            return;
        }

        if (resultCode != 200) {
            sendMessage(MSG_UI_LOAD_FAILE, message);
            return;
        }

        if (!TextUtils.isEmpty(parametersBean.getCurPage())) {
            if (list == null || list.size() < 1) {
                sendEmptyMessage(MSG_UI_LOAD_NO_DATA);
                return;
            }
        }

        //记录下更新数据前的滚动值
        int scrolly = mRecyclerView.getScrollY();
        // 成功获取到了数据
        if (!parametersBean.isHasNextPage()) {
            BaseRecyclerAdapter adapter = recyclerLoadListener.setAdapter();
            adapter.update(list);
            mRecyclerView.getAdapter().notifyItemRangeChanged(0, mRecyclerView.getAdapter().getItemCount());
        } else {
            BaseRecyclerAdapter adapter = (BaseRecyclerAdapter) mRecyclerView.getRecyclerAdapter();
            adapter.add(list);
            mRecyclerView.getAdapter().notifyItemRangeInserted(adapter.getItemCount(), list.size());
        }
        //恢复更新数据前的滚动值
        mRecyclerView.scrollTo(0, scrolly);
        //		mRecyclerView.scrollToPosition(0);

        BaseRecyclerAdapter adapter = (BaseRecyclerAdapter) mRecyclerView.getRecyclerAdapter();
        int curSize = 0;
        if (adapter != null && adapter.getList() != null) {
//            if (parametersBean.getCurPage() != 1) {
            curSize = adapter.getList().size();
//            }
        }
//        if (list != null && list.size() > 0) {
//            curSize += list.size();
//        }
//        if (parametersBean.getPageCount() > curSize) {
//        parametersBean.setPageCount(parametersBean.getCurPage() + 1);
//        } else {
//            parametersBean.setPageCount(parametersBean.getCurPage());
//        }

//        if (parametersBean.getPageCount() <= parametersBean.getCurPage()) {
//            sendMessage(MSG_ALL_DATA_HAVE_LOADED, "无更多数据");
//        } else {
//            // 加载成功且还有更多数据
        sendMessage(MSG_UI_LOAD_SUCCESS, "加载下一页");
//        }
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public MyRecyclerLoadListener getRecyclerLoadListener() {
        return this.recyclerLoadListener;
    }

    public void setLoadListener(MyRecyclerLoadListener<T> recyclerLoadListener) {
        this.recyclerLoadListener = recyclerLoadListener;
    }
}
