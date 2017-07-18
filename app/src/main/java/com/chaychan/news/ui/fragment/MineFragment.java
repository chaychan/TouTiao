package com.chaychan.news.ui.fragment;

import android.view.View;

import com.chaychan.news.R;
import com.chaychan.news.ui.base.BaseFragment;
import com.chaychan.news.ui.base.BasePresenter;
import com.socks.library.KLog;

/**
 * @author ChayChan
 * @description: 我的fragment
 * @date 2017/6/12  21:47
 */

public class MineFragment extends BaseFragment{

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return  R.layout.fragment_mine;
    }

    @Override
    public void initView(View rootView) {
        KLog.i("initView");
    }

    @Override
    public void initData() {
        KLog.i("initData");
    }

    @Override
    public void initListener() {
        KLog.i("initListener");
    }

    @Override
    public void loadData() {
        KLog.i("loadData");
    }
}
