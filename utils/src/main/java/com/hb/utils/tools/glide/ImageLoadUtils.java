package com.hb.utils.tools.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.hb.utils.BaseApplication;
import com.hb.utils.R;

import java.io.File;

/**
 * Created by 69095 on 2017/5/29 0029.
 */
public class ImageLoadUtils {


    public static void load(String url,ImageView iv) {
        load(url,iv,0,0,false);
    }

    public static void load(String url,ImageView iv,int placeholder ,int error,boolean isCircle){
        if(TextUtils.isEmpty(url)){
            return;
        }

        if(url.lastIndexOf("http://") > 0){
            int lastIndexOf = url.lastIndexOf("http://");
            url = url.substring(lastIndexOf);
        }

        Context context = BaseApplication.getAppContext();
        DrawableTypeRequest<String> load = Glide.with(context).load(url);
        load.diskCacheStrategy(DiskCacheStrategy.ALL);//磁盘缓存策略:
        if(error > 0){
            load .error(error);
        }
        if(placeholder > 0){
            load.placeholder(placeholder);
        }
        DrawableRequestBuilder listener = load.listener(new MyRequestListener());
        if(isCircle){
            listener .transform(new GlideCircleTransform(context, 1, context.getResources().getColor(R.color.line)));
        }
        listener.into(iv);
    }

    /**
     * 下载一张图片
     * @param imageHttpUrl
     * @return
     */
    public static String downloadImage(String imageHttpUrl){
        File file = null;
        try {
            DrawableTypeRequest<String> load = Glide.with(BaseApplication.getAppContext()).load(imageHttpUrl);
            load.diskCacheStrategy(DiskCacheStrategy.ALL);
            file = load.downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        if(file == null || !file.exists()){
            return "";
        }
        return file.getPath();
    }
}
