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
public class WebPager extends ContentBasePager {

    private WebView wv_webview;
    private Bundle bundle;
    private String url = "";

    /**
     * 构造方法
     *
     * @param mActivity
     * @param bundle
     */
    public WebPager(Activity mActivity, Bundle bundle, boolean isPush) {
        super(mActivity);
        this.bundle = bundle;
        if (isPush) {
            startPushUrl();
        } else {
            startWebUrl();
        }
    }

    /**
     * 普通Web
     */
    private void startWebUrl() {
        url = bundle.getString("url");
        title = bundle.getString("title");
        startUrl();
    }

    /**
     * Push推送
     */
    private void startPushUrl() {
        LogUtils.loge("push 开始加载");
        LogUtils.loge(bundle.toString());

        String urlJson = bundle.getString(JPushInterface.EXTRA_EXTRA);
        title = bundle.getString(JPushInterface.EXTRA_TITLE);
        try {
            JSONObject jsonObject = new JSONObject(urlJson);
            url = jsonObject.getString("url");
            LogUtils.loge("url = " + url);
            startUrl();
            LogUtils.loge("push 加载完成");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void startUrl() {
        LogUtils.loge("title = " + title);
        if (url != null) {
            wv_webview.loadUrl(url);
        }
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_webview, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        wv_webview = (WebView) v.findViewById(R.id.wv_webview);
        initWebView();
    }

    /**
     * 初始化WebView
     */
    private void initWebView() {
        WebSettings settings = wv_webview.getSettings();
        settings.setJavaScriptEnabled(true);//允许javascript脚本执行
        wv_webview.setWebChromeClient(new WebChromeClient());//允许弹出窗口

        wv_webview.setOnKeyListener(new View.OnKeyListener() {//判断如果允许返回上一页,则返回上一页
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && wv_webview.canGoBack()) {
                    wv_webview.goBack();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public String setTitle() {
        return title;
    }

    @Override
    public void initData() {
        super.initData();
    }

}