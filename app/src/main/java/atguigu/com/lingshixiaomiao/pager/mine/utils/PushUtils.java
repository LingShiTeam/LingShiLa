package atguigu.com.lingshixiaomiao.pager.mine.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by lanmang on 2016/4/11.
 * 推送工具类
 */
public class PushUtils {
    /**
     * 判断是否开启推送
     */
    public static boolean isPush(Context context) {
        File push = CacheUtils.getSmallFile(context, "push");
        String cache = CacheUtils.getCache(push);
        if ("否".equals(cache)) {
            return false;
        }
        return true;
    }
}
