package com.chaychan.news.ui.adapter.provider.news;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.news.R;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.ui.adapter.NewsListAdapter;
import com.chaychan.news.utils.GlideUtils;
import com.chaychan.news.utils.TimeUtils;
import com.chaychan.news.utils.UIUtils;

/**
 * @author ChayChan
 * @description: 居中大图布局(1.单图文章；2.单图广告；3.视频，中间显示播放图标，右侧显示时长)
 * @date 2018/3/22  14:36
 */
public class CenterPicNewsItemProvider extends BaseNewsItemProvider {


    public CenterPicNewsItemProvider(String channelCode) {
        super(channelCode);
    }

    @Override
    public int viewType() {
        return NewsListAdapter.CENTER_SINGLE_PIC_NEWS;
    }

    @Override
    public int layout() {
        return R.layout.item_center_pic_news;
    }

    @Override
    protected void setData(BaseViewHolder helper, News news) {
        //中间大图布局，判断是否有视频
        TextView tvBottomRight = helper.getView(R.id.tv_bottom_right);
        if (news.has_video) {
            helper.setVisible(R.id.iv_play, true);//显示播放按钮
            tvBottomRight.setCompoundDrawables(null, null, null, null);//去除TextView左侧图标
            helper.setText(R.id.tv_bottom_right, TimeUtils.secToTime(news.video_duration));//设置时长
            GlideUtils.load(mContext, news.video_detail_info.detail_video_large_image.url, helper.getView(R.id.iv_img));//中间图片使用视频大图
        } else {
            helper.setVisible(R.id.iv_play, false);//隐藏播放按钮
            if (news.gallary_image_count == 1){
                tvBottomRight.setCompoundDrawables(null, null, null, null);//去除TextView左侧图标
            }else{
                tvBottomRight.setCompoundDrawables(mContext.getResources().getDrawable(R.mipmap.icon_picture_group), null, null, null);//TextView增加左侧图标
                helper.setText(R.id.tv_bottom_right, news.gallary_image_count + UIUtils.getString(R.string.img_unit));//设置图片数
            }
            GlideUtils.load(mContext, news.image_list.get(0).url.replace("list/300x196", "large"), helper.getView(R.id.iv_img));//中间图片使用image_list第一张
        }
    }
}
