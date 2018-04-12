package com.icelevin.www.show.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.common.LazyViewPager;
import com.icelevin.www.show.common.MyGridView;
import com.icelevin.www.show.ui.sports.QQSportsModel;

import java.util.List;

/**
 * Created by ice on 2018/1/15.
 */

public class SportAdapter extends BaseRecyclerAdapter<QQSportsModel.Data, SportAdapter.Model> {
    public HeadOnClickListener getHeadOnClickListener() {
        return headOnClickListener;
    }

    public void setHeadOnClickListener(HeadOnClickListener headOnClickListener) {
        this.headOnClickListener = headOnClickListener;
    }

    private HeadOnClickListener headOnClickListener;

    public SportAdapter(Context context, List<QQSportsModel.Data> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(context).inflate(R.layout.item_sport, arg0, false));
    }

    @Override
    public void onBindViewHolder(final Model arg0, int position) {
        super.onBindViewHolder(arg0, position);
        QQSportsModel.Data data = list.get(position);
        if (!TextUtils.isEmpty(data.getPosterScreenName())) {
            arg0.tv_name.setText(data.getPosterScreenName());
            arg0.tv_name.setVisibility(View.VISIBLE);
        } else {
            arg0.tv_name.setText("");
            arg0.tv_name.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getContent())) {
            arg0.tv_content.setText(data.getContent());
            arg0.tv_content.setVisibility(View.VISIBLE);
        } else {
            arg0.tv_content.setText("");
            arg0.tv_content.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getTitle())) {
            arg0.tv_title.setText(data.getTitle());
            arg0.tv_title.setVisibility(View.VISIBLE);
        } else {
            arg0.tv_title.setText("");
            arg0.tv_title.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(data.getPublishDateStr())) {
            String title = data.getPublishDateStr();
            if (data.getPublishDateStr().contains("T")) {

                title = data.getPublishDateStr().replace("T", " ");
            }
            arg0.tv_time.setText(title);
            arg0.tv_time.setVisibility(View.VISIBLE);
        } else {
            arg0.tv_time.setText("");
            arg0.tv_time.setVisibility(View.GONE);
        }
        if (data.getImageUrls() != null && data.getImageUrls().size() > 0) {
            arg0.gridView.setAdapter(new GridViewAdapter(context, data.getImageUrls(), arg0.gridView));
            arg0.gridView.setVisibility(View.VISIBLE);
            switch (data.getImageUrls().size()) {

                case 1:
                    arg0.gridView.setNumColumns(1);
                    break;

                case 2:
                    arg0.gridView.setNumColumns(2);
                    break;

                default:
                    arg0.gridView.setNumColumns(3);
                    break;
            }
        } else {
            arg0.gridView.setVisibility(View.GONE);
        }

        arg0.iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headOnClickListener.clickListener(arg0.iv_head, arg0.getLayoutPosition());
            }
        });
    }


    class Model extends RecyclerView.ViewHolder {
        TextView tv_name, tv_time, tv_title, tv_content;
        ImageView iv_head;
        GridView gridView;

        public Model(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_head = itemView.findViewById(R.id.iv_head);
            gridView = itemView.findViewById(R.id.gridView);
        }
    }

    class GridViewAdapter extends BaseAdapter {
        private Context context;
        private List<String> list;
        private GridView gridView;

        public GridViewAdapter(Context context, List<String> list, GridView gridView) {
            this.context = context;
            this.list = list;
            this.gridView = gridView;
        }

        @Override
        public int getCount() {
            int count = 0;
            if (list == null || list.size() < 1) {
                count = 0;
            }
            if (list.size() <= 9) {
                count = list.size();
            }
            if (list.size() > 9) {
                count = 9;
            }
            return count;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_img, parent, false);
                holder.imageView = convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            Glide.with(context).load(list.get(position)).asBitmap().error(R.mipmap.none).placeholder(R.mipmap.none).into(holder.imageView);
            return convertView;
        }

        class Holder {
            ImageView imageView;

        }
    }

    public interface HeadOnClickListener {
        void clickListener(View view, int position);
    }

}
