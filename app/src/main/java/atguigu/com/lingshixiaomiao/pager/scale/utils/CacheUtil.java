package atguigu.com.lingshixiaomiao.pager.scale.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/4/15 0015.
 */
public class CacheUtil {
    /**
     * 保存数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();

    }

    /**
     * 得到缓存数据
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 保持String类型数据
     *
     * @param context
     * @param key
     * @param values
     */
    public static void putString(Context context, String key, String values) {

        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putString(key, values).commit();

    }

    /**
     * 得到保持的String类型数据
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
}
