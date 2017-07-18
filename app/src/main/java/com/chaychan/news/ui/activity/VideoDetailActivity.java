package com.chaychan.news.ui.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.ImageView;

import com.chaychan.news.R;
import com.chaychan.news.model.entity.NewsDetail;
import com.chaychan.news.utils.UIUtils;
import com.chaychan.news.utils.VideoPathDecoder;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.OnClick;
import flyn.Eyes;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author ChayChan
 * @description: 视频新闻
 * @date 2017/7/4  16:51
 */

public class VideoDetailActivity extends NewsDetailBaseActivity {

    @Bind(R.id.video_player)
    JCVideoPlayerStandard mVideoPlayer;
    @Bind(R.id.iv_back)
    ImageView ivBack;

    private SensorManager mSensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener mSensorEventListener;
    private int mProgress;
    private int mPosition;
    private String mChannelCode;

    @Override
    public void initView() {
        super.initView();
        Eyes.setStatusBarColor(this, UIUtils.getColor(android.R.color.black));
    }

    @Override
    public void initData() {
        super.initData();
        mProgress = getIntent().getIntExtra(PROGRESS, 0);
    }

    @Override
    public void initListener() {
        super.initListener();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();

        mVideoPlayer.setAllControlsVisible(GONE, GONE, VISIBLE, GONE, VISIBLE, VISIBLE, GONE);
        mVideoPlayer.titleTextView.setVisibility(GONE);
    }

    @Override
    protected int getViewContentViewId() {
        return R.layout.activity_video_detail;
    }

    @Override
    public void onGetNewsDetailSuccess(NewsDetail newsDetail) {
        newsDetail.content = "";
        mHeaderView.setDetail(newsDetail);

        VideoPathDecoder decoder = new VideoPathDecoder() {
            @Override
            public void onSuccess(String url) {
                KLog.e("onGetNewsDetailSuccess", url);
                mVideoPlayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, newsDetail.title);
                mVideoPlayer.seekToInAdvance = mProgress;//设置进度
                mVideoPlayer.startVideo();
            }

            @Override
            public void onDecodeError() {

            }
        };
        decoder.decodePath(newsDetail.url);
        KLog.e("onGetNewsDetailSuccess", newsDetail.url);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        postVideoEvent(true);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        postVideoEvent(true);
    }
}
