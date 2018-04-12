package com.icelevin.www.show.multi_type;

import android.view.View;
import android.widget.TextView;

import com.icelevin.www.show.R;

/**
 * Created by ice on 2018/3/28.
 */

public class SportsHeadHolder extends BaseViewHolder<SportsHeadModel>{
    public SportsHeadHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(SportsHeadModel model, int position, MultiTypeAdapter adapter) {
        TextView textView=itemView.findViewById(R.id.tv_title);
        textView.setText(model.getTitle());
    }
}
