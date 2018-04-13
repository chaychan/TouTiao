package com.chaychan.news.ui.adapter.provider.news;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.news.R;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.ui.adapter.NewsListAdapter;
import com.chaychan.news.utils.GlideUtils;
import com.chaychan.news.utils.TimeUtils;

/**
 * @author ChayChan
 * @description: 右侧小图布局(1.小图新闻；2.视频类型，右下角显示视频时长)
 * @date 2018/3/22  14:36
 */
public class RightPicNewsItemProvider extends BaseNewsItemProvider {


    public RightPicNewsItemProvider(String channelCode) {
        super(channelCode);
    }

    @Override
    public int viewType() {
        return NewsListAdapter.RIGHT_PIC_VIDEO_NEWS;
    }

    @Override
    public int layout() {
        return R.layout.item_pic_video_news;
    }


    @Override
    protected void setData(BaseViewHolder helper, News news) {
        //右侧小图布局，判断是否有视频
        if (news.has_video) {
            helper.setVisible(R.id.ll_duration, true);//显示时长
            helper.setText(R.id.tv_duration, TimeUtils.secToTime(news.video_duration));//设置时长
        } else {
            helper.setVisible(R.id.ll_duration, false);//隐藏时长
        }
        GlideUtils.load(mContext, news.middle_image.url, helper.getView(R.id.iv_img));//右侧图片或视频的图片使用middle_image
    }

}
