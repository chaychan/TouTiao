package com.chaychan.news.ui.adapter.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.adapter.ItemProviderTag;
import com.chaychan.news.R;
import com.chaychan.news.model.entity.News;
import com.chaychan.news.ui.adapter.NewsListAdapter;

/**
 * @author ChayChan
 * @description: 纯文本新闻
 * @date 2018/3/22  14:36
 */

@ItemProviderTag(
        viewType = NewsListAdapter.TEXT_NEWS,
        layout = R.layout.item_text_news
)
public class TextNewsItemProvider extends BaseNewsItemProvider {


    public TextNewsItemProvider(String channelCode) {
        super(channelCode);
    }

    @Override
    protected void setData(BaseViewHolder helper, News news) {
         //由于文本消息的逻辑目前已经在基类中封装，所以此处无须写
        //定义此类是提供文本消息的ItemProvider
    }

    @Override
    public void onClick(BaseViewHolder baseViewHolder, News news, int i) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder baseViewHolder, News news, int i) {
        return false;
    }
}
