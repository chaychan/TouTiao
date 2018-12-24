package com.chaychan.news.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.chaychan.news.R;
import com.chaychan.news.listener.VideoStateListener;
import com.socks.library.KLog;

import cn.jzvd.JzvdStd;


/**
 * @author JayChan
 * @desc 自定义的JZVideoPlayerStandard
 * @date 2018/5/2  10:43
 */
public class MyJZVideoPlayerStandard extends JzvdStd {

    public MyJZVideoPlayerStandard(Context context) {
        super(context);
    }

    public MyJZVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case cn.jzvd.R.id.thumb:
            case cn.jzvd.R.id.start:
                KLog.e("state: " + currentState);
                if (currentState == CURRENT_STATE_IDLE || currentState == CURRENT_STATE_NORMAL){
                    //如果当前是闲置状态，点击后回调点击播放的事件
                    if (mListener != null){
                        mListener.onStartClick();
                        return;
                    }
                }
                break;
            case cn.jzvd.R.id.fullscreen:
                if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
                    //click quit fullscreen
                } else {
                    //click goto fullscreen
                }
                break;
        }
        super.onClick(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_std;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mListener != null){
            mListener.onTouch();
        }
        return super.onTouch(v, event);
    }

    @Override
    public void startVideo() {
        super.startVideo();
        KLog.i(TAG,"startVideo...");
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
        if (mListener != null) {
            mListener.onStateNormal();
        }
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
        KLog.i(TAG,"onStatePreparing...");
        if (mListener != null){
            mListener.onPreparing();
        }
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
        KLog.i(TAG,"onStatePlaying...");
        if (mListener != null){
            mListener.onPlaying();
        }
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
        KLog.i(TAG,"onStatePause...");
        if (mListener != null){
            mListener.onPause();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        super.onProgressChanged(seekBar, progress, fromUser);
        if (mListener != null){
            mListener.onProgressChanged(progress);
        }
    }

    @Override
    public void onStateError() {
        super.onStateError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        if (mListener != null){
            mListener.onComplete();
        }
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
        KLog.i(TAG,"onInfo...");
    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
    }

    @Override
    public void startWindowFullscreen() {
        super.startWindowFullscreen();
    }

    @Override
    public void startWindowTiny() {
        super.startWindowTiny();
    }

    @Override
    public void startDismissControlViewTimer() {
        super.startDismissControlViewTimer();
        KLog.i(TAG,"startDismissControlViewTimer...");
        if (mListener != null){
            mListener.onStartDismissControlViewTimer();
        }
    }


    private VideoStateListener mListener;

    public VideoStateListener getListener() {
        return mListener;
    }

    public void setVideoStateListener(VideoStateListener listener) {
        mListener = listener;
    }
}
