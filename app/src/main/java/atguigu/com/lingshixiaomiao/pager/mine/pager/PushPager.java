package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by lanmang on 2016/4/10.
 */
public class PushPager extends ContentBasePager {

    private WebView wv_push;
    private Bundle bundle;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param bundle
     */
    public PushPager(Activity mActivity, Bundle bundle) {
        super(mActivity);
        this.bundle = bundle;
        startUrl();
    }

    private void startUrl() {
        LogUtils.loge("push 开始加载");
        LogUtils.loge(bundle.toString());

        String urlJson = bundle.getString(JPushInterface.EXTRA_EXTRA);
        try {
            JSONObject jsonObject = new JSONObject(urlJson);
            String url = jsonObject.getString("url");
            LogUtils.loge("url = " + url);
            if (url != null) {
                wv_push.loadUrl(url);
            }
            LogUtils.loge("push 加载完成");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_push, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        wv_push = (WebView) v.findViewById(R.id.wv_push);
        initWebView();
    }

    /**
     * 初始化WebView
     */
    private void initWebView() {
        WebSettings settings = wv_push.getSettings();
        settings.setJavaScriptEnabled(true);//允许javascript脚本执行
        wv_push.setWebChromeClient(new WebChromeClient());//允许弹出窗口

        wv_push.setOnKeyListener(new View.OnKeyListener() {//判断如果允许返回上一页,则返回上一页
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && wv_push.canGoBack()) {
                    wv_push.goBack();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public String setTitle() {
        return "推送消息";
    }

    @Override
    public void initData() {
        super.initData();
    }

}
