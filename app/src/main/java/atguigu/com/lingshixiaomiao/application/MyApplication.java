package atguigu.com.lingshixiaomiao.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.PushUtils;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;

/**
 * Created by Liu_haiwei on 2016/4/9.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();


        /**
         * xutils初始化--home
         */
        x.Ext.init(this);
        x.Ext.setDebug(Constants.isLog);

        /**
         * JPush 极光推送初始化
         */
        initJPush();

        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
        }

        /**
         * 第三方的登录初始化
         */
        PlatformConfig.setSinaWeibo("3388924422", "09aa9ea9b7a6d5b3a312fa826b6db8c4");

    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 极光推送 初始化
     */
    public void initJPush() {
        if (PushUtils.isPush(context)) {//判断是否推送
            if (JPushInterface.isPushStopped(this)) {
                JPushInterface.setDebugMode(Constants.isLog);    // 设置开启日志,发布时请关闭日志
                JPushInterface.init(this);    // 初始化 JPush
            }
            LogUtils.loge("极光推送 已经初始化");
        } else {
            LogUtils.loge("极光推送 没有初始化");
        }
    }

    public void setJpush() {
        if (PushUtils.isPush(context)) {
            JPushInterface.resumePush(this);
        } else {
            JPushInterface.stopPush(this);
        }
    }

    /**
     * 得到全局的Content
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

}
