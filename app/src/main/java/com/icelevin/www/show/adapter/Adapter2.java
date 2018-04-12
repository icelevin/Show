package com.icelevin.www.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.utils.tools.LogUtils;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.DiseaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice on 2017/11/8.
 */

public class Adapter2 extends RecyclerView.Adapter<Adapter2.Model> implements View.OnClickListener {
    private List<DiseaseModel> list;
    private OnItemClickListener listener;
    private SparseBooleanArray array = new SparseBooleanArray();
    private int maxSelect;
    private Context context;
    private List<DiseaseModel> selectItem;

    private void setItemChecked(int position, boolean isChcecked) {
        array.put(position, isChcecked);
    }


    private boolean isChecked(int position) {
        return array.get(position);
    }

    public Adapter2(Context context, List<DiseaseModel> list, List<DiseaseModel> selectItem, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
        this.selectItem = selectItem;

    }

    @Override
    public Model onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_adapter2, viewGroup, false);
        Model model = new Model(view);
        view.setOnClickListener(this);
        return model;
    }

    public int getMaxSelect() {

        return maxSelect;
    }

    @Override
    public void onBindViewHolder(final Model model, final int i) {
        model.tv_text.setText(list.get(i).getName());
        model.checkBox.setChecked(isChecked(i));

        model.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isCheck()) {
                    model.checkBox.setBackgroundResource(R.mipmap.hide_pwd);
                    list.get(i).setCheck(false);
                    selectItem.get(i).setCheck(false);
                } else {

                    model.checkBox.setBackgroundResource(R.mipmap.show_password);
                    list.get(i).setCheck(true);
                    selectItem.get(i).setCheck(true);
                }
            }
        });
        model.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.setOnOnClick(v, (int) v.getTag());
        }
    }

    public List<DiseaseModel> getSelectItem() {
        List<DiseaseModel> datas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            datas.add(list.get(i));
        }
        return datas;
    }


    public interface OnItemClickListener {
        void setOnOnClick(View view, int position);
    }

    class Model extends RecyclerView.ViewHolder {

        public Model(View itemView) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

        TextView tv_text;
        CheckBox checkBox;

    }
}
