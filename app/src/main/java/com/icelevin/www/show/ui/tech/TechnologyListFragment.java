package com.icelevin.www.show.ui.tech;

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
import com.icelevin.www.show.adapter.LeiFengListAdapter;
import com.icelevin.www.show.model.LeiFengListModel;
import com.icelevin.www.show.net.NetWorkController;

import java.util.List;

/**
 * Created by ice on 2017/12/29.
 */

public class TechnologyListFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private String type;
    private RecyclerViewFragment fragment;
    private LeiFengListAdapter adapter;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，
    // 在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_technology_list, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.VALUENAME, TechnologyInfoFragment.class.getName());
        List<LeiFengListModel.LeiFengList> list = adapter.getList();
        intent.putExtra(LeiFengListModel.LeiFengList.class.getName(), list.get(position));

        startActivity(intent);
    }

    private void initDatas() {
        ParametersBean bean = new ParametersBean();
        bean.setFragmentParentId(R.id.fragment_parent);
        fragment = RecyclerViewFragment.getInstance(getChildFragmentManager(), bean);
        fragment.setLoadListener(new RecyclerLoadListener<LeiFengListModel>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                adapter = new LeiFengListAdapter(getActivity(), null, TechnologyListFragment.this);
                fragment.getRecyclerView().setAdapter(adapter);
                return adapter;
            }

            @Override
            public void onLoad(ParametersBean parametersBean, OnResultListener<LeiFengListModel> listener) {
                NetWorkController.INSTANCE.getLeifeng(type, parametersBean.getCurPage() + "", listener);
            }

            @Override
            public void onResult(LeiFengListModel model) {
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }
                if (!"000000".equals(model.getRetcode())) {
                    fragment.onResult(-1, model.getMessage(), null);
                    return;
                }
                fragment.getParameters().setPageCount(model.getData().size());
                fragment.onResult(200, model.getMessage(), model.getData());
            }
        });
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
        //getData();//数据请求
        initDatas();
    }
    protected void onInvisible() {
    }
}
