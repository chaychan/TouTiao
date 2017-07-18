package com.chaychan.news.ui.view.htmlview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.chaychan.news.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.defaultWidth;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class HtmlTextView extends TextView {

    private Drawable mErrorImage;
    private Drawable mLoadingImg;
    private int mDefaultWidth = 10;
    private int mDefaultHeight = 10;
    private OnImageClickListener onImageClickListener;

    public HtmlTextView(Context context) {
        this(context, null);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        targets = new HashSet<>();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HtmlTextView);
        mLoadingImg = typedArray.getDrawable(R.styleable.HtmlTextView_loadingImg);
        mErrorImage = typedArray.getDrawable(R.styleable.HtmlTextView_errorImg);

        mDefaultWidth = typedArray.getDimensionPixelSize(R.styleable.HtmlTextView_defaultWidth, mDefaultWidth);
        mDefaultHeight = typedArray.getDimensionPixelSize(R.styleable.HtmlTextView_defaultHeight, mDefaultHeight);

        if (mLoadingImg == null) {
            mLoadingImg = new ColorDrawable(Color.GRAY);
        }
        mLoadingImg.setBounds(0, 0, defaultWidth, mDefaultHeight);
        if (mErrorImage == null) {
            mErrorImage = new ColorDrawable(Color.GRAY);
        }
        mErrorImage.setBounds(0, 0, defaultWidth, mDefaultHeight);
        typedArray.recycle();
    }


    /**
     * 设置富文本
     *
     * @param text 富文本
     */
    public void setRichText(String text) {
        Spanned spanned = Html.fromHtml(text, asyncImageGetter, null);
        SpannableStringBuilder spannableStringBuilder;
        if (spanned instanceof SpannableStringBuilder) {
            spannableStringBuilder = (SpannableStringBuilder) spanned;
        } else {
            spannableStringBuilder = new SpannableStringBuilder(spanned);
        }

        ImageSpan[] imageSpans = spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), ImageSpan.class);
        final List<String> imageUrls = new ArrayList<>();

        for (int i = 0, size = imageSpans.length; i < size; i++) {
            ImageSpan imageSpan = imageSpans[i];
            String imageUrl = imageSpan.getSource();
            int start = spannableStringBuilder.getSpanStart(imageSpan);
            int end = spannableStringBuilder.getSpanEnd(imageSpan);
            imageUrls.add(imageUrl);

            final int finalI = i;
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (onImageClickListener != null) {
                        onImageClickListener.imageClicked(imageUrls, finalI);
                    }
                }
            };
            ClickableSpan[] clickableSpans = spannableStringBuilder.getSpans(start, end, ClickableSpan.class);
            if (clickableSpans != null && clickableSpans.length != 0) {
                for (ClickableSpan cs : clickableSpans) {
                    spannableStringBuilder.removeSpan(cs);
                }
            }
            spannableStringBuilder.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        super.setText(spanned);
        setMovementMethod(LinkMovementMethod.getInstance());
    }


    /**
     * 异步加载图片（依赖于Picasso）
     */
    private Html.ImageGetter asyncImageGetter = new UrlImageGetter(this);

    public static final class URLDrawable extends BitmapDrawable {
        private Drawable drawable;

        @SuppressWarnings("deprecation")
        public URLDrawable() {
        }

        @Override
        public void draw(Canvas canvas) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }


        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public interface OnImageClickListener {
        /**
         * 图片被点击后的回调方法
         *
         * @param imageUrls 本篇富文本内容里的全部图片
         * @param position  点击处图片在imageUrls中的位置
         */
        void imageClicked(List<String> imageUrls, int position);
    }
}
