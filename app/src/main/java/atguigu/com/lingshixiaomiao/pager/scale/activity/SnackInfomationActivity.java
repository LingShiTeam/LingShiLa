package atguigu.com.lingshixiaomiao.pager.scale.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.scale.utils.Url;

/**
 * 美味详情页面
 */
public class SnackInfomationActivity extends Activity {

    private WebView wv_infomation;

    private ImageView iv_infomation_share;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_infomation);

        wv_infomation = (WebView) findViewById(R.id.wv_infomation);

        iv_infomation_share = (ImageView) findViewById(R.id.iv_infomation_share);

        //设置响应javascript控件
        wv_infomation.getSettings().setJavaScriptEnabled(true);

        //支持从html中弹出对话框
        wv_infomation.setWebChromeClient(new WebChromeClient());

        WebSettings settings = wv_infomation.getSettings();

        //设置编码
        settings.setDefaultTextEncodingName("utf-8");

        /*添加一个对象，让js可以访问该对象的方法，该对象中可以调用js的方法
         注意java调用js时，addJavascripeInterface()是不必须的*/
        

        //获取当前食物的id
        int snack_id = getIntent().getIntExtra("snack_id", 0);


        //加载一个网页
        wv_infomation.loadUrl(Url.INFOMATION_BASE_0 + snack_id + Url.INFOMATION_BASE_1);

        iv_infomation_share.setOnClickListener(new MyOnClickListener());

    }

    private void showShare() {

       /* ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        // 分享时Notification的图标和文字
        // oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        oks.setText(getIntent().getStringExtra("snack_title"));
        oks.setImageUrl(getIntent().getStringExtra("image_url"));
        // 启动分享GUI
        oks.show(this);*/
    }

    /**
     * 点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {//分享按钮
                case R.id.iv_infomation_share:
                    showShare();
                    break;
            }
        }
    }
}
