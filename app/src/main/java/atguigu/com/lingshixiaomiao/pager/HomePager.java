package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.MainActivity;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.home.activity.HomeHeader2Activity;
import atguigu.com.lingshixiaomiao.pager.home.activity.LeftMenuItemActivity;
import atguigu.com.lingshixiaomiao.pager.home.activity.SearchActivity;
import atguigu.com.lingshixiaomiao.pager.home.activity.ShoppingListActivity;
import atguigu.com.lingshixiaomiao.pager.home.adapter.LeftMenuAdapter;
import atguigu.com.lingshixiaomiao.pager.home.adapter.ListViewAdapter;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomeBean;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomePagerBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.CarouselUtilsHome;
import atguigu.com.lingshixiaomiao.pager.home.utils.DataUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.NetWorkUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.SPUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Url;
import atguigu.com.lingshixiaomiao.pager.home.view.RefreshLayout;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CartUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.scale.activity.SnackInfomationActivity;

/**
 * 首页
 */
public class HomePager extends BasePager implements View.OnClickListener {

    private ImageButton ib_left_menu;
    private TextView et_search;
    private TextView tv_shopcount;
    private RelativeLayout rl_cart;
    private DrawerLayout dl_menu;
    private RefreshLayout refreshlayout_home;

    // 顶部轮播图数据
    private LinearLayout ll_top_points;
    private ListView listview_home;
    private View headerView;
    private ListViewAdapter adapter;
    // 分页数据
    private HomePagerBean homePagerBean;
    private List<HomePagerBean.DataEntity.ItemsEntity> pagerData;
    // 置顶按钮
    private ImageView iv_home_tiptop;
    private RelativeLayout loading_dialog;
    private ImageView widget_loading_pb;
    // 加载动画
    private AnimationDrawable ad;
    // 首页主数据
    private HomeBean homeBean;
    private List<HomeBean.DataEntity.TopicsEntity> hometopics;
    private ImageView drawerleft_image_avatar;
    private LinearLayout drawerleft_framelayout_login;
    private TextView drawerleft_nick_name;
    private TextView drawerleft_to_person_page;
    private TextView drawerleft_to_myorder_page;
    private LinearLayout drawerleft_framelayout_unlogin;
    private TextView drawerleft_to_login_page;
    private TextView drawerleft_to_registe_page;
    private ListView left_drawer_list;
    private LeftMenuAdapter menuAdapter;
    private View menuView;
    private ImageOptions imageOption;
    private LoginUtils loginUtils; // 登录信息

    // 顶部小圆点
    private List<ImageView> topPoints;
    private TextView home_item_title_left;
    private TextView home_item_title_right;
    // 第一个
    ImageView item_home_promotion_img;
    ImageView item_goods_empty;
    TextView item_home_promotion_discount;
    TextView item_home_promotion_desc;
    TextView item_home_promotion_tag_time;
    // 第二个
    ImageView btn_lover;
    ImageView btn_movie;
    ImageView btn_tea;
    ImageView btn_beer;

    TextView home_item_title_left2;
    TextView home_item_title_right2;

    private ViewPager viewPager;

    public HomePager(Activity mActivity, DrawerLayout dl_menu) {
        super(mActivity);
        this.dl_menu = dl_menu;
        //CartUtils.getInstance().checkGoodsNum();
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.home_pager, null);
        findViewById(inflate);
        setListener();
        return inflate;
    }

    // home当前数据页
    int pagerIndex = 1;

    /**
     * 请求数据
     * http://api.ds.lingshi.cccwei.com/?cid=760294&uid=0&tms=20160406224555&sig" +
     * "=77fe35c8027c2e4a&wssig=e4fe9113b21617de&os_type=3&version=18&channel_name=
     * feibo&srv=2206&since_id=0&pg_cur=1&pg_size=20";
     */
    private void getDataFromNet() {

        RequestParams request = new RequestParams(Url.HOME_TOP_PAGE + "&pg_cur=" + pagerIndex + "&pg_size=20");
        x.http().get(request, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    // 解析json数据
                    parsedHomePageJson(result);
                    // 保存数据
                    SPUtils.putString(mActivity, SPUtils.HOME_PAGE_DATA, result);
                } else {
                    Toast.makeText(mActivity, "没有更多数据!", Toast.LENGTH_SHORT).show();
                }

                pagerIndex++;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("onError", ex.toString());
                // 取消加载
                refreshlayout_home.setLoading(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("onCancelled", cex.toString());
                // 取消加载
                refreshlayout_home.setLoading(false);
            }

            @Override
            public void onFinished() {
                LogUtils.loge("onFinished", "加载完成!");
                // 取消加载
                refreshlayout_home.setLoading(false);
                LogUtils.loge("onFinished :", "pagerIndex = " + pagerIndex);
            }
        });
    }

    /**
     * 解析json数据
     *
     * @param json
     */
    private void parsedHomePageJson(String json) {
        homePagerBean = new Gson().fromJson(json, HomePagerBean.class);
        LogUtils.loge("TAG-homePagerBean", homePagerBean.toString());
        pagerData.addAll(homePagerBean.getData().getItems());
        LogUtils.loge("TAG", pagerData.size() + "");

    }

    /**
     * 点击监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            // 置顶按钮点击监听
            case R.id.iv_home_tiptop:
                listview_home.setSelection(0);
                break;
            // 搜索框点击监听
            case R.id.et_search:
                intent = new Intent(mActivity, SearchActivity.class);
                mActivity.startActivity(intent);
                break;
            case R.id.item_home_promotion_img:
            case R.id.item_home_promotion_discount:
            case R.id.item_home_promotion_desc:
            case R.id.item_home_promotion_tag_time:
                intent = new Intent(mActivity, HomeHeader2Activity.class);
                mActivity.startActivity(intent);
                break;
            case R.id.btn_lover:
                intent = new Intent(mActivity, ShoppingListActivity.class);
                intent.putExtra("shoppinglist", Url.LOVERURL);
                mActivity.startActivity(intent);
                break;
            case R.id.btn_movie:
                intent = new Intent(mActivity, ShoppingListActivity.class);
                intent.putExtra("shoppinglist", Url.MOVIEURL);
                mActivity.startActivity(intent);
                break;
            case R.id.btn_tea:
                intent = new Intent(mActivity, ShoppingListActivity.class);
                intent.putExtra("shoppinglist", Url.TEAURL);
                mActivity.startActivity(intent);
                break;
            case R.id.btn_beer:
                intent = new Intent(mActivity, ShoppingListActivity.class);
                intent.putExtra("shoppinglist", Url.BEERURL);
                mActivity.startActivity(intent);
                break;
            case R.id.rl_cart: // 进入购物车(模拟进入支付界面)
                intent = new Intent(mActivity, MineContentActivity.class);
                intent.putExtra("pager", Constants.MINE_CART_PAGER);
                mActivity.startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 上拉加载更多
     */
    class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            if (!NetWorkUtils.isNetworkConnected()) {
                Toast.makeText(mActivity, "没有网络...", Toast.LENGTH_SHORT).show();
                refreshlayout_home.setRefreshing(false);
                return;
            }
            if (adapter != null) {
                adapter.notifyDataSetChanged();
                refreshlayout_home.setRefreshing(false);
            }

        }
    }


    /**
     * 上拉加载更多
     */
    class MyOnLoadListener implements RefreshLayout.OnLoadListener {

        @Override
        public void onLoad() {
            if (!NetWorkUtils.isNetworkConnected()) {
                Toast.makeText(mActivity, "没有网络...", Toast.LENGTH_SHORT).show();
                refreshlayout_home.setLoading(false);
                return;
            }
            //请求数据
            getDataFromNet();
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //设置按钮点击弹出侧滑菜单
        ib_left_menu.setOnClickListener(new MyOnClickListener());
        // 设置颜色--不起作用
        //refreshlayout_home.setColorSchemeColors(R.color.color1, R.color.color2, R.color.color3);
        // 第一次进入页面的时候显示加载进度条
        refreshlayout_home.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, mActivity.getResources()
                        .getDisplayMetrics()));
        // 下拉刷新
        refreshlayout_home.setOnRefreshListener(new MyOnRefreshListener());
        // 上拉加载更多
        refreshlayout_home.setOnLoadListener(new MyOnLoadListener());
        // 置顶按钮
        iv_home_tiptop.setOnClickListener(this);

        // 搜索框点击监听
        et_search.setOnClickListener(this);

        // 首页商品listView的item的点击监听
        listview_home.setOnItemClickListener(new MyOnItemClickListener());

        // 点击购物车
        rl_cart.setOnClickListener(this);
    }

    /**
     * 首页商品listView的item的点击监听
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position > 2) {
                Intent intent = new Intent(mActivity, SnackInfomationActivity.class);
                intent.putExtra("snack_id", pagerData.get(position - 3).getId());
                mActivity.startActivity(intent);
            }
        }
    }

    /**
     * 通过id获取view
     *
     * @param parent
     */
    private void findViewById(View parent) {
        ib_left_menu = (ImageButton) parent.findViewById(R.id.ib_left_menu);
        et_search = (TextView) parent.findViewById(R.id.et_search);
        tv_shopcount = (TextView) parent.findViewById(R.id.tv_shopcount);
        rl_cart = (RelativeLayout) parent.findViewById(R.id.rl_cart);
        refreshlayout_home = (RefreshLayout) parent.findViewById(R.id.refreshlayout_home);
        listview_home = (ListView) parent.findViewById(R.id.listview_home);
        iv_home_tiptop = (ImageView) parent.findViewById(R.id.iv_home_tiptop);

        loading_dialog = (RelativeLayout) parent.findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) parent.findViewById(R.id.widget_loading_pb);

        // 设置主界面listview
        setListView();
    }

    /**
     * 设置主界面listview的显示
     */
    private void setListView() {
        // 加载头布局
        LayoutInflater layoutInflater = mActivity.getLayoutInflater();
        headerView = layoutInflater.inflate(R.layout.home_top, null);
        initHeadView(headerView);
        View item1 = layoutInflater.inflate(R.layout.home_listview_item1, null);
        initItem1(item1);
        View item2 = layoutInflater.inflate(R.layout.home_listview_item2, null);
        initItem2(item2);

        setHeaderListener();

        listview_home.addHeaderView(headerView);
        listview_home.addHeaderView(item1);
        listview_home.addHeaderView(item2);

    }

    /**
     * 设置头部监听
     */
    private void setHeaderListener() {
        item_home_promotion_img.setOnClickListener(this);
        btn_lover.setOnClickListener(this);
        btn_movie.setOnClickListener(this);
        btn_tea.setOnClickListener(this);
        btn_beer.setOnClickListener(this);

    }


    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("首页 开始加载首页数据");

        imageOption = new ImageOptions.Builder()
                //.setImageScaleType(ImageView.ScaleType.FIT_START)//等比例缩小到充满长/宽居中显示, 或原样显示
                .setLoadingDrawableId(R.drawable.default_home_banner_640_270)
                .setFailureDrawableId(R.drawable.default_home_banner_640_270)
                .setConfig(Bitmap.Config.ARGB_4444)
                .setCircular(true)
                .build();

        // 显示加载的dialog
        ad = (AnimationDrawable) widget_loading_pb.getBackground();
        ad.start();

        pagerData = new ArrayList<>();

        //获取保存的首页数据
        String homeData = SPUtils.getString(mActivity, SPUtils.HOME_TOP_DATA);
        if (homeData != null && !NetWorkUtils.isNetworkConnected()) {
            homeBean = new Gson().fromJson(homeData, HomeBean.class);
            //设置顶部轮播图
            new CarouselUtilsHome(headerView, mActivity).setViewPagerData(homeBean);
        }

        String homePageData = SPUtils.getString(mActivity, SPUtils.HOME_PAGE_DATA);
        if (homePageData != null && !NetWorkUtils.isNetworkConnected()) {
            parsedHomePageJson(homePageData);
            showListView();
            return;
        }

        //通过JsonUtils工具类解析url, 并通过EventBus返回数据
        if (NetWorkUtils.isNetworkConnected()) {
            new JsonUtils().loadData(Url.HOME_DATA_BASE, HomeBean.class);
        }

    }

    /**
     * 显示listView列表
     */
    private void showListView() {
        if (adapter == null) {
            adapter = new ListViewAdapter(mActivity, pagerData, iv_home_tiptop);
        }
        listview_home.setAdapter(adapter);
    }

    @Subscribe
    public void onEventMainThread(HomeBean homeBean) {
        LogUtils.loge("数据解析成功 : " + homeBean.toString());

        // 取消加载显示的dialog
        ad.stop();
        loading_dialog.setVisibility(View.GONE);

        this.homeBean = homeBean;
        hometopics = homeBean.getData().getTopics();

        if (homeBean != null) {
            // 判断顶部轮播图是否有数据
            if (homeBean.getData().getTopics().size() != 0) {
                //有数据-设置顶部轮播图
                new CarouselUtilsHome(headerView, mActivity).setViewPagerData(homeBean);
            } else {
                // 无数据-移除
                listview_home.removeHeaderView(headerView);
                //headerView.setVisibility(View.GONE);
            }

            setHeader1Data();
            setHeader2Data();
            // 显示ListView列表
            showListView();
        }
        //unRegisterEventBus();
    }


    // 第一个视图
    private void initItem1(View view1) {

        item_home_promotion_img = (ImageView) view1.findViewById(R.id.item_home_promotion_img);
        item_goods_empty = (ImageView) view1.findViewById(R.id.item_goods_empty);
        item_home_promotion_discount = (TextView) view1.findViewById(R.id.item_home_promotion_discount);
        item_home_promotion_desc = (TextView) view1.findViewById(R.id.item_home_promotion_desc);
        item_home_promotion_tag_time = (TextView) view1.findViewById(R.id.item_home_promotion_tag_time);
    }

    // 第二个视图
    private void initItem2(View view2) {

        btn_lover = (ImageView) view2.findViewById(R.id.btn_lover);
        btn_movie = (ImageView) view2.findViewById(R.id.btn_movie);
        btn_tea = (ImageView) view2.findViewById(R.id.btn_tea);
        btn_beer = (ImageView) view2.findViewById(R.id.btn_beer);
        home_item_title_left2 = (TextView) view2.findViewById(R.id.home_item_title_left);
        home_item_title_right2 = (TextView) view2.findViewById(R.id.home_item_title_right);
    }

    boolean flag = true;

    /**
     * 设置item1的数据
     */
    private void setHeader1Data() {
        // 第一个
        final List<HomeBean.DataEntity.BrandsEntity> brands = homeBean.getData().getBrands();
        x.image().bind(item_home_promotion_img, brands.get(0).getImg().getImg_url());
        item_home_promotion_discount.setText(brands.get(0).getDiscount());
        item_home_promotion_desc.setText(brands.get(0).getTitle());

        if (flag) {
            flag = false;
            // 倒计时
            new CountDownTimer(brands.get(0).getTime() * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long lsatTime = brands.get(0).getTime() - System.currentTimeMillis() / 1000;
                    item_home_promotion_tag_time.setText("剩余时间:" + DataUtils.formatTime(lsatTime));

                }

                @Override
                public void onFinish() {
                    // 显示过期提示图片
                    item_goods_empty.setVisibility(View.VISIBLE);
                    flag = true;
                }
            }.start();
        }

    }

    /**
     * 设置item2的数据
     */
    private void setHeader2Data() {
        // 第二个
        List<HomeBean.DataEntity.SpecialsEntity> specials = homeBean.getData().getSpecials();
        x.image().bind(btn_lover, specials.get(0).getImg().getImg_url());
        x.image().bind(btn_movie, specials.get(1).getImg().getImg_url());
        x.image().bind(btn_tea, specials.get(2).getImg().getImg_url());
        x.image().bind(btn_beer, specials.get(3).getImg().getImg_url());
        home_item_title_left2.setText(homeBean.getData().getNew_title_big());
        home_item_title_right2.setText(homeBean.getData().getNew_title_sml());
    }

    /**
     * headview子控件
     *
     * @param headerView
     */
    private void initHeadView(View headerView) {
        // 轮播图
        viewPager = (ViewPager) headerView.findViewById(R.id.vp_top_image);
        ll_top_points = (LinearLayout) headerView.findViewById(R.id.ll_top_points);
        home_item_title_left = (TextView) headerView.findViewById(R.id.home_item_title_left);
        home_item_title_right = (TextView) headerView.findViewById(R.id.home_item_title_right);
    }

    /**
     * 判断是否登录
     */
    private void checkLoading() {
        Log.e("TAG", "登录成功!");
        drawerleft_framelayout_login.setVisibility(View.VISIBLE);
        drawerleft_framelayout_unlogin.setVisibility(View.GONE);
        Log.e("TAG", "是否登录" + LoginUtils.getInstance().isLogin());
        if (LoginUtils.getInstance().isLogin()) {
            loginUtils = LoginUtils.getInstance();
            LoginBean loginBean = (LoginBean) loginUtils.getData();
            if (loginBean == null) {
                return;
            }
            LoginBean.DataEntity data = loginBean.getData();
            String nickname = data.getNickname();
            drawerleft_nick_name.setText(nickname);
            x.image().bind(drawerleft_image_avatar, data.getAvatar(), imageOption);
        } else {
            drawerleft_framelayout_login.setVisibility(View.GONE);
            drawerleft_framelayout_unlogin.setVisibility(View.VISIBLE);
            drawerleft_image_avatar.setImageResource(R.drawable.default_photo);
        }
    }

    /**
     * 点击按钮弹出左侧菜单
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 打开方向
            dl_menu.openDrawer(Gravity.LEFT);
            // 显示侧滑菜单数据
            if (homeBean != null && ib_left_menu.isShown()) {

                if (menuAdapter == null) {
                    menuView = dl_menu.getRootView();
                    initMenuView(menuView);
                }

                if (menuAdapter == null) {
                    menuAdapter = new LeftMenuAdapter(mActivity, homeBean);
                }
                // 判断是否登录
                checkLoading();

                // 点击登录
                drawerleft_to_login_page.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtils.loge("TAG", "跳转到登录界面");
                        Intent intent = new Intent(mActivity, MineContentActivity.class);
                        intent.putExtra("pager", Constants.LOGIN_PAGER);
                        mActivity.startActivity(intent);
                        dl_menu.closeDrawers();
                    }
                });
                // 点击注册
                drawerleft_to_registe_page.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, MineContentActivity.class);
                        intent.putExtra("pager", Constants.WEBVIEW_PAGER);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", "注册");
                        bundle.putString("url", atguigu.com.lingshixiaomiao.pager.mine.utils.Url.REGISTER_URL);
                        intent.putExtras(bundle);
                        mActivity.startActivity(intent);
                        dl_menu.closeDrawers();
                    }
                });
                // 个人中心
                drawerleft_to_person_page.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity mainActivity = (MainActivity) mActivity;
                        RadioGroup rg_main = (RadioGroup) mainActivity.findViewById(R.id.rg_main);
                        rg_main.check(R.id.rb_main_mine);
                        dl_menu.closeDrawers();
                    }
                });
                // 我的订单
                drawerleft_to_myorder_page.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* Intent intent = new Intent(mActivity, MineContentActivity.class);
                        intent.putExtra("pager", Constants.);
                        mActivity.startActivity(intent);
                        dl_menu.closeDrawers(); */

                        Intent intent = new Intent(mActivity, MineContentActivity.class);
                        intent.putExtra("pager", Constants.MINE_CART_PAGER);
                        mActivity.startActivity(intent);
                        dl_menu.closeDrawers();
                    }
                });


                left_drawer_list.setAdapter(menuAdapter);
            }
        }
    }

    /**
     * 初始化侧滑菜单布局控件
     *
     * @param menuView
     */
    private void initMenuView(View menuView) {
        left_drawer_list = (ListView) menuView.findViewById(R.id.left_drawer_list);
        drawerleft_image_avatar = (ImageView) menuView.findViewById(R.id.drawerleft_image_avatar);
        drawerleft_framelayout_login = (LinearLayout) menuView.findViewById(R.id.drawerleft_framelayout_login);
        drawerleft_nick_name = (TextView) menuView.findViewById(R.id.drawerleft_nick_name);
        drawerleft_to_person_page = (TextView) menuView.findViewById(R.id.drawerleft_to_person_page);
        drawerleft_to_myorder_page = (TextView) menuView.findViewById(R.id.drawerleft_to_myorder_page);
        drawerleft_framelayout_unlogin = (LinearLayout) menuView.findViewById(R.id.drawerleft_framelayout_unlogin);
        drawerleft_to_login_page = (TextView) menuView.findViewById(R.id.drawerleft_to_login_page);
        drawerleft_to_registe_page = (TextView) menuView.findViewById(R.id.drawerleft_to_registe_page);
        // 设置侧滑菜单点击监听
        setLeftMenuListener();

    }

    /**
     * 设置侧滑菜单点击监听
     */
    private void setLeftMenuListener() {
        // 侧滑菜单的item的点击监听
        left_drawer_list.setOnItemClickListener(new LeftMenuOnItemClickListener());
    }

    class LeftMenuOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LogUtils.loge("TAG", "点击了LeftMenu的第" + position + "项");
            Intent intent = new Intent(mActivity, LeftMenuItemActivity.class);
            intent.putExtra("itembeanTitle", homeBean.getData().getClassifies().get(position).getTitle());
            intent.putExtra("itembeanID", homeBean.getData().getClassifies().get(position).getId());
            mActivity.startActivity(intent);
            dl_menu.closeDrawers();
        }
    }

    /**
     * 注册EventBus
     */
    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    /**
     * 解注册EventBus
     */
    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    /**
     * 获取购物车商品数量
     * @param cartUtils
     */
    @Subscribe
    public void onEventMainThread(CartUtils cartUtils) {
        int goodsNum =  cartUtils.getGoodsNum();
        Log.e("TAG", "goodsNum = " + goodsNum);
        if (goodsNum > 0) {
            tv_shopcount.setVisibility(View.VISIBLE);
            tv_shopcount.setText(goodsNum + "");
        }else{
            tv_shopcount.setVisibility(View.INVISIBLE);
        }
    }

}
