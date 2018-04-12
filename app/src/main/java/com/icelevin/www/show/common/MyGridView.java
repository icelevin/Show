package com.icelevin.www.show.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.GridView;

/**
 * Created by ice on 2018/1/18.
 */

public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;
        // 这几行代码比较重要
//        if (getLayoutParams().height == GridView.LayoutParams.WRAP_CONTENT) {
//            heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 3, MeasureSpec.AT_MOST);
//        } else {
//            heightSpec = heightMeasureSpec;
//        }
        heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 4, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, heightSpec);
    }

}
