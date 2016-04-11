package atguigu.com.lingshixiaomiao.pager.mine.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanmang on 2016/4/11.
 * 缓存管理工具类
 */
public class CacheUtils {
    /**
     * 获取缓存文件路径
     *
     * @return
     */
    public static List<File> getCacheFiles(Context context) {
        List<File> cacheFiles = new ArrayList<>();
        File cacheFile = null;
        //获取外部缓存路径
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheFile = context.getExternalCacheDir();
            cacheFiles.add(cacheFile);
        }

        //获取内部缓存路径
        cacheFile = context.getCacheDir();
        cacheFiles.add(cacheFile);
        return cacheFiles;
    }

    /**
     * 保存缓存
     *
     */
    public static void setCache(File file, String value) {

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);

            fos.write(value.getBytes());

            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存数据

     * @return
     */
    public static String getCache(File file) {
        if (!file.exists()) {
            return "";
        }

        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            String cache = baos.toString();

            return cache;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

    /**
     * 获取缓存文件
     *
     * @param context
     * @param name
     * @return
     */
    @NonNull
    public static File getCacheFile(Context context, String name) {
        name = MD5Utils.md5(name);
        File cache;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cache = context.getExternalCacheDir();
        } else {
            cache = context.getCacheDir();
        }

        return new File(cache, name);
    }
    /**
     * 获取非缓存小文件路径
     *
     * @param context
     * @param name
     * @return
     */
    @NonNull
    public static File getSmallFile(Context context, String name) {
        name = MD5Utils.md5(name);
        File cache = context.getCacheDir();
        return new File(cache, name);
    }

}
