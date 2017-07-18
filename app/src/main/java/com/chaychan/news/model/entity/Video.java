package com.chaychan.news.model.entity;

/**
 * @author ChayChan
 * @description: 视频的实体类
 * @date 2017/6/18  19:37
 */
public class Video {

    /**
     * backup_url_1 : aHR0cDovL3Y3LnBzdGF0cC5jb20vYmM0YmExYTk4MGY2OGY2NmQxMmM4Mjg2NjZhYjBmZDkvNTg5YzI4NGIvdmlkZW8vbS8xMTQzMDI2MDAwMDJkZTE5MTdiM2QwZDIyMDFhYWM1ZWFlYzI0YjQ5YTBiYjJhNGZiMDU1NWYzNWUzLw==
     * bitrate : 228992
     * definition : 360p
     * main_url : aHR0cDovL3YxLjM2NXlnLmNvbS8yMjAxYjhlYmVkZGJmY2MwYjg3MWQ0ZWNjMWM1OWE3ZC81ODljMjg0Yi92aWRlby9tLzExNDMwMjYwMDAwMmRlMTkxN2IzZDBkMjIwMWFhYzVlYWVjMjRiNDlhMGJiMmE0ZmIwNTU1ZjM1ZTMv
     * preload_interval : 25
     * preload_max_step : 10
     * preload_min_step : 5
     * preload_size : 327680
     * size : 8357746.0
     * socket_buffer : 1.373952E8
     * user_video_proxy : 1
     * vheight : 360
     * vtype : mp4
     * vwidth : 640
     */
    public String backup_url_1;
    public int bitrate;
    public String definition;
    public String main_url;
    public int preload_interval;
    public int preload_max_step;
    public int preload_min_step;
    public int preload_size;
    public double size;
    public double socket_buffer;
    public int user_video_proxy;
    public int vheight;
    public String vtype;
    public int vwidth;

    @Override
    public String toString() {
        return "Video{" +
                "backup_url_1='" + backup_url_1 + '\'' +
                ", bitrate=" + bitrate +
                ", definition='" + definition + '\'' +
                ", main_url='" + main_url + '\'' +
                ", preload_interval=" + preload_interval +
                ", preload_max_step=" + preload_max_step +
                ", preload_min_step=" + preload_min_step +
                ", preload_size=" + preload_size +
                ", size=" + size +
                ", socket_buffer=" + socket_buffer +
                ", user_video_proxy=" + user_video_proxy +
                ", vheight=" + vheight +
                ", vtype='" + vtype + '\'' +
                ", vwidth=" + vwidth +
                '}';
    }
}
