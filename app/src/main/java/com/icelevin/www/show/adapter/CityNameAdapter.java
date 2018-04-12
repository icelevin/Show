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
import com.icelevin.www.show.model.CityQiongYou;

import java.util.List;

/**
 * Created by ice on 2018/1/3.
 */

public class CityNameAdapter extends BaseRecyclerAdapter<CityQiongYou, CityNameAdapter.Model> {
    public CityNameAdapter(Context context, List list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }

    @Override
    public Model onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new Model(LayoutInflater.from(context).inflate(R.layout.item_city_name, arg0, false));
    }

    @Override
    public void onBindViewHolder(Model arg0, int position) {
        super.onBindViewHolder(arg0, position);
        if (!TextUtils.isEmpty(list.get(position).getCity_name()))
            arg0.textView.setText(list.get(position).getCity_name());
    }

    class Model extends RecyclerView.ViewHolder {
        TextView textView;

        public Model(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
