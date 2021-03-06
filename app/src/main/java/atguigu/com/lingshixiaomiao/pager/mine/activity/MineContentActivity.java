package atguigu.com.lingshixiaomiao.pager.mine.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.net.URL;
import java.net.URLConnection;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.AboutPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.AddressPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.CartMinePager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.CollectionPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.EditAddressPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.LoginPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.MineCartPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.OrderPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.MyCoupon;
import atguigu.com.lingshixiaomiao.pager.mine.pager.ResetHeaderPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.ResetNicknamePager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.SettingPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.TaoBaoOrder;
import atguigu.com.lingshixiaomiao.pager.mine.pager.UserAggrementPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.UserPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.WebPager;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.ShareUtils;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 我的小喵页面中的那些侧滑退出页面
 */
public class MineContentActivity extends SwipeBackActivity implements View.OnClickListener {

    private ImageButton ib_mine_back;
    private TextView tv_mine_title;
    private FrameLayout fl_mine_view;
    private ContentBasePager pager;
    private TextView tv_mine_title_complete;
    private ImageView iv_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_content);
        findViewById();
        loadPagerView();
        //设置侧滑退出activity
        setSwipeBack();
    }

    private void loadPagerView() {
        int position = getIntent().getIntExtra("pager", 0);
        Bundle bundle = getIntent().getExtras();
        switch (position) {
            case Constants.MY_COUPON: // 我的优惠券
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                tv_mine_title_complete.setTextColor(Color.RED);
                tv_mine_title_complete.setTextSize(12);
                pager = new MyCoupon(this);
                break;
            case Constants.TAOBAO_ORDER: // 我的淘宝订单
                iv_share.setVisibility(View.VISIBLE);
                pager = new TaoBaoOrder(this);
                break;
            case Constants.SETTING_PAGER:
                pager = new SettingPager(this);//设置界面
                break;
            case Constants.CART_PAGER:
                pager = new MineCartPager(this);//购物车界面
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                break;
            case Constants.MINE_CART_PAGER:
                pager = new CartMinePager(this);//购物车界面 可复用
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                break;
            case Constants.ORDER_PAGER:
                pager = new OrderPager(this, bundle);//订单界面
                break;
            case Constants.ABOUT_PAGER:
                pager = new AboutPager(this);//关于零食小喵界面
                break;
            case Constants.COLLECTION_PAGER:
                pager = new CollectionPager(this);//我的收藏
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                break;
            case Constants.USERAGGREMENT_PAGER:
                pager = new UserAggrementPager(this, bundle);//用户协议界面
                break;
            case Constants.ADDRESS_PAGER:
                pager = new AddressPager(this);//管理收货地址界面
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                break;
            case Constants.USER_PAGER:
                pager = new UserPager(this);//用户信息界面
                break;
            case Constants.PUSH_PAGER:
                pager = new WebPager(this, bundle, true);//推送界面
                break;
            case Constants.LOGIN_PAGER:
                pager = new LoginPager(this);//登录界面
                break;
            case Constants.CHANGE_HEADER_PAGER:
                pager = new ResetHeaderPager(this);//修改头像界面
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                break;
            case Constants.CHANGE_NICKNAME_PAGER:
                pager = new ResetNicknamePager(this);//修改昵称界面
                break;
            case Constants.WEBVIEW_PAGER:
                pager = new WebPager(this, bundle, false);//普通界面
                break;
            case Constants.EDITADDRESS_PAGER:
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                boolean isEdit = getIntent().getBooleanExtra("edit", false);
                pager = new EditAddressPager(this, bundle, isEdit);//编辑地址页面
                break;
        }
        loadViews();
    }

    private void loadViews() {
        tv_mine_title.setText(pager.title);
        tv_mine_title_complete.setText(pager.complete);
        fl_mine_view.removeAllViews();
        fl_mine_view.addView(pager.rootView);
    }

    /**
     * 侧滑退出activity
     */
    private void setSwipeBack() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    private void findViewById() {
        ib_mine_back = (ImageButton) findViewById(R.id.ib_mine_back);
        tv_mine_title = (TextView) findViewById(R.id.tv_mine_title);
        fl_mine_view = (FrameLayout) findViewById(R.id.fl_mine_view);
        tv_mine_title_complete = (TextView) findViewById(R.id.tv_mine_title_complete);
        iv_share = (ImageView) findViewById(R.id.iv_share);

        ib_mine_back.setOnClickListener(this);
        tv_mine_title_complete.setOnClickListener(this);
        iv_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_mine_back:
                //侧滑返回上一个页面
                getSwipeBackLayout().scrollToFinishActivity();
                break;
            case R.id.tv_mine_title_complete:
                //完成
                pager.complete();
                break;
            case R.id.iv_share:
                // 分享
                ShareUtils.showShare(this);
                break;
        }
    }



    @Override
    protected void onDestroy() {
        pager.unRegisterEventBus();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        LogUtils.loge("resultCode = " + resultCode);

        super.onActivityResult(requestCode, resultCode, data);
        //处理扫描结果（在界面上显示）
        if (resultCode == RESULT_OK && requestCode == 1) {

            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            LogUtils.loge("scanResult = " + scanResult);


            if (scanResult.startsWith("www")) {
                scanResult = "http://" + scanResult;
            }

            try {
                URL url = new URL(scanResult);
                URLConnection urlConnection = url.openConnection();
                showLink(scanResult);
            } catch (Exception e) {
                e.printStackTrace();
                showResult(scanResult);
            }

        }

        if (requestCode == 2 && resultCode == RESULT_OK) {
            startPhotoZoom(data.getData());
        }

        // 取得裁剪后的图片
        if (requestCode == 3) {
            if (data != null) {
                setPicToView(data);
            }
        }
    }

    private void showResult(String resultString) {
        new AlertDialog.Builder(this)
                .setTitle("扫描结果")
                .setMessage(resultString)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    private void showLink(final String resultString) {
        new AlertDialog.Builder(this)
                .setTitle("扫描结果")
                .setMessage("是否链接: " + resultString)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MineContentActivity.this, MineContentActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", "扫描结果");
                        bundle.putString("url", resultString);
                        intent.putExtras(bundle);
                        intent.putExtra("pager", Constants.WEBVIEW_PAGER);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            EventBus.getDefault().post(photo);
        }
    }


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
