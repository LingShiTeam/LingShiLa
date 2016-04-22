package atguigu.com.lingshixiaomiao.pager.scale.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import atguigu.com.lingshixiaomiao.R;

public class CommentActivity extends Activity {

    private ImageView iv_comment_back;
    private WebView wv_comment;
    private TextView tv_comment_title;

    private String comment_url;
    private int comment_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        iv_comment_back = (ImageView) findViewById(R.id.iv_comment_back);
        wv_comment = (WebView) findViewById(R.id.wv_comment);
        tv_comment_title = (TextView) findViewById(R.id.tv_comment_title);

        iv_comment_back.setOnClickListener(new MyOnClickListener());

        comment_url = getIntent().getStringExtra("comment_url");
        comment_count = getIntent().getIntExtra("comment_count", 0);

        //设置title文本
        tv_comment_title.setText("喵亲口碑(" + comment_count + ")");

        //设置响应javascript控件
        wv_comment.getSettings().setJavaScriptEnabled(true);

        //支持从html中弹出对话框
        wv_comment.setWebChromeClient(new WebChromeClient());

        WebSettings settings = wv_comment.getSettings();

        //设置编码
        settings.setDefaultTextEncodingName("utf-8");

        //加载一个网页
        Log.e("TAG", "comment_url:" + comment_url);
        wv_comment.loadUrl(comment_url);

    }

    /**
     * 点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.iv_comment_back:
                    finish();
                    break;
            }
        }
    }
}
