package com.chaychan.news.ui.fragment;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.chaychan.news.R;
import com.chaychan.news.ui.base.BaseFragment;
import com.chaychan.news.ui.base.BasePresenter;
import com.chaychan.news.utils.UIUtils;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.sunfusheng.glideimageview.GlideImageLoader;
import com.sunfusheng.glideimageview.progress.CircleProgressView;
import com.sunfusheng.glideimageview.progress.OnGlideImageViewListener;
import com.sunfusheng.glideimageview.util.DisplayUtil;

import butterknife.Bind;

/**
 * @author ChayChan
 * @description: 展示大图的fragment
 * @date 2017/8/23  10:42
 */

public class BigImageFragment extends BaseFragment {

    public static final String IMG_URL = "imgUrl";

    @Bind(R.id.pv_pic)
    PhotoView mIvPic;

    @Bind(R.id.progressView)
    CircleProgressView mCircleProgressView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_big_image;
    }

    @Override
    public void initListener() {
        mIvPic.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                mActivity.finish();
            }
        });
    }

    @Override
    protected void loadData() {
        String imgUrl = getArguments().getString(IMG_URL);

        GlideImageLoader imageLoader = new GlideImageLoader(mIvPic);

        imageLoader.setOnGlideImageViewListener(imgUrl, new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    UIUtils.showToast(getString(R.string.net_error));
                }
                mCircleProgressView.setProgress(percent);
                mCircleProgressView.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });

        RequestOptions options = imageLoader.requestOptions(R.color.placeholder_color)
                .centerCrop()
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);;

        RequestBuilder<Drawable> requestBuilder = imageLoader.requestBuilder(imgUrl, options);
        requestBuilder.transition(DrawableTransitionOptions.withCrossFade())
                .into(new SimpleTarget<Drawable>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (resource.getIntrinsicHeight() > DisplayUtil.getScreenHeight(mActivity)) {
                            mIvPic.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }
                        requestBuilder.into(mIvPic);
                    }
                });
    }
}


