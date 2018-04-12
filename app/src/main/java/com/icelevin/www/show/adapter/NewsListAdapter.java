package com.icelevin.www.show.adapter;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.CCTVNewsModel;
import com.icelevin.www.show.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ice on 2017/9/25.
 */

public class NewsListAdapter extends BaseRecyclerAdapter<CCTVNewsModel.Data, NewsListAdapter.Model> {
    public NewsListAdapter(Context context, List list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(context).inflate(R.layout.item_newslist, arg0, false));
    }

    @Override
    public void onBindViewHolder(Model model, int position) {
        super.onBindViewHolder(model, position);
        CCTVNewsModel.Data data = list.get(position);
        if (!TextUtils.isEmpty(data.getTitle())) {
            model.tv_title.setText(data.getTitle());
        }
        if (!TextUtils.isEmpty(String.valueOf(data.getPublishDate()))) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            final String format1 = format.format(new Date(data.getPublishDate() * 1000L));
            model.tv_date.setText(DateUtils.getDaysAgo(format1));
        }
        if (!TextUtils.isEmpty(data.getPosterScreenName())) {
            model.tv_source.setText(data.getPosterScreenName());
        }
        if (data.getImageUrls() == null || data.getImageUrls().size() < 1 || TextUtils.isEmpty(data.getImageUrls().get(0))) {
            model.iv_title.setVisibility(View.GONE);
            return;
        }

        model.iv_title.setVisibility(View.VISIBLE);
        Glide.with(context).load(data.getImageUrls().get(0)).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().thumbnail(0.5f).into(model.iv_title);
    }

    public class Model extends RecyclerView.ViewHolder {

        public Model(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_source = (TextView) itemView.findViewById(R.id.tv_source);
            iv_title = (ImageView) itemView.findViewById(R.id.iv_title);
        }

        TextView tv_title, tv_date, tv_source, tv_count;
        ImageView iv_title;
    }
}
