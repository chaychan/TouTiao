package com.chaychan.news.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class ItemDragHelperCallBack extends ItemTouchHelper.Callback {
    private OnChannelDragListener onChannelDragListener;

    public ItemDragHelperCallBack(OnChannelDragListener onChannelDragListener) {
        this.onChannelDragListener = onChannelDragListener;
    }

    public void setOnChannelDragListener(OnChannelDragListener onChannelDragListener) {
        this.onChannelDragListener = onChannelDragListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        int dragFlags;
        if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
            //监听上下左右拖动
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 不同Type之间不可移动
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        if (onChannelDragListener != null)
            onChannelDragListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        //不需要长按拖动，因为我们的标题和 频道推荐 是不需要拖动的，所以手动控制
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        //不需要侧滑
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
