package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.hb.utils.view.recycler.adapter.BaseRecyclerAdapter;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.BiliBiliModel;

import java.util.List;

/**
 * Created by ice on 2017/12/14.
 */

public class BiLiAdapter extends BaseRecyclerAdapter<BiliBiliModel.Data, BiLiAdapter.ViewHolder> {
    public BiLiAdapter(Context context, List<BiliBiliModel.Data> list, AdapterView.OnItemClickListener itemClickListener) {
        super(context, list, itemClickListener);
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_bangumi_list,arg0,false));

    }

    @Override
    public void onBindViewHolder(ViewHolder arg0, int position) {
        super.onBindViewHolder(arg0, position);

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
