package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.QiongYouScenicModel;

import java.util.List;

/**
 * Created by ice on 2018/1/4.
 */

public class QiongYouScenicAdapter extends BaseRecyclerAdapter<QiongYouScenicModel.Data, QiongYouScenicAdapter.Model> {
    public QiongYouScenicAdapter(Context context, List<QiongYouScenicModel.Data> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(context).inflate(R.layout.item_qy_scenic, arg0, false));
    }

    @Override
    public void onBindViewHolder(Model arg0, int position) {
        super.onBindViewHolder(arg0, position);
        QiongYouScenicModel.Data data = list.get(position);
        arg0.tv_title.setText(data.getTitle());
        if (data.getImageUrls() == null || data.getImageUrls().size() < 1) {
            arg0.iv_title.setImageResource(R.color.line);

        } else {
            Glide.with(context).load(data.getImageUrls().get(0)).into(arg0.iv_title);

        }
        arg0.tv_stars.setText(String.valueOf(data.getRating()));
        arg0.ratingBar.setRating((float) data.getRating());
    }


    class Model extends RecyclerView.ViewHolder {
        ImageView iv_title;
        RatingBar ratingBar;
        TextView tv_title, tv_stars;

        public Model(View itemView) {
            super(itemView);
            iv_title = itemView.findViewById(R.id.iv_title);
            ratingBar = itemView.findViewById(R.id.tv_rating);
            tv_stars = itemView.findViewById(R.id.tv_stars);
            tv_title = itemView.findViewById(R.id.tv_title);

        }
    }
}
