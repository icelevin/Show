package com.icelevin.www.show.ui.sports;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.adapter.SportAdapter;
import com.icelevin.www.show.multi_type.SportsHeadModel;
import com.icelevin.www.show.multi_type.SportsImageModel;
import com.icelevin.www.show.multi_type.SportsTitleModel;
import com.icelevin.www.show.multi_type.SpostsContentModel;
import com.icelevin.www.show.multi_type.Visitable;
import com.icelevin.www.show.net.NetWorkController;
import com.icelevin.www.show.view.MyRecyclerLoadListener;
import com.icelevin.www.show.view.MyRecyclerViewFragment;
import com.icelevin.www.show.view.ParmBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2018/1/15.
 */

public class SportFragment extends BaseFragment implements AdapterView.OnItemClickListener, SportAdapter.HeadOnClickListener {
    private MyRecyclerViewFragment fragment;
    private String type;
    private SportAdapter adapter;

    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，
    // 在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常...
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sport, container, false);
    }


    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    private List<Visitable> getData() {
        final List<Visitable> list = new ArrayList<>();
        NetWorkController.INSTANCE.getQQSports(type, "", new OnResultListener<QQSportsModel>() {
            @Override
            public void onResult(QQSportsModel qqSportsModel) {
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }
                if (!"000000".equals(qqSportsModel.getRetcode())) {
                    return;
                }
                List<QQSportsModel.Data> datas = qqSportsModel.getData();
                for (int i = 0; i < datas.size(); i++) {
                    QQSportsModel.Data data = datas.get(i);
                    if (data == null) {
                        return;
                    }
                    if (!TextUtils.isEmpty(data.getPosterScreenName()) && !TextUtils.isEmpty(data.getPublishDateStr())) {
                        SportsTitleModel sportsTitleModel = new SportsTitleModel();
                        sportsTitleModel.setName(data.getPosterScreenName());
                        sportsTitleModel.setTime(data.getPublishDateStr());
                        list.add(sportsTitleModel);
                    }
                    if (!TextUtils.isEmpty(data.getTitle())) {
                        SportsHeadModel sportsHeadModel = new SportsHeadModel();
                        sportsHeadModel.setTitle(data.getTitle());
                        list.add(sportsHeadModel);
                    }
                    if (!TextUtils.isEmpty(data.getContent())) {
                        SpostsContentModel sportsContentHolder = new SpostsContentModel();
                        sportsContentHolder.setContent(data.getContent());
                        list.add(sportsContentHolder);
                    }
                    if (data.getImageUrls() != null && data.getImageUrls().size() > 0) {
                        SportsImageModel sportsImageModel = new SportsImageModel();
                        sportsImageModel.setList(data.getImageUrls());
                        list.add(sportsImageModel);
                    }
                }

            }
        });
        return list;
    }

    private void initDatas() {
        final ParmBean parmBean = new ParmBean();
        parmBean.setFragmentParentId(R.id.fragment_parent);
        fragment = MyRecyclerViewFragment.getInstance(getChildFragmentManager(), parmBean);
        fragment.setLoadListener(new MyRecyclerLoadListener<QQSportsModel>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                adapter = new SportAdapter(getActivity(), null, SportFragment.this);
                adapter.setHeadOnClickListener(SportFragment.this);

                fragment.getRecyclerView().setAdapter(adapter);
                return adapter;
            }

            @Override
            public void onLoad(ParmBean parametersBean, OnResultListener<QQSportsModel> listener) {
                NetWorkController.INSTANCE.getQQSports(type, parametersBean.getCurPage(), listener);
            }

            @Override
            public void onResult(QQSportsModel qqSportsModel) {
                if (getActivity() == null || getActivity().isFinishing()) {
                    return;
                }
                if (!"000000".equals(qqSportsModel.getRetcode())) {
                    fragment.onResult(-1, qqSportsModel.getMessage(), null);
                    return;
                }
                fragment.getParameters().setCurPage(qqSportsModel.getPageToken());
                fragment.onResult(200, qqSportsModel.getMessage(), qqSportsModel.getData());
                parmBean.setHasNextPage(qqSportsModel.isHasNext());
            }
        });
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        QQSportsModel.Data data = adapter.getList().get(position);
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.VALUENAME, SportInfoFragment.class.getName());
        intent.putExtra(QQSportsModel.Data.class.getName(), data);
        startActivity(intent);
    }

    @Override
    public void clickListener(View view, int position) {
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        intent.putExtra(FragmentContainerActivity.VALUENAME, SportUserInfoFragment.class.getName());
        QQSportsModel.Data data = adapter.getList().get(position);
        LogUtils.print("data", data.getPosterScreenName());
        intent.putExtra(QQSportsModel.Data.class.getName(), data);
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
        //getData();//数据请求
        initDatas();
    }
    protected void onInvisible() {
    }

}
