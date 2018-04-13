package com.chaychan.news.ui.adapter.provider.news;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.news.R;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.ui.adapter.NewsListAdapter;

/**
 * @author ChayChan
 * @description: 纯文本新闻
 * @date 2018/3/22  14:36
 */
public class TextNewsItemProvider extends BaseNewsItemProvider {

    public TextNewsItemProvider(String channelCode) {
        super(channelCode);
    }

    @Override
    public int viewType() {
        return NewsListAdapter.TEXT_NEWS;
    }

    @Override
    public int layout() {
        return R.layout.item_text_news;
    }

    @Override
    protected void setData(BaseViewHolder helper, News news) {
         //由于文本消息的逻辑目前已经在基类中封装，所以此处无须写
        //定义此类是提供文本消息的ItemProvider
    }
}
