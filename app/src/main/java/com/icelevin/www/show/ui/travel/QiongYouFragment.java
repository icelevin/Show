package com.icelevin.www.show.ui.travel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.activity.FragmentContainerActivity;
import com.hb.utils.view.fragment.BaseFragment;
import com.hb.utils.view.recycler.ParametersBean;
import com.hb.utils.view.recycler.RecyclerLoadListener;
import com.hb.utils.view.recycler.RecyclerViewFragment;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.adapter.CityNameAdapter;
import com.icelevin.www.show.model.BaseModel;
import com.icelevin.www.show.model.CityQiongYou;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by ice on 2017/12/14.
 */

public class QiongYouFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private RecyclerViewFragment fragment, fragment1;
    private CityNameAdapter adapter;
    private FloatingActionButton floatButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qiong_you, container, false);
    }

    @Override
    protected void initView() {
        floatButton = (FloatingActionButton) getView().findViewById(R.id.floatButton);
    }

    @Override
    protected void initData() {
        initCitys();
    }

    @Override
    protected void initListener() {
        floatButton.setOnClickListener(this);
    }

    private void initCitys() {
        ParametersBean bean = new ParametersBean();
        bean.setFragmentParentId(R.id.fragment_parent1);
        fragment1 = RecyclerViewFragment.getInstance(getChildFragmentManager(), bean);
        fragment1.setLoadListener(new RecyclerLoadListener<BaseModel>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                adapter = new CityNameAdapter(getActivity(), null, QiongYouFragment.this);
                fragment1.getRecyclerView().setAdapter(adapter);
                return adapter;
            }

            @Override
            public void onLoad(ParametersBean parametersBean, OnResultListener<BaseModel> listener) {
                BmobQuery<CityQiongYou> query = new BmobQuery<>();
                query.setSkip((parametersBean.getCurPage() - 1) * 10).setLimit(Integer.MAX_VALUE).findObjects(new FindListener<CityQiongYou>() {
                    @Override
                    public void done(List<CityQiongYou> list, BmobException e) {
                        if (e == null) {
                            fragment1.onResult(200, "", list);
                            LogUtils.print("长度", list.size() + "");
                        } else {
                            fragment1.onResult(-1, e.getMessage(), null);
                        }
                    }
                });
            }

            @Override
            public void onResult(BaseModel o) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.floatButton){
            Intent intent=new Intent(getActivity(), FragmentContainerActivity.class);
            intent.putExtra(FragmentContainerActivity.VALUENAME,SearchScenicFragment.class.getName());
            startActivity(intent);
        }
    }
}
