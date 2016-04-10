package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.home.adapter.MyAdapter;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomeTopBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.CarouselUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.NetWorkUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.SPUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Url;

/**
 * 首页
 */
public class HomePager extends BasePager {

    private ImageButton ib_left_menu;
    private TextView tv_search;
    private RelativeLayout rl_cart;
    private LinearLayout lv_left_menu;
    private DrawerLayout dl_menu;
    private ViewPager vp_top_image;
    private SwipeRefreshLayout refreshlayout_home;
    private RecyclerView recyclerview_home;

    /**
     * 顶部数据
     */
    private HomeTopBean homeTopBean;
    // 顶部轮播图数据
    private List<HomeTopBean.DataEntity.TopicsEntity> topics;
    private LinearLayout ll_top_points;
    private MyAdapter recyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    public HomePager(Activity mActivity, DrawerLayout dl_menu) {
        super(mActivity);
        this.dl_menu = dl_menu;
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.home_pager, null);
        findViewById(inflate);
        addHeaderView();
        setListener();
        return inflate;
    }

    /**
     * 加载顶部轮播图---加载更新
     */
    private void addHeaderView() {
        // 设置颜色
        refreshlayout_home.setColorSchemeColors(R.color.color1, R.color.color2, R.color.color3);
        // 第一次进入页面的时候显示加载进度条
        refreshlayout_home.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, mActivity.getResources()
                        .getDisplayMetrics()));

        refreshlayout_home.setOnRefreshListener(new MyOnRefreshListener());
        /*refreshlayout_home.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                LogUtils.loge("TAG", "已加载更多");
            }
        });*/
        // 滚动监听
        recyclerview_home.addOnScrollListener(new MyOnScrollListener());
    }

    class MyOnScrollListener extends RecyclerView.OnScrollListener {

        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LogUtils.loge("TAG", newState + "");
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem  + 1 == recyclerViewAdapter.getItemCount()) {
                refreshlayout_home.setRefreshing(true);
                // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                //handler.sendEmptyMessageDelayed(0, 3000);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
        }
    }

    class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            refreshlayout_home.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshlayout_home.setRefreshing(false);
                }
            }, 2000);
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //设置按钮点击弹出侧滑菜单
        ib_left_menu.setOnClickListener(new MyOnClickListener());

    }

    /**
     * 通过id获取view
     *
     * @param parent
     */
    private void findViewById(View parent) {
        ib_left_menu = (ImageButton) parent.findViewById(R.id.ib_left_menu);
        tv_search = (TextView) parent.findViewById(R.id.tv_search);
        rl_cart = (RelativeLayout) parent.findViewById(R.id.rl_cart);
        lv_left_menu = (LinearLayout) parent.findViewById(R.id.lv_left_menu);
        refreshlayout_home = (SwipeRefreshLayout) parent.findViewById(R.id.refreshlayout_home);
        recyclerview_home = (RecyclerView) parent.findViewById(R.id.recyclerview_home);

        linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        List<String> data = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            data.add("" + (char) i);
        }

        recyclerViewAdapter = new MyAdapter(mActivity, data);
        LayoutInflater layoutInflater = mActivity.getLayoutInflater();
        View headerView = layoutInflater.inflate(R.layout.home_top, null);

        vp_top_image = (ViewPager) headerView.findViewById(R.id.vp_top_image);
        ll_top_points = (LinearLayout) headerView.findViewById(R.id.ll_top_points);

        recyclerViewAdapter.addHeadView(headerView);
        // 设置布局管理器
        recyclerview_home.setLayoutManager(linearLayoutManager);
        recyclerview_home.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("首页 开始加载首页数据");

        //获取保存的首页数据
        String homeData = SPUtils.getString(mActivity, SPUtils.HOME_TOP_DATA);
        if (homeData != null && !NetWorkUtils.isNetworkConnected()) {
            homeTopBean = new Gson().fromJson(homeData, HomeTopBean.class);
            topics = this.homeTopBean.getData().getTopics();
            //设置顶部轮播图
            new CarouselUtils(vp_top_image, ll_top_points, mActivity).setViewPagerData(topics);
        }

        //通过JsonUtils工具类解析url, 并通过EventBus返回数据
        new JsonUtils().loadData(Url.HOME_TOP_URL, HomeTopBean.class);

    }

    /**
     * 使用EventBus接收解析后的数据
     *
     * @param homeTopBean
     */
    @Subscribe
    public void onEventMainThread(HomeTopBean homeTopBean) {
        LogUtils.loge("数据解析成功 : " + homeTopBean.toString());
        this.homeTopBean = homeTopBean;
        topics = homeTopBean.getData().getTopics();

        if (homeTopBean != null) {
            //设置顶部轮播图
            new CarouselUtils(vp_top_image, ll_top_points, mActivity).setViewPagerData(topics);
        }
    }

    /**
     * 点击按钮弹出左侧菜单
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dl_menu.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void registerEventBus() {
        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void unRegisterEventBus() {
        //解注册EventBus
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

}
