package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.TabBasePager;
import atguigu.com.lingshixiaomiao.pager.home.adapter.MenuPagerAdapter;
import atguigu.com.lingshixiaomiao.pager.home.bean.MenuTabTitleBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.DensityUtil;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Url;
import atguigu.com.lingshixiaomiao.pager.home.view.FlowLayout;

/**
 * 侧滑菜单项的item详情页面
 */
public class LeftMenuItemActivity extends Activity implements View.OnClickListener {

    private TabPageIndicator tab_indicator;
    private ViewPager vp_leftmenu_detail;
    private ImageButton ib_left_menu;
    private ImageView iv_search_back;
    private EditText et_search;
    private TextView tv_shopname;
    private ImageView iv_cart;
    private TextView tv_shopcount;
    private String itembeanTitle;
    private List<String> tabTitleLists;
    private List<String> tabIDLists;
    private MenuPagerAdapter adapter;
    private LinearLayout linearlayout;
    private ImageView widget_loading_pb;
    private AnimationDrawable background;
    private List<TabBasePager> pagers;
    private int itembeanID;
    private RelativeLayout loading_dialog;
    private PopupWindow pw;
    private ImageView ib_next;
    private int currPosition = 0;
    private FlowLayout tabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_menu_item);

        findView();
        initData();
        initView();
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        iv_search_back.setOnClickListener(this);
        ib_next.setOnClickListener(this);
    }

    private void findView() {
        ib_left_menu = (ImageButton) findViewById(R.id.ib_left_menu);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_shopname = (TextView) findViewById(R.id.tv_shopname);
        iv_cart = (ImageView) findViewById(R.id.iv_cart);
        tv_shopcount = (TextView) findViewById(R.id.tv_shopcount);
        ib_next = (ImageView) findViewById(R.id.ib_next);

        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
        loading_dialog = (RelativeLayout) findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) findViewById(R.id.widget_loading_pb);
        background = (AnimationDrawable) widget_loading_pb.getBackground();

        tab_indicator = (TabPageIndicator) findViewById(R.id.tab_indicator);
        vp_leftmenu_detail = (ViewPager) findViewById(R.id.vp_leftmenu_detail);
    }

    /**
     * 初始化数据
     */
    private void initData() {

        background.start();
        // 注册EventBus
        registerEventBus();
        // TAB标签
        tabTitleLists = new ArrayList<>();
        tabTitleLists.add("全部");

        // TAB标签ID
        tabIDLists = new ArrayList<>();
        // 从菜单列表中传递过来的数据
        itembeanID = getIntent().getIntExtra("itembeanID", 0);
        itembeanTitle = getIntent().getStringExtra("itembeanTitle");
        LogUtils.loge("TAG", "itembeanID = " + itembeanID + "itembeanTitle = " + itembeanTitle);

        /**
         * http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160415140544&sig=c98893f7c3cd52ff&wssig=3334a64360e583c4&os_type=" +
         "3&version=20&channel_name=wandoujia&srv=2406&pg_cur=1&pg_size=20&sub_id=0&parent_id=;//32&since_id=0
         */
        pagers = new ArrayList<>();
        String tabUrl = Url.MENU_ITEM_TAB_DES_MAIM + itembeanID + "since_id=0";
        pagers.add(new TabPager(this, tabUrl));

        /**
         * 请求TAB标签的链接
         * http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160415145826&sig=9f18131c921e2758&wssig=
         * b1aae3295371e0f1&os_type=3&version=20&channel_name=wandoujia&srv=2402&classify_id=32
         */
        String url = Url.MENU_ITEM_TAB_TITLE + itembeanID;
        background.start();
        new JsonUtils().loadData(url, MenuTabTitleBean.class);
    }

    /**
     * 初始化视图
     */
    private void initView() {

        ib_left_menu.setVisibility(View.GONE);
        iv_search_back.setVisibility(View.VISIBLE);
        et_search.setVisibility(View.GONE);
        tv_shopname.setVisibility(View.VISIBLE);
        // 设置标题
        tv_shopname.setText(itembeanTitle);

    }

    /**
     * 设置PopupWindow
     */
    private void setPopupWindow() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //params.width = DensityUtil.dip2px(5);
        params.leftMargin = params.rightMargin = DensityUtil.dip2px(this, 5);
        params.topMargin = params.bottomMargin = DensityUtil.dip2px(this, 5);
        params.height = DensityUtil.dip2px(this, 30);
        tabView = (FlowLayout) View.inflate(this, R.layout.popupwindow_layout, null);
        for (int i = 0; i < tabTitleLists.size(); i++) {
            Button button = new Button(this);
            button.setBackgroundResource(R.drawable.bg_btn_cancel_pressed);
            button.setText(tabTitleLists.get(i));
            button.setTextSize(12);
            button.setTag(i);
            tabView.addView(button, params);
            if (i == currPosition) {
                button.setBackgroundResource(R.drawable.btn_look_normal);
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currPosition = (int) v.getTag();
                    tab_indicator.setCurrentItem(currPosition);
                    pw.dismiss();
                }
            });
        }

        pw = new PopupWindow(tabView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        pw.setTouchable(true);
        pw.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    /**
     * 解析TAB标签的数据成功
     *
     * @param menuTabTitleBean
     */
    @Subscribe
    public void onEventMainThread(MenuTabTitleBean menuTabTitleBean) {
        LogUtils.loge("数据解析成功TAB : " + menuTabTitleBean.toString());
        stopLoading();
        linearlayout.setVisibility(View.VISIBLE);

        // 准备数据
        for (int i = 0; i < menuTabTitleBean.getData().getCount(); i++) {
            tabTitleLists.add(menuTabTitleBean.getData().getItems().get(i).getTitle());
            tabIDLists.add(menuTabTitleBean.getData().getItems().get(i).getId() + "");
            /**
             * http://api.ds.lingshi.cccwei.com/?cid=760272&uid=182129&tms=20160415140544&sig=c98893f7c3cd52ff&wssig=3334a64360e583c4&os_type=" +
             "3&version=20&channel_name=wandoujia&srv=2406&pg_cur=1&pg_size=20&sub_id=;//0&parent_id=32&since_id=0
             */
            String tabUrl = Url.MENU_ITEM_TAB_DES + menuTabTitleBean.getData().getItems().get(i).getId() + "&parent_id=" + itembeanID + "&since_id=0";
            pagers.add(new TabPager(this, tabUrl));
        }

        // 设置PopupWindow
        setPopupWindow();

        // 显示数据
        if (adapter == null) {
            adapter = new MenuPagerAdapter(LeftMenuItemActivity.this, tabTitleLists, pagers);
        }
        vp_leftmenu_detail.setAdapter(adapter);
        tab_indicator.setViewPager(vp_leftmenu_detail);
        tab_indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 屏蔽ViewPager的预加载
                pagers.get(position).initData();
                currPosition = position;
                for (int i = 0; i < tabTitleLists.size(); i++) {
                    if (i == currPosition) {
                        tabView.getChildAt(i).setBackgroundResource(R.drawable.btn_look_normal);
                    } else {
                        tabView.getChildAt(i).setBackgroundResource(R.drawable.bg_btn_cancel_pressed);
                    }

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        stopLoading();
    }


    /**
     * 停止加载动画
     */
    private void stopLoading() {
        background.stop();
        loading_dialog.setVisibility(View.GONE);
    }

    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    /**
     * 解注册EventBus
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解注册EventBus
        unRegisterEventBus();
    }

    /**
     * 点击的回调监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回按钮监听
            case R.id.iv_search_back:
                finish();
                break;
            case R.id.ib_next:
                if (!pw.isShowing()) {
                    popupWindowAnimationOpen();
                    pw.showAsDropDown(tab_indicator);
                } else {
                    pw.dismiss();
                    popupWindowAnimationClose();
                }

                break;
        }
    }


    /**
     * 关闭popupWindow的动画
     */
    private void popupWindowAnimationClose() {
        RotateAnimation ra = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(800);
        ra.setFillAfter(true);
        ib_next.startAnimation(ra);
    }

    /**
     * 打开popupWindow的动画
     */
    private void popupWindowAnimationOpen() {
        RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(800);
        ra.setFillAfter(true);
        ib_next.startAnimation(ra);
    }
}
