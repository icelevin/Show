package com.icelevin.www.show.adapter;

/**
 * Created by ice on 2017/10/10.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.SearchCCTVModel;

import java.util.List;


/**
 * Created by ice on 2017/9/25.
 */

public class SearchCCTVNewsAdapter extends BaseRecyclerAdapter<SearchCCTVModel.Data, SearchCCTVNewsAdapter.Model> {
    public SearchCCTVNewsAdapter(Context context, List list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(context).inflate(R.layout.item_newslist, arg0, false));
    }

    @Override
    public void onBindViewHolder(Model arg0, int position) {
        super.onBindViewHolder(arg0, position);
        SearchCCTVModel.Data data = list.get(position);
        LogUtils.print(SearchCCTVNewsAdapter.class.getName(), data + "");
        if (!TextUtils.isEmpty(data.getTitle())) {
            arg0.tv_title.setText(data.getTitle());
        }
        if (!TextUtils.isEmpty(data.getPublishDateStr())) {
            arg0.tv_date.setText(data.getPublishDateStr());
        }
        if (!TextUtils.isEmpty(data.getPosterScreenName())) {
            arg0.tv_source.setText(data.getPosterScreenName());
        }
        if (data.getImageUrls() ==null || data.getImageUrls().size() <1) {
            return;
        }
        if (TextUtils.isEmpty(data.getImageUrls().get(0))) {
            return;
        }
        Glide.with(context).load(data.getImageUrls().get(0)).into(arg0.iv_title);
    }

    public class Model extends RecyclerView.ViewHolder {

        public Model(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_source = (TextView) itemView.findViewById(R.id.tv_source);
            iv_title = (ImageView) itemView.findViewById(R.id.iv_title);
        }

        TextView tv_title, tv_date, tv_source;
        ImageView iv_title;
    }
}
