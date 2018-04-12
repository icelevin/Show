package com.icelevin.www.show.multi_type;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icelevin.www.show.R;

/**
 * Created by ice on 2018/3/28.
 */

public class SportsTitleHolder extends BaseViewHolder<SportsTitleModel> {
    public SportsTitleHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(SportsTitleModel model, int position, MultiTypeAdapter adapter) {
        ImageView iv_head = itemView.findViewById(R.id.iv_head);
        TextView tv_name = itemView.findViewById(R.id.tv_name);
        TextView tv_time = itemView.findViewById(R.id.tv_time);
        Glide.with(itemView.getContext()).load(model.getHead()).error(R.mipmap.d_touxiang).placeholder(R.mipmap.d_touxiang).into(iv_head);
        tv_name.setText(model.getName());
        tv_time.setText(model.getTime());
    }
}
