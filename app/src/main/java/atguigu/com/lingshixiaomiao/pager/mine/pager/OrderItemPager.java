package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.MainActivity;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.MineOrderItemAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.OrderBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/21.
 */
public class OrderItemPager extends BasePager {

    private LinearLayout ll_loading;
    private PullToRefreshListView lv_mine_order;
    private LinearLayout ll_mine_order_null;
    private ImageView iv_loading;
    private TextView tv_mine_random;
    private String type;
    private final String uid;
    private int pageSize = 1;
    private List<OrderBean.DataEntity.ItemsEntity> items;
    private MineOrderItemAdapter adapter;

    public OrderItemPager(Activity mActivity, String type) {
        super(mActivity);
        this.type = type;
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        uid = loginBean.getData().getUid();
        loadData();
    }

    private int currentPager = 0;
    private int prePager = 1;
    private boolean isLoadMore = false;

    private void loadData() {
        ll_mine_order_null.setVisibility(View.GONE);
        if (isLoadMore) {
            prePager = currentPager++;
        } else {
            currentPager = 1;
        }


        showLoadingAnim();
        String[] u = Url.ORDER_URL;
        String url = u[0] + uid + u[1] + currentPager + u[2] + pageSize + u[3] + type + u[4];
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.loge("order result = " + result);
                hindLoadingAnim();
                paraseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("order onError");
                currentPager = prePager;
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("order onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtils.loge("order onFinished");

            }
        });
    }

    private void paraseJson(String json) {
        OrderBean orderBean = new Gson().fromJson(json, OrderBean.class);
        if (Constants.SUCCESS.equals(orderBean.getRs_code())) {

            if (isLoadMore) {
                List<OrderBean.DataEntity.ItemsEntity> items = orderBean.getData().getItems();
                this.items.addAll(items);
                adapter.notifyDataSetChanged();
            } else {
                items = orderBean.getData().getItems();
                addData();
            }
        } else {
            //Toast.makeText(mActivity, orderBean.getRs_msg(), Toast.LENGTH_SHORT).show();
            currentPager = prePager;
            if (items == null || items.size() == 0) {
                ll_mine_order_null.setVisibility(View.VISIBLE);
            }
            tv_mine_random.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.startActivity(new Intent(mActivity, MainActivity.class));
                    Constants.isToHome = true;
                    mActivity.finish();
                }
            });
        }
    }

    private void addData() {
        adapter = new MineOrderItemAdapter(mActivity, items);
        lv_mine_order.setAdapter(adapter);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_order_pager, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        ll_loading = (LinearLayout) v.findViewById(R.id.ll_loading);
        lv_mine_order = (PullToRefreshListView) v.findViewById(R.id.lv_mine_order);
        ll_mine_order_null = (LinearLayout) v.findViewById(R.id.ll_mine_order_null);
        tv_mine_random = (TextView) v.findViewById(R.id.tv_mine_random);
        refresh();
    }

    private void refresh() {
        lv_mine_order.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout loadingLayoutProxy = lv_mine_order.getLoadingLayoutProxy(true, false);
        loadingLayoutProxy.setPullLabel("下拉小喵开始刷新~");
        loadingLayoutProxy.setRefreshingLabel("小喵正在刷新哦~");
        loadingLayoutProxy.setReleaseLabel("松手小喵开始刷新~");
        ILoadingLayout loadingLayoutProxy1 = lv_mine_order.getLoadingLayoutProxy(false, true);
        loadingLayoutProxy1.setPullLabel("上拉小喵开始加载更多~");
        loadingLayoutProxy1.setRefreshingLabel("小喵正在加载更多哦~");
        loadingLayoutProxy1.setReleaseLabel("松手小喵开始加载更多~");

        lv_mine_order.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                isLoadMore = false;
                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                isLoadMore = true;
                loadData();
            }
        });
    }


    private void showLoadingAnim() {
        ll_loading.setVisibility(View.VISIBLE);
        iv_loading = (ImageView) ll_loading.findViewById(R.id.iv_loading);
        AnimationDrawable loading = (AnimationDrawable) iv_loading.getBackground();
        loading.start();
    }

    private void hindLoadingAnim() {
        ll_loading.setVisibility(View.GONE);
        lv_mine_order.onRefreshComplete();
    }
}
