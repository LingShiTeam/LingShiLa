package atguigu.com.lingshixiaomiao.pager.subject.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CartUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.StringUtils;
import atguigu.com.lingshixiaomiao.pager.subject.bean.CollectStatusBean;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubDetailsBean;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SubjectDetailsActivity extends SwipeBackActivity {

    private WebView wv_sub_details;
    private LinearLayout ll_subject_loading;
    private ImageView subject_loading;

    /**
     * 加载动画
     */
    private AnimationDrawable loadAnim;

    /**
     * 专题详情请数据地址
     */
    private String subDetailsUrl;

    /**
     * 需要请求数据的id 商品的Id
     */
    private String shopId;
    /**
     * 专题详情页面的尸体类
     */
    private SubDetailsBean subDetailsBean;

    /**
     * 确定是否收藏
     */
    private boolean isCollect = false;
    /**
     * 登录账号的实体类
     */
    private LoginBean loginBean;
    /**
     * 登录账号的id
     */
    private String uid;
    private ImageButton ib_left_menu;
    private ImageView iv_search_back;
    private EditText et_search;
    private TextView tv_shopname;
    private RelativeLayout rl_cart;
    private TextView tv_shopcount;


    /**
     * 商品是否被收藏
     */
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
        wv_sub_details.setWebViewClient(new WebViewClient() {

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
            switch (v.getId()) {
                case R.id.iv_search_back:
                    finish();
                    break;
            }

        }
    }

    /**
     * 获取数据
     */
    private void initData() {
        if (LoginUtils.getInstance().isLogin()) {//当判断为登录状态时候 需要根据uid 来请求数据
            loginBean = (LoginBean) LoginUtils.getInstance().getData();
            uid = loginBean.getData().getUid();
            subDetailsUrl = Url.SUBJECT_DETAILS + uid + Url.SUBJECT_DETAILS_END + shopId;
        } else {//当前为未登录状态时 不使用uid 登录
            subDetailsUrl = Url.SUBJECT_DETAILS + 0 + Url.SUBJECT_DETAILS_END + shopId;
        }
        //联网请求数据
        new JsonUtils().loadData(subDetailsUrl, SubDetailsBean.class);
        Log.d("TAG", "sub 收藏的数据走这里");

    }

    @Subscribe
    public void onEventMainThread(SubDetailsBean subDetailsBean) {
        this.subDetailsBean = subDetailsBean;
        // Log.d("TAG", "subject 的单个商品的尸体类 ");
        getWebDataFromNet();
    }

    /**
     * 联网获取webview 中的数据
     */
    @SuppressLint("JavascriptInterface")
    private void getWebDataFromNet() {
        Log.d("TAG", subDetailsBean.getData().getTitle());

        if (subDetailsBean == null) {
            return;
        }
        wv_sub_details.loadUrl(subDetailsBean.getData().getWeb_url());
        int collect_status = subDetailsBean.getData().getCollect_status();
        Log.d("TAG", "collect_status:" + collect_status);

    }


    /**
     * 获取js调用java的接口对象
     *
     * @return
     */
    private Object getHtmlObject() {

        Object insertObj = new Object() {
            /**
             * 收藏
             */
            @JavascriptInterface
            public void clickCollect() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //如果未登录
                        if (!LoginUtils.getInstance().isLogin()) {
                            Toast.makeText(SubjectDetailsActivity.this, "请登录哦", Toast.LENGTH_SHORT).show();
                            //进入登录的选择界面
                            showisLogin();
                            return;
                        }
                        //获取登录状态
                        int collect_status = subDetailsBean.getData().getCollect_status();
                        //联网传递数据
                        getDataCollectFormNet(collect_status);
                    }
                });
            }

            /**
             * 分享 点击事件
             */
            @JavascriptInterface
            public void clickShare(){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //调用分享的dialog
                        //  ShareUtils.showShare(SubjectDetailsActivity.this);
                        //改变分享的增加
                        changeShareNum();
                    }
                });
            }

            /**
             * 详情页面的itmes 的点击实事件
             * @param id
             */
            @JavascriptInterface
            public void clickItem(final int id){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //携带一个商品的的id 传递到DdetailsActivity
                        Intent intent = new Intent(SubjectDetailsActivity.this,DelicacyDetailsActivity.class);
                        intent.putExtra("DelicacyDetails",id + "");
                        startActivity(intent);
                    }
                });
            }


            /**
             * 添加到购物车
             */
            @JavascriptInterface
            public void addToCartOnHtml(int id,int king_id,String subKinds,int good_id,int num){

                Log.d("TAG", "id:" + id);
                Log.d("TAG", "king_id:" + king_id);
                Log.d("TAG", subKinds);
                Log.d("TAG", "good_id:" + good_id);
                Log.d("TAG", "num:" + num);

                String addCartUrl = Url.SUBJECT_ADDCART_START + uid + Url.SUBJECT_ADDCART_MIDDLE1 +
                        id + Url.SUBJECT_ADDCART_MIDDLE2 + king_id + Url.SUBJECT_ADDCART_MIDDLE3 + subKinds +
                        Url.SUBJECT_ADDCART_END + num;

                getDataCartFromNet(addCartUrl);


            }
        };

       return insertObj;
    }

    /**
     *
     * @param addCartUrl 购物车请求
     */
    private void getDataCartFromNet(String addCartUrl) {
        RequestParams params = new RequestParams(addCartUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(SubjectDetailsActivity.this, "已将添加到购物车", Toast.LENGTH_SHORT).show();

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
     * 设置分享的数目的增加
     */
    private void changeShareNum() {
        //获取当前的实体类中分享的数据
        int share_num = subDetailsBean.getData().getShare_num() + 1;
        //本地的shqre_num 的变化
        subDetailsBean.getData().setShare_num(share_num);
        wv_sub_details.loadUrl("javascript: changeShareNum('"+ share_num + "')");
        //网络请求的的变化
        //getDataChangeShare();

    }
    /**
     * 是否登录的自定义对话框
     */
    private void showisLogin() {
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        Log.d("SubjectDetailsActivity", "width" + width + "height" + height);

        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout loginLayout = (LinearLayout) inflater.inflate(R.layout.dialog_login, null);

        final Dialog loginDialog = new AlertDialog.Builder(SubjectDetailsActivity.this).create();
        loginDialog.show();
        loginDialog.getWindow().setContentView(loginLayout);
        loginDialog.getWindow().setLayout((int) (width * 0.7), (int) (height * 0.20));

        //取消按钮
        Button buttonCacel = (Button) loginLayout.findViewById(R.id.dialog_login_cacel);
        buttonCacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.dismiss();
            }
        });

        Button buttonLogin = (Button) loginLayout.findViewById(R.id.dialog_login_go);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog 消失
                loginDialog.dismiss();
                //启动登录的界面 并且携带结果的回调数据
                Intent intent = new Intent(SubjectDetailsActivity.this, MineContentActivity.class);
                intent.putExtra("pager", Constants.LOGIN_PAGER);
                startActivityForResult(intent, 1);
            }
        });
    }

    /**
     * 联网获取请求的数据
     *
     * @param collect_status 商品的收藏状态
     */
    private void getDataCollectFormNet(final int collect_status) {
        String collStaUrl = null;
        if (0 == collect_status) {//没有收藏 可以收藏
            collStaUrl = Url.SUBJECT_COLLECTOR_STATRT + uid + Url.SUBJECT_COLLECTOR_MIDDLE + shopId + Url.SUBJECT_COLLECTOR_END + 0;
            Log.d("TAG", "登录后的id " + uid);

        } else {//已经收藏 可以取消收藏
            collStaUrl = Url.SUBJECT_COLLECTOR_STATRT + uid + Url.SUBJECT_COLLECTOR_MIDDLE + shopId + Url.SUBJECT_COLLECTOR_END + 1;
            Log.d("TAG", "登录后的id " + uid);

        }


        RequestParams params = new RequestParams(collStaUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("TAG", "sub 收藏成功后" + result);
                processCollectData(result, collect_status);

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
     * 解析收藏的时候请求的数据
     *
     * @param json
     * @param collect_status 收藏的状态
     */
    private void processCollectData(String json, int collect_status) {

        if (collect_status == 0) { //可以收藏
            CollectStatusBean collectStatusBean = new Gson().fromJson(json, CollectStatusBean.class);
            if ("1000".equals(collectStatusBean.getRs_code())) {
                wv_sub_details.loadUrl("javascript: changeCollect('true')");
                subDetailsBean.getData().setHotindex(subDetailsBean.getData().getHotindex() + 1);
                wv_sub_details.loadUrl("javascript: changeCollectNum('" + subDetailsBean.getData().getHotindex() + "')");
                subDetailsBean.getData().setCollect_status(1);
                Toast.makeText(this, "好吃的到碗里了哦", Toast.LENGTH_SHORT).show();
            }
        } else {
            wv_sub_details.loadUrl("javascript: changeCollect('false')");
            subDetailsBean.getData().setHotindex(subDetailsBean.getData().getHotindex() - 1);
            wv_sub_details.loadUrl("javascript: changeCollectNum('" + subDetailsBean.getData().getHotindex() + "')");
            subDetailsBean.getData().setCollect_status(0);
            Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
        }


    }


    /**
     * 初始化布局文件
     */
    private void initView() {


        ib_left_menu = (ImageButton) findViewById(R.id.ib_left_menu);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_shopname = (TextView) findViewById(R.id.tv_shopname);
        rl_cart = (RelativeLayout) findViewById(R.id.rl_cart);
        tv_shopcount = (TextView) findViewById(R.id.tv_shopcount);

        ib_left_menu.setVisibility(View.GONE);
        iv_search_back.setVisibility(View.VISIBLE);
        et_search.setVisibility(View.GONE);
        tv_shopname.setVisibility(View.VISIBLE);

        tv_shopname.setText("专题详情");
        if(CartUtils.getInstance().getGoodsNum() != 0) {
            tv_shopcount.setVisibility(View.VISIBLE);
            tv_shopcount.setText(CartUtils.getInstance().getGoodsNum() + "");
        } else {
            tv_shopcount.setVisibility(View.GONE);
        }

        iv_search_back.setOnClickListener(new MyOnClickListener());
        rl_cart.setOnClickListener(new MyOnClickListener());

        wv_sub_details = (WebView) findViewById(R.id.wv_sub_details);
        ll_subject_loading = (LinearLayout) findViewById(R.id.ll_subject_loading);
        subject_loading = (ImageView) findViewById(R.id.subject_loading);


        //开启加载页面的动画
        loadAnim = (AnimationDrawable) subject_loading.getBackground();
        if (loadAnim != null && !loadAnim.isRunning()) {
            loadAnim.start();
        }

        //注册Eventbus;
        registerEventBus();
        //获取设置模式
        WebSettings webSettings = wv_sub_details.getSettings();
        //设置编码
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptEnabled(true);//调用js 实现
        /**
         *添加一个js交互的接口 方便html 布局文件中javascript代码可以与后台java进行直接交互访问
         * ""为该对象起别名 对应html中的 contact
         */
        wv_sub_details.addJavascriptInterface(getHtmlObject(), "bridge");
        shopId = getIntent().getStringExtra("detailsUrl");

    }


    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否注册
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 反注册 EventBus
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册
        unRegisterEventBus();
        finish();
    }

    /**
     * 登录界面 正常登录时 传回到这里的一个结果 来判断是否成功
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //重新加载数据
        initData();
    }
}