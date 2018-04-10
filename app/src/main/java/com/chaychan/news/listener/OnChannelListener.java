package com.chaychan.news.listener;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public interface OnChannelListener {
    void onItemMove(int starPos, int endPos);
    void onMoveToMyChannel(int starPos, int endPos);
    void onMoveToOtherChannel(int starPos, int endPos);
}
