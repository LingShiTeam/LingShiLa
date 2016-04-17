package atguigu.com.lingshixiaomiao.pager.subject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import atguigu.com.lingshixiaomiao.application.MyApplication;

/**
 * Created by CheungJhonny on 2016/4/14.
 * 判断网络是否工作的工具类
 */
public class SubjectNetUtils {

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

}
