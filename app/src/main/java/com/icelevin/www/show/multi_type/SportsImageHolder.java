package com.icelevin.www.show.multi_type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.icelevin.www.show.R;

import java.util.List;

/**
 * Created by ice on 2018/3/28.
 */

public class SportsImageHolder extends BaseViewHolder<SportsImageModel> {
    public SportsImageHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(SportsImageModel model, int position, MultiTypeAdapter adapter) {
        GridView gridView = itemView.findViewById(R.id.gridView);
        if (model.getList() == null || model.getList().size() < 1) {
            gridView.setVisibility(View.GONE);
            return;
        }
        switch (model.getList().size()) {
            case 1:
                gridView.setNumColumns(1);
                gridView.setVisibility(View.VISIBLE);
                break;
            case 2:
                gridView.setNumColumns(2);
                gridView.setVisibility(View.VISIBLE);
                break;

            default:
                gridView.setNumColumns(3);
                gridView.setVisibility(View.VISIBLE);
                break;

        }
        gridView.setAdapter(new GridViewAdapter(itemView.getContext(), model.getList()));
    }

    class GridViewAdapter extends BaseAdapter {
        private Context context;
        private List<String> list;

        public GridViewAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            int count = 0;
            if (list == null || list.size() < 1) {
                count = 0;
            } else if (list.size() > 9) {
                count = 9;
            } else {
                count = list.size();
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
            GridViewAdapter.Holder holder = null;
            if (convertView == null) {
                holder = new GridViewAdapter.Holder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_img, parent, false);
                holder.imageView = convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (GridViewAdapter.Holder) convertView.getTag();
            }
            Glide.with(context).load(list.get(position)).placeholder(R.mipmap.none).error(R.mipmap.none).into(holder.imageView);
            return convertView;
        }

        class Holder {
            ImageView imageView;

        }
    }
}
