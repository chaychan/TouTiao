package com.chaychan.news.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chaychan.lib.SlidingLayout;
import com.chaychan.news.ui.activity.MainActivity;
import com.github.nukc.stateview.StateView;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;

/**
 * @author ChayChan
 * @description: activity的基类
 * @date 2017/6/10  16:42
 */

public abstract class BaseActivity<T extends BasePresenter>  extends AppCompatActivity {

    protected T mPresenter;
    private static long mPreTime;
    private static Activity mCurrentActivity;// 对所有activity进行管理
    public static List<Activity> mActivities = new LinkedList<Activity>();
    protected  Bundle savedInstanceState;
    protected StateView mStateView;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (enableSlideClose()) {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }

        this.savedInstanceState = savedInstanceState;

        //初始化的时候将其添加到集合中
        synchronized (mActivities) {
            mActivities.add(this);
        }

        mPresenter = createPresenter();

        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }


    public boolean enableSlideClose() {
        return true;
    }

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //销毁的时候从集合中移除
        synchronized (mActivities) {
            mActivities.remove(this);
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 退出应用的方法
     */
    public static void exitApp() {

        ListIterator<Activity> iterator = mActivities.listIterator();

        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * 统一退出控制
     */
    @Override
    public void onBackPressed() {
        if (mCurrentActivity instanceof MainActivity){
            //如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) {// 两次点击间隔大于2秒
//                UIUtils.showToast("再按一次，退出应用");
                mPreTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressed();// finish()
    }

    public boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }

    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }
}
