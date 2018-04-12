package com.icelevin.www.show.multi_type;

import android.view.View;

import com.icelevin.www.show.R;

/**
 * Created by ice on 2018/3/28.
 */

public class TypeFactoryForList implements TypeFactory {
    private final int LAYOUT_TITLE = R.layout.item_sports_list_title;
    private final int LAYOUT_HEAD = R.layout.item_sports_list_head;
    private final int LAYOUT_CONTENT = R.layout.item_sports_list_content;
    private final int LAYOUT_IMAGE = R.layout.item_sports_list_image;


    @Override
    public int type(SportsTitleModel sportsTitleModel) {
        return LAYOUT_TITLE;
    }

    @Override
    public int type(SportsHeadModel sportsHeadModel) {
        return LAYOUT_HEAD;
    }

    @Override
    public int type(SpostsContentModel spostsContentModel) {
        return LAYOUT_CONTENT;
    }

    @Override
    public int type(SportsImageModel sportsImageModel) {
        return LAYOUT_IMAGE;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        if (LAYOUT_TITLE == type) {
            return new SportsTitleHolder(itemView);
        } else if (LAYOUT_HEAD == type) {
            return new SportsHeadHolder(itemView);
        } else if (LAYOUT_CONTENT == type) {
            return new SportsContentHolder(itemView);
        } else if (LAYOUT_IMAGE == type) {
            return new SportsImageHolder(itemView);
        }
        return null;
    }
}
