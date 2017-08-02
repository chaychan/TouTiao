package com.chaychan.news.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.chaychan.news.R;
import com.chaychan.news.constants.Constant;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.utils.GlideUtils;
import com.chaychan.news.utils.ListUtils;
import com.chaychan.news.utils.TimeUtils;
import com.chaychan.news.utils.UIUtils;
import com.chaychan.news.utils.VideoPathDecoder;
import com.socks.library.KLog;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import fm.jiecao.jcvideoplayer_lib.OnVideoClickListener;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author ChayChan
 * @description: TODO
 * @date 2017/7/6  17:10
 */

public class NewsAdapter extends BaseQuickAdapter<News, BaseViewHolder> {

    /**
     * 纯文字布局(文章、广告)
     */
    private static final int TEXT_NEWS = 100;
    /**
     * 居中大图布局(1.单图文章；2.单图广告；3.视频，中间显示播放图标，右侧显示时长)
     */
    private static final int CENTER_SINGLE_PIC_NEWS = 200;
    /**
     * 右侧小图布局(1.小图新闻；2.视频类型，右下角显示视频时长)
     */
    private static final int RIGHT_PIC_VIDEO_NEWS = 300;
    /**
     * 三张图片布局(文章、广告)
     */
    private static final int THREE_PICS_NEWS = 400;

    /**
     * 视频列表类型
     */
    private static final int VIDEO_LIST_NEWS = 500;


    private Context mContext;
    private String mChannelCode;
    private boolean isVideoList;


    /**
     * @param context     上下文
     * @param channelCode 频道
     * @param isVideoList 是否是视频列表
     * @param data        新闻集合
     */
    public NewsAdapter(Context context, String channelCode, boolean isVideoList, List<News> data) {
        super(data);
        mContext = context;
        mChannelCode = channelCode;
        this.isVideoList = isVideoList;

        //Step.1
        setMultiTypeDelegate(new MultiTypeDelegate<News>() {
            @Override
            protected int getItemType(News news) {
                if (isVideoList) {
                    return VIDEO_LIST_NEWS;
                }

                if (news.has_video) {
                    //如果有视频
                    if (news.video_style ==0) {
                        //右侧视频
                        if (TextUtils.isEmpty(news.middle_image.url)){
                            return TEXT_NEWS;
                        }
                        return RIGHT_PIC_VIDEO_NEWS;
                    } else if (news.video_style == 2) {
                        //居中视频
                        return CENTER_SINGLE_PIC_NEWS;
                    }
                } else {
                    //非视频新闻
                    if (!news.has_image) {
                        //纯文字新闻
                        return TEXT_NEWS;
                    } else {
                        if (ListUtils.isEmpty(news.image_list)) {
                            //图片列表为空，则是右侧图片
                            return RIGHT_PIC_VIDEO_NEWS;
                        }

                        if (news.gallary_image_count == 3) {
                            //图片数为3，则为三图
                            return THREE_PICS_NEWS;
                        }

                        //中间大图，右下角显示图数
                        return CENTER_SINGLE_PIC_NEWS;
                    }
                }

                return TEXT_NEWS;
            }
        });
        //Step .2
        getMultiTypeDelegate()
                .registerItemType(TEXT_NEWS, R.layout.item_text_news)//纯文字布局
                .registerItemType(CENTER_SINGLE_PIC_NEWS, R.layout.item_center_pic_news)//居中大图布局
                .registerItemType(RIGHT_PIC_VIDEO_NEWS, R.layout.item_pic_video_news)//右侧小图布局
                .registerItemType(THREE_PICS_NEWS, R.layout.item_three_pics_news)//三张图片布局
                .registerItemType(VIDEO_LIST_NEWS, R.layout.item_video_list);//三张图片布局
    }

    @Override
    protected void convert(BaseViewHolder helper, News news) {
        if (TextUtils.isEmpty(news.title)){
            //如果没有标题，则直接跳过
            return;
        }

        //设置标题、底部作者、评论数、发表时间
        if (!isVideoList) {
            //如果不是视频列表
            helper.setText(R.id.tv_title, news.title)
                    .setText(R.id.tv_author, news.source)
                    .setText(R.id.tv_comment_num, news.comment_count + UIUtils.getString(R.string.comment))
                    .setText(R.id.tv_time, TimeUtils.getShortTime(news.behot_time * 1000));
        }


        //根据类型为不同布局的条目设置数据
        switch (helper.getItemViewType()) {
            case CENTER_SINGLE_PIC_NEWS:
                //中间大图布局，判断是否有视频
                TextView tvBottomRight = helper.getView(R.id.tv_bottom_right);
                if (news.has_video) {
                    helper.setVisible(R.id.iv_play, true);//显示播放按钮
                    tvBottomRight.setCompoundDrawables(null, null, null, null);//去除TextView左侧图标
                    helper.setText(R.id.tv_bottom_right, TimeUtils.secToTime(news.video_duration));//设置时长
                    GlideUtils.load(mContext, news.video_detail_info.detail_video_large_image.url, helper.getView(R.id.iv_img));//中间图片使用视频大图
                } else {
                    helper.setVisible(R.id.iv_play, false);//隐藏播放按钮
                    tvBottomRight.setCompoundDrawables(mContext.getResources().getDrawable(R.mipmap.icon_picture_group), null, null, null);//TextView增加左侧图标
                    helper.setText(R.id.tv_bottom_right, news.gallary_image_count + UIUtils.getString(R.string.img_unit));//设置时长
                    GlideUtils.load(mContext, news.image_list.get(0).url.replace("list/300x196", "large"), helper.getView(R.id.iv_img));//中间图片使用image_list第一张
                }
                break;
            case RIGHT_PIC_VIDEO_NEWS:
                //右侧小图布局，判断是否有视频
                if (news.has_video) {
                    helper.setVisible(R.id.ll_duration, true);//显示时长
                    helper.setText(R.id.tv_duration, TimeUtils.secToTime(news.video_duration));//设置时长
                } else {
                    helper.setVisible(R.id.ll_duration, false);//隐藏时长
                }
                GlideUtils.load(mContext, news.middle_image.url, helper.getView(R.id.iv_img));//右侧图片或视频的图片使用middle_image
                break;
            case THREE_PICS_NEWS:
                //三张图片的新闻
                GlideUtils.load(mContext, news.image_list.get(0).url, helper.getView(R.id.iv_img1));
                GlideUtils.load(mContext, news.image_list.get(1).url, helper.getView(R.id.iv_img2));
                GlideUtils.load(mContext, news.image_list.get(2).url, helper.getView(R.id.iv_img3));
                break;

            case VIDEO_LIST_NEWS:
                dealVideo(helper, news);
                return;//视频列表布局没有下面的设置标签的操作，直接return
        }

        //根据情况显示置顶、广告和热点的标签
        int position = helper.getAdapterPosition();
        String[] channelCodes = UIUtils.getStringArr(R.array.channel_code);
        boolean isTop = position == 0 && mChannelCode.equals(channelCodes[0]); //属于置顶
        boolean isHot = news.hot == 1;//属于热点新闻
        boolean isAD = !TextUtils.isEmpty(news.tag) ? news.tag.equals(Constant.ARTICLE_GENRE_AD) : false;//属于广告新闻
        boolean isMovie = !TextUtils.isEmpty(news.tag) ? news.tag.equals(Constant.TAG_MOVIE) : false;//如果是影视
        helper.setVisible(R.id.tv_tag, isTop || isHot || isAD);//如果是上面任意一个，显示标签
        helper.setVisible(R.id.tv_comment_num, !isAD);//如果是广告，则隐藏评论数

        String tag = "";
        if (isTop) {
            tag = UIUtils.getString(R.string.to_top);
            helper.setTextColor(R.id.tv_tag, UIUtils.getColor(R.color.news_tag_border_red));
        } else if (isHot) {
            tag = UIUtils.getString(R.string.hot);
            helper.setTextColor(R.id.tv_tag, UIUtils.getColor(R.color.news_tag_border_red));
        } else if (isAD) {
            tag = UIUtils.getString(R.string.ad);
            helper.setTextColor(R.id.tv_tag, UIUtils.getColor(R.color.news_tag_border_blue));
        } else if (isMovie) {
            //如果是影视
            tag = UIUtils.getString(R.string.tag_movie);
            helper.setTextColor(R.id.tv_tag, UIUtils.getColor(R.color.news_tag_border_red));
        }
        helper.setText(R.id.tv_tag, tag);
    }

    private void dealVideo(final BaseViewHolder helper, final News news) {
        helper.setVisible(R.id.ll_title,true);//显示标题栏
        helper.setText(R.id.tv_title, news.title);//设置标题

        String format = UIUtils.getString(R.string.video_play_count);
        int watchCount = news.video_detail_info.video_watch_count;
        String countUnit = "";
        if (watchCount> 10000){
            watchCount = watchCount / 10000;
            countUnit = "万";
        }

        helper.setText(R.id.tv_watch_count, String.format(format, watchCount + countUnit));//播放次数
        GlideUtils.loadRound(mContext, news.user_info.avatar_url, helper.getView(R.id.iv_avatar));//作者头像

        helper.setVisible(R.id.ll_duration, true)//显示时长
                .setText(R.id.tv_duration, TimeUtils.secToTime(news.video_duration));//设置时长

        helper.setText(R.id.tv_author, news.user_info.name)//昵称
                .setText(R.id.tv_comment_count, String.valueOf(news.comment_count));//评论数


        JCVideoPlayerStandard videoPlayer = helper.getView(R.id.video_player);
        GlideUtils.load(mContext, news.video_detail_info.detail_video_large_image.url, videoPlayer.thumbImageView, R.color.divider);//设置缩略图

        videoPlayer.setAllControlsVisible(GONE, GONE, VISIBLE, GONE, VISIBLE, VISIBLE, GONE);
        videoPlayer.tinyBackImageView.setVisibility(GONE);
        videoPlayer.setPosition(helper.getAdapterPosition());//绑定Position

        videoPlayer.titleTextView.setText("");//清除标题,防止复用的时候出现

        videoPlayer.setOnVideoClickListener(new OnVideoClickListener() {
            @Override
            public void onVideoClickToStart() {
                //点击播放
                helper.setVisible(R.id.ll_duration, false);//隐藏时长
                helper.setVisible(R.id.ll_title,false);//隐藏标题栏
//
                VideoPathDecoder decoder = new VideoPathDecoder() {
                    @Override
                    public void onSuccess(String url) {
                        KLog.i("Video url:" + url);
                        videoPlayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST,news.title);
                        videoPlayer.seekToInAdvance = news.video_detail_info.progress;
                        videoPlayer.startVideo();
                    }

                    @Override
                    public void onDecodeError() {
                    }
                };
                decoder.decodePath(news.url);
            }
        });
    }
}
