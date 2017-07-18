package com.chaychan.uikit;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public class HeaderZoomLayout extends ScrollView {
    private View mHeaderView;
    private int mHeaderWidth;
    private int mHeaderHeight;
    /**
     * 是否正在下拉
     */
    private boolean mIsPulling;
    private int mLastY;
    //滑动放大系数，系数越大，滑动时放大程度越大
    private float mScaleRatio = 0.4f;
    //    最大的放大倍数
    private float mScaleTimes = 2f;
    //    回弹时间系数，系数越小，回弹越快
    private float mReplyRatio = 0.5f;

    public HeaderZoomLayout(Context context) {
        this(context, null);
    }

    public HeaderZoomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderZoomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        mHeaderWidth = mHeaderView.getMeasuredWidth();
        mHeaderHeight = mHeaderView.getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //设置不可过度滚动，否则上移后下拉会出现部分空白的情况
        setOverScrollMode(OVER_SCROLL_NEVER);
        View child = getChildAt(0);
        if (child != null && child instanceof ViewGroup) {
            //获取默认第一个子View
            mHeaderView = ((ViewGroup) child).getChildAt(0);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeaderWidth = mHeaderView.getMeasuredWidth();
        mHeaderHeight = mHeaderView.getMeasuredHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mHeaderView == null)
            return super.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!mIsPulling) {
                    //第一次下拉
                    if (getScrollY() == 0) {
                        //在顶部的时候，记录顶部位置
                        mLastY = (int) ev.getY();
                    } else {
                        break;
                    }
                }
                if(ev.getY()-mLastY<0)
                    return super.onTouchEvent(ev);
                int distance = (int) ((ev.getY() - mLastY) * mScaleRatio);
                mIsPulling = true;
                setZoom(distance);
                return true;
            case MotionEvent.ACTION_UP:
                mIsPulling = false;
                replyView();
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 放大view
     */
    private void setZoom(float s) {
        float scaleTimes = (float) ((mHeaderWidth + s) / (mHeaderWidth * 1.0));
//        如超过最大放大倍数，直接返回
        if (scaleTimes > mScaleTimes) return;

        ViewGroup.LayoutParams layoutParams = mHeaderView.getLayoutParams();
        layoutParams.width = (int) (mHeaderWidth + s);
        layoutParams.height = (int) (mHeaderHeight * ((mHeaderWidth + s) / mHeaderWidth));
//        设置控件水平居中
        ((MarginLayoutParams) layoutParams).setMargins(0, 0, -(layoutParams.width - mHeaderWidth) / 2, 0);
        mHeaderView.setLayoutParams(layoutParams);
    }

    /**
     * 回弹
     */
    private void replyView() {
        final float distance = mHeaderView.getMeasuredWidth() - mHeaderWidth;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(distance, 0.0F).setDuration((long) (distance * mReplyRatio));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setZoom((Float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null) onScrollListener.onScroll(l, t, oldl, oldt);
    }

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 滑动监听
     */
    public interface OnScrollListener {
        void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }

}
