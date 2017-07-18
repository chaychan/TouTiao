package com.chaychan.news.ui.view.htmlview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class UrlImageGetter implements Html.ImageGetter {
    //    private Map<String, WeakReference<Drawable>> mCache = Collections.synchronizedMap(new WeakHashMap<String, WeakReference<Drawable>>());
    private TextView mTextView;

    public UrlImageGetter(TextView textView) {
        mTextView = textView;
    }

    @Override
    public Drawable getDrawable(final String source) {


        final HtmlTextView.URLDrawable urlDrawable = new HtmlTextView.URLDrawable();

        Glide.with(mTextView.getContext()) // could be an issue!
                .load(source)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(){
                    @Override
                    public void onResourceReady(Bitmap loadedImage, GlideAnimation glideAnimation) {
                        int tvWidth = mTextView.getMeasuredWidth();
                        int width = tvWidth;
                        int height = tvWidth * loadedImage.getHeight() / loadedImage.getWidth();
                        BitmapDrawable drawable = new BitmapDrawable(loadedImage);
                        drawable.setBounds(0, 0, width, height);
                        urlDrawable.setBounds(0, 0, width, height);
                        urlDrawable.setDrawable(drawable);
                        mTextView.setText(mTextView.getText());
                    }
                });


        return urlDrawable;
    }

    public int[] getImageWidthHeight(InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options); // 此时返回的bitmap为null
        return new int[]{options.outWidth, options.outHeight};
    }

}
