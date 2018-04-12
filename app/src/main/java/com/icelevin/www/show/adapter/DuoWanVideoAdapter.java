package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.DuoWanVideoModel;
import com.icelevin.www.show.utils.DateUtils;

import java.util.List;

import cn.ittiger.player.VideoPlayerView;


/**
 * Created by ice on 2017/9/28.
 */

public class DuoWanVideoAdapter extends BaseRecyclerAdapter<DuoWanVideoModel.Data, DuoWanVideoAdapter.Model> {

    public DuoWanVideoAdapter(Context context, List list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(context).inflate(R.layout.item_nba_video_list, arg0, false));

    }

    @Override
    public void onBindViewHolder(Model model, int position) {
        super.onBindViewHolder(model, position);
        DuoWanVideoModel.Data data = list.get(position);

        if (!TextUtils.isEmpty(data.getTitle())) {
            model.tv_videoTitle.setText(data.getTitle());
        } else {
            model.tv_videoTitle.setText("0");
        }
        if (!TextUtils.isEmpty(String.valueOf(data.getDanmakuCount()))) {
            model.video_comment.setText(String.valueOf(data.getDanmakuCount()));
        } else {
            model.video_comment.setText("0");
        }
        if (!TextUtils.isEmpty(String.valueOf(data.getViewCount()))) {
            model.video_play.setText(String.valueOf(data.getViewCount()));
        } else {
            model.video_play.setText("0");
        }
        if (!TextUtils.isEmpty(data.getFavoriteCount())) {
            model.video_prise.setText(data.getFavoriteCount());
        } else {
            model.video_prise.setText("0");
        }
        Glide.with(context).load(data.getCoverUrl()).centerCrop().into(model.iv_video.getThumbImageView());
        if (data.getVideoUrls() != null && data.getVideoUrls().size() > 0) {

            if (!TextUtils.isEmpty(data.getVideoUrls().get(0))) {
                if (!TextUtils.isEmpty(data.getTitle())) {
                    model.iv_video.bind(data.getVideoUrls().get(0), data.getTitle());
                } else {
                    model.iv_video.bind(data.getVideoUrls().get(0));
                }
            }
        } else {
            model.iv_video.bind("");
        }
        if (!TextUtils.isEmpty(data.getDurationMin() + "")) {
            model.tv_times.setText(DateUtils.getVideoTime(data.getDurationMin()));
        } else {
            model.tv_times.setText("0");
        }

    }

    class Model extends RecyclerView.ViewHolder {

        public Model(View itemView) {
            super(itemView);
            tv_videoTitle = (TextView) itemView.findViewById(R.id.tv_videoTitle);
            video_play = (TextView) itemView.findViewById(R.id.video_play);
            video_prise = (TextView) itemView.findViewById(R.id.video_prise);
            video_comment = (TextView) itemView.findViewById(R.id.video_comment);
            iv_video = (VideoPlayerView) itemView.findViewById(R.id.iv_video);
            tv_times = (TextView) itemView.findViewById(R.id.tv_times);
        }

        TextView tv_videoTitle, video_play, video_prise, video_comment, tv_times;
        VideoPlayerView iv_video;
    }
}
