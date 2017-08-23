package com.chaychan.news.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;
import com.chaychan.news.R;
import com.chaychan.news.listener.PermissionListener;
import com.chaychan.news.ui.base.BaseActivity;
import com.chaychan.news.ui.base.BasePresenter;
import com.chaychan.news.ui.fragment.BigImageFragment;
import com.chaychan.news.utils.FileUtils;
import com.chaychan.news.utils.UIUtils;
import com.socks.library.KLog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import flyn.Eyes;

/**
 * @author ChayChan
 * @description: 详情页查看图片的activity
 * @date 2017/8/23  11:02
 */

public class ImageViewPagerActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = ImageViewPagerActivity.class.getSimpleName();
    public static final String IMG_URLS = "mImageUrls";
    public static final String POSITION = "position";

    @Bind(R.id.vp_pics)
    ViewPager mVpPics;

    @Bind(R.id.tv_indicator)
    TextView mTvIndicator;

    @Bind(R.id.tv_save)
    TextView mTvSave;

    private List<String> mImageUrls = new ArrayList<String>();
    private List<BigImageFragment> mFragments = new ArrayList<BigImageFragment>();
    private int mCurrentPosition;
    private Map<Integer,Boolean> mDownloadingFlagMap = new HashMap<>();//用于保存对应位置图片是否在下载的标识

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_image_view_pager;
    }

    @Override
    public void initView() {
        Eyes.translucentStatusBar(this);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mImageUrls = intent.getStringArrayListExtra(IMG_URLS);
        int position = intent.getIntExtra(POSITION, 0);
        mCurrentPosition = position;

        for (int i=0;i<mImageUrls.size();i++) {
            String url = mImageUrls.get(i);
            BigImageFragment imageFragment = new BigImageFragment();

            Bundle bundle = new Bundle();
            bundle.putString(BigImageFragment.IMG_URL, url);
            imageFragment.setArguments(bundle);

            mFragments.add(imageFragment);//添加到fragment集合中
            mDownloadingFlagMap.put(i,false);//初始化map，一开始全部的值都为false
        }

        mVpPics.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mVpPics.addOnPageChangeListener(this);


        mVpPics.setCurrentItem(mCurrentPosition);// 设置当前所在的位置
        setIndicator(mCurrentPosition);//设置当前位置指示
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        ;//页面变化时，设置当前的指示
        setIndicator(mCurrentPosition);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setIndicator(int position) {
        mTvIndicator.setText(position + 1 + "/" + mImageUrls.size());//设置当前的指示
    }


    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        requestRuntimePermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onGranted() {
                //保存图片
                downloadImg();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {
                UIUtils.showToast(getString(R.string.write_storage_permission_deny));
            }
        });
    }

    private void downloadImg() {
        String imgUrl = mImageUrls.get(mCurrentPosition);
        Boolean isDownlading = mDownloadingFlagMap.get(mCurrentPosition);
        if (!isDownlading){
            //如果不是正在下载，则开始下载
            mDownloadingFlagMap.put(mCurrentPosition,true);//更改标识为下载中
            new DownloadImgTask(mCurrentPosition).execute(imgUrl);
        }
    }

 class DownloadImgTask extends AsyncTask<String,Integer,Void>{

     private int mPosition;

     public  DownloadImgTask(int position){
         mPosition = position;
     }

     @Override
     protected Void doInBackground(String... params) {
         String imgUrl = params[0];
         File file = null;
         try {
             FutureTarget<File>  future = Glide
                     .with(ImageViewPagerActivity.this)
                     .load(imgUrl)
                     .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
             file = future.get();

             String filePath = file.getAbsolutePath();

             String destFileName = System.currentTimeMillis() + FileUtils.getImageFileExt(filePath);
             File destFile = new File(FileUtils.getDir(""), destFileName);

             FileUtils.copy(file, destFile);// 保存图片

             // 最后通知图库更新
             sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                     Uri.fromFile(new File(destFile.getPath()))));
         } catch (Exception e) {
             KLog.e(TAG, e.getMessage());
         }
         return null;
     }

     @Override
     protected void onPostExecute(Void aVoid) {
         mDownloadingFlagMap.put(mPosition,false);//下载完成后更改对应的flag
         UIUtils.showToast("保存成功，图片所在文件夹:SD卡根路径/TouTiao");
     }

     @Override
     protected void onProgressUpdate(Integer... values) {
         KLog.i(TAG,"progress: " + values[0]);
     }
 }

class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
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
}
