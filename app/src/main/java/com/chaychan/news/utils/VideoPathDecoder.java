package com.chaychan.news.utils;

import com.chaychan.news.R;
import com.chaychan.news.api.ApiRetrofit;
import com.chaychan.news.model.entity.Video;
import com.chaychan.news.model.entity.VideoModel;
import com.socks.library.KLog;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author ChayChan
 * @date 2017/6/10  10:54
 */
public abstract class VideoPathDecoder {

    public static final String TAG = VideoPathDecoder.class.getSimpleName();

    public void decodePath(String srcUrl) {
        KLog.e(TAG,"srcUrl: " + srcUrl);
        ApiRetrofit.getInstance().getApiService().getVideoHtml("https://pv.vlogdownloader.com")
                .flatMap(new Func1<String, Observable<VideoModel>>() {
                    @Override
                    public Observable<VideoModel> call(String response) {
                        Pattern pattern = Pattern.compile("var hash = \"(.+)\"");
                        Matcher matcher = pattern.matcher(response);
                        KLog.e(TAG, "onResponse: " + response);
                        if (matcher.find()) {
                            String hash = matcher.group(1);
                            String url = String.format("http://pv.vlogdownloader.com/api.php?url=%s&hash=%s", srcUrl, hash);
                            KLog.e(TAG, "url: " + url);
                            return ApiRetrofit.getInstance().getApiService().getVideoData(url);
                        }
                        return null;
                    }
                })
                .map(new Func1<VideoModel, Video>() {
                    @Override
                    public Video call(VideoModel response) {
                        List<Video> video = response.video;
                        if (!ListUtils.isEmpty(video)){
                            //取数组中最后一个
                            return video.get(video.size() - 1);
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Video>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        onDecodeError(UIUtils.getString(R.string.video_parse_error));
                    }

                    @Override
                    public void onNext(Video video) {
                        if (video != null){
                            KLog.e(TAG,"视频地址：" + video.url);
                            onSuccess(video.url);
                        }else{
                            onDecodeError(UIUtils.getString(R.string.video_parse_error));
                        }
                    }
                });
    }

    public abstract void onSuccess(String url);
    public abstract void onDecodeError(String errorMsg);
}
