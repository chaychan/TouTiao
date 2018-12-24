package com.chaychan.news.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.news.R;
import com.chaychan.news.listener.VideoStateListenerAdapter;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.utils.GlideUtils;
import com.chaychan.news.utils.MyJZVideoPlayerStandard;
import com.chaychan.news.utils.TimeUtils;
import com.chaychan.news.utils.UIUtils;
import com.chaychan.news.utils.VideoPathDecoder;
import com.socks.library.KLog;

import java.util.List;

import cn.jzvd.JzvdStd;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author ChayChan
 * @description: 视频列表的Adapter
 * @date 2018/3/22  17:13
 */

public class VideoListAdapter extends BaseQuickAdapter<News, BaseViewHolder> {

    public VideoListAdapter(@Nullable List<News> data) {
        super(R.layout.item_video_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News news) {
        if (TextUtils.isEmpty(news.title)) {
            //如果没有标题，则直接跳过
            return;
        }

        helper.setVisible(R.id.ll_title, true);//显示标题栏
        helper.setText(R.id.tv_title, news.title);//设置标题

        MyJZVideoPlayerStandard videoPlayer = helper.getView(R.id.video_player);
        if (news.video_detail_info != null){
            String format = UIUtils.getString(R.string.video_play_count);
            int watchCount = news.video_detail_info.video_watch_count;
            String countUnit = "";
            if (watchCount > 10000) {
                watchCount = watchCount / 10000;
                countUnit = "万";
            }
            helper.setText(R.id.tv_watch_count, String.format(format, watchCount + countUnit));//播放次数
            GlideUtils.load(mContext, news.video_detail_info.detail_video_large_image.url, videoPlayer.thumbImageView, R.color.color_d8d8d8);//设置缩略图
        }

        GlideUtils.loadRound(mContext, news.user_info.avatar_url, helper.getView(R.id.iv_avatar));//作者头像

        helper.setVisible(R.id.ll_duration, true)//显示时长
                .setText(R.id.tv_duration, TimeUtils.secToTime(news.video_duration));//设置时长

        helper.setText(R.id.tv_author, news.user_info.name)//昵称
                .setText(R.id.tv_comment_count, String.valueOf(news.comment_count));//评论数

        videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE);
        videoPlayer.tinyBackImageView.setVisibility(GONE);

        videoPlayer.titleTextView.setText("");//清除标题,防止复用的时候出现

        videoPlayer.setVideoStateListener(new VideoStateListenerAdapter() {

            boolean isVideoParsing = false; //视频是否在解析的标识

            @Override
            public void onStartClick() {
                KLog.e("onStartClick");
                String videoUrl = "";
                if (news.video_detail_info != null) {
                    //取出解析后的网址
                    videoUrl = news.video_detail_info.parse_video_url;
                }

                if (!TextUtils.isEmpty(videoUrl)){
                    //如果已经解析过
                    KLog.e("取出对应的视频地址: " + videoUrl);
                    videoPlayer.setUp(videoUrl, news.title, JzvdStd.SCREEN_WINDOW_LIST);
                    videoPlayer.startVideo();
                    return;
                }

                //解析视频
                parseVideo();
            }

            private void parseVideo() {
                KLog.e("title: " + news.title);

                if (isVideoParsing) {
                    KLog.e("视频正在解析，不重复调用...");
                    return;
                } else {
                    isVideoParsing = true;
                }

                //隐藏开始按钮 显示加载中
                videoPlayer.setAllControlsVisiblity(GONE, GONE, GONE, VISIBLE, VISIBLE, GONE, GONE);
                helper.setVisible(R.id.ll_duration, false);//隐藏时长
                helper.setVisible(R.id.ll_title, false);//隐藏标题栏

                VideoPathDecoder decoder = new VideoPathDecoder() {
                    @Override
                    public void onSuccess(String url) {
                        KLog.i("Video url:" + url);
                        UIUtils.postTaskSafely(new Runnable() {
                            @Override
                            public void run() {
                                //更改视频是否在解析的标识
                                isVideoParsing = false;

                                //准备播放
                                videoPlayer.setUp(url, news.title, JzvdStd.SCREEN_WINDOW_LIST);

                                if (news.video_detail_info != null) {
                                    news.video_detail_info.parse_video_url = url; //赋值解析后的视频地址
                                    videoPlayer.seekToInAdvance = news.video_detail_info.progress; //设置播放进度
                                }

                                //开始播放
                                videoPlayer.startVideo();
                            }
                        });
                    }

                    @Override
                    public void onDecodeError(String errorMsg) {
                        isVideoParsing = false;//更改视频是否在解析的标识
                        //隐藏加载中 显示开始按钮
                        videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE);
                        UIUtils.showToast(errorMsg);
                    }
                };
                decoder.decodePath(news.url);
            }

        });
    }
}
