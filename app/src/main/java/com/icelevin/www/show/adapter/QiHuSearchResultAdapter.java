package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.QiHuNewsDataModel;

import java.util.List;

/**
 * Created by ice on 2017/9/30.
 */

public class QiHuSearchResultAdapter extends BaseRecyclerAdapter<QiHuNewsDataModel, QiHuSearchResultAdapter.Model> {


    public QiHuSearchResultAdapter(Context context, List list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {

        return new Model(LayoutInflater.from(context).inflate(R.layout.item_qihu_search_result, arg0,false));
    }

    @Override
    public void onBindViewHolder(Model arg0, int position) {
        super.onBindViewHolder(arg0, position);
        QiHuNewsDataModel data = list.get(position);
        if (!TextUtils.isEmpty(data.getTitle())) {
            arg0.tv_title.setText(list.get(position).getTitle());
        }
        if (!TextUtils.isEmpty(data.getPublishDateStr())) {
            arg0.tv_time.setText(data.getPublishDateStr());
        }
        if (!TextUtils.isEmpty(data.getPosterScreenName())) {
            arg0.tv_source.setText(data.getPosterScreenName());
        }
        if (data.getImageUrls() != null && data.getImageUrls().size() > 0) {
            if (!TextUtils.isEmpty(data.getImageUrls().get(0)) && data.getImageUrls().size() < 2) {
                arg0.ll_three.setVisibility(View.GONE);
                arg0.iv_left.setVisibility(View.VISIBLE);
                Glide.with(context).load(data.getImageUrls().get(0)).into(arg0.iv_left);
            }
            if (data.getImageUrls().size() == 3) {
                arg0.iv_left.setVisibility(View.GONE);
                arg0.ll_three.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(data.getImageUrls().get(0))){
                    Glide.with(context).load(data.getImageUrls().get(0)).into(arg0.iv_1);
                }
                if (!TextUtils.isEmpty(data.getImageUrls().get(1))){
                    Glide.with(context).load(data.getImageUrls().get(1)).into(arg0.iv_2);
                }
                if (!TextUtils.isEmpty(data.getImageUrls().get(2))){
                    Glide.with(context).load(data.getImageUrls().get(2)).into(arg0.iv_3);
                }
            }
        }else {
            arg0.iv_left.setVisibility(View.GONE);
            arg0.ll_three.setVisibility(View.GONE);
        }
    }


    class Model extends RecyclerView.ViewHolder {

        public Model(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_source = (TextView) itemView.findViewById(R.id.tv_source);
            iv_left = (ImageView) itemView.findViewById(R.id.iv_left);
            iv_1 = (ImageView) itemView.findViewById(R.id.iv_1);
            iv_2 = (ImageView) itemView.findViewById(R.id.iv_2);
            iv_3 = (ImageView) itemView.findViewById(R.id.iv_3);
            ll_three= (LinearLayout) itemView.findViewById(R.id.ll_three);
        }

        TextView tv_time, tv_title, tv_source;
        ImageView iv_left, iv_1, iv_2, iv_3;
        LinearLayout ll_three;

    }
}
