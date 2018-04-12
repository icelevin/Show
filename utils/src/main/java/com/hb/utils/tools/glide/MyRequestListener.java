package com.hb.utils.tools.glide;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hb.utils.tools.LogUtils;

/**
 * Created by 69095 on 2017/5/29 0029.
 */
public class MyRequestListener implements RequestListener {
    @Override
    public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
       LogUtils.print(MyRequestListener.class.getName(),"url="+model);
        if(e != null){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
        return false;
    }
}
