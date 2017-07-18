package com.chaychan.news.view;

import com.chaychan.news.model.response.CommentResponse;
import com.chaychan.news.model.entity.NewsDetail;

/**
 * @author ChayChan
 * @description: 新闻详情的回调
 * @date 2017/6/28  15:41
 */

public interface INewsDetailView {

    void onGetNewsDetailSuccess(NewsDetail newsDetail);

    void onGetCommentSuccess(CommentResponse response);

    void onError();
}
