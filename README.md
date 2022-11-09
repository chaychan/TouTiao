# 精仿今日头条

精仿今日头条，数据是抓取今日头条App的数据。使用RxJava + Retrofit + MVP开发的开源项目，仅供学习用途。觉得对你有帮助的话请帮忙star一下，让更多人知道，多谢啦！

### 2022-11-09
&emsp;&emsp;近期有个小伙伴向我反馈项目下下来后编译出错，愿意付费希望我解决下，今天抽空把问题解决了下，希望可以帮到他，当然也是无偿的，这个
项目从17年到现在已经5年了，很高兴还可以帮到一些刚入门的安卓朋友或者参考开发的朋友，由衷感谢大家支持和Star。

### 2019-12-02
&emsp;&emsp;时间过得真快，不知不觉就过了一年，去年年底我更新了视频解析的逻辑，然后一直用到前段时间，有小伙伴issue上提出又不能解析了。由于前段时间比较忙，一直没能腾出时间来解决这个问题，上周末通过分析，发现以往解析视频的逻辑已经用不了了，所以重新找了个可以解析头条视频的网站，并分析到了他们的解析接口规则，修复了视频播放的问题，小伙伴们可以用作交流，讨论下，但不可用作商用，主要有以下两点原因：

- 调用解析接口并未获得该网站的同意，属于非正常渠道获取，仅用于学习交流

- 该网站可能变动接口规则，如果真的有解析视频方面的需求，可以和该网站联系和合作


### 2018-12-04 更新内容：  
&emsp;&emsp;项目已经发布很久了，最近看到有不少小伙伴帮我star，想想自己已经好久没有维护这个项目了，主要是因为忙，最近抽空把issue上提出的问题解决了，方便需要参考到其中某些功能的小伙伴使用，同时也希望这个项目可以成为我在github上首个star破k的项目，希望小伙伴们可以帮忙star一下，多谢了，项目的话我也会继续维护和更新。
    
- 更改右滑关闭的依赖库，解决8.0以上系统不兼容右滑的问题；

- 修改视频解析逻辑，之前通过加载详情页成功后延迟5秒获取html中的video标签内的视频地址，体验极差，故花了几天时间寻找解析头条视频地址的方法，经过多次实验终于成功；

- 解决视频播放错乱的问题；更换了最新版的饺子播放器；    

- 整理项目结构，包的路径等 



# Apk下载地址

[[点击下载体验](https://raw.githubusercontent.com/chaychan/TouTiao/master/apk/news.apk)]

扫码下载体验：

![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/apk_qrcode.png)  

# Blog
[http://blog.csdn.net/chay_chan/article/details/75319452](http://blog.csdn.net/chay_chan/article/details/75319452)


# 演示截图

## 首页
![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/home.jpg)  

## 视频
![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/video.jpg)  

## 微头条
![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/micro.jpg)  

## 我的
![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/mine.jpg)  


# gif图

## 新闻列表、视频列表
![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/main.gif)  

## 非视频新闻详情页面
![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/text_detail.gif)  

## 查看和保存图片 
![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/watch_save_img.gif)  

## 视频播放、视频详情页面
![](https://raw.githubusercontent.com/chaychan/TouTiaoPics/master/screenshot/video_detail.gif)  


# 使用到的第三方库
* [okhttp](https://github.com/square/okhttp)
* [Retrofit](https://github.com/square/retrofit)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Gson](https://github.com/google/gson)
* [BottomBarLayout(轻量级底部导航栏)](https://github.com/chaychan/BottomBarLayout)
* [BaseRecyclerViewAdapterHelper(ReclerView万能适配器)](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
* [StateView(用于几种状态布局的切换)](https://github.com/nukc/StateView)
* [JieCaoVideoPlayer，改名 JiaoZiVideoPlayer (视频播放)](https://github.com/lipangit/JiaoZiVideoPlayer)
* [BGARefreshLayout-Android(下拉刷新)](https://github.com/bingoogolapple/BGARefreshLayout-Android)
* [Eyes(修改状态栏颜色)](https://github.com/imflyn/Eyes)
* [ColorTrackTabLayout](https://github.com/yewei02538/ColorTrackTabLayout)
* [EventBus](https://github.com/greenrobot/EventBus)
* [KLog(log管理)](https://github.com/ZhaoKaiQiang/KLog)

# 现有功能

1.获取各种频道的新闻列表,包括视频和非视频新闻；

2.查看新闻详情，包括视频和非视频新闻的详情；  

3.查看新闻评论列表； 

4.新闻数据本地存储，已经获取到的新闻数据保存在本地数据库中，上拉加载更多时可查看历史新闻；

5.底部页签点击下拉刷新;

6.视频列表播放中的视频不可见时停止播放。

7.查看和保存图片。

# 技术要点

1.新闻数据的抓取和分析,使用fidder抓取，具体使用可参考下面的网址：

[http://www.tuicool.com/articles/BJjQZf](http://www.tuicool.com/articles/BJjQZf)

2.新闻列表多种Item布局的展示，使用的是我封装的MultipleItemRvAdapter，基于BaseRecyclerViewAdapterHelper封装，便于多布局条目的管理：

[https://github.com/chaychan/MultipleItemRvAdapter](https://github.com/chaychan/MultipleItemRvAdapter "https://github.com/chaychan/MultipleItemRvAdapter")

3.视频源地址的解析，请查看我写的博客 [今日头条最新视频解析方法](https://blog.csdn.net/Chay_Chan/article/details/84825807)

# 声明
这个属于个人开发作品，仅做学习交流使用，如用到实际项目还需多考虑其他因素如并发等，请多多斟酌。**诸位勿传播于非技术人员，拒绝用于商业用途，数据均属于非正常渠道获取，原作公司拥有所有权利。**