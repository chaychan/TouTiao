package com.chaychan.news.listener;

public interface VideoStateListener {

    void onStateNormal();

    void onPreparing();

    void onStartClick();

    void onStart();

    void onPlaying();

    void onPause();

    void onProgressChanged(int progress);

    void onComplete();

    void onTouch();

    void onStartDismissControlViewTimer();
}