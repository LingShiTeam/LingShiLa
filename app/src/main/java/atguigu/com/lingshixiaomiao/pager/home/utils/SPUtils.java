package atguigu.com.lingshixiaomiao.pager.home.utils;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Liu_haiwei on 2016/3/23.
 * 操作sp存储的工具类
 */
public class SPUtils {

    public static final String FIRST_SPLASH = "firstsplash";
    public static final String HOME_TOP_DATA = "home_top_data";

    /**
     * 保存数据(boolean)
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        context.getSharedPreferences("lingshi", Context.MODE_PRIVATE).edit().putBoolean(key, value).commit();
    }

    /**
     * 获取数据(boolean)
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean value) {
        return context.getSharedPreferences("lingshi", Context.MODE_PRIVATE).getBoolean(key, false);

    }

    /**
     * 保存数据(string)
     *
     * @param content
     * @param key
     * @param value
     */
    public static void putString(Context content, String key, String value) {
        // 判断内存卡的挂载状态
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                String fileName = MD5Encoder.encode(key);
                File file = new File(Environment.getExternalStorageDirectory() + "/lingshi_file/" + fileName);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                // 保存数据
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(value.getBytes());

                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            content.getSharedPreferences("lingshi", Context.MODE_PRIVATE).edit().putString(key, value).commit();
        }
    }

    /**
     * 获取数据(string)
     *
     * @param content
     * @param key
     * @return
     */
    public static String getString(Context content, String key) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                String fileName = MD5Encoder.encode(key);
                File file = new File(Environment.getExternalStorageDirectory() + "/lingshi_file/" + fileName);
                if (!file.exists()) {
                    return "";
                }
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buffer = new byte[1024];
                while ((len = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }

                fis.close();
                baos.close();
                return baos.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return content.getSharedPreferences("lingshi", Context.MODE_PRIVATE).getString(key, "");
        }
    }
}
