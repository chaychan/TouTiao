package com.chaychan.news.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author ChayChan
 * @description: 网络的工具类
 * @date 2017/6/16  21:22
 */

public class NetWorkUtils {

    public static boolean isNetworkAvailable(Context context) {
       if(context !=null){
           ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo info = cm.getActiveNetworkInfo();
           if(info !=null){
               return info.isAvailable();
           }
       }
        return false;
    }
}
