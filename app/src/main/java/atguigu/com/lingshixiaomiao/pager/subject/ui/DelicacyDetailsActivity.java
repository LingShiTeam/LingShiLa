package atguigu.com.lingshixiaomiao.pager.subject.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Comment;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.subject.bean.CollectStatusBean;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubShopBean;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;
import de.greenrobot.dao.InternalUnitTestDaoAccess;
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
     * 点击的首页的地址
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
    /**
     * 商品的id
     */
    private String shopId;

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

        wv_Delicacy.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ll_cart.setVisibility(View.VISIBLE);
                ll_subject_loading.setVisibility(View.GONE);
                loadAnim.stop();

            }
        });


        btn_cart.setOnClickListener(new MyOnClickListener());
        btn_buy_cart.setOnClickListener(new MyAddCartOnClickListener());

    }


    class MyAddCartOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
           if(!LoginUtils.getInstance().isLogin()) {
               showisLogin();
           }
            LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
            String uid = loginBean.getData().getUid();
            int good_id = subShopBean.getData().getId();
            int kindsId = subShopBean.getData().getKinds().get(0).getId();
            int num = 1;


            String cartUrl = Url.SUBJECT_ADDCART_START + uid + Url.SUBJECT_ADDCART_MIDDLE1 + good_id +
                    Url.SUBJECT_ADDCART_MIDDLE2 + kindsId + num;

            //上传数据
            getAddCartDataFormNet(cartUrl);

        }
    }

    /**
     * 上传购买数据
     * @param cartUrl
     */
    private void getAddCartDataFormNet(String cartUrl) {
        RequestParams params = new RequestParams(cartUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(DelicacyDetailsActivity.this, "已经添加到购物车", Toast.LENGTH_SHORT).show();
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

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //进入购物车页面
            Intent intent = new Intent(DelicacyDetailsActivity.this, MineContentActivity.class);
            intent.putExtra("pager", Constants.MINE_CART_PAGER);
            startActivity(intent);


        }
    }

    /**
     *
     */
    private void initData() {

        if (LoginUtils.getInstance().isLogin()) {
            LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
            String uid = loginBean.getData().getUid();

            //用户登录状态下的地址
            shopDeatilUrl = Url.SUBJECT_DELICACY_DETAILS_START + uid + Url.SUBJECT_DELICACY_DETAILS_END + shopId;
        } else {
            //未登录时或者是其他状态时候的地址
            shopDeatilUrl = Url.SUBJECT_DELICACY_DETAILS_START + 0 + Url.SUBJECT_DELICACY_DETAILS_END + shopId;
        }

        new JsonUtils().loadData(shopDeatilUrl, SubShopBean.class);
        Log.d("TAG", "shopDeatilUrl" + shopDeatilUrl);
    }


    @Subscribe
    public void onEventMainThread(SubShopBean subShopBean) {
        this.subShopBean = subShopBean;
        //获取网页的数据
        setWebViewDataFormNet();
    }

    /**
     * webView 加载html
     */
    private void setWebViewDataFormNet() {
        if (subShopBean == null) {
            return;
        }
        wv_Delicacy.loadUrl(subShopBean.getData().getUrl());
    }

    /**
     * 加载数据时候显示的动画
     */
    private void startAnimation() {
        ll_cart.setVisibility(View.GONE);
        loadAnim = (AnimationDrawable) subject_loading.getBackground();
        if (loadAnim != null && !loadAnim.isRunning()) {
            loadAnim.start();
        }
    }


    private void initView() {
        setContentView(R.layout.activity_delicacy_deatils);
        iv_back_sub = (ImageView) findViewById(R.id.iv_back_sub);
        tv_subject_title = (TextView) findViewById(R.id.tv_subject_title);
        iv_cart = (ImageView) findViewById(R.id.iv_cart);
        wv_Delicacy = (WebView) findViewById(R.id.wv_Delicacy);
        ll_subject_loading = (LinearLayout) findViewById(R.id.ll_subject_loading);
        subject_loading = (ImageView) findViewById(R.id.subject_loading);
        btn_cart = (ImageView) findViewById(R.id.btn_cart);
        tv_cart_nums = (TextView) findViewById(R.id.tv_cart_nums);
        btn_buy_cart = (TextView) findViewById(R.id.btn_buy_cart);
        ll_cart = (LinearLayout) findViewById(R.id.ll_cart);
        iv_share = (ImageView) findViewById(R.id.iv_share);

        tv_subject_title.setText("美味详情");
        iv_cart.setVisibility(View.GONE);
        iv_share.setVisibility(View.VISIBLE);
        //开始加载动画
        startAnimation();
        //注册EventBus
        registerEventBus();

        //获取设置模式
        WebSettings settings = wv_Delicacy.getSettings();
        //设置编码的方式
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptEnabled(true);
        /**
         * 添加一个js的交互接口 方便html 的布局文件中的javaScript代码可以和后台的java进行交互
         * ""为该对象的别名 对应html中的一个从他成天
         */
        wv_Delicacy.addJavascriptInterface(getHtmlObject(), "bridge");

        //商品的编号id
        shopId = getIntent().getStringExtra("DelicacyDetails");
    }

    /**
     * 获取js调用剪口的对象
     *
     * @return
     */
    private Object getHtmlObject() {
        Object insertObj = new Object() {

            @JavascriptInterface
            public void clickCollect() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!LoginUtils.getInstance().isLogin()) {
                            //显示登录的消息
                            showisLogin();
                            return;
                        }

                        //获取登录状态
                        int collect_status = subShopBean.getData().getCollect_status();
                        Log.d("DelicacyDetailsActivity", "这个的登录状态");
                        //联网传递数据
                        getDataCollectFormNet(collect_status);

                    }
                });
            }

            /**
             * 复制到剪切板
             */
            @JavascriptInterface
            public void clickCopyLink(final String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        cm.setText(str);
                        Toast.makeText(DelicacyDetailsActivity.this, "已复制到剪切板", Toast.LENGTH_SHORT).show();

                    }
                });
            }


            /**
             * 更多的评论
             * @param url
             * @param total
             */
            @JavascriptInterface
            public void clickMoreComment(final String url, final int total) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent = new Intent(DelicacyDetailsActivity.this, AllCmmentActivity.class);
                        intent.putExtra("comm_url", url);
                        intent.putExtra("comm_count", total);
                        startActivity(intent);
                    }
                });
            }

            /**
             * 猜你喜欢
             */
            @JavascriptInterface
            public void clickGuessLike(int id) {

                Intent intent = new Intent(DelicacyDetailsActivity.this, DelicacyDetailsActivity.class);
                intent.putExtra("DelicacyDetails", id + "");
                startActivity(intent);

                finish();
            }
        };

        return insertObj;
    }

    /**
     * @param collect_status
     */
    private void getDataCollectFormNet(final int collect_status) {

        String collStaUrl = null;
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String uid = loginBean.getData().getUid();


        if (0 == collect_status) {//没有收藏 可以收藏
            collStaUrl = Url.SUBJECT_COLLECTOR_STATRT + uid + Url.SUBJECT_COLLECTOR_MIDDLE +
                    shopId + Url.SUBJECT_COLLECTOR_END + 0;
        } else {//已经收藏 可以取消收藏
            collStaUrl = Url.SUBJECT_COLLECTOR_STATRT + uid + Url.SUBJECT_COLLECTOR_MIDDLE +
                    shopId + Url.SUBJECT_COLLECTOR_END + 1;
        }

        RequestParams params = new RequestParams(collStaUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processCollectData(result, collect_status);

                Log.d("TAG", "网络请求成功 已经收藏");

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
     * 解析收藏时候即系
     *
     * @param json
     * @param collect_status
     */
    private void processCollectData(String json, int collect_status) {
        CollectStatusBean collectStatusBean = new Gson().fromJson(json, CollectStatusBean.class);

        // if ("2019".equals(collectStatusBean.getRs_code())) {
        if (0 == collect_status) {
            wv_Delicacy.loadUrl("javascript: changeCollect('true')");
            subShopBean.getData().setCollect_status(1);
            Toast.makeText(this, "好吃的到碗里了哦", Toast.LENGTH_SHORT).show();
        } else {
            wv_Delicacy.loadUrl("javascript: changeCollect('false')");
            subShopBean.getData().setCollect_status(0);
            Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
        }

        // } else {
        //   Toast.makeText(this, "收藏未成功", Toast.LENGTH_SHORT).show();
        // }


    }

    /**
     * 显示登录状态
     */
    private void showisLogin() {
        //获取系统中本机的高度和宽度
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        //把layout 变换成View
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout loginLayout = (LinearLayout) inflater.inflate(R.layout.dialog_login, null);
        //定义一个dialog
        final Dialog loginDialog = new AlertDialog.Builder(DelicacyDetailsActivity.this).create();
        loginDialog.show();

        loginDialog.getWindow().setContentView(loginLayout);
        loginDialog.getWindow().setLayout((int) (width * 0.7), (int) (height * 0.2));

        //取消按钮
        Button buttonCacel = (Button) loginLayout.findViewById(R.id.dialog_login_cacel);
        buttonCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.dismiss();
            }
        });


        Button buttonLogin = (Button) loginLayout.findViewById(R.id.dialog_login_cacel);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.dismiss();

                //进入登陆界面
                Intent intent = new Intent(DelicacyDetailsActivity.this, MineContentActivity.class);
                intent.putExtra("pager", Constants.LOGIN_PAGER);
                startActivityForResult(intent, 1);
            }
        });

    }


    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 反注册
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterEventBus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //重新加载
        initData();
    }
}
