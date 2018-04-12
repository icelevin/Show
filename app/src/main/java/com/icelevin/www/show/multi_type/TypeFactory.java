package com.icelevin.www.show.multi_type;

import android.view.View;

/**
 * Created by ice on 2018/3/28.
 */

public interface TypeFactory {
    int type(SportsTitleModel sportsTitleModel);

    int type(SportsHeadModel sportsHeadModel);

    int type(SpostsContentModel spostsContentModel);

    int type(SportsImageModel sportsImageModel);

    BaseViewHolder createViewHolder(int type, View itemView);
}
