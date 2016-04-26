package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import atguigu.com.lingshixiaomiao.pager.home.adapter.ShoppingGridAdapter;
import atguigu.com.lingshixiaomiao.pager.home.bean.ShoppingListBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.scale.activity.SnackInfomationActivity;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 商品列表页面
 */
public class ShoppingListActivity extends SwipeBackActivity implements View.OnClickListener {

    private PullToRefreshGridView shoppinglist_gridview;
    private RelativeLayout loading_dialog;
    private ImageView widget_loading_pb;
    private AnimationDrawable ad;
    private ImageButton ib_left_menu;
    private ImageView iv_search_back;
    private ImageView iv_cart;
    private TextView tv_shopcount;
    private String shoppinglisturl;
    private ShoppingListBean shoppingListBean;
    private List<ShoppingListBean.DataEntity.ItemsEntity> dataList;
    private ShoppingGridAdapter adapter;
    private boolean isLoadMore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        registerEventBus();
        initView();
        initData();
        // 侧滑退出activity
        setSwipeBack();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        loading_dialog.setVisibility(View.VISIBLE);
        shoppinglist_gridview.setVisibility(View.GONE);
        ad.start();
        // 获取数据
        shoppinglisturl = getIntent().getStringExtra("shoppinglist");
        dataList = new ArrayList<>();
        // 解析数据
        new JsonUtils().loadData(shoppinglisturl, ShoppingListBean.class);

        // 设置监听
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        shoppinglist_gridview.setOnRefreshListener(new MyOnRefreshListener());
        // GridView的item的点击监听
        shoppinglist_gridview.setOnItemClickListener(new MyOnItemClickListener());
        iv_search_back.setOnClickListener(this);
    }


    /**
     * 点击监听的回调方法
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_back : // 返回
                finish();
                break;
        }
    }

    /**
     * GridView的item的点击监听
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ShoppingListActivity.this, SnackInfomationActivity.class);
            intent.putExtra("snack_id", dataList.get(position).getId());
            startActivity(intent);
        }
    }

    private int page = 1;

    /**
     * PullToRefresh的刷新监听
     */
    class MyOnRefreshListener implements PullToRefreshBase.OnRefreshListener2 {

        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            getDataFromNet(shoppinglisturl);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            isLoadMore = true;
            String[] curs = shoppinglisturl.split("pg_cur=1");
            page++;
            String subUrl = curs[0] + "pg_cur=" + page + curs[1];
            getDataFromNet(subUrl);
        }
    }


    /**
     * 解析数据
     *
     * @param subUrl
     */
    private void getDataFromNet(String subUrl) {
        RequestParams params = new RequestParams(subUrl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    parsedJson(result);
                } else {
                    Toast.makeText(ShoppingListActivity.this, "没有更多数据!", Toast.LENGTH_SHORT).show();
                }

                shoppinglist_gridview.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                shoppinglist_gridview.onRefreshComplete();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                shoppinglist_gridview.onRefreshComplete();
            }

            @Override
            public void onFinished() {
                shoppinglist_gridview.onRefreshComplete();
            }
        });
    }

    /**
     * 解析Json数据
     *
     * @param result
     */
    private void parsedJson(String result) {
        shoppingListBean = new Gson().fromJson(result, ShoppingListBean.class);
        if (!isLoadMore) {
            dataList.clear();
            dataList.addAll(shoppingListBean.getData().getItems());
            if (adapter == null) {
                adapter = new ShoppingGridAdapter(this, dataList);
            }
            shoppinglist_gridview.setAdapter(adapter);
        } else {
            dataList.addAll(shoppingListBean.getData().getItems());
            adapter.notifyDataSetChanged();
            isLoadMore = false;
        }
    }

    /**
     * 使用EventBus接收解析后的数据-ShoppingListBean
     *
     * @param shoppingListBean
     */
    @Subscribe
    public void onEventMainThread(ShoppingListBean shoppingListBean) {
        Log.e("TAG", "商品列表数据解析成功");
        this.shoppingListBean = shoppingListBean;
        if (shoppingListBean != null) {
            dataList.addAll(shoppingListBean.getData().getItems());
            if (adapter == null) {
                adapter = new ShoppingGridAdapter(this, dataList);
            }
            shoppinglist_gridview.setAdapter(adapter);
        }

        stopLoading();
        // 解注册EventBus
        unRegisterEventBus();
    }

    /**
     * 停止加载
     */
    private void stopLoading() {
        ad.stop();
        loading_dialog.setVisibility(View.GONE);
        shoppinglist_gridview.setVisibility(View.VISIBLE);
    }

    /**
     * 侧滑退出activity
     */
    private void setSwipeBack() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        shoppinglist_gridview = (PullToRefreshGridView) findViewById(R.id.shoppinglist_gridview);
        // 下拉刷新时的提示文本设置
        shoppinglist_gridview.getLoadingLayoutProxy(true, true);
        shoppinglist_gridview.getLoadingLayoutProxy(true, false).setPullLabel("精选全宇宙美食,100%保证正品");
        shoppinglist_gridview.getLoadingLayoutProxy(true, false).setRefreshingLabel("精选全宇宙美食,100%保证正品");
        shoppinglist_gridview.getLoadingLayoutProxy(true, false).setReleaseLabel("精选全宇宙美食,100%保证正品");
        // 上拉加载更多时的提示文本设置
        shoppinglist_gridview.getLoadingLayoutProxy(false, true).setPullLabel(null);
        shoppinglist_gridview.getLoadingLayoutProxy(false, true).setRefreshingLabel(null);
        shoppinglist_gridview.getLoadingLayoutProxy(false, true).setReleaseLabel(null);

        loading_dialog = (RelativeLayout) findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) findViewById(R.id.widget_loading_pb);
        ad = (AnimationDrawable) widget_loading_pb.getBackground();

        ib_left_menu = (ImageButton) findViewById(R.id.ib_left_menu);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        EditText et_search = (EditText) findViewById(R.id.et_search);
        TextView tv_shopname = (TextView) findViewById(R.id.tv_shopname);
        iv_cart = (ImageView) findViewById(R.id.iv_cart);
        tv_shopcount = (TextView) findViewById(R.id.tv_shopcount);

        ib_left_menu.setVisibility(View.GONE);
        iv_search_back.setVisibility(View.VISIBLE);
        et_search.setVisibility(View.GONE);
        tv_shopname.setVisibility(View.VISIBLE);
        tv_shopname.setText("商品列表");
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
