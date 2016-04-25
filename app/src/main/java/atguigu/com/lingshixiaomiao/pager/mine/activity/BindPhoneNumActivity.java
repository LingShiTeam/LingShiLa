package atguigu.com.lingshixiaomiao.pager.mine.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.SinaBindNumResBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

public class BindPhoneNumActivity extends Activity {

    private WebView wv_bind_phone_num;
    private LoginBean loginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone_num);

        initData();
        initView();
        initHTML();
    }

    private void initData() {
        registerEventBus();
        loginBean = (LoginBean) getIntent().getExtras().getSerializable("SinaWeiBo");
        Log.e("TAG", "SinaWeiBo接收数据成功");
    }

    private void initHTML() {
        //设置响应javascript控件
        wv_bind_phone_num.getSettings().setJavaScriptEnabled(true);

        //支持从html中弹出对话框
        wv_bind_phone_num.setWebChromeClient(new WebChromeClient());
        WebSettings settings = wv_bind_phone_num.getSettings();
        //设置编码
        settings.setDefaultTextEncodingName("utf-8");

        /*添加一个对象，让js可以访问该对象的方法，该对象中可以调用js的方法
         注意java调用js时，addJavascripeInterface()是不必须的*/
        wv_bind_phone_num.addJavascriptInterface(getHtmlObject(), "bridge");
        wv_bind_phone_num.loadUrl(Url.BIND_PHONE_NUM_URL);
    }


    private String telNum;

    private Object getHtmlObject() {
        Object incertObj = new Object() {

            @JavascriptInterface
            public void jump(int i) {
                Log.e("TAG", "11111111111");
            }

            @JavascriptInterface
            public void setPwd(int i, String tel, String login_code, String pwd) {

                String bindUrl = Url.BIND_URL[0] + loginBean.getData().getUid() + Url.BIND_URL[1] + tel + Url.BIND_URL[2] + pwd + Url.BIND_URL[3] + login_code + Url.BIND_URL[4];
                LogUtils.loge("TAG", "bindUrl = " + bindUrl);

                new JsonUtils().loadData(bindUrl, SinaBindNumResBean.class);

            }

            @JavascriptInterface
            public void getMobVerify(String tel) {
                telNum = tel;
                String url = Url.CHECK_SMS[0] + tel + Url.CHECK_SMS[1];
                x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        LogUtils.loge("onlyRequest = " + result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }

        };
        return incertObj;
    }

    /**
     * 接收解析成功后的数据(绑定手机号码)
     *
     * @param sinaBindNumResBean
     */
    @Subscribe
    public void onEventMainThread(SinaBindNumResBean sinaBindNumResBean) {
        LogUtils.loge("TAG", "绑定手机号数据解析成功");
        if ("1000".equals(sinaBindNumResBean.getRs_code())) {
            String weibo_info_url = Url.WEIBO_INFO[0] + loginBean.getData().getUid() + Url.WEIBO_INFO[1] + Url.WEIBO_INFO[2];
            // 再次发送消息
            new JsonUtils().loadData(weibo_info_url, LoginBean.class);

        } else {
            new AlertDialog.Builder(this)
                    .setMessage("验证码错误或该手机号已绑定!")
                    .show();
        }
    }


    private void initView() {
        wv_bind_phone_num = (WebView) findViewById(R.id.wv_bind_phone_num);
    }

    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    /**
     * 解注册EventBus
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterEventBus();
    }
}
