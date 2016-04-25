package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;
import io.rong.imkit.RongIM;

/**
 * Created by lanmang on 2016/4/8.
 * 我的小喵页面
 */
public class MinePager extends BasePager implements View.OnClickListener {

    private ImageButton ib_mine_setting;
    private ImageView ib_mine_user_header;
    private ImageView iv_cart;
    private LinearLayout ll_mine_call_service;
    private LinearLayout ll_mine_service;
    private LinearLayout ll_mine_collections;
    private LinearLayout ll_register_login;
    private LinearLayout ll_mine_order;
    private TextView tv_mine_login;
    private TextView tv_mine_register;
    private TextView tv_mine_nickname;
    private TextView tv_mine_order_1;
    private TextView tv_mine_order_2;
    private TextView tv_mine_order_3;
    private TextView tv_mine_order_4;
    private TextView tv_mine_cart_num;
    private int position;
    private Bundle bundle;
    private LinearLayout ll_taobao_order;
    private LinearLayout ll_mycoupon;

    public MinePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_pager, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        ib_mine_setting = (ImageButton) v.findViewById(R.id.ib_mine_setting);
        ib_mine_user_header = (ImageView) v.findViewById(R.id.ib_mine_user_header);
        iv_cart = (ImageView) v.findViewById(R.id.iv_cart);
        ll_mine_call_service = (LinearLayout) v.findViewById(R.id.ll_mine_call_service);
        ll_mine_order = (LinearLayout) v.findViewById(R.id.ll_mine_order);
        ll_mine_collections = (LinearLayout) v.findViewById(R.id.ll_mine_collections);
        ll_mine_service = (LinearLayout) v.findViewById(R.id.ll_mine_service);
        ll_register_login = (LinearLayout) v.findViewById(R.id.ll_register_login);
        tv_mine_login = (TextView) v.findViewById(R.id.tv_mine_login);
        tv_mine_register = (TextView) v.findViewById(R.id.tv_mine_register);
        tv_mine_nickname = (TextView) v.findViewById(R.id.tv_mine_nickname);
        tv_mine_order_1 = (TextView) v.findViewById(R.id.tv_mine_order_1);
        tv_mine_order_2 = (TextView) v.findViewById(R.id.tv_mine_order_2);
        tv_mine_order_3 = (TextView) v.findViewById(R.id.tv_mine_order_3);
        tv_mine_order_4 = (TextView) v.findViewById(R.id.tv_mine_order_4);
        tv_mine_cart_num = (TextView) v.findViewById(R.id.tv_mine_cart_num);
        ll_taobao_order = (LinearLayout) v.findViewById(R.id.ll_taobao_order);
        ll_mycoupon = (LinearLayout) v.findViewById(R.id.ll_mycoupon);

        //设置监听
        tv_mine_order_1.setOnClickListener(this);
        tv_mine_order_2.setOnClickListener(this);
        tv_mine_order_3.setOnClickListener(this);
        tv_mine_order_4.setOnClickListener(this);
        ib_mine_setting.setOnClickListener(this);
        ib_mine_user_header.setOnClickListener(this);
        tv_mine_login.setOnClickListener(this);
        tv_mine_register.setOnClickListener(this);
        tv_mine_nickname.setOnClickListener(this);
        ll_mine_collections.setOnClickListener(this);
        ll_mine_order.setOnClickListener(this);
        iv_cart.setOnClickListener(this);
        ll_taobao_order.setOnClickListener(this);
        ll_mycoupon.setOnClickListener(this);
        //与客服聊天 融云
        ll_mine_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance() != null) {
                    /**
                     * 启动客户服聊天界面。
                     *
                     * @param context               应用上下文。
                     * @param customerServiceUserId 要与之聊天的客服 Id。
                     * @param title                 聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
                     */
                    RongIM.getInstance().startCustomerServiceChat(mActivity, "KEFU146096865825500", "在线客服");
                }
            }
        });

        //电话联系客服
        ll_mine_call_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCallService();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("我的小喵 数据加载了");
    }

    private int orderPosition = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_mine_setting://设置界面
                position = Constants.SETTING_PAGER;
                break;
            case R.id.iv_cart://购物车界面
                //position = Constants.CART_PAGER;
                position = Constants.MINE_CART_PAGER;
                break;
            case R.id.ll_mine_collections://我的收藏界面
                position = Constants.COLLECTION_PAGER;
                break;
            case R.id.tv_mine_login://登录界面
            case R.id.tv_mine_nickname:
            case R.id.ib_mine_user_header:
                if (!LoginUtils.getInstance().isLogin()) {
                    position = Constants.LOGIN_PAGER;
                } else {
                    position = Constants.USER_PAGER;
                }
                break;
            case R.id.tv_mine_register://注册
                bundle = new Bundle();
                bundle.putString("url", Url.REGISTER_URL);
                bundle.putString("title", "注册");
                position = Constants.WEBVIEW_PAGER;
                break;
            case R.id.ll_mine_order://订单0界面
                orderPosition = 0;
                startOrder();
                break;
            case R.id.tv_mine_order_1://订单1界面
                orderPosition = 1;
                startOrder();
                break;
            case R.id.tv_mine_order_2://订单2界面
                orderPosition = 2;
                startOrder();
                break;
            case R.id.tv_mine_order_3://订单3界面
                orderPosition = 3;
                startOrder();
                break;
            case R.id.tv_mine_order_4://订单4界面
                orderPosition = 4;
                startOrder();
                break;
            case R.id.ll_taobao_order: // 我的淘宝订单
                position = Constants.TAOBAO_ORDER;
                break;
            case R.id.ll_mycoupon:
                position = Constants.MY_COUPON;
                break;
        }
        startActivity(MineContentActivity.class);
    }

    private void startOrder() {
        bundle = new Bundle();
        bundle.putInt("position", orderPosition);
        position = Constants.ORDER_PAGER;
    }

    /**
     * 联系客服
     */
    private void startCallService() {

        View inflate = View.inflate(mActivity, R.layout.mine_call_service, null);

        new AlertDialog.Builder(mActivity)
                .setMessage("客服电话:18686823149")
                .setNegativeButton("取消", null)
                .setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constants.serviceNum));
                        mActivity.startActivity(intent);
                    }
                })
                .show();
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(mActivity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (position >= 11 && !LoginUtils.getInstance().isLogin()) {
            position = Constants.LOGIN_PAGER;
        }

        intent.putExtra("pager", position);
        mActivity.startActivity(intent);
    }

    /**
     * 登录成功后更新用户信息
     */
    @Subscribe
    public void onEventMainThread(LoginUtils loginUtils) {
        if (LoginUtils.getInstance().isLogin()) {
            LoginBean.DataEntity data = ((LoginBean) loginUtils.getData()).getData();
            if (data == null) {
                return;
            }
            String nickname = data.getNickname();
            ll_register_login.setVisibility(View.GONE);
            tv_mine_nickname.setText(nickname);
            tv_mine_nickname.setVisibility(View.VISIBLE);
            //更新头像
            String headerUrl = data.getAvatar();
            ImageOptions imageOptions = new ImageOptions.Builder().setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP).build();
            x.image().bind(ib_mine_user_header, headerUrl, imageOptions);
        } else {
            ll_register_login.setVisibility(View.VISIBLE);
            tv_mine_nickname.setVisibility(View.GONE);
            ib_mine_user_header.setImageResource(R.drawable.default_photo_person);
        }
    }


    /**
     * 获取修改个人头像返回值
     */
    @Subscribe
    public void onEventMainThread(Bitmap bitmap) {
        ib_mine_user_header.setImageBitmap(bitmap);
    }

    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
