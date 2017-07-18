package com.chaychan.news.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.chaychan.news.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChayChan
 * @description: 首页页签的adapter
 * @date 2017/6/12  21:36
 */

public class MainTabAdapter extends FragmentStatePagerAdapter{

    private List<BaseFragment> mFragments = new ArrayList<BaseFragment>();

    public MainTabAdapter(List<BaseFragment>fragmentList,FragmentManager fm) {
        super(fm);
        if (fragmentList != null){
            mFragments = fragmentList;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
