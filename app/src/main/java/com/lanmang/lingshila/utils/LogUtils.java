package com.lanmang.lingshila.utils;

import android.util.Log;

/**
 * Created by lanmang on 2016/4/8.
 * 日志工具类
 */
public class LogUtils {

    /**
     * 打印日志
     * @param content
     */
    public static void loge(String content) {
        if (Constants.isLog) {
            Log.e("TAG", content);
        }
    }

    /**
     * 打印日志
     * @param clazz
     * @param content
     */
    public static void loge(Class clazz, String content) {
        if (Constants.isLog) {
            Log.e(clazz.getSimpleName(), content);
        }
    }

    /**
     * 打印日志
     * @param TAG
     * @param content
     */
    public static void loge(String TAG, String content) {
        if (Constants.isLog) {
            Log.e(TAG, content);
        }
    }


}
