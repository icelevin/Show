package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import com.icelevin.www.show.model.LeiFengListModel;

import java.util.List;

/**
 * Created by ice on 2018/1/12.
 */

public class SearchLeiPhoneAdapter extends BaseRecyclerAdapter<LeiFengListModel.LeiFengList, SearchLeiPhoneAdapter.Model> {
    public SearchLeiPhoneAdapter(Context context, List<LeiFengListModel.LeiFengList> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(context).inflate(R.layout.item_serach_leiphone, arg0, false));
    }

    @Override
    public void onBindViewHolder(Model arg0, int position) {
        super.onBindViewHolder(arg0, position);
        LeiFengListModel.LeiFengList leiFengList = list.get(position);
        arg0.tv_title.setText(leiFengList.getTitle());
        arg0.tv_name.setText(leiFengList.getPosterScreenName());
        arg0.tv_time.setText(leiFengList.getPublishDateStr());
        arg0.commentCount.setText(leiFengList.getCommentCount());
        if (leiFengList.getImageUrls() != null && leiFengList.getImageUrls().size() > 0) {
            if (leiFengList.getImageUrls().size() > 2) {
                arg0.ll_one.setVisibility(View.GONE);
                arg0.ll_three.setVisibility(View.VISIBLE);
                Glide.with(context).load(leiFengList.getImageUrls().get(0)).into(arg0.iv_img1);
                Glide.with(context).load(leiFengList.getImageUrls().get(1)).into(arg0.iv_img2);
                Glide.with(context).load(leiFengList.getImageUrls().get(2)).into(arg0.iv_img3);
            } else if (leiFengList.getImageUrls().size() > 0 && leiFengList.getImageUrls().size() <= 2) {
                Glide.with(context).load(leiFengList.getImageUrls().get(0)).into(arg0.iv_img);
                arg0.ll_one.setVisibility(View.VISIBLE);
                arg0.ll_three.setVisibility(View.GONE);
            }
        }

    }

    class Model extends RecyclerView.ViewHolder {
        ImageView iv_img1, iv_img2, iv_img3, iv_img;
        TextView tv_title, tv_name, commentCount, tv_time;
        LinearLayout ll_one, ll_three;

        public Model(View itemView) {
            super(itemView);

            iv_img1 = itemView.findViewById(R.id.iv_img1);
            iv_img2 = itemView.findViewById(R.id.iv_img2);
            iv_img3 = itemView.findViewById(R.id.iv_img3);
            iv_img = itemView.findViewById(R.id.iv_img);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_name = itemView.findViewById(R.id.tv_name);
            commentCount = itemView.findViewById(R.id.commentCount);
            tv_time = itemView.findViewById(R.id.tv_time);
            ll_one = itemView.findViewById(R.id.ll_one);
            ll_three = itemView.findViewById(R.id.ll_three);

        }
    }
}
