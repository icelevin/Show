package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.NewsCommentModel;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ice on 2017/10/19.
 */

public class NewsCommentAdapter extends BaseRecyclerAdapter<NewsCommentModel.Data, NewsCommentAdapter.Model> {

    public NewsCommentAdapter(Context context, List<NewsCommentModel.Data> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(arg0.getContext()).inflate(R.layout.item_comment,arg0,false));
    }

    @Override
    public void onBindViewHolder(Model holder, int position) {
        super.onBindViewHolder(holder, position);

        NewsCommentModel.Data data = list.get(position);
        if (!TextUtils.isEmpty(data.getCommenterScreenName()))
            holder.comment_author.setText(data.getCommenterScreenName());
        if (!TextUtils.isEmpty(data.getContent()))
            holder.comment_content.setText(data.getContent());
        if (!TextUtils.isEmpty(data.getPublishDate() + "")) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long publishDate = data.getPublishDate();
            String format1 = format.format(publishDate * 1000L);
            holder.comment_time.setText(format1);
        }

    }


    class Model extends RecyclerView.ViewHolder {

        public Model(View itemView) {
            super(itemView);
            comment_author = (TextView) itemView.findViewById(R.id.comment_author);
            comment_time = (TextView) itemView.findViewById(R.id.comment_time);
            comment_content = (TextView) itemView.findViewById(R.id.comment_content);
        }

        TextView comment_author, comment_time, comment_content;
    }
}
