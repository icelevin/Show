package com.icelevin.www.show.multi_type;

import android.view.View;
import android.widget.TextView;

import com.icelevin.www.show.R;

/**
 * Created by ice on 2018/3/28.
 */

public class SportsContentHolder extends BaseViewHolder<SpostsContentModel> {
    public SportsContentHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(SpostsContentModel model, int position, MultiTypeAdapter adapter) {
        TextView textView = itemView.findViewById(R.id.tv_content);
        textView.setText(model.getContent());
    }
}
