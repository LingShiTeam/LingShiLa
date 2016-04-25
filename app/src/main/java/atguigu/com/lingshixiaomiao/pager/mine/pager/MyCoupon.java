package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.activity.CouponUseActivity;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CouponBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.GetCouponBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by Liu_haiwei on 2016/4/21.
 * 我的优惠券
 */
public class MyCoupon extends ContentBasePager implements View.OnClickListener {

    private RelativeLayout fragment_orders_detail_bottom;
    private Button bt_fragment_coupon_getcoupon;
    private EditText eidt_fragment_coupo_number;
    private ListView list;
    private LinearLayout coupon_empty;
    private RelativeLayout loading_dialog;
    private ImageView widget_loading_pb;
    private AnimationDrawable ad;

    public MyCoupon(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_my_coupon, null);
        findView(inflate);
        return inflate;
    }

    private void findView(View inflate) {
        fragment_orders_detail_bottom = (RelativeLayout) inflate.findViewById(R.id.fragment_orders_detail_bottom);
        bt_fragment_coupon_getcoupon = (Button) inflate.findViewById(R.id.bt_fragment_coupon_getcoupon);
        eidt_fragment_coupo_number = (EditText) inflate.findViewById(R.id.eidt_fragment_coupo_number);
        list = (ListView) inflate.findViewById(R.id.list);
        coupon_empty = (LinearLayout) inflate.findViewById(R.id.coupon_empty);
        loading_dialog = (RelativeLayout) inflate.findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) inflate.findViewById(R.id.widget_loading_pb);
        ad = (AnimationDrawable) widget_loading_pb.getBackground();

        bt_fragment_coupon_getcoupon.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        registerEventBus();
        startLoading();
        new JsonUtils().loadData(Url.COUPON_URL, CouponBean.class);
    }

    /**
     * 开始加载
     */
    private void startLoading() {
        ad.start();
        loading_dialog.setVisibility(View.VISIBLE);
        //list.setVisibility(View.GONE);
    }

    /**
     * 停止加载
     */
    private void stopLoading() {
        ad.stop();
        loading_dialog.setVisibility(View.GONE);
        //list.setVisibility(View.VISIBLE);
    }

    /**
     * 使用EventBus接收解析后的数据-CouponBean
     *
     * @param couponBean
     */
    @Subscribe
    public void onEventMainThread(CouponBean couponBean) {
        stopLoading();
        LogUtils.loge("TAG", "优惠券数据解析成功 = " + couponBean.toString());
        if (couponBean != null) {
            String code = couponBean.getRs_code();
            if ("1000".equals(code)) {
                coupon_empty.setVisibility(View.VISIBLE);
                fragment_orders_detail_bottom.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
            } else {
                coupon_empty.setVisibility(View.GONE);
                fragment_orders_detail_bottom.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
            }
        }

        unRegisterEventBus();
    }

    /**
     * 使用EventBus接收解析后的数据-CouponBean
     *
     * @param getCouponBean
     */
    @Subscribe
    public void onEventMainThread(GetCouponBean getCouponBean) {

        stopLoading();
        if (getCouponBean != null) {
            if ("6003".equals(getCouponBean.getRs_code())) {
                new AlertDialog.Builder(mActivity)
                        .setMessage("您输入的验证码不正确,请重新输入!")
                        .setPositiveButton("确定", null)
                        .show();
            } else {
                new AlertDialog.Builder(mActivity)
                        .setMessage("领取成功!")
                        .setPositiveButton("确定", null)
                        .show();
            }
        }
    }

    @Override
    public String setTitle() {
        return "我的优惠券";
    }

    @Override
    public String setComplete() {
        return "使用规则";
    }

    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void complete() {
        super.complete();
        Intent intent = new Intent(mActivity, CouponUseActivity.class);
        mActivity.startActivity(intent);
    }

    /**
     * 解注册EventBus
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fragment_coupon_getcoupon:
                String couponNum = eidt_fragment_coupo_number.getText().toString();
                if (couponNum != null) {
                    registerEventBus();
                    eidt_fragment_coupo_number.setText("");
                    startLoading();
                    new JsonUtils().loadData(Url.GET_COUPON_URL + couponNum, GetCouponBean.class);
                } else {
                    Toast.makeText(mActivity, "优惠券领取码不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


}
