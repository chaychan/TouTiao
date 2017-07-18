package com.chaychan.uikit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 用于作为标签显示的TextView
 * 边框默认与文字颜色一致
 * Created by chuxin on 2015/9/11.
 */
public class BorderTextView extends android.support.v7.widget.AppCompatTextView {

    public static final float DEFAULT_STROKE_WIDTH = 1.0f;    // 默认边框宽度, 1dp
    public static final float DEFAULT_CORNER_RADIUS = 2.0f;   // 默认圆角半径, 2dp
    public static final float DEFAULT_LR_PADDING = 3f;      // 默认左右内边距
    public static final float DEFAULT_TB_PADDING = 1f;      // 默认上下内边距

    private int strokeWidth;    // 边框线宽
    private int strokeColor;    // 边框颜色
    private int cornerRadius;   // 圆角半径
    private boolean mFollowTextColor; // 边框颜色是否跟随文字颜色

    private Paint mPaint = new Paint();     // 画边框所使用画笔对象
    private RectF mRectF;                   // 画边框要使用的矩形

    public BorderTextView(Context context) {
        this(context, null);
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 将DIP单位默认值转为PX
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        strokeWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_STROKE_WIDTH, displayMetrics);
        cornerRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_CORNER_RADIUS, displayMetrics);

        // 读取属性值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BorderTextView);
        strokeWidth = ta.getDimensionPixelSize(R.styleable.BorderTextView_strokeWidth, strokeWidth);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.BorderTextView_cornerRadius, cornerRadius);
        strokeColor = ta.getColor(R.styleable.BorderTextView_strokeColor, Color.TRANSPARENT);
        mFollowTextColor = ta.getBoolean(R.styleable.BorderTextView_followTextColor, true);
        ta.recycle();

        mRectF = new RectF();

        // 边框默认颜色与文字颜色一致
//        if (strokeColor == Color.TRANSPARENT)
//            strokeColor = getCurrentTextColor();

        // 如果使用时没有设置内边距, 设置默认边距
        int paddingLeft = getPaddingLeft() == 0 ? (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, DEFAULT_LR_PADDING, displayMetrics) : getPaddingLeft();
        int paddingRight = getPaddingRight() == 0 ? (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, DEFAULT_LR_PADDING,
                displayMetrics) : getPaddingRight();
        int paddingTop = getPaddingTop() == 0 ? (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TB_PADDING, displayMetrics) : getPaddingTop();
        int paddingBottom = getPaddingBottom() == 0 ? (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TB_PADDING,
                displayMetrics) : getPaddingBottom();
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);     // 空心效果
        mPaint.setAntiAlias(true);               // 设置画笔为无锯齿
        mPaint.setStrokeWidth(strokeWidth);      // 线宽

        // 设置边框线的颜色, 如果声明为边框跟随文字颜色且当前边框颜色与文字颜色不同时重新设置边框颜色
        if (mFollowTextColor && strokeColor != getCurrentTextColor())
            strokeColor = getCurrentTextColor();
        mPaint.setColor(strokeColor);

        // 画空心圆角矩形
        mRectF.left = mRectF.top = 0.5f * strokeWidth;
        mRectF.right = getMeasuredWidth() - strokeWidth;
        mRectF.bottom = getMeasuredHeight() - strokeWidth;
        canvas.drawRoundRect(mRectF, cornerRadius, cornerRadius, mPaint);
    }
}