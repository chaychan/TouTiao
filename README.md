# 精仿今日头条

精仿今日头条，数据是抓取今日头条App的数据。使用RxJava + Retrofit + MVP开发的开源项目，仅供学习用途。觉得对你有帮助的话请帮忙star一下，让更多人知道，多谢啦！

感谢大神 wey ye，项目中参考到他做过的仿今日头条项目，他的项目地址是:[https://github.com/yewei02538/TodayNews](https://github.com/yewei02538/TodayNews)


# Apk下载地址
[[点击下载体验](http://ot6hoxs4u.bkt.clouddn.com/news_0802.apk)]

# Blog
[http://blog.csdn.net/chay_chan/article/details/75319452](http://blog.csdn.net/chay_chan/article/details/75319452)


# 演示截图

## 首页
![](/screenshot/home.jpg)  

## 视频
![](/screenshot/video.jpg)  

## 微头条
![](/screenshot/micro.jpg)  

## 我的
![](/screenshot/mine.jpg)  


# gif图

## 新闻列表、视频列表
![](/screenshot/main.gif)  

## 非视频新闻详情页面
![](/screenshot/text_detail.gif)  

## 视频播放、视频详情页面
![](/screenshot/video_detail.gif)  


# 使用到的第三方库
* [okhttp](https://github.com/square/okhttp)
* [Retrofit](https://github.com/square/retrofit)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Gson](https://github.com/google/gson)
* 
* [BottomBarLayout(轻量级底部导航栏)](https://github.com/chaychan/BottomBarLayout)
* [BaseRecyclerViewAdapterHelper(ReclerView万能适配器)](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
* [StateView(用于几种状态布局的切换)](https://github.com/nukc/StateView)
* [JieCaoVideoPlayer(视频播放)](https://github.com/lipangit/JieCaoVideoPlayer)
* [BGARefreshLayout-Android(下拉刷新)](https://github.com/bingoogolapple/BGARefreshLayout-Android)
* [Eyes(修改状态栏颜色)](https://github.com/imflyn/Eyes)
* [ColorTrackTabLayout](https://github.com/yewei02538/ColorTrackTabLayout)
* 
* [EventBus](https://github.com/greenrobot/EventBus)
* [KLog(log管理)](https://github.com/ZhaoKaiQiang/KLog)

# 现有功能

1.获取各种频道的新闻列表,包括视频和非视频新闻；

2.查看新闻详情，包括视频和非视频新闻的详情；  

3.查看新闻评论列表； 

4.新闻数据本地存储，已经获取到的新闻数据保存在本地数据库中，上拉加载更多时可查看历史新闻；

5.底部页签点击下拉刷新;

6.视频列表播放中的视频不可见时停止播放。

# 技术要点

1.新闻数据的抓取和分析,使用fidder抓取，具体使用可参考下面的网址：

[http://www.tuicool.com/articles/BJjQZf](http://www.tuicool.com/articles/BJjQZf)

2.新闻列表多种Item布局的展示，使用的是BaseRecyclerViewAdapterHelper，ReclerView万能适配器，多布局使用可参考下面的网址：

[https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/968](https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/968)

3.视频源地址的解析，原先参照大神 wey ye的解析已经失效，目前是通过[今日头条视频解析](http://toutiao.iiilab.com/ )这个网址解析的，传入新闻详情页地址，获取到对应视频下载地址的json数据。

# 声明
这个属于个人开发作品，仅做学习交流使用，如用到实际项目还需多考虑其他因素如并发等，请多多斟酌。**诸位勿传播于非技术人员，拒绝用于商业用途，数据均属于非正常渠道获取，原作公司拥有所有权利。**
