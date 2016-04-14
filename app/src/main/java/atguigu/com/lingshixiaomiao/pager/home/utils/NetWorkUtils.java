package atguigu.com.lingshixiaomiao.pager.home.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.UnsupportedEncodingException;

import atguigu.com.lingshixiaomiao.application.MyApplication;

/**
 * Created by Liu_haiwei on 2016/4/10.
 * 联网工具类
 */
public class NetWorkUtils {

    private static ConnectivityManager connManager;
    /**
     * 网络是否连接可用
     *
     * @return
     */
    public static boolean isNetworkConnected() {

        if (connManager == null) {
            connManager = (ConnectivityManager) MyApplication.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }

        if (connManager != null) {
            final NetworkInfo networkinfo = connManager.getActiveNetworkInfo();

            if (networkinfo != null) {
                return networkinfo.isConnected();
            }
        } else {
            return true;
        }

        return false;
    }

    /*public static boolean isAvailable(Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return true;
        }
        return false;
    }*/

    /**
     * 把中文字符转换为带百分号的浏览器编码
     * @param word   中文字符
     * @param encoding  字符编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String toBrowserCode(String word, String encoding)
            throws UnsupportedEncodingException {
        byte[] textByte = word.getBytes(encoding);
        StringBuilder strBuilder = new StringBuilder();

        for (int j = 0; j < textByte.length; j++) {
            // 转换为16进制字符
            String hexStr = Integer.toHexString(textByte[j] & 0xff);
            strBuilder.append("%" + hexStr.toUpperCase());
        }

        return strBuilder.toString();
    }
}
