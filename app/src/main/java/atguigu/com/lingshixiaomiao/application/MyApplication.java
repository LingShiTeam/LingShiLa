package atguigu.com.lingshixiaomiao.application;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.PushUtils;
import cn.jpush.android.api.JPushInterface;

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

    }

    /**
     * 极光推送 初始化
     */
    public void initJPush() {
        if (PushUtils.isPush(context)) {//判断是否推送
            LogUtils.loge("极光推送 已经初始化");
            JPushInterface.setDebugMode(Constants.isLog);    // 设置开启日志,发布时请关闭日志
            JPushInterface.init(this);    // 初始化 JPush
        }else{
            LogUtils.loge("极光推送 没有初始化");
        }
    }

    public void setJpush() {
        if (PushUtils.isPush(context)) {
            JPushInterface.resumePush(this);
        }else{
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
