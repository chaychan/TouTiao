package com.chaychan.news.ui.adapter.provider.news;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.BaseItemProvider;
import com.chaychan.news.R;
import com.chaychan.news.constants.Constant;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.utils.TimeUtils;
import com.chaychan.news.utils.UIUtils;

/**
 * @author ChayChan
 * @description: 将新闻中设置数据公共部分抽取
 * @date 2018/3/22  14:48
 */

public abstract class BaseNewsItemProvider extends BaseItemProvider<News,BaseViewHolder> {

    private String mChannelCode;

    public BaseNewsItemProvider(String channelCode) {
        mChannelCode = channelCode;
    }

    @Override
    public void convert(BaseViewHolder helper, News news, int i) {
        if (TextUtils.isEmpty(news.title)) {
            //如果没有标题，则直接跳过
            return;
        }

        //设置标题、底部作者、评论数、发表时间
        helper.setText(R.id.tv_title, news.title)
                .setText(R.id.tv_author, news.source)
                .setText(R.id.tv_comment_num, news.comment_count + UIUtils.getString(R.string.comment))
                .setText(R.id.tv_time, TimeUtils.getShortTime(news.behot_time * 1000));

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
            helper.setTextColor(R.id.tv_tag, UIUtils.getColor(R.color.color_F96B6B));
        } else if (isHot) {
            tag = UIUtils.getString(R.string.hot);
            helper.setTextColor(R.id.tv_tag, UIUtils.getColor(R.color.color_F96B6B));
        } else if (isAD) {
            tag = UIUtils.getString(R.string.ad);
            helper.setTextColor(R.id.tv_tag, UIUtils.getColor(R.color.color_3091D8));
        } else if (isMovie) {
            //如果是影视
            tag = UIUtils.getString(R.string.tag_movie);
            helper.setTextColor(R.id.tv_tag, UIUtils.getColor(R.color.color_F96B6B));
        }
        helper.setText(R.id.tv_tag, tag);


        setData(helper, news);
    }

    protected abstract void setData(BaseViewHolder helper, News news);
}
