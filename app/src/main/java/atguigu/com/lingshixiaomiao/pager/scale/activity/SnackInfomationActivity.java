package atguigu.com.lingshixiaomiao.pager.scale.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.scale.utils.Url;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 美味详情页面
 */
public class SnackInfomationActivity extends Activity implements View.OnClickListener {

    private WebView wv_infomation;

    private ImageView iv_infomation_share;
    private ImageView iv_back;
    private RelativeLayout head_right;
    private TextView home_car_number;
    private TextView goods_detail_addtocart;
    private boolean isCollect = false;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_infomation);

        initView();
        // 掉用HTML的初始化
        initHTML();

        setListener();

    }

    private void initHTML() {
        //设置响应javascript控件
        wv_infomation.getSettings().setJavaScriptEnabled(true);

        //支持从html中弹出对话框
        wv_infomation.setWebChromeClient(new WebChromeClient());
        WebSettings settings = wv_infomation.getSettings();
        //设置编码
        settings.setDefaultTextEncodingName("utf-8");

        /*添加一个对象，让js可以访问该对象的方法，该对象中可以调用js的方法
         注意java调用js时，addJavascripeInterface()是不必须的*/
        wv_infomation.addJavascriptInterface(getHtmlObject(), "bridge");
        //获取当前食物的id
        int snack_id = getIntent().getIntExtra("snack_id", 0);
        //加载一个网页
        wv_infomation.loadUrl(Url.INFOMATION_BASE_0 + snack_id + Url.INFOMATION_BASE_1);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        // 分享
        iv_infomation_share.setOnClickListener(this);
        // 返回
        iv_back.setOnClickListener(this);
    }

    private Object getHtmlObject() {

        Object incertObj = new Object() {

            //js调用该方法
            @JavascriptInterface
            public void clickCollect() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isCollect) {
                            //收藏图标变红
                            wv_infomation.loadUrl("javascript: changeCollect('true')");
                            isCollect = true;
                            Toast.makeText(SnackInfomationActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        } else {
                            //收藏图标变白
                            wv_infomation.loadUrl("javascript: changeCollect('false')");
                            isCollect = false;
                            Toast.makeText(SnackInfomationActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @JavascriptInterface
            public void clickCopyLink(final String str) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(str);
                        Toast.makeText(SnackInfomationActivity.this, "已复制到剪贴板", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @JavascriptInterface
            public void clickMoreComment(final String url, final int total) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent = new Intent(SnackInfomationActivity.this, CommentActivity.class);
                        intent.putExtra("comment_url", url);
                        Log.e("TAG", "infomation comment_url:" + url);
                        intent.putExtra("comment_count", total);
                        startActivity(intent);
                        Toast.makeText(SnackInfomationActivity.this, "查看更多评论", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @JavascriptInterface
            public void clickGuessLike(int id) {

                Intent intent = new Intent(SnackInfomationActivity.this, SnackInfomationActivity.class);
                intent.putExtra("like_id", id);
                startActivity(intent);

                finish();

            }
        };

        return incertObj;
    }

    private void initView() {
        wv_infomation = (WebView) findViewById(R.id.wv_infomation);
        iv_infomation_share = (ImageView) findViewById(R.id.iv_infomation_share);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        head_right = (RelativeLayout) findViewById(R.id.head_right);
        home_car_number = (TextView) findViewById(R.id.home_car_number);
        goods_detail_addtocart = (TextView) findViewById(R.id.goods_detail_addtocart);
    }

    private void showShare() {

        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        // 分享时Notification的图标和文字
        // oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        oks.setText(getIntent().getStringExtra("snack_title"));
        oks.setImageUrl(getIntent().getStringExtra("image_url"));
        // 启动分享GUI
        oks.show(this);
    }

    /**
     * 点击监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_infomation_share:
                showShare();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
