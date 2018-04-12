package com.icelevin.www.show.ui.tech;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.awen.photo.photopick.controller.PhotoPagerConfig;
import com.hb.utils.tools.LogUtils;
import com.hb.utils.tools.SDCardUtil;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.LeiFengListModel;
import com.zzhoujay.okhttpimagedownloader.OkHttpImageDownloader;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.RichType;
import com.zzhoujay.richtext.callback.OnImageClickListener;
import com.zzhoujay.richtext.ig.DefaultImageGetter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ice on 2018/1/2.
 */

public class TechnologyInfoFragment extends BaseFragment implements OnImageClickListener {
    private TextView tv_title;
    private LeiFengListModel.LeiFengList leiFengList;
    private TextView richText, queryWord, tv_tags, tv_viewCount, tv_name, tv_time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_technology_info, container, false);
    }

    @Override
    protected void initView() {
        tv_title = (TextView) getView().findViewById(R.id.tv_title);
        richText = (TextView) getView().findViewById(R.id.richText);
        queryWord = (TextView) getView().findViewById(R.id.queryWord);
        tv_tags = (TextView) getView().findViewById(R.id.tv_tags);
        tv_viewCount = (TextView) getView().findViewById(R.id.tv_viewCount);
        tv_name = (TextView) getView().findViewById(R.id.tv_name);
        tv_time = (TextView) getView().findViewById(R.id.tv_time);
    }

    @Override
    protected void initData() {
        leiFengList = (LeiFengListModel.LeiFengList) getActivity().getIntent().getSerializableExtra(LeiFengListModel.LeiFengList.class.getName());
        tv_title.setText(leiFengList.getTitle());

        queryWord.setText("关键字 : " + leiFengList.getQueryWord());
        tv_viewCount.setText(leiFengList.getViewCount() + " 已阅");
        tv_name.setText("发布者 : " + leiFengList.getPosterScreenName());
        tv_time.setText("时间 : " + leiFengList.getPublishDateStr());
        if (leiFengList.getTags() != null || leiFengList.getTags().size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < leiFengList.getTags().size(); i++) {
                builder.append(" " + leiFengList.getTags().get(i));
            }
            tv_tags.setText(builder.toString());
        }

        File file = new File(SDCardUtil.getInstance(getActivity()).IMAGE_CACHE_FOLDER + System.currentTimeMillis());
        RichText.initCacheDir(file);
        RichText.from(leiFengList.getContent()).type(RichType.html).scaleType(ImageHolder.ScaleType.center_crop).imageDownloader(new OkHttpImageDownloader()).cache(CacheType.all).imageGetter(new DefaultImageGetter()).imageClick(this).into(richText);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void imageClicked(List<String> imageUrls, int position) {
        LogUtils.print("size",imageUrls.size()+"");
//        imageUrls.remove(imageUrls.size()-1);
        new PhotoPagerConfig.Builder(getActivity())
                .setBigImageUrls((ArrayList<String>) imageUrls)      //大图片url,可以是sd卡res，asset，网络图片.
                .setSavaImage(true)                                 //开启保存图片，默认false
                .setPosition(position)                                     //默认展示第2张图片
//                .setSaveImageLocalPath("Android/SD/xxx/xxx")        //这里是你想保存大图片到手机的地址,可在手机图库看到，不传会有默认地址
                .setOpenDownAnimate(false)                          //是否开启下滑关闭activity，默认开启。类似微信的图片浏览，可下滑关闭一样
                .build();
    }
}
