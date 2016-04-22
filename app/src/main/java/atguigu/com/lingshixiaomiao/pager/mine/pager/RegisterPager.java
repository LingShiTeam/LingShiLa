package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.RegisterBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/19.
 */
public class RegisterPager extends ContentBasePager {
    private WebView wv_webview;
    private Bundle bundle;
    private String url = "";
    private static String phone;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param bundle
     */
    public RegisterPager(Activity mActivity, Bundle bundle) {
        super(mActivity);
        this.bundle = bundle;
            startWebUrl();
            wv_webview.addJavascriptInterface(new Register(), "bridge");
    }

    public class Register {
        @JavascriptInterface
        public void jump(int type, String mobNum) {
            //注册
            LogUtils.loge("type = " + type + ", mobNum = " + mobNum);
            String url = Url.REGISTER_URL_CHECK[0] + mobNum + Url.REGISTER_URL_CHECK[1];
            RegisterPager.phone = mobNum;
            new JsonUtils().loadData(url, RegisterBean.class);
        }

        @JavascriptInterface
        public void jump(int type) {
            //登录
            if (type == 1) {
                LogUtils.loge("type = " + type);
                Intent intent = new Intent(mActivity, MineContentActivity.class);
                intent.putExtra("pager", Constants.LOGIN_PAGER);
                mActivity.startActivity(intent);
                mActivity.finish();
            } else if (type == 6) {
                new JsonUtils().loadData(Url.USERAGREEMENT, RegisterBean.class);
            }
        }

        @JavascriptInterface
        public void register(int code, String pwd, String verifyPwd) {
            String url = Url.REGISTER_URL_CHECK2[0] + RegisterPager.phone + Url.REGISTER_URL_CHECK2[1]
                    + pwd + Url.REGISTER_URL_CHECK2[2] + code + Url.REGISTER_URL_CHECK2[3];
            new JsonUtils().loadData(url, RegisterBean.class);
        }

        @JavascriptInterface
        public void getMobVerify() {

        }
    }


    /**
     * 获取注册返回信息
     *
     * @param registerBean
     */
    @Subscribe
    public void onEventMainThread(RegisterBean registerBean) {
        int rs_code = registerBean.getRs_code();
        int code = registerBean.getData().getCode();
        String rs_msg = registerBean.getRs_msg();
        String url = registerBean.getData().getUrl();
        if (rs_code == 1000 && !TextUtils.isEmpty(url)) {
            loadUserAggrement(url);
        } else if (rs_code == 1000 && code == 0) {
            startRegister2();
        } else if (rs_code == 1000 && code == 1) {
            Toast.makeText(mActivity, "该手机号已经注册", Toast.LENGTH_SHORT).show();
        } else if (rs_code == 1007) {//验证码错误
            Toast.makeText(mActivity, rs_msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadUserAggrement(String url) {
        Intent intent = new Intent(mActivity, MineContentActivity.class);
        intent.putExtra("pager", Constants.WEBVIEW_PAGER);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("title", "用户协议");
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    public void startRegister2() {
        Intent intent = new Intent(mActivity, MineContentActivity.class);
        intent.putExtra("pager", Constants.WEBVIEW_PAGER);
        bundle = new Bundle();
        bundle.putString("url", Url.REGISTER_URL2);
        bundle.putString("title", "设置登录密码");
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
        mActivity.finish();
    }

    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
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
