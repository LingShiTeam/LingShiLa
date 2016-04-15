package atguigu.com.lingshixiaomiao.pager.scale;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.scale.utils.Url;

/**
 * 美味详情页面
 */
public class SnackInfomationActivity extends Activity {

    private WebView wv_infomation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_infomation);

        wv_infomation = (WebView) findViewById(R.id.wv_infomation);

        //设置响应javascript控件
        wv_infomation.getSettings().setJavaScriptEnabled(true);

        //获取当前食物的id
        int snack_id = getIntent().getIntExtra("snack_id", 0);

        //加载一个网页
        wv_infomation.loadUrl(Url.INFOMATION_BASE_0 + snack_id + Url.INFOMATION_BASE_1);

    }
}
