package com.icelevin.www.show.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hb.utils.view.fragment.BaseFragment;
import com.icelevin.www.show.R;
import com.icelevin.www.show.manger.CustomMediaController;
import com.icelevin.www.show.model.CCTVNewsModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.ittiger.player.PlayerManager;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * 新闻详情页
 * Created by ice on 2017/10/11.
 */

public class CCTVNewsInfoFragment extends BaseFragment implements AdapterView.OnItemClickListener, MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener {
    private TextView info_title, info_date, info_author, info_content, info_viewCount, info_likeCount;

    private CCTVNewsModel.Data data;
    private VideoView videoView;
    private CustomMediaController customMediaController;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;
    private JZVideoPlayerStandard jzVideoPlayerStandard;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Vitamio.isInitialized(getContext());

    }

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
//        info_video = (VideoPlayerView) getView().findViewById(R.id.info_video);
        info_viewCount = (TextView) getView().findViewById(R.id.info_viewCount);
        info_likeCount = (TextView) getView().findViewById(R.id.info_likeCount);
        videoView = getView().findViewById(R.id.info_video);
        customMediaController = new CustomMediaController(getContext(), videoView, getActivity());
        pb = (ProgressBar) getView().findViewById(R.id.probar);
        downloadRateView = (TextView) getView().findViewById(R.id.download_rate);
        loadRateView = (TextView) getView().findViewById(R.id.load_rate);

        jzVideoPlayerStandard = getView().findViewById(R.id.videoplayer);


    }

    @Override
    protected void initData() {
        data = (CCTVNewsModel.Data) getActivity().getIntent().getSerializableExtra(CCTVNewsModel.Data.class.getName());
        initNews(data);
        customMediaController.show(5000);
        customMediaController.setAnchorView(videoView);
        videoView.setMediaController(customMediaController);
        videoView.setVideoQuality(MediaController.DRAWING_CACHE_QUALITY_LOW);
        videoView.requestFocus();
        videoView.setOnInfoListener(this);
        videoView.setOnBufferingUpdateListener(this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
            }
        });


    }


    private void initNews(CCTVNewsModel.Data data) {
        //编辑名字
        if (!TextUtils.isEmpty(data.getPosterScreenName())) {
            info_author.setText(data.getPosterScreenName());
        }
        //标题
        if (!TextUtils.isEmpty(data.getTitle())) {
            info_title.setText(data.getTitle());
            customMediaController.setVideoName(data.getTitle());
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
            videoView.setVisibility(View.GONE);
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
    public void onResume() {
        super.onResume();
        PlayerManager.getInstance().resume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PlayerManager.getInstance().stop();
    }

    @Override
    public void onPause() {
        super.onPause();
        PlayerManager.getInstance().pause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PlayerManager.getInstance().release();
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


    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (videoView.isPlaying()) {
                    videoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);
                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                videoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (videoView != null) {
            videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }
}
