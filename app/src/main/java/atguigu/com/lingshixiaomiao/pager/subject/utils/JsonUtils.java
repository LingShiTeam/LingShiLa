package atguigu.com.lingshixiaomiao.pager.subject.utils;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;

/**
 * Created by CheungJhonny on 2016/4/11.
 * 解析数据的工具类
 */
public class JsonUtils<T> {

    private Class aClass;
    private T t;

    /**
     *解析数据
     * @param url 传入的数据
     * @param aClass  数据类xin
     */
    public void loadData(String url, Class<T> aClass) {
        this.aClass = aClass;

        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtils.loge("subject_onSuccess");
                LogUtils.loge(result);
                if (result != null) {
                    //保存首页的数据
                    parseJaon(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("subject_onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("subject_onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtils.loge("subject_onFinished");
            }
        });
    }

    private T parseJaon(String json) {
        t = (T) new Gson().fromJson(json, aClass);

        LogUtils.loge(aClass.getSimpleName());
        sendEventBus();
        return t;
    }

    /**
     * 发送消息
     */
    private void sendEventBus() {
        EventBus.getDefault().post(t);

    }


}
