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

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.scale.bean.ScallToCarBean;
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
    private int snack_id;


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
        snack_id = getIntent().getIntExtra("snack_id", 0);
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

    /**
     * 获取js调用java的接口对象
     *
     * @return
     */
    private Object getHtmlObject() {

        Object incertObj = new Object() {

            /**
             *收藏
             */
            @JavascriptInterface
            public void clickCollect() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //如果未登录
                        if (!LoginUtils.getInstance().isLogin()) {
                            Toast.makeText(SnackInfomationActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (!isCollect) {

                            //收藏
                            collect();

                        } else {

                            //取消收藏
                            cancellCollect();
                        }
                    }
                });
            }

            /**
             * 复制到剪切板
             * @param str
             */
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

            /**
             * 更多评论
             * @param url
             * @param total
             */
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

            /**
             * 猜你喜欢
             * @param id
             */
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

    /**
     * 收藏
     */
    private void collect() {

        //获取用户id
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String uid = loginBean.getData().getUid();

        //得到请求地址
        String collectUrl = Url.COLLECT_IN_0 + uid + Url.COLLECT_IN_1 + snack_id + Url.COLLECT_IN_2;

        //发送携带数据的get请求
        RequestParams requestParams = new RequestParams(collectUrl);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                //收藏图标变红
                wv_infomation.loadUrl("javascript: changeCollect('true')");

                isCollect = true;

                Toast.makeText(SnackInfomationActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
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

    /**
     * 取消收藏
     */
    private void cancellCollect() {

        //获取用户id
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String uid = loginBean.getData().getUid();

        //得到请求地址
        String collectUrl = Url.COLLECT_OUT_0 + uid + Url.COLLECT_OUT_1 + snack_id + Url.COLLECT_OUT_2;

        //发送携带数据的get请求
        RequestParams requestParams = new RequestParams(collectUrl);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                //收藏图标变白
                wv_infomation.loadUrl("javascript: changeCollect('false')");

                isCollect = false;

                Toast.makeText(SnackInfomationActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
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

    private void initView() {

        wv_infomation = (WebView) findViewById(R.id.wv_infomation);
        iv_infomation_share = (ImageView) findViewById(R.id.iv_infomation_share);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        head_right = (RelativeLayout) findViewById(R.id.head_right);
        home_car_number = (TextView) findViewById(R.id.home_car_number);
        goods_detail_addtocart = (TextView) findViewById(R.id.goods_detail_addtocart);

        head_right.setOnClickListener(new MyOnClickListener());
        goods_detail_addtocart.setOnClickListener(new MyOnClickListener());
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

    /**
     * 点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.head_right:

                    Intent intent = new Intent(SnackInfomationActivity.this, ShoppingCarActivity.class);
                    startActivity(intent);
                    break;

                case R.id.goods_detail_addtocart://添加到购物车

                    String uploadDataForDetail = getIntent().getStringExtra("uploadDataForDetail");

                    Log.e("TAG", "=================uploadDataForDetail: " + uploadDataForDetail);

                    //如果未登录
                    if (!LoginUtils.getInstance().isLogin()) {

                        Toast.makeText(getApplicationContext(), "请登录", Toast.LENGTH_SHORT).show();

                        return;
                    }

                    //获取用户id
                    LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
                    String uid = loginBean.getData().getUid();

                    uploadGotoCarData(uploadDataForDetail, uid);
                    break;
            }
        }
    }

    /**
     * 上传购物数据到服务器
     *
     * @param result
     * @param uid
     */
    private void uploadGotoCarData(String result, String uid) {

        Log.e("TAG", "=====================uploadGotoCarData: " + result);

        ScallToCarBean scallToCarBean = new Gson().fromJson(result, ScallToCarBean.class);

        Log.e("TAG", "======================scallToCarBean: " + scallToCarBean);

        Log.e("TAG", "==========scallToCarBean.getData().getKindss(): " + scallToCarBean.getData().getKindss());

        int kind_id = scallToCarBean.getData().getKindss().get(0).getId();

        int subkind_id = scallToCarBean.getData().getKindss().get(0).getKinds().get(0).getId();

        String url_0 = Url.GOTOCARR_0 + uid + Url.GOTOCARR_1 + snack_id + Url.GOTOCARR_2 + kind_id + Url.GOTOCARR_3 + subkind_id + Url.GOTOCARR_4;

        Log.e("TAG", "url_0==============" + url_0);

        RequestParams requestParamss = new RequestParams(url_0);

        x.http().get(requestParamss, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.e("TAG", "onSuccess()================:" + result);

                Toast.makeText(getApplicationContext(), "添加购物车成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError()================");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled()================");
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished()================");
            }
        });
    }
}
