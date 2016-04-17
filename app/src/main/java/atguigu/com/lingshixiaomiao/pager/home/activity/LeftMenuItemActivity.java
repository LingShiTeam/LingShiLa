package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Url;

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
    private String[] strs = {"tab1", "tab2", "tab3"};
    private MenuPagerAdapter adapter;
    private LinearLayout linearlayout;
    private ImageView widget_loading_pb;
    private AnimationDrawable background;
    private List<TabBasePager> pagers;
    private int itembeanID;

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
    }

    private void findView() {
        ib_left_menu = (ImageButton) findViewById(R.id.ib_left_menu);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_shopname = (TextView) findViewById(R.id.tv_shopname);
        iv_cart = (ImageView) findViewById(R.id.iv_cart);
        tv_shopcount = (TextView) findViewById(R.id.tv_shopcount);

        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
        widget_loading_pb = (ImageView) findViewById(R.id.widget_loading_pb);
        background = (AnimationDrawable) widget_loading_pb.getBackground();

        tab_indicator = (TabPageIndicator) findViewById(R.id.tab_indicator);
        vp_leftmenu_detail = (ViewPager) findViewById(R.id.vp_leftmenu_detail);
    }

    /**
     * 初始化数据
     */
    private void initData() {
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
        pagers.add(new TabPager1(this, tabUrl));

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
            pagers.add(new TabPager1(this, tabUrl));
        }

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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 停止加载动画
     */
    private void stopLoading() {
        background.stop();
        widget_loading_pb.setVisibility(View.GONE);
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
        }
    }
}
