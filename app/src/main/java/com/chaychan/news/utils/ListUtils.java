package com.chaychan.news.utils;

import java.util.List;

/**
 * @author ChayChan
 * @description: 和List相关的工具方法
 * @date 2017/6/19  17:41
 */

public class ListUtils {

    public static boolean isEmpty(List list){
        if (list == null){
            return true;
        }
        return list.size() == 0;
    }
}
