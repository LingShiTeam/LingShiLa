package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.TabBasePager;
import atguigu.com.lingshixiaomiao.pager.home.adapter.TabItemAdapter;
import atguigu.com.lingshixiaomiao.pager.home.bean.TabDataBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.scale.activity.SnackInfomationActivity;

/**
 * Created by Liu_haiwei on 2016/4/16.
 * 侧滑菜单详情页面
 */
public class TabPager extends TabBasePager {

    private final String tabUrl;
    private TabDataBean tabDataBean;
    private TabItemAdapter adapter;
    private ImageView widget_loading_pb;
    private AnimationDrawable ad;
    private PullToRefreshGridView tab_gridview;
    private RelativeLayout loading_dialog;
    private List<TabDataBean.DataEntity.ItemsEntity> dataLists;
    private boolean isLoadMore;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param tabUrl
     */
    public TabPager(Activity mActivity, String tabUrl) {
        super(mActivity);
        Log.e("TAG", "tabUrl == " + tabUrl);
        this.tabUrl = tabUrl;
        registerEventBus();
    }

    @Override
    public View initView() {
        View view = View.inflate(mContent, R.layout.tab_item_layout, null);
        tab_gridview = (PullToRefreshGridView) view.findViewById(R.id.tab_gridview);
        // 下拉刷新时的提示文本设置
        tab_gridview.getLoadingLayoutProxy(true, true);
        tab_gridview.getLoadingLayoutProxy(true, false).setPullLabel("精选全宇宙美食,100%保证正品");
        tab_gridview.getLoadingLayoutProxy(true, false).setRefreshingLabel("精选全宇宙美食,100%保证正品");
        tab_gridview.getLoadingLayoutProxy(true, false).setReleaseLabel("精选全宇宙美食,100%保证正品");
        // 上拉加载更多时的提示文本设置
        tab_gridview.getLoadingLayoutProxy(false, true).setPullLabel(null);
        tab_gridview.getLoadingLayoutProxy(false, true).setRefreshingLabel(null);
        tab_gridview.getLoadingLayoutProxy(false, true).setReleaseLabel(null);

        loading_dialog = (RelativeLayout) view.findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) view.findViewById(R.id.widget_loading_pb);
        ad = (AnimationDrawable) widget_loading_pb.getBackground();
        return view;
    }

    @Override
    public void initData() {
        loading_dialog.setVisibility(View.VISIBLE);
        tab_gridview.setVisibility(View.GONE);
        ad.start();

        super.initData();
        dataLists = new ArrayList<>();
        // 解析数据
        new JsonUtils().loadData(tabUrl, TabDataBean.class);
        // 设置监听
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        tab_gridview.setOnRefreshListener(new MyOnRefreshListener());
        // GridView的item的点击监听
        tab_gridview.setOnItemClickListener(new MyOnItemClickListener());
    }

    /**
     * GridView的item的点击监听
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(mContent, SnackInfomationActivity.class);
            intent.putExtra("snack_id", dataLists.get(position).getId());
            mContent.startActivity(intent);
        }
    }

    private int page = 1;

    /**
     * PullToRefresh的刷新监听
     */
    class MyOnRefreshListener implements PullToRefreshBase.OnRefreshListener2 {

        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            getDataFromNet(tabUrl);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            isLoadMore = true;
            String[] curs = tabUrl.split("pg_cur=1");
            page++;
            String subUrl = curs[0] + "pg_cur=" + page + curs[1];
            getDataFromNet(subUrl);
        }
    }

    /**
     * 解析数据
     * @param subUrl
     */
    private void getDataFromNet(String subUrl) {
        RequestParams params = new RequestParams(subUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(result != null) {
                    parsedJson(result);
                } else {
                    Toast.makeText(mContent,"没有更多数据!",Toast.LENGTH_SHORT).show();
                }

                tab_gridview.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                tab_gridview.onRefreshComplete();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                tab_gridview.onRefreshComplete();
            }

            @Override
            public void onFinished() {
                tab_gridview.onRefreshComplete();
            }
        });
    }

    /**
     * 解析Json数据
     * @param result
     */
    private void parsedJson(String result) {
        tabDataBean = new Gson().fromJson(result, TabDataBean.class);
        if (!isLoadMore) {
            dataLists.clear();
            dataLists.addAll(tabDataBean.getData().getItems());
            if (adapter == null) {
                adapter = new TabItemAdapter(mContent, tabDataBean,dataLists);
            }
            tab_gridview.setAdapter(adapter);
        } else {
            dataLists.addAll(tabDataBean.getData().getItems());
            adapter.notifyDataSetChanged();
            isLoadMore = false;
        }
    }

    /**
     * 使用EventBus接收解析后的数据--TabDataBean
     *
     * @param tabDataBean
     */
    @Subscribe
    public void onEventMainThread(TabDataBean tabDataBean) {
        this.tabDataBean = tabDataBean;
        if (tabDataBean != null) {

            dataLists.addAll(tabDataBean.getData().getItems());
            if (adapter == null) {
                adapter = new TabItemAdapter(mContent, tabDataBean,dataLists);
            }
            tab_gridview.setAdapter(adapter);

        }
        // 停止加载
        stopLoading();
        // 解注册EventBus
        unRegisterEventBus();
    }

    private void stopLoading() {
        ad.stop();
        loading_dialog.setVisibility(View.GONE);
        tab_gridview.setVisibility(View.VISIBLE);
    }

    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 解注册EventBus
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
