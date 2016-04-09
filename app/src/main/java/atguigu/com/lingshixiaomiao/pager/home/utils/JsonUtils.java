package atguigu.com.lingshixiaomiao.pager.home.utils;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.application.MyApplication;

/**
 * Created by lanmang on 2016/4/8.
 * 解析json的工具类
 */
public class JsonUtils<T> {

    private Class clazz;
    private T t;

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
                if(result != null) {
                    // 保存数据
                    SPUtils.putString(MyApplication.getContext(), SPUtils.HOME_TOP_DATA,result);
                }
                parseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("onError==" + ex.getMessage());
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

    public void parseJson(String json) {
        t = (T) new Gson().fromJson(json, clazz);
        sendEventBus();
    }

    private void sendEventBus() {
        EventBus.getDefault().post(t);
    }
}
