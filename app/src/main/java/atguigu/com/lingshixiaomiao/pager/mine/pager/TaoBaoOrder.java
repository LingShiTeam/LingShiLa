package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by Liu_haiwei on 2016/4/21.
 * 我的淘宝页面
 */
public class TaoBaoOrder extends ContentBasePager {

    private WebView wv_taobao_order;
    private FrameLayout loading_dialog;

    public TaoBaoOrder(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_taobao_order, null);
        findViewById(inflate);
        initWebView();
        return inflate;
    }

    private void initWebView() {
        //设置响应javascript控件
        wv_taobao_order.getSettings().setJavaScriptEnabled(true);
        //支持从html中弹出对话框
        wv_taobao_order.setWebChromeClient(new WebChromeClient());
        WebSettings settings = wv_taobao_order.getSettings();
        //设置编码
        settings.setDefaultTextEncodingName("utf-8");

        wv_taobao_order.loadUrl(Url.TAOBAO_ORDER_URL);

        wv_taobao_order.setWebViewClient(new MyWebViewClient());
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            loading_dialog.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loading_dialog.setVisibility(View.GONE);
        }
    }

    private void findViewById(View inflate) {
        wv_taobao_order = (WebView) inflate.findViewById(R.id.wv_taobao_order);
        loading_dialog = (FrameLayout) inflate.findViewById(R.id.loading_dialog);
    }

    @Override
    public String setTitle() {
        return "淘宝订单";
    }
}
