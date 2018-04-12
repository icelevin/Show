package com.icelevin.www.show.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hb.utils.net.listener.OnResultListener;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.hb.utils.view.recycler.ParametersBean;
import com.hb.utils.view.recycler.RecyclerLoadListener;
import com.hb.utils.view.recycler.RecyclerViewFragment;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.adapter.NewsCommentAdapter;
import com.icelevin.www.show.model.CCTVNewsModel;
import com.icelevin.www.show.model.NewsCommentModel;
import com.icelevin.www.show.net.NetWorkController;
import com.icelevin.www.show.view.MyRecyclerLoadListener;
import com.icelevin.www.show.view.MyRecyclerViewFragment;
import com.icelevin.www.show.view.ParmBean;

/**
 * Created by ice on 2017/10/20.
 */

public class CommentListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private MyRecyclerViewFragment fragment;
    private NewsCommentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_list, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        final CCTVNewsModel.Data data = (CCTVNewsModel.Data) getActivity().getIntent().getSerializableExtra(CCTVNewsModel.Data.class.getName());
        if (TextUtils.isEmpty(data.getId())) {
            return;
        }

        final ParmBean bean = new ParmBean();
        bean.setFragmentParentId(R.id.fragment_parent);
        fragment = MyRecyclerViewFragment.getInstance(getChildFragmentManager(), bean);
        fragment.setLoadListener(new MyRecyclerLoadListener<NewsCommentModel>() {
            @Override
            public BaseRecyclerAdapter setAdapter() {
                adapter = new NewsCommentAdapter(getActivity(), null, CommentListFragment.this);
                fragment.getRecyclerView().setAdapter(adapter);
                return adapter;
            }

            @Override
            public void onLoad(ParmBean parametersBean, OnResultListener<NewsCommentModel> listener) {
                NetWorkController.INSTANCE.GetNewsComment(data.getId(), parametersBean.getCurPage(), listener);
            }

            @Override
            public void onResult(NewsCommentModel newsCommentModel) {
                if (getActivity() == null || getActivity().isFinishing())
                    return;

                if (!"000000".equals(newsCommentModel.getRetcode()) || TextUtils.isEmpty(newsCommentModel.getRetcode())) {
                    fragment.onResult(-1, "暂无评论", null);

                    return;
                }

                if (newsCommentModel.getData() == null || newsCommentModel.getData().size() < 1) {
                    fragment.onResult(-1, "暂无评论", null);
                    return;
                }
                fragment.onResult(200, "加载成功", newsCommentModel.getData());
                fragment.getParameters().setHasNextPage(newsCommentModel.isHasNext());
                fragment.getParameters().setCurPage(newsCommentModel.getPageToken());

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
