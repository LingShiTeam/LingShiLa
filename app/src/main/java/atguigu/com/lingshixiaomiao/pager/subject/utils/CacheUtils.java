package atguigu.com.lingshixiaomiao.pager.subject.utils;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import atguigu.com.lingshixiaomiao.pager.home.utils.MD5Encoder;

/**
 * Created by CheungJhonny on 2016/4/13.
 * 缓存数据的工具类
 */
public class CacheUtils {

    /**
     * 专题页面中顶部top的数据
     */
    public static final String SUBJECT_TOP_DATA = "subject_top_data";
    public static final String SUBJECT_LIST_DATA = "subject_list_data";


    /**
     * 保存加载的数据
     *
     * @param value
     * @param key
     * @param context
     */
    public static void putString(Context context, String key, String value) {
        //判断内存卡是否在挂在状态
        if (Environment.getDataDirectory().equals(Environment.MEDIA_MOUNTED)) {

            try {
                String fileName = MD5Encoder.encode(key);
                File file = new File(Environment.getExternalStorageDirectory() + "/lingshi_subfile/" + fileName);
                File fileParent = file.getParentFile();
                if (!fileParent.exists()) {
                    fileParent.mkdirs();//创建所有目录
                }
                if (!file.exists()) {
                    file.createNewFile();//必须上级目录存在才可以保存数据
                }

                //保存数据
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(value.getBytes());

                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //若果sd卡没有挂载 那么保存到sp存储中g
            context.getSharedPreferences("linshi", Context.MODE_PRIVATE).edit().putString(key, value).commit();
        }
    }


    /**
     * 根据可以值获取保存的数据
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        //判断sd卡是否在挂载在手机上

        if (Environment.getDataDirectory().equals(Environment.MEDIA_MOUNTED)) {

            //取出数据
            try {

                String fileName = MD5Encoder.encode(key);
                File file = new File(Environment.getExternalStorageDirectory() + "/lingshi_subfile/" + fileName);
                if (!file.exists()) {
                    return "";
                }

                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buffer = new byte[1024];
                while ((len = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);

                }
                    fis.close();
                    bos.close();
                    return bos.toString();//返回读取的数据

            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }


        } else {
            //如果没有在sd卡中有数据的话 那么就从sp存储中获取
            return context.getSharedPreferences("linshi", context.MODE_PRIVATE).getString(key, "");
        }
    }

}
