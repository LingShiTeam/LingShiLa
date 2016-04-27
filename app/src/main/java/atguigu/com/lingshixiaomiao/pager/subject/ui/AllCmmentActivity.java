package atguigu.com.lingshixiaomiao.pager.subject.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import atguigu.com.lingshixiaomiao.R;

public class AllCmmentActivity extends Activity {

    private ImageView iv_search_back;
    private ImageButton ib_left_menu;
    private EditText et_search;
    private TextView tv_shopname;
    private RelativeLayout rl_cart;

    private WebView wv_comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        initData();
    }


    private void initData() {

        String comm_url = getIntent().getStringExtra("comm_url");
        int comm_count = getIntent().getIntExtra("comm_count", 0);

        tv_shopname.setText("喵亲口碑" + comm_count);

        WebSettings settings = wv_comment.getSettings();
        settings.setJavaScriptEnabled(true);
        wv_comment.setWebChromeClient(new WebChromeClient());

        //设置编码
        settings.setDefaultTextEncodingName("utf-8");

        wv_comment.loadUrl(comm_url);

        iv_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        setContentView(R.layout.activity_all_cmment);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        ib_left_menu = (ImageButton) findViewById(R.id.ib_left_menu);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_shopname = (TextView) findViewById(R.id.tv_shopname);
        rl_cart = (RelativeLayout) findViewById(R.id.rl_cart);
        wv_comment = (WebView)findViewById(R.id.wv_comment);
        iv_search_back.setVisibility(View.VISIBLE);
        ib_left_menu.setVisibility(View.GONE);
        et_search.setVisibility(View.GONE);
        tv_shopname.setVisibility(View.VISIBLE);

        rl_cart.setVisibility(View.GONE);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
