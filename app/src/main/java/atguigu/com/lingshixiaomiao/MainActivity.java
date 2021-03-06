package atguigu.com.lingshixiaomiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import atguigu.com.lingshixiaomiao.application.GlobalVariables;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.HomePager;
import atguigu.com.lingshixiaomiao.pager.MinePager;
import atguigu.com.lingshixiaomiao.pager.SalePager;
import atguigu.com.lingshixiaomiao.pager.SubjectPager;
import atguigu.com.lingshixiaomiao.pager.mine.activity.BindPhoneNumActivity;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CacheUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CartUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.OrderUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 主页面
 */
public class MainActivity extends FragmentActivity {

    public RadioGroup rg_main;
    private DrawerLayout dl_menu;
    private List<BasePager> pagers;

    /**
     * 当前页面的标记 首次进入主页面默认为0 范围:0~3
     */
    private int position = 0;
    private UMShareAPI mShareAPI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        /**
         * 友盟第三方登录初始化
         */
        /** init auth api**/
        mShareAPI = UMShareAPI.get(this);
        GlobalVariables.mShareAPI = mShareAPI;
        GlobalVariables.mainActivity = this;

        findViewById();
        loadPager();
        setListener();

        //设置初始化页面 首页
        rg_main.check(R.id.rb_main_home);
        //锁定左侧菜单
        dl_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //判断是否自动登录
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        checkIsLogin();

        String Token = "sse55ZfB+l5Eu5ALKDtU+vZZ974o5NwHfIz1STycoViVc5E9csh0tlEiarq7XZfJokLknrAleDpanPLGtTQAXg==";//test
        /**
         * IMKit SDK调用第二步
         *
         * 建立与服务器的连接
         *
         */

        connect(Token);


    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {


        /**
         * IMKit SDK调用第二步,建立与服务器的连接
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
             */
            @Override
            public void onTokenIncorrect() {

                Log.d("LoginActivity", "--onTokenIncorrect");
            }

            /**
             * 连接融云成功
             * @param userid 当前 token
             */
            @Override
            public void onSuccess(String userid) {

                Log.d("LoginActivity", "--onSuccess" + userid);
                // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                // finish();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

                Log.d("LoginActivity", "--onError" + errorCode);
            }
        });
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //设置下面四个RadioButton点击切换页面
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置侧滑菜单监听
        dl_menu.setDrawerListener(new MyDrawListener());
    }

    /**
     * 加载四个页面: 首页 特卖 专题 我的小喵
     */
    private void loadPager() {
        pagers = new ArrayList<>();
        pagers.add(new HomePager(this, dl_menu));
        pagers.add(new SalePager(this));
        pagers.add(new SubjectPager(this));
        pagers.add(new MinePager(this));
        OrderUtils.getInstance().checkOrderNum();
    }

    /**
     * 通过id来获得view的对象
     */
    private void findViewById() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        dl_menu = (DrawerLayout) findViewById(R.id.dl_menu);

        //  setDrawerLeftEdgeSize(this, dl_menu, 0.1f);
    }

    /**
     * 利用反射修改侧滑菜单的范围 不确定是否有效
     *
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage
     */
    public void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (Exception e) {
        }
    }

    /**
     * 设置下面四个RadioButton点击切换页面
     */
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_main_home:
                    position = 0;
                    break;
                case R.id.rb_main_sale:
                    position = 1;
                    break;
                case R.id.rb_main_subject:
                    position = 2;
                    break;
                case R.id.rb_main_mine:
                    position = 3;
                    break;
            }
            setCurrentPage();
        }
    }

    /**
     * 根据position设置当前页面
     */
    public void setCurrentPage() {
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ft = sfm.beginTransaction();
        ft.replace(R.id.fl_main_pager, new Fragment() {
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View pagerView = getPagerView();
                return pagerView;
            }
        }).commit();
    }

    /**
     * 根据position得到当前页面的View
     *
     * @return
     */
    private View getPagerView() {
        BasePager pager = pagers.get(position);
        return pager.rootView;
    }

    /**
     * 设置侧滑菜单监听
     */
    private class MyDrawListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {
            dl_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            dl_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    }

    @Override
    public void onDestroy() {
        //解注册EventBus
        for (int i = 0; i < pagers.size(); i++) {
            BasePager pager = pagers.get(i);
            pager.unRegisterEventBus();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    private long endTime;

    /**
     * 按两次back键退出程序
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - endTime > 2000) {
            Toast.makeText(this, "再按一次back键退出", Toast.LENGTH_SHORT).show();
            endTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }

    }

    /**
     * 判断是否保存用户登录信息,已经保存则直接登录并加载用户信息
     */
    public void checkIsLogin() {
        String login = CacheUtils.getCache(CacheUtils.getSmallFile(this, "login"));
        LogUtils.loge("login auto = " + login);
        new JsonUtils().parseJson(login, LoginBean.class);
    }

    /**
     * 获取自动登录信息
     *
     * @param loginBean
     */
    @Subscribe
    public void onEventMainThread(LoginBean loginBean) {
        LogUtils.loge("接收到自动登录信息 = " + loginBean);
        if (Constants.SUCCESS.equals(loginBean.getRs_code())) {
            GlobalVariables.uid = loginBean.getData().getUid();
            if ("".equals(loginBean.getData().getMobi_num())) {
                // 进入手机号绑定界面
                LogUtils.loge("TAG", "进入手机号绑定界面");
                Intent intent = new Intent(MainActivity.this, BindPhoneNumActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SinaWeiBo", loginBean);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                // 自动登录界面
                LogUtils.loge("TAG", "进入自动登录界面");
                LoginUtils.getInstance().loginRequestSuccess(loginBean);
            }
        }
    }

    /**
     * 设置当前位置
     */
    public void setPosition(int position) {

        this.position = position;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Constants.isToHome) {
            Constants.isToHome = false;
            rg_main.check(R.id.rb_main_home);
        }

        CartUtils.getInstance().checkGoodsNum();

        OrderUtils.getInstance().checkOrderNum();

    }
    public void loginWeiBo() {
        //DialogUtils.startLogin();
        SHARE_MEDIA platform = SHARE_MEDIA.SINA;
        mShareAPI.doOauthVerify(this, platform, umAuthListener);
    }

    /**
     * sina第三方登录回调借口
     * auth callback interface
     **/
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            android.util.Log.e("TAG", "data = " + data.get("uid"));
            android.util.Log.e("TAG", "data = " + data.get("access_token"));
            android.util.Log.e("TAG", "data = " + data.toString());
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            // 用户uid
            GlobalVariables.uid = data.get("uid");
            // 再次请求
            String weibo_info_url = Url.WEIBO_INFO[0] + data.get("uid") + Url.WEIBO_INFO[1] + Url.WEIBO_INFO[2];
            new JsonUtils().loadData(weibo_info_url, LoginBean.class);

            //DialogUtils.endLogin();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "onError");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "onCancel");
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        com.umeng.socialize.utils.Log.d("auth", "on activity re 2");
        mShareAPI.onActivityResult(requestCode, resultCode, data);
        com.umeng.socialize.utils.Log.d("auth", "on activity re 3");
    }

}
