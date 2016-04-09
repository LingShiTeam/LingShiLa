package atguigu.com.lingshixiaomiao.application;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

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
        x.Ext.setDebug(true);



    }

    /**
     * 得到全局的Content
     * @return
     */
    public static Context getContext() {
        return context;
    }
}
