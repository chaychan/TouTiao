package com.chaychan.news.ui.activity;

import android.content.Intent;

import com.chaychan.news.R;
import com.chaychan.news.ui.base.BaseActivity;
import com.chaychan.news.ui.base.BasePresenter;
import com.chaychan.news.utils.UIUtils;

import flyn.Eyes;

/**
 * @author ChayChan
 * @description: 闪屏页
 * @date 2017/7/16  17:17
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public boolean enableSlideClose() {
        return false;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        Eyes.translucentStatusBar(this,false);//
        UIUtils.postTaskDelay(new Runnable() {
            @Override
            public void run() {
                 startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
