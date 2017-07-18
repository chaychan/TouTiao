package com.chaychan.news.model.response;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author ChayChan
 * @description: TODO
 * @date 2017/7/13  22:05
 */

public class VideoPathResponse {


    /**
     * retCode : 200
     * retDesc : 成功
     * data : {"text":"邱淑贞当年这个出场至今无人超越！王晶赌片又一巅峰之作！","video":{"link":[{"url":"http://v1.365yg.com/2c8813620fc24e51f1975eb7e875911a/596779e7/video/m/2205c620ddfa26a4916a0a720929c711c1c114ab6600002345a6997e04/#mp4","buttonText":"极速(360p)视频地址1","type":"极速(360p)"},{"url":"http://v7.pstatp.com/335833f83c5bb9b82fcee60de9a34157/596779e7/video/m/2205c620ddfa26a4916a0a720929c711c1c114ab6600002345a6997e04/#mp4","buttonText":"极速(360p)视频地址2","type":null},{"url":"http://v6.365yg.com/video/m/2204b724074f2f6428897cee1c94152c424114aa6500001a3802bb4557/?Expires=1499957239&AWSAccessKeyId=qh0h9TdcEMoS2oPj7aKX&Signature=jYQlYr4XcXrLBPwKq1%2BqVqF3E4c%3D","buttonText":"高清(480p)视频地址1","type":"高清(480p)"},{"url":"http://v7.pstatp.com/867e9c3d968c4a17af6e3742e3dd0f07/596779e7/video/m/2204b724074f2f6428897cee1c94152c424114aa6500001a3802bb4557/#mp4","buttonText":"高清(480p)视频地址2","type":null},{"url":"http://v3.365yg.com/05c33d9c284b6bc257c99a1b47a09b10/596779e7/video/m/220661c887bdc3d42b6b77fbf816e90b42c114ab38000015dae05f90de/#mp4","buttonText":"超清(720p)视频地址1","type":"超清(720p)"},{"url":"http://v1.365yg.com/4ebb714791d0f3fe62190c70fa21ac7d/596779e7/video/m/220661c887bdc3d42b6b77fbf816e90b42c114ab38000015dae05f90de/#mp4","buttonText":"超清(720p)视频地址2","type":null}],"download":[{"url":"http://v1.365yg.com/2c8813620fc24e51f1975eb7e875911a/596779e7/video/m/2205c620ddfa26a4916a0a720929c711c1c114ab6600002345a6997e04/#mp4","buttonText":"极速(360p)视频地址1","type":"极速(360p)"},{"url":"http://v6.365yg.com/video/m/2204b724074f2f6428897cee1c94152c424114aa6500001a3802bb4557/?Expires=1499957239&AWSAccessKeyId=qh0h9TdcEMoS2oPj7aKX&Signature=jYQlYr4XcXrLBPwKq1%2BqVqF3E4c%3D","buttonText":"高清(480p)视频地址1","type":"高清(480p)"},{"url":"http://v3.365yg.com/05c33d9c284b6bc257c99a1b47a09b10/596779e7/video/m/220661c887bdc3d42b6b77fbf816e90b42c114ab38000015dae05f90de/#mp4","buttonText":"超清(720p)视频地址1","type":"超清(720p)"}]}}
     * succ : true
     */

    public int retCode;
    public String retDesc;
    public DataBean data;
    public boolean succ;

    public static class DataBean {
        /**
         * text : 邱淑贞当年这个出场至今无人超越！王晶赌片又一巅峰之作！
         * video : {"link":[{"url":"http://v1.365yg.com/2c8813620fc24e51f1975eb7e875911a/596779e7/video/m/2205c620ddfa26a4916a0a720929c711c1c114ab6600002345a6997e04/#mp4","buttonText":"极速(360p)视频地址1","type":"极速(360p)"},{"url":"http://v7.pstatp.com/335833f83c5bb9b82fcee60de9a34157/596779e7/video/m/2205c620ddfa26a4916a0a720929c711c1c114ab6600002345a6997e04/#mp4","buttonText":"极速(360p)视频地址2","type":null},{"url":"http://v6.365yg.com/video/m/2204b724074f2f6428897cee1c94152c424114aa6500001a3802bb4557/?Expires=1499957239&AWSAccessKeyId=qh0h9TdcEMoS2oPj7aKX&Signature=jYQlYr4XcXrLBPwKq1%2BqVqF3E4c%3D","buttonText":"高清(480p)视频地址1","type":"高清(480p)"},{"url":"http://v7.pstatp.com/867e9c3d968c4a17af6e3742e3dd0f07/596779e7/video/m/2204b724074f2f6428897cee1c94152c424114aa6500001a3802bb4557/#mp4","buttonText":"高清(480p)视频地址2","type":null},{"url":"http://v3.365yg.com/05c33d9c284b6bc257c99a1b47a09b10/596779e7/video/m/220661c887bdc3d42b6b77fbf816e90b42c114ab38000015dae05f90de/#mp4","buttonText":"超清(720p)视频地址1","type":"超清(720p)"},{"url":"http://v1.365yg.com/4ebb714791d0f3fe62190c70fa21ac7d/596779e7/video/m/220661c887bdc3d42b6b77fbf816e90b42c114ab38000015dae05f90de/#mp4","buttonText":"超清(720p)视频地址2","type":null}],"download":[{"url":"http://v1.365yg.com/2c8813620fc24e51f1975eb7e875911a/596779e7/video/m/2205c620ddfa26a4916a0a720929c711c1c114ab6600002345a6997e04/#mp4","buttonText":"极速(360p)视频地址1","type":"极速(360p)"},{"url":"http://v6.365yg.com/video/m/2204b724074f2f6428897cee1c94152c424114aa6500001a3802bb4557/?Expires=1499957239&AWSAccessKeyId=qh0h9TdcEMoS2oPj7aKX&Signature=jYQlYr4XcXrLBPwKq1%2BqVqF3E4c%3D","buttonText":"高清(480p)视频地址1","type":"高清(480p)"},{"url":"http://v3.365yg.com/05c33d9c284b6bc257c99a1b47a09b10/596779e7/video/m/220661c887bdc3d42b6b77fbf816e90b42c114ab38000015dae05f90de/#mp4","buttonText":"超清(720p)视频地址1","type":"超清(720p)"}]}
         */

        public String text;
        public VideoBean video;

        public static class VideoBean {
            public List<LinkBean> link;
            public List<DownloadBean> download;

            public static class LinkBean {
                /**
                 * url : http://v1.365yg.com/2c8813620fc24e51f1975eb7e875911a/596779e7/video/m/2205c620ddfa26a4916a0a720929c711c1c114ab6600002345a6997e04/#mp4
                 * buttonText : 极速(360p)视频地址1
                 * type : 极速(360p)
                 */

                public String url;
                public String buttonText;
                public String type;
            }

            public static class DownloadBean {
                /**
                 * url : http://v1.365yg.com/2c8813620fc24e51f1975eb7e875911a/596779e7/video/m/2205c620ddfa26a4916a0a720929c711c1c114ab6600002345a6997e04/#mp4
                 * buttonText : 极速(360p)视频地址1
                 * type : 极速(360p)
                 */

                public String url;
                public String buttonText;
                public String type;
            }
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
