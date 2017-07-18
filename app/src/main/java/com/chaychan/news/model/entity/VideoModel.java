package com.chaychan.news.model.entity;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class VideoModel {

    /**
     * status : 10
     * user_id : toutiao
     * video_id : 5e0c23070e3a4368bbf216e38f90d3fa
     * video_duration : 225.6
     * video_list : {"video_1":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vYmM0YmExYTk4MGY2OGY2NmQxMmM4Mjg2NjZhYjBmZDkvNTg5YzI4NGIvdmlkZW8vbS8xMTQzMDI2MDAwMDJkZTE5MTdiM2QwZDIyMDFhYWM1ZWFlYzI0YjQ5YTBiYjJhNGZiMDU1NWYzNWUzLw==","bitrate":228992,"definition":"360p","main_url":"aHR0cDovL3YxLjM2NXlnLmNvbS8yMjAxYjhlYmVkZGJmY2MwYjg3MWQ0ZWNjMWM1OWE3ZC81ODljMjg0Yi92aWRlby9tLzExNDMwMjYwMDAwMmRlMTkxN2IzZDBkMjIwMWFhYzVlYWVjMjRiNDlhMGJiMmE0ZmIwNTU1ZjM1ZTMv","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":8357746,"socket_buffer":1.373952E8,"user_video_proxy":1,"vheight":360,"vtype":"mp4","vwidth":640},"video_2":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vZmE0NzYxYjJmNTQ5ZjI0MmI0YmFmYjk0NGQ1N2Q0YWEvNTg5YzI4NGIvdmlkZW8vbS8yMjBhMmY2MzgyMTkzMDg0MDg0YmFiOGU2NzIwMzBmZWIyOTExNDMwNzkwMDAwMThkYzJjNTNjNzNjLw==","bitrate":351431,"definition":"480p","main_url":"aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzIyMGEyZjYzODIxOTMwODQwODRiYWI4ZTY3MjAzMGZlYjI5MTE0MzA3OTAwMDAxOGRjMmM1M2M3M2MvP0V4cGlyZXM9MTQ4NjYzMjUzOSZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNcm0xVmxSMmFkJTJGJlNpZ25hdHVyZT1oTUFSeUVvbG9lZm00dkhXcGVBT1dNV1RUMzQlM0Q=","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":1.1810348E7,"socket_buffer":2.108586E8,"user_video_proxy":1,"vheight":480,"vtype":"mp4","vwidth":854},"video_3":{"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vODU2ZTM4N2UyZDZjZDAxMzQ2MDNmZTA3NmRhYjM1NjYvNTg5YzI4NGIvdmlkZW8vbS8yMjBmMTgwOTE1ODI1NmI0ZjViYThhYTAxNjVjMTI5M2I2ODExNDMwY2YwMDAwMTdlZTFiYjc4ZmNjLw==","bitrate":740928,"definition":"720p","main_url":"aHR0cDovL3YxLjM2NXlnLmNvbS80OGRhMDMzMWI1ZGRjZmE2Y2NhMjFhMWYyZGVjM2QxYy81ODljMjg0Yi92aWRlby9tLzIyMGYxODA5MTU4MjU2YjRmNWJhOGFhMDE2NWMxMjkzYjY4MTE0MzBjZjAwMDAxN2VlMWJiNzhmY2Mv","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":2.2793753E7,"socket_buffer":4.445568E8,"user_video_proxy":1,"vheight":720,"vtype":"mp4","vwidth":1280}}
     */

    public int status;
    public String user_id;
    public String video_id;
    public double video_duration;
    /**
     * video_1 : {"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vYmM0YmExYTk4MGY2OGY2NmQxMmM4Mjg2NjZhYjBmZDkvNTg5YzI4NGIvdmlkZW8vbS8xMTQzMDI2MDAwMDJkZTE5MTdiM2QwZDIyMDFhYWM1ZWFlYzI0YjQ5YTBiYjJhNGZiMDU1NWYzNWUzLw==","bitrate":228992,"definition":"360p","main_url":"aHR0cDovL3YxLjM2NXlnLmNvbS8yMjAxYjhlYmVkZGJmY2MwYjg3MWQ0ZWNjMWM1OWE3ZC81ODljMjg0Yi92aWRlby9tLzExNDMwMjYwMDAwMmRlMTkxN2IzZDBkMjIwMWFhYzVlYWVjMjRiNDlhMGJiMmE0ZmIwNTU1ZjM1ZTMv","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":8357746,"socket_buffer":1.373952E8,"user_video_proxy":1,"vheight":360,"vtype":"mp4","vwidth":640}
     * video_2 : {"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vZmE0NzYxYjJmNTQ5ZjI0MmI0YmFmYjk0NGQ1N2Q0YWEvNTg5YzI4NGIvdmlkZW8vbS8yMjBhMmY2MzgyMTkzMDg0MDg0YmFiOGU2NzIwMzBmZWIyOTExNDMwNzkwMDAwMThkYzJjNTNjNzNjLw==","bitrate":351431,"definition":"480p","main_url":"aHR0cDovL3Y2LjM2NXlnLmNvbS92aWRlby9tLzIyMGEyZjYzODIxOTMwODQwODRiYWI4ZTY3MjAzMGZlYjI5MTE0MzA3OTAwMDAxOGRjMmM1M2M3M2MvP0V4cGlyZXM9MTQ4NjYzMjUzOSZBV1NBY2Nlc3NLZXlJZD1xaDBoOVRkY0VNcm0xVmxSMmFkJTJGJlNpZ25hdHVyZT1oTUFSeUVvbG9lZm00dkhXcGVBT1dNV1RUMzQlM0Q=","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":1.1810348E7,"socket_buffer":2.108586E8,"user_video_proxy":1,"vheight":480,"vtype":"mp4","vwidth":854}
     * video_3 : {"backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vODU2ZTM4N2UyZDZjZDAxMzQ2MDNmZTA3NmRhYjM1NjYvNTg5YzI4NGIvdmlkZW8vbS8yMjBmMTgwOTE1ODI1NmI0ZjViYThhYTAxNjVjMTI5M2I2ODExNDMwY2YwMDAwMTdlZTFiYjc4ZmNjLw==","bitrate":740928,"definition":"720p","main_url":"aHR0cDovL3YxLjM2NXlnLmNvbS80OGRhMDMzMWI1ZGRjZmE2Y2NhMjFhMWYyZGVjM2QxYy81ODljMjg0Yi92aWRlby9tLzIyMGYxODA5MTU4MjU2YjRmNWJhOGFhMDE2NWMxMjkzYjY4MTE0MzBjZjAwMDAxN2VlMWJiNzhmY2Mv","preload_interval":25,"preload_max_step":10,"preload_min_step":5,"preload_size":327680,"size":2.2793753E7,"socket_buffer":4.445568E8,"user_video_proxy":1,"vheight":720,"vtype":"mp4","vwidth":1280}
     */

    public VideoListBean video_list;

    public static class VideoListBean {
        public Video video_1;
        public Video video_2;
        public Video video_3;

    }
}
