package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.behavior.ScrollingViewBehavior;
import com.icelevin.www.show.model.LeiFengListModel;

import java.util.List;

/**
 * Created by ice on 2018/1/2.
 */

public class LeiFengListAdapter extends BaseRecyclerAdapter<LeiFengListModel.LeiFengList, LeiFengListAdapter.Model> {

    public LeiFengListAdapter(Context context, List<LeiFengListModel.LeiFengList> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(context).inflate(R.layout.item_lei_feng_list, arg0, false));
    }

    @Override
    public void onBindViewHolder(Model arg0, int position) {
        super.onBindViewHolder(arg0, position);
        LeiFengListModel.LeiFengList leiFengList = list.get(position);
        if (!TextUtils.isEmpty(leiFengList.getTitle())) {
            arg0.tv_title.setText(leiFengList.getTitle());
        } else {
            arg0.tv_title.setText("");
        }
        if (!TextUtils.isEmpty(String.valueOf(leiFengList.getViewCount()))) {
            arg0.tv_read.setText(leiFengList.getViewCount() + " 阅读");
        } else {
            arg0.tv_read.setText("");
        }
        if (!TextUtils.isEmpty(String.valueOf(leiFengList.getLikeCount()))) {
            arg0.tv_like.setText(leiFengList.getLikeCount() + "");
        } else {
            arg0.tv_like.setText("");
        }
        if (!TextUtils.isEmpty(leiFengList.getPosterScreenName())) {
            arg0.tv_poster.setText(leiFengList.getPosterScreenName());
        } else {
            arg0.tv_poster.setText("");
        }
        if (!TextUtils.isEmpty(leiFengList.getPublishDateStr())) {
            String time = leiFengList.getPublishDateStr();
            if (leiFengList.getPublishDateStr().contains("T")) {
                time = leiFengList.getPublishDateStr().replace("T", " ");
                arg0.tv_time.setText(time);
            } else {
                arg0.tv_time.setText(time);
            }
        } else {
            arg0.tv_time.setText("");
        }

        if (leiFengList.getImageUrls() != null || leiFengList.getImageUrls().size() > 0) {
            Glide.with(context).load(leiFengList.getImageUrls().get(0)).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().thumbnail(0.5f).centerCrop().into(arg0.iv_title);
        }
    }

    class Model extends RecyclerView.ViewHolder {
        TextView tv_title, tv_time, tv_poster, tv_read, tv_like;
        ImageView iv_title;

        public Model(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_poster = (TextView) itemView.findViewById(R.id.tv_poster);
            tv_read = (TextView) itemView.findViewById(R.id.tv_read);
            tv_like = (TextView) itemView.findViewById(R.id.tv_like);
            iv_title = (ImageView) itemView.findViewById(R.id.iv_title);
        }
    }
}
