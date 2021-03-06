package atguigu.com.lingshixiaomiao.pager.mine.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;

/**
 * Created by lanmang on 2016/4/8.
 * 解析json的工具类
 */
public class JsonUtils<T> {

    private Class clazz;
    private T t;
    private String json;

    /**
     * 联网获取数据
     * http://api.ds.lingshi.cccwei.com/?cid=760294&uid=0&tms=20160406223600
     * &sig=5ad479a0b6bcaff9&wssig=a62fcd9fb5171bca&os_type=3&version=18&channel_name=feibo&srv=2201
     * @param url
     * @param clazz
     */
    public void loadData(String url, Class<T> clazz) {
        this.clazz = clazz;
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.loge("onSuccess");
                LogUtils.loge(result);
                json = result;
                parseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtils.loge("onFinished");
            }
        });
    }

    private void parseJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return;
        }
        t = (T) new Gson().fromJson(json, clazz);
        LogUtils.loge("测试T = " + t.toString());
        sendDataByEventBus();
    }

    public T parseJson(String json, Class<T> clazz) {
        this.clazz = clazz;
        parseJson(json);
        return t;
    }

    private void sendDataByEventBus() {
        EventBus.getDefault().post(t);
    }

    public String getJson() {
        return json;
    }
}
