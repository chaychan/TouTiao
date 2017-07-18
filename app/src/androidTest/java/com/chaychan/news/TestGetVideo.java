package com.chaychan.news;

import android.test.AndroidTestCase;

import com.chaychan.news.api.ApiRetrofit;
import com.chaychan.news.model.response.VideoPathResponse;
import com.socks.library.KLog;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author ChayChan
 * @description: TODO
 * @date 2017/7/13  22:02
 */

public class TestGetVideo extends AndroidTestCase {

    public void testGet() {
        String link = "http://www.toutiao.com/a6441916548616552974/";
        ApiRetrofit.getInstance().getApiService().getVideoPath(link).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoPathResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(VideoPathResponse videpPathResponse) {
                        KLog.e(videpPathResponse.toString());
                    }
                });

    }
}
