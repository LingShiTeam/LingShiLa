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

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SubjectDetailsActivity extends SwipeBackActivity {

    private ImageView iv_back_sub;
    private TextView tv_subject_title;
    private ImageView iv_cart;

    private WebView wv_sub_details;
    private LinearLayout ll_subject_loading;
    private ImageView subject_loading;

    /**
     * 加载动画
     */
    private AnimationDrawable loadAnim;

    /**
     * 专题详情请求的地址
     */
    private String subDetailsUrl;
    /**
     * 需要请求数据的id
     */
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        //c初始化布局
        initView();
        //获取数据
        initData();
        //设置监听
        setListener();
    }

    /**
     * 设置各种监听
     */
    private void setListener() {
        iv_back_sub.setOnClickListener(new MyOnClickListener());

        wv_sub_details.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                loadAnim.stop();
                ll_subject_loading.setVisibility(View.GONE);

            }
        });

    }


    class MyOnClickListener implements View.OnClickListener {
        //返回专题页面
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    /**
     * 获取数据
     */
    private void initData() {
        tv_subject_title.setText("专题详情");
        id = getIntent().getStringExtra("detailsUrl");
        loadAnim = (AnimationDrawable) subject_loading.getBackground();
        if(loadAnim != null && !loadAnim.isRunning()) {
            loadAnim.start();
        }

        subDetailsUrl = Url.SUBJECT_DETAILS + id + "&cid=10002&uid=0&tms=20150721190147&sig=8c35f5a024148111&wssig=308efe4382a088e0&os_type=3&version=12";
        Log.d("TAG", "webView 的地址啊" + subDetailsUrl);
        Log.d( "TAG","webView 的id " + id);
        //webView 加载数据
        wv_sub_details.loadUrl(subDetailsUrl);
        //获取设置模式
        WebSettings webSettings = wv_sub_details.getSettings();
        webSettings.setJavaScriptEnabled(true);//调用js
        //webSettings.setBuiltInZoomControls(true);//当前页面是可以缩放的时候
       // webSettings.setUseWideViewPort(true);//当前页面乐意加载的时候 如果可以缩放页面,可以双击进行缩放


    }

    /**
     * 初始化布局文件
     */
    private void initView() {
        iv_back_sub = (ImageView) findViewById(R.id.iv_back_sub);
        tv_subject_title = (TextView) findViewById(R.id.tv_subject_title);
        iv_cart = (ImageView) findViewById(R.id.iv_cart);

        wv_sub_details = (WebView) findViewById(R.id.wv_sub_details);
        ll_subject_loading = (LinearLayout) findViewById(R.id.ll_subject_loading);
        subject_loading = (ImageView)findViewById(R.id.subject_loading);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        wv_sub_details.clearHistory();
    }


}
