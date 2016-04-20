package atguigu.com.lingshixiaomiao.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xutils.x;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.bean.ProvinceBean;
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
            LogUtils.loge("极光推送 已经初始化");
            JPushInterface.setDebugMode(Constants.isLog);    // 设置开启日志,发布时请关闭日志
            JPushInterface.init(this);    // 初始化 JPush
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

    public boolean isLoadCities = false;

    public ArrayList<ProvinceBean> options1Items;
    public ArrayList<ArrayList<String>> options2Items;
    public ArrayList<ArrayList<ArrayList<String>>> options3Items;

    public void loadCities() {

        if (!isLoadCities) {
            isLoadCities = true;

            options1Items = new ArrayList<ProvinceBean>();
            options2Items = new ArrayList<ArrayList<String>>();
            options3Items = new ArrayList<ArrayList<ArrayList<String>>>();

            int position = 0;
            ProvinceBean provinceBean;
            ArrayList<String> list1 = null;
            ArrayList<String> list2_2 = null;
            ArrayList<ArrayList<String>> list2 = null;
            InputStream is = null;

            try {
                is = getAssets().open("fullcities.xml");
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(is, "utf-8");
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("province")) {
                                String province = parser.getAttributeValue(1);
                                provinceBean = new ProvinceBean(position++, province, "", "");
                                options1Items.add(provinceBean);
                                list1 = new ArrayList<>();
                                list2 = new ArrayList<ArrayList<String>>();

                            } else if (parser.getName().equals("city")) {
                                String city = parser.getAttributeValue(1);
                                list1.add(city);
                                list2_2 = new ArrayList<>();

                            } else if (parser.getName().equals("county")) {
                                String country = parser.getAttributeValue(1);
                                list2_2.add(country);
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("province")) {
                                options2Items.add(list1);
                                options3Items.add(list2);
                            } else if (parser.getName().equals("city")) {
                                list2.add(list2_2);
                            }
                            break;
                    }
                    eventType = parser.next();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}
