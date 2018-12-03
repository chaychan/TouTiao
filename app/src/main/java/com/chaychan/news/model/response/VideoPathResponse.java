package com.chaychan.news.model.response;

import java.util.List;

/**
 * @author ChayChan
 * @description: 视频解析返回
 * @date 2018/12/03  17:55
 */
public class VideoPathResponse {
    public String status;
    public List<VideoEntity> video;

    public class VideoEntity{
        public String url;
    }
}
