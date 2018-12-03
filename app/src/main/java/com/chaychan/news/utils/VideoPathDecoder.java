package com.chaychan.news.utils;

import com.chaychan.news.api.ApiRetrofit;
import com.chaychan.news.model.response.VideoPathResponse;
import com.socks.library.KLog;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public abstract class VideoPathDecoder {

    private static final String HASH = "d84ac12351166e12007565b468c60fc9";

    public void decodePath(String srcUrl) {
        ApiRetrofit.getInstance().getApiService().parseVideo(srcUrl,HASH) .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VideoPathResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.getLocalizedMessage());
                        onDecodeError();
                    }

                    @Override
                    public void onNext(VideoPathResponse response) {
                        KLog.e("parseVideoResponse: " + response);
                        String url = "";
                        if (response.status.equals("ok")){
                            //请求成功
                            List<VideoPathResponse.VideoEntity> videoList = response.video;
                            if (!ListUtils.isEmpty(videoList)){
                                url = videoList.get(videoList.size() -1).url;//获取下载地址中最后一个地址，即超清
                                KLog.e("videoUrl: ",url);
                            }
                            onSuccess(url);
                        }else{
                            //请求失败
                            onDecodeError();
                        }
                    }
                });
    }

    public abstract void onSuccess(String url);
    public abstract void onDecodeError();
}
