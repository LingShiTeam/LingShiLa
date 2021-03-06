package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.application.GlobalVariables;
import atguigu.com.lingshixiaomiao.pager.alpay.H5PayDemoActivity;
import atguigu.com.lingshixiaomiao.pager.alpay.PayResult;
import atguigu.com.lingshixiaomiao.pager.alpay.SignUtils;
import atguigu.com.lingshixiaomiao.pager.home.bean.AlpayInfoBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Url;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.bean.AddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.DefaultAddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;

/**
 * 支付信息界面
 */
public class PayInfoActivity extends Activity implements View.OnClickListener {

    private RelativeLayout orders_confirm_address_viewgroup;
    private TextView orders_confirm_no_select_address;
    private TextView orders_confirm_name;
    private TextView orders_confirm_phone;
    private TextView orders_confirm_address;
    private TextView item_cost_pay_all;
    private TextView item_cost_detail;
    private TextView item_cost_detail_up;
    private TextView item_cost_goods;
    private TextView item_cost_post;
    private TextView item_coupon_amount;
    private TextView item_coupon;
    private EditText item_orders_confirm_note_edit_new;
    private RelativeLayout item_order_choice_zfb;
    private RelativeLayout item_order_choice_wx;
    private TextView goods_pay;
    private RelativeLayout loading_dialog;
    private ImageView widget_loading_pb;
    private AnimationDrawable ad;
    private ImageView btn_zfb;
    private ImageView btn_wx;
    private AlpayInfoBean alpayInfoBean;
    private String payInfo;
    private LinearLayout item_confirm_is_show_pay_detail;
    private CartBean cartBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_info);

        initView();
        initData();

    }

    private void initData() {

        registerEventBus();
        // 得到购物车中传递进来的items_id,items_items_id,总金额...
        cartBean = GlobalVariables.cartBean;
        if (cartBean != null) {
            LogUtils.loge("TAG", "shopping_info" + cartBean.toString());
            setData();
        }


        // 模拟数据
        String uid = GlobalVariables.uid;
        if (uid == null) {
            Toast.makeText(this, "未登录,请先登录", Toast.LENGTH_SHORT).show();
        } else {
            String payInfoUrl = Url.PAY_INFO_URL[0] + GlobalVariables.uid + Url.PAY_INFO_URL[1] + "4" + Url.PAY_INFO_URL[2] + "316914";
        }
        //String payInfoUrl = Url.PAY_INFO_URL[0] + GlobalVariables.uid + Url.PAY_INFO_URL[1] + "4" + Url.PAY_INFO_URL[2] + "316914";
        String payInfoUrl = Url.PAY_INFO_URL[0] + "184237" + Url.PAY_INFO_URL[1] + "4" + Url.PAY_INFO_URL[2] + "316931";
        startLoading();
        Log.e("TAG", "url == " + payInfoUrl);
        new JsonUtils().loadData(payInfoUrl, AlpayInfoBean.class);
    }

    /**
     * 数据解析结果
     *
     * @param alpayInfoBean
     */
    @Subscribe
    public void onEventMainThread(AlpayInfoBean alpayInfoBean) {
        this.alpayInfoBean = alpayInfoBean;
        LogUtils.loge("TAG", "支付信息数据解析成功!");

        if ("5010".equals(alpayInfoBean.getRs_code())) {
            Log.e("TAG", "订单失效");
        } else {
            Log.e("TAG", "支付订单" + alpayInfoBean.getData().getPay_info());
            payInfo = alpayInfoBean.getData().getPay_info();
        }

        stopLoading();
        unRegisterEventBus();
    }

    private void initView() {
        // address
        orders_confirm_address_viewgroup = (RelativeLayout) findViewById(R.id.orders_confirm_address_viewgroup);
        orders_confirm_no_select_address = (TextView) findViewById(R.id.orders_confirm_no_select_address);
        orders_confirm_name = (TextView) findViewById(R.id.orders_confirm_name);
        orders_confirm_phone = (TextView) findViewById(R.id.orders_confirm_phone);
        orders_confirm_address = (TextView) findViewById(R.id.orders_confirm_address);
        // info
        item_cost_pay_all = (TextView) findViewById(R.id.item_cost_pay_all);
        item_cost_detail = (TextView) findViewById(R.id.item_cost_detail);
        item_cost_detail_up = (TextView) findViewById(R.id.item_cost_detail_up);
        item_cost_goods = (TextView) findViewById(R.id.item_cost_goods);
        item_cost_post = (TextView) findViewById(R.id.item_cost_post);
        item_coupon_amount = (TextView) findViewById(R.id.item_coupon_amount);
        item_coupon = (TextView) findViewById(R.id.item_coupon);
        item_orders_confirm_note_edit_new = (EditText) findViewById(R.id.item_orders_confirm_note_edit_new);
        item_order_choice_zfb = (RelativeLayout) findViewById(R.id.item_order_choice_zfb);
        item_order_choice_wx = (RelativeLayout) findViewById(R.id.item_order_choice_wx);
        btn_zfb = (ImageView) findViewById(R.id.btn_zfb);
        btn_wx = (ImageView) findViewById(R.id.btn_wx);
        item_confirm_is_show_pay_detail = (LinearLayout) findViewById(R.id.item_confirm_is_show_pay_detail);

        // pay
        goods_pay = (TextView) findViewById(R.id.goods_pay);

        // loading
        loading_dialog = (RelativeLayout) findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) findViewById(R.id.widget_loading_pb);
        ad = (AnimationDrawable) widget_loading_pb.getBackground();

        btn_zfb.setSelected(true);

        item_order_choice_wx.setOnClickListener(this);
        item_order_choice_zfb.setOnClickListener(this);
        item_cost_detail.setOnClickListener(this);
        item_cost_detail_up.setOnClickListener(this);
        orders_confirm_address_viewgroup.setOnClickListener(this);
        goods_pay.setOnClickListener(this);

    }

    // 更新订单信息
    private void setData() {
        DefaultAddressBean defaultAddress = GlobalVariables.defaultAddressBean;
        if (defaultAddress != null) {
            orders_confirm_no_select_address.setVisibility(View.GONE);
            orders_confirm_name.setText(defaultAddress.getName());
            orders_confirm_phone.setText(defaultAddress.getPhone());
            orders_confirm_address.setText(defaultAddress.getFull_add());
        }
        List<CartBean.DataEntity.ItemssEntity> itemss = cartBean.getData().getItemss();
        item_cost_goods.setText(itemss.get(0).getSum_price() + "");
        item_cost_post.setText(itemss.get(0).getFreight() + "");
        item_coupon_amount.setText("¥0");
        item_cost_pay_all.setText(itemss.get(0).getSum_price() + itemss.get(0).getFreight() + "");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goods_pay: // 支付
                if (btn_wx.isSelected()) {
                    Log.e("TAG", "微信支付");
                    Toast.makeText(this, "微信支付尚未集成,请选择其他支付方式", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("TAG", "支付宝支付");
                    pay();
                }
                break;
            case R.id.item_order_choice_wx: // 微信支付
                btn_zfb.setSelected(false);
                btn_wx.setSelected(true);
                break;
            case R.id.item_order_choice_zfb: // 支付宝支付
                btn_zfb.setSelected(true);
                btn_wx.setSelected(false);
                break;
            case R.id.item_cost_detail:
                item_cost_detail.setVisibility(View.GONE);
                item_cost_detail_up.setVisibility(View.VISIBLE);
                item_confirm_is_show_pay_detail.setVisibility(View.VISIBLE);
                break;
            case R.id.item_cost_detail_up:
                item_cost_detail.setVisibility(View.VISIBLE);
                item_cost_detail_up.setVisibility(View.GONE);
                item_confirm_is_show_pay_detail.setVisibility(View.GONE);
                break;
            case R.id.orders_confirm_address_viewgroup:
                Intent intent = new Intent(this, MineContentActivity.class);
                intent.putExtra("pager", Constants.ADDRESS_PAGER);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            AddressBean.DataEntity.ItemsEntity entity = (AddressBean.DataEntity.ItemsEntity) data.getExtras().getSerializable("address");
            if (entity != null) {
                orders_confirm_no_select_address.setVisibility(View.GONE);
                orders_confirm_name.setText(entity.getName());
                orders_confirm_phone.setText(entity.getPhone());
                orders_confirm_address.setText(entity.getFull_add());
            }
        }
    }

    /**
     * 停止加载
     */
    private void stopLoading() {
        ad.stop();
        loading_dialog.setVisibility(View.GONE);
    }

    /**
     * 开始加载
     */
    private void startLoading() {
        loading_dialog.setVisibility(View.VISIBLE);
        ad.start();
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
     * 解注册EventBus
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    // 商户PID
    public static final String PARTNER = "2088911876712776";
    // 商户收款账号
    public static final String SELLER = "chenlei@atguigu.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALKhO7FIbFBRQXVV\n" +
            "EE7YyRgqhjxAE/TU2H09NrxQN0k9NFU3UY2kFR5GKd3RBbe2DrIMNC946Vn+4sYZ\n" +
            "rKjrtFddNELBQ6/HLwEVntrIr9Hrh/phXSNvYLkXz1N7LODGkNMNdd4anF9pb8Vg\n" +
            "KpvgW0KE6hm6PXNjfy4HpMH+H79ZAgMBAAECgYEAsmEKAAZni2kpKecxKtuXLRfC\n" +
            "BsaiZxppud74GuMr4mpJKVPdj8ks" +
            "AZhlVInX4NXgJPLE5icjN+itQs/+uIzv/6Sn\n" +
            "TK4EnWWE4vII/Juptn3/PNzJuDQ9KAALJdIlCyPUDlGp3BbsyHKwXi5u04y4CSKR\n" +
            "AbwMXK106B5A54zmRwECQQDtwyhBu5Xy059NE3OZrhiSZoN0qWfSP7V6i3dGh6uk\n" +
            "NKTvpKZVJFZN/UhHODedAkykCltBGjs95Lr29DGWfIy5AkEAwFTqJQaiZMRuqTNU\n" +
            "O57jDz94u1GYvMXm/OO0KR4+3/NgtxWFTD4SIS62ImvAa4+reNKdgV0L+hVlMZk9\n" +
            "T0u3oQJAGVoR1NB3sKi397P3Q4+4twOPSMU3eMeqRiLQ2QhmWN/0JklcIv/449/5\n" +
            "8P4hj/BK08L27/4oCvV0qhhnoTAuoQJAcbnEOeCSZcNBLAuN4zMwT/PYD+tPxU/A\n" +
            "Jgj+ntGjnAjtR1USXOLXFYfparEMNo3ddrQTLks2qcm87HcQKbM8AQJAFm7yvj7h\n" +
            "2j7Ook7JqvCq1qmQDQZKAAhx1Nej9N/2LvOcyM6eSwW+IoKN4BeFYoTSsekOqSeh\n" +
            "iz2Xb5XIdky0KQ==";
    // 支付宝公钥
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayInfoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayInfoActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayInfoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    /**
     * call alipay sdk pay. 调用SDK支付
     */

    // 商户PID
    public static final String PARTNER1 = "2088611281802746";
    // 商户收款账号
    public static final String SELLER1 = "feibobomail@126.com";

    public void pay() {
        //校验是否设置商户私钥，pkcs8格式 和公钥
        /*if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            finish();
                        }
                    }).show();
            return;
        }*/

        //开始组拼支付商品的信息

        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayInfoActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * get the sdk version. 获取SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     */
    public void h5Pay() {
        Intent intent = new Intent(this, H5PayDemoActivity.class);
        Bundle extras = new Bundle();
        /**
         * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
         * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
         * 商户可以根据自己的需求来实现
         */
        String url = "http://m.meituan.com";
        // url可以是一号店或者美团等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);

    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }


}
