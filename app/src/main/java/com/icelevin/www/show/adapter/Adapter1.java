package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.icelevin.www.show.R;
import com.icelevin.www.show.model.DepartmentModel;

import java.util.List;

/**
 * Created by ice on 2017/11/8.
 */

public class Adapter1 extends RecyclerView.Adapter<Adapter1.Model> implements View.OnClickListener {
    private Context context;
    private OnItemClickListener listener;
    private List<DepartmentModel> list;

    public Adapter1(Context context, List<DepartmentModel> list,OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @Override
    public Model onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle1, viewGroup,false);
        Model model = new Model(view);
        view.setOnClickListener(this);
        return model;
    }

    @Override
    public void onBindViewHolder(Model model, int i) {
        model.tv_text.setText(list.get(i).getName());
        model.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.setOnItem(v, (int) v.getTag());
        }
    }

    public interface OnItemClickListener {
        void setOnItem(View view,int position);
    }

    class Model extends RecyclerView.ViewHolder {

        public Model(View itemView) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        }

        TextView tv_text;
    }
}