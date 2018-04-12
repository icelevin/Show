package com.icelevin.www.show.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.view.TitleView;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.model.CCTVNewsModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.ittiger.player.PlayerManager;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 新闻详情页
 * Created by ice on 2017/10/11.
 */

public class CCTVNewsInfoFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private TextView info_title, info_date, info_author, info_content, info_viewCount, info_likeCount;

    private CCTVNewsModel.Data data;
    private JZVideoPlayerStandard jzVideoPlayerStandard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cctv_news_info, container, false);
    }

    @Override
    protected void initView() {

        info_author = (TextView) getView().findViewById(R.id.info_author);
        info_title = (TextView) getView().findViewById(R.id.info_title);
        info_date = (TextView) getView().findViewById(R.id.info_date);
        info_content = (TextView) getView().findViewById(R.id.info_content);
        info_viewCount = (TextView) getView().findViewById(R.id.info_viewCount);
        info_likeCount = (TextView) getView().findViewById(R.id.info_likeCount);

        jzVideoPlayerStandard = getView().findViewById(R.id.videoplayer);


    }

    @Override
    protected void initData() {
        TitleView titleView = getView().findViewById(R.id.title_view);

        data = (CCTVNewsModel.Data) getActivity().getIntent().getSerializableExtra(CCTVNewsModel.Data.class.getName());
        titleView.setTitleText(data.getTitle());
        initNews(data);


    }


    private void initNews(CCTVNewsModel.Data data) {
        //编辑名字
        if (!TextUtils.isEmpty(data.getPosterScreenName())) {
            info_author.setText(data.getPosterScreenName());
        }
        //标题
        if (!TextUtils.isEmpty(data.getTitle())) {
            info_title.setText(data.getTitle());
        }
        //内容
        if (!TextUtils.isEmpty(data.getContent())) {
            info_content.setText(data.getContent());
        }
        //时间
        if (!TextUtils.isEmpty(String.valueOf(data.getPublishDate()))) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

            info_date.setText(format.format(new Date(data.getPublishDate() * 1000L)));
        }
        //视频
        if (data.getVideoUrls() == null || data.getVideoUrls().size() < 1) {
            jzVideoPlayerStandard.setVisibility(View.GONE);
        } else {
            if (!TextUtils.isEmpty(data.getVideoUrls().get(0))) {
                if (data.getImageUrls() != null && data.getImageUrls().size() > 0) {
//                    videoView.setVideoURI(Uri.parse(data.getVideoUrls().get(0)));
                    jzVideoPlayerStandard.setUp(data.getVideoUrls().get(0)
                            , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
                    Glide.with(getActivity()).load(data.getImageUrls().get(0)).into(jzVideoPlayerStandard.thumbImageView);
                }
            }
        }
        //点赞数
        if (!TextUtils.isEmpty(data.getLikeCount())) {
            info_likeCount.setText(data.getLikeCount());
        } else {
            info_likeCount.setText("0");
        }
        //查看数
        if (!TextUtils.isEmpty(data.getViewCount() + "")) {
            info_viewCount.setText("已阅: " + data.getViewCount());
        } else {
            info_viewCount.setText("已阅: 0");
        }


    }


    @Override
    protected void initListener() {

    }


    @Override
    public boolean onBackPressed() {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            PlayerManager.getInstance().onBackPressed();
            return true;
        }
        if (JZVideoPlayer.backPress()) {
            return true;
        }
        return super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}
