package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

/**
 * Created by lanmang on 2016/4/8.
 * 我的小喵页面
 */
public class MinePager extends BasePager implements View.OnClickListener {

    private ImageButton ib_mine_setting;
    private ImageView ib_mine_user_header;
    private LinearLayout ll_mine_call_service;
    private LinearLayout ll_register_login;
    private TextView tv_mine_login;
    private TextView tv_mine_register;
    private TextView tv_mine_nickname;
    private int position;
    private Bundle bundle;

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
        ll_mine_call_service = (LinearLayout) v.findViewById(R.id.ll_mine_call_service);
        ll_register_login = (LinearLayout) v.findViewById(R.id.ll_register_login);
        tv_mine_login = (TextView) v.findViewById(R.id.tv_mine_login);
        tv_mine_register = (TextView) v.findViewById(R.id.tv_mine_register);
        tv_mine_nickname = (TextView) v.findViewById(R.id.tv_mine_nickname);

        //设置监听
        ib_mine_setting.setOnClickListener(this);
        ib_mine_user_header.setOnClickListener(this);
        tv_mine_login.setOnClickListener(this);
        tv_mine_register.setOnClickListener(this);
        tv_mine_nickname.setOnClickListener(this);

        //联系客服
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

    @Override
    public void onClick(View v) {
        bundle = null;
        switch (v.getId()) {
            case R.id.ib_mine_setting://设置界面
                position = Constants.SETTING_PAGER;
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
        }
        startActivity(MineContentActivity.class);
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
               /* .setView(inflate);*/

      /*  AlertDialog alertDialog = builder.create();
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        int height = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        attributes.height = (int) (height*0.6);
        window.setAttributes(attributes);
        builder.show();*/
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(mActivity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra("pager", position);
        mActivity.startActivity(intent);
    }

    /**
     * 登录成功后更新用户信息
     *
     */
    @Subscribe
    public void onEventMainThread(LoginUtils loginUtils) {
        if(LoginUtils.getInstance().isLogin()){
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
        }else{
            ll_register_login.setVisibility(View.VISIBLE);
            tv_mine_nickname.setVisibility(View.GONE);
            ib_mine_user_header.setImageResource(R.drawable.default_photo_person);
        }
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
