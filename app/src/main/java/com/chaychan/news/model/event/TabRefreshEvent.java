package com.chaychan.news.model.event;


import com.chaychan.library.BottomBarItem;

/**
 * @author ChayChan
 * @description: 下拉刷新的事件
 * @date 2017/6/22  11:17
 */

public class TabRefreshEvent {
    /**
     * 频道
     */
    private String channelCode;
    private BottomBarItem bottomBarItem;
    private boolean isHomeTab;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public BottomBarItem getBottomBarItem() {
        return bottomBarItem;
    }

    public void setBottomBarItem(BottomBarItem bottomBarItem) {
        this.bottomBarItem = bottomBarItem;
    }

    public boolean isHomeTab() {
        return isHomeTab;
    }

    public void setHomeTab(boolean homeTab) {
        isHomeTab = homeTab;
    }
}
