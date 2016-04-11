package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.home.adapter.ListViewAdapter;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomePagerBean;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomeTopBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.CarouselUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.NetWorkUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.SPUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Url;
import atguigu.com.lingshixiaomiao.pager.home.view.RefreshLayout;

/**
 * 首页
 */
public class HomePager extends BasePager {

    private ImageButton ib_left_menu;
    private TextView tv_search;
    private RelativeLayout rl_cart;
    private LinearLayout lv_left_menu;
    private DrawerLayout dl_menu;
    private RefreshLayout refreshlayout_home;

    /**
     * 顶部数据
     */
    private HomeTopBean homeTopBean;
    // 顶部轮播图数据
    private List<HomeTopBean.DataEntity.TopicsEntity> topics;
    private LinearLayout ll_top_points;
    private ListView listview_home;
    private View headerView;
    private ListViewAdapter adapter;
    // 分页数据
    private HomePagerBean homePagerBean;
    private List<HomePagerBean.DataEntity.ItemsEntity> pagerData;

    public HomePager(Activity mActivity, DrawerLayout dl_menu) {
        super(mActivity);
        this.dl_menu = dl_menu;
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.home_pager, null);
        findViewById(inflate);
        //addHeaderView();
        setListener();
        return inflate;
    }
    // home当前数据页
    int pagerIndex = 1;
    /**
     * 请求数据
     * http://api.ds.lingshi.cccwei.com/?cid=760294&uid=0&tms=20160406224555&sig" +
     "=77fe35c8027c2e4a&wssig=e4fe9113b21617de&os_type=3&version=18&channel_name=
     feibo&srv=2206&since_id=0&pg_cur=1&pg_size=20";
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
            }

            refreshlayout_home.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshlayout_home.setRefreshing(false);
                }
            }, 2000);
        }
    }

    /**
     * 上拉加载更多
     */
    class MyOnLoadListener implements RefreshLayout.OnLoadListener {

        @Override
        public void onLoad() {
            if(!NetWorkUtils.isNetworkConnected()) {
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
        refreshlayout_home = (RefreshLayout) parent.findViewById(R.id.refreshlayout_home);
        listview_home = (ListView) parent.findViewById(R.id.listview_home);

        // 显示主界面
        setListView();
    }

    /**
     * 设置主界面listview的显示
     */
    private void setListView() {
        // 加载头布局
        LayoutInflater layoutInflater = mActivity.getLayoutInflater();
        headerView = layoutInflater.inflate(R.layout.home_top, null);
        listview_home.addHeaderView(headerView);

        ll_top_points = (LinearLayout) headerView.findViewById(R.id.ll_top_points);


    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("首页 开始加载首页数据");

        pagerData = new ArrayList<>();

        //获取保存的首页数据
        String homeData = SPUtils.getString(mActivity, SPUtils.HOME_TOP_DATA);
        if (homeData != null && !NetWorkUtils.isNetworkConnected()) {
            homeTopBean = new Gson().fromJson(homeData, HomeTopBean.class);
            //设置顶部轮播图
            new CarouselUtils(headerView, mActivity).setViewPagerData(homeTopBean);
        }

        String homePageData = SPUtils.getString(mActivity, SPUtils.HOME_PAGE_DATA);
        if (homePageData != null && !NetWorkUtils.isNetworkConnected()) {
            parsedHomePageJson(homePageData);
            showListView();
        }

        //通过JsonUtils工具类解析url, 并通过EventBus返回数据
        new JsonUtils().loadData(Url.HOME_TOP_URL, HomeTopBean.class);

    }

    /**
     * 显示listView列表
     */
    private void showListView() {
        if (adapter == null) {
            adapter = new ListViewAdapter(mActivity, pagerData);
        }
        listview_home.setAdapter(adapter);
    }

    /**
     * 使用EventBus接收解析后的数据--HomePagerBean
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
            new CarouselUtils(headerView, mActivity).setViewPagerData(homeTopBean);
            // 显示ListView列表
            showListView();
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

}
