package atguigu.com.lingshixiaomiao.pager.mine.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 使用规则说明页面
 */
public class CouponUseActivity extends SwipeBackActivity implements View.OnClickListener {

    private WebView wv_coupon_use;
    private ImageButton ib_mine_back;
    private RelativeLayout loading_dialog;
    private ImageView widget_loading_pb;
    private AnimationDrawable ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_use);

        initView();

        // 侧滑退出activity
        setSwipeBack();
    }

    private void initView() {
        wv_coupon_use = (WebView) findViewById(R.id.wv_coupon_use);
        ib_mine_back = (ImageButton) findViewById(R.id.ib_mine_back);
        loading_dialog = (RelativeLayout) findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) findViewById(R.id.widget_loading_pb);
        ad = (AnimationDrawable) widget_loading_pb.getBackground();

        ib_mine_back.setOnClickListener(this);

        //设置响应javascript控件
        wv_coupon_use.getSettings().setJavaScriptEnabled(true);
        //支持从html中弹出对话框
        wv_coupon_use.setWebChromeClient(new WebChromeClient());
        WebSettings settings = wv_coupon_use.getSettings();
        //设置编码
        settings.setDefaultTextEncodingName("utf-8");

        wv_coupon_use.loadUrl(Url.COUPON_USE_URL);
        wv_coupon_use.setWebViewClient(new MyWebViewClient());
    }

    /**
     * 加载监听
     */
    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            startLoading();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            stopLoading();
        }
    }

    /**
     * 开始加载
     */
    private void startLoading() {
        ad.start();
        loading_dialog.setVisibility(View.VISIBLE);
        wv_coupon_use.setVisibility(View.GONE);
    }

    /**
     * 停止加载
     */
    private void stopLoading() {
        ad.stop();
        loading_dialog.setVisibility(View.GONE);
        wv_coupon_use.setVisibility(View.VISIBLE);
    }

    /**
     * 侧滑退出activity
     */
    private void setSwipeBack() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_mine_back:
                finish();
                break;
        }
    }
}
