package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.application.GlobalVariables;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CacheUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.DialogUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.PhoneUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/10.
 */
public class LoginPager extends ContentBasePager implements View.OnClickListener, TextWatcher {

    private EditText et_mine_login_phone;
    private EditText et_mine_login_password;
    private ImageView iv_mine_login_eye;
    private Button btn_mine_login;
    private TextView tv_mine_login_forget_password;
    private TextView tv_mine_login_mobile_register;
    private ImageView iv_mine_three_qq;
    private ImageView iv_mine_three_weixin;
    private ImageView iv_mine_three_weibo;

    private boolean isOpenEye = false;
    private LoginBean.DataEntity data;
    private JsonUtils jsonUtils;
    private LinearLayout ll_loading;

    private UMShareAPI mShareAPI;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public LoginPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_login, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        et_mine_login_phone = (EditText) v.findViewById(R.id.et_mine_login_phone);
        et_mine_login_password = (EditText) v.findViewById(R.id.et_mine_login_password);
        iv_mine_login_eye = (ImageView) v.findViewById(R.id.iv_mine_login_eye);
        btn_mine_login = (Button) v.findViewById(R.id.btn_mine_login);
        tv_mine_login_forget_password = (TextView) v.findViewById(R.id.tv_mine_login_forget_password);
        tv_mine_login_mobile_register = (TextView) v.findViewById(R.id.tv_mine_login_mobile_register);
        iv_mine_three_qq = (ImageView) v.findViewById(R.id.iv_mine_three_qq);
        iv_mine_three_weixin = (ImageView) v.findViewById(R.id.iv_mine_three_weixin);
        iv_mine_three_weibo = (ImageView) v.findViewById(R.id.iv_mine_three_weibo);
        ll_loading = (LinearLayout) v.findViewById(R.id.ll_loading);

        setListener();
    }

    private void setListener() {
        et_mine_login_phone.addTextChangedListener(this);

        et_mine_login_password.addTextChangedListener(this);

        iv_mine_login_eye.setOnClickListener(this);
        btn_mine_login.setOnClickListener(this);
        tv_mine_login_forget_password.setOnClickListener(this);
        tv_mine_login_mobile_register.setOnClickListener(this);
        iv_mine_three_qq.setOnClickListener(this);
        iv_mine_three_weixin.setOnClickListener(this);
        iv_mine_three_weibo.setOnClickListener(this);
    }

    private void canLogin() {
        //判断帐号是否为手机号
        String number = et_mine_login_phone.getText().toString();
        boolean phoneNumber = PhoneUtils.isMobile(number);

        //判断密码是否为6-16位
        String password = et_mine_login_password.getText().toString();
        int length = password.length();
        if (phoneNumber && length >= 6 && length <= 16) {
            btn_mine_login.setEnabled(true);
        } else {
            btn_mine_login.setEnabled(false);
        }
    }

    @Override
    public String setTitle() {
        return "登录";
    }

    @Override
    public void initData() {
        super.initData();
        this.mShareAPI = GlobalVariables.mShareAPI;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mActivity, MineContentActivity.class);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.iv_mine_login_eye:
                setEye();
                break;
            case R.id.btn_mine_login:
                login();
                break;
            case R.id.tv_mine_login_forget_password:
                bundle.putString("title", "重置密码");
                bundle.putString("url", Url.FORGET_PASSWORD_URL);
                intent.putExtras(bundle);
                intent.putExtra("pager", Constants.WEBVIEW_PAGER);
                mActivity.startActivity(intent);
                break;
            case R.id.tv_mine_login_mobile_register:
                bundle.putString("title", "注册");
                bundle.putString("url", Url.REGISTER_URL);
                intent.putExtras(bundle);
                intent.putExtra("pager", Constants.WEBVIEW_PAGER);
                mActivity.startActivity(intent);
                break;
            case R.id.iv_mine_three_qq:
                DialogUtils.showDialog("内容持续完善中,尽情期待...");
                break;
            case R.id.iv_mine_three_weixin:
                DialogUtils.showDialog("内容持续完善中,尽情期待...");
                break;
            case R.id.iv_mine_three_weibo: // 微博授权
                GlobalVariables.mainActivity.loginWeiBo();
                //mActivity.finish();
                break;
        }
    }



    /**
     * 登录
     */
    private void login() {
        showLoadingAnim();
        String number = et_mine_login_phone.getText().toString();
        String password = et_mine_login_password.getText().toString();
        String url = Url.LOGIN_URLS[0] + number + Url.LOGIN_URLS[1] + password + Url.LOGIN_URLS[2];
        LogUtils.loge("登录 url = " + url);
        jsonUtils = new JsonUtils();
        jsonUtils.loadData(url, LoginBean.class);
    }

    private void showLoadingAnim() {
        ll_loading.setVisibility(View.VISIBLE);
        ImageView iv_loading = (ImageView) ll_loading.findViewById(R.id.iv_loading);
        AnimationDrawable loading = (AnimationDrawable) iv_loading.getBackground();
        loading.start();
    }

    private void hindLoadingAnim() {
        ll_loading.setVisibility(View.GONE);
    }

    /**
     * 获取登陆返回信息
     *
     * @param loginBean
     */
    @Subscribe
    public void onEventMainThread(LoginBean loginBean) {
        hindLoadingAnim();
        String rs_code = loginBean.getRs_code();
        if (Constants.SUCCESS.equals(rs_code)) {
            data = loginBean.getData();
            if (jsonUtils!=null && !TextUtils.isEmpty(jsonUtils.getJson())) {
                CacheUtils.setCache(CacheUtils.getSmallFile(mActivity, "login"), jsonUtils.getJson());
                LoginUtils.getInstance().loginRequestSuccess(loginBean);
            }
            mActivity.finish();
        } else {
            Toast.makeText(mActivity, loginBean.getRs_msg(), Toast.LENGTH_SHORT).show();
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

    /**
     * 设置是否显示密码
     */
    private void setEye() {
        isOpenEye = !isOpenEye;
        iv_mine_login_eye.setSelected(isOpenEye);
        if (isOpenEye) {
            et_mine_login_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            et_mine_login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        canLogin();
    }
}
