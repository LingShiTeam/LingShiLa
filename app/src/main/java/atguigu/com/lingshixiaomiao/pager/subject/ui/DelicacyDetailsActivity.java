package atguigu.com.lingshixiaomiao.pager.subject.ui;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubShopBean;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class DelicacyDetailsActivity extends SwipeBackActivity {


    private ImageView iv_back_sub;
    private TextView tv_subject_title;
    private ImageView iv_cart;
    private WebView wv_Delicacy;
    private LinearLayout ll_subject_loading;
    private ImageView subject_loading;
    private ImageView btn_cart;
    private TextView tv_cart_nums;
    private TextView btn_buy_cart;
    private LinearLayout ll_cart;
    private ImageView iv_share;

    /**
     *  点击的首页的地址
     */
    private String shopDeatilUrl;
    /**
     * 商品的实体类
     */
    private SubShopBean subShopBean;
    /**
     * 加载数据时候显示的动画
     */
    private AnimationDrawable loadAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化视图文件
        initView();
        //加载数据
        initData();
        //设置监听
        setListener();
    }

    private void setListener() {
        iv_back_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        wv_Delicacy.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ll_cart.setVisibility(View.VISIBLE);
                ll_subject_loading.setVisibility(View.GONE);
                loadAnim.stop();

            }
        });

    }

    /**
     *
     */
    private void initData() {
        tv_subject_title.setText("美味详情");
        iv_cart.setVisibility(View.GONE);
       iv_share.setVisibility(View.VISIBLE);


        //获取请求数据的地址
        String id = getIntent().getStringExtra("DelicacyDetails");
        shopDeatilUrl = Url.SUBJECT_DELICACY_DETAILS + id;
        //加载时的动画
        startAnimation();
        //获取网络数据
        getDataFormNet();


    }

    /**
     * webView 加载html
     */
    private void setWebViewDataFormNet() {
        if(subShopBean == null) {
            return;
        }
        //加载数据
        Log.d("TAG", "html shop 的地址 " + subShopBean.getData().getUrl());
        WebSettings webSettings = wv_Delicacy.getSettings();
        webSettings.setJavaScriptEnabled(true);

        wv_Delicacy.loadUrl(subShopBean.getData().getUrl());
    }

    /**
     * 加载数据时候显示的动画
     */
    private void startAnimation() {
        ll_cart.setVisibility(View.GONE);
        loadAnim = (AnimationDrawable) subject_loading.getBackground();
        if(loadAnim != null && !loadAnim.isRunning()) {
            loadAnim.start();

        }
    }

    /**
     * 获取shop详情的数据
     */
    private void getDataFormNet() {
        RequestParams params = new RequestParams(shopDeatilUrl);
        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                LogUtils.loge("TAG", "shop details  onSuccess" + result);

                if (result != null) {
                    processJson(result);
                    Log.d("TAG","shop 详情 " +  result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("TAG", "shop details  onError" + isOnCallback);

            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("TAG", "shop details  onCancelled" + cex);

            }

            @Override
            public void onFinished() {
                LogUtils.loge("TAG", "shop details  onFinished");

            }
        });
    }

    /**
     * 解析数据
     * @param json
     */
    private void processJson(String json) {
        String myJson = json.replaceFirst("kinds", "kind");
        subShopBean =  new Gson().fromJson(myJson, SubShopBean.class);
        setWebViewDataFormNet();

        Log.d("TAG","----------------------" +  subShopBean.getData().getUrl());
    }



    private void initView() {
        setContentView(R.layout.activity_delicacy_deatils);
        iv_back_sub = (ImageView)findViewById(R.id.iv_back_sub);
        tv_subject_title = (TextView)findViewById(R.id.tv_subject_title);
        iv_cart = (ImageView)findViewById(R.id.iv_cart);
        wv_Delicacy = (WebView)findViewById(R.id.wv_Delicacy);
        ll_subject_loading = (LinearLayout)findViewById(R.id.ll_subject_loading);
        subject_loading = (ImageView)findViewById(R.id.subject_loading);
        btn_cart = (ImageView)findViewById(R.id.btn_cart);
        tv_cart_nums = (TextView)findViewById(R.id.tv_cart_nums);
        btn_buy_cart = (TextView)findViewById(R.id.btn_buy_cart);
        ll_cart = (LinearLayout)findViewById(R.id.ll_cart);
        iv_share = (ImageView)findViewById(R.id.iv_share);
    }
}
