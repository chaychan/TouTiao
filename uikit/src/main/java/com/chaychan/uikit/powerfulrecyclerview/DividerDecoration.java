package com.chaychan.uikit.powerfulrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class DividerDecoration extends RecyclerView.ItemDecoration {

    private int mOrientation;
    private int mDividerSize;
    private Paint mPaint;
    private Drawable mDividerDrawable;
    private int mMarginLeft;
    private int mMarginRight;

    /**
     * 颜色分割线
     *
     * @param context     上下文
     * @param orientation 方向
     * @param color       分割线颜色
     * @param dividerSize 分割线大小
     */
    public DividerDecoration(Context context, int orientation,int color, int dividerSize, int marginLeft, int marginRight) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("Orientation is invalidate");
        }
        if (dividerSize <= 0) {
            throw new IllegalArgumentException("DividerSize must be greated than 0");
        }

        mOrientation = orientation;
        mDividerSize = dividerSize;
        mMarginLeft = marginLeft;
        mMarginRight = marginRight;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置为抗锯齿
        mPaint.setColor(color);//设置画笔颜色
        mPaint.setStyle(Paint.Style.FILL);//设置为填充
    }

    /**
     * 图片分割线
     *
     * @param context         上下文
     * @param orientation     方向
     * @param dividerDrawable 分割线图片
     * @param dividerSize     分割线大小
     */
    public DividerDecoration(Context context, int orientation,Drawable dividerDrawable, int dividerSize, int marginLeft, int marginRight) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("Orientation is invalidate");
        }

        mOrientation = orientation;
        mDividerDrawable = dividerDrawable;
        mMarginLeft = marginLeft;
        mMarginRight = marginRight;

        if (dividerSize > 0) {
            mDividerSize = dividerSize;
        } else {
            //如果传入的dividerSize不大于0，则使用图片的宽或高
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                //如果是垂直方向，使用图片的高度
                mDividerSize = mDividerDrawable.getIntrinsicHeight();
            } else {
                mDividerSize = mDividerDrawable.getIntrinsicWidth();
            }
        }
        Log.i("","mDividerSize: " + mDividerSize);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 绘制垂直分割线
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int left = parent.getPaddingLeft() + mMarginLeft;
        int right = parent.getMeasuredWidth() - parent.getPaddingRight() - mMarginRight;
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDividerSize;

            if (mDividerDrawable != null) {
                //如果是图片分割线，绘制图片
                mDividerDrawable.setBounds(left, top, right, bottom);
                mDividerDrawable.draw(canvas);
            } else {
                //绘制矩形
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    /**
     * 绘制横向水平分割线
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + mDividerSize;

            if (mDividerDrawable != null) {
                //如果是图片分割线，绘制图片
                mDividerDrawable.setBounds(left, top, right, bottom);
                canvas.drawPaint(mPaint);
                mDividerDrawable.draw(canvas);
            } else {
                //绘制矩形
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    /**
     * 设置item分割线的size
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mDividerSize);
        } else {
            outRect.set(0, 0, mDividerSize, 0);
        }
    }
}