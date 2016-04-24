package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.CollectionGoodsAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CollectGoodsBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/19.
 */
public class GoodsPager extends BasePager {

    private PullToRefreshListView lv_mine_collection_goods;
    public CollectionGoodsAdapter adapter;
    public List<CollectGoodsBean.DataEntity.ItemsEntity> data;
    private LinearLayout ll_loading;

    public GoodsPager(Activity mActivity) {
        super(mActivity);
        loadData();
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_collection_goods_pager, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        lv_mine_collection_goods = (PullToRefreshListView) v.findViewById(R.id.lv_mine_collection_goods);
        refresh();
        ll_loading = (LinearLayout) v.findViewById(R.id.ll_loading);
    }

    private void refresh() {
        lv_mine_collection_goods.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout loadingLayoutProxy = lv_mine_collection_goods.getLoadingLayoutProxy(true, false);
        loadingLayoutProxy.setPullLabel("下拉小喵开始刷新~");
        loadingLayoutProxy.setRefreshingLabel("小喵正在刷新哦~");
        loadingLayoutProxy.setReleaseLabel("松手小喵开始刷新~");
        ILoadingLayout loadingLayoutProxy1 = lv_mine_collection_goods.getLoadingLayoutProxy(false, true);
        loadingLayoutProxy1.setPullLabel("上拉小喵开始加载更多~");
        loadingLayoutProxy1.setRefreshingLabel("小喵正在加载更多哦~");
        loadingLayoutProxy1.setReleaseLabel("松手小喵开始加载更多~");

        lv_mine_collection_goods.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                loadMore();
            }
        });
    }

    private boolean isPull = true;

    private void loadMore() {
        isPull = false;
        prePager = currentPager++;
        load();
    }


    @Override
    public void initData() {
        super.initData();
    }

    private int currentPager = 1;
    private int prePager = 1;
    private int sinceId = 0;
    private int pageSize = 4;

    private void loadData() {
        isPull = true;
        currentPager = 1;
        load();
    }

    private void load() {
        showLoadingAnim();
        LoginBean data = (LoginBean) LoginUtils.getInstance().getData();
        String uid = data.getData().getUid();
        String url = Url.COLLECTION_GOODS_URL[0] + uid + Url.COLLECTION_GOODS_URL[1] +
                currentPager + Url.COLLECTION_GOODS_URL[2] + pageSize + Url.COLLECTION_GOODS_URL[3]
                + sinceId + Url.COLLECTION_GOODS_URL[4];
        new JsonUtils().loadData(url, CollectGoodsBean.class);
    }

    private void showLoadingAnim() {
        ll_loading.setVisibility(View.VISIBLE);
        ImageView iv_loading = (ImageView) ll_loading.findViewById(R.id.iv_loading);
        AnimationDrawable loading = (AnimationDrawable) iv_loading.getBackground();
        loading.start();
    }

    private void hindLoadingAnim() {
        ll_loading.setVisibility(View.GONE);
        lv_mine_collection_goods.onRefreshComplete();
    }

    /**
     * 获取收藏商品返回信息
     *
     * @param collectGoodsBean
     */

    @Subscribe
    public void onEventMainThread(CollectGoodsBean collectGoodsBean) {
        hindLoadingAnim();
        if (Constants.SUCCESS.equals(collectGoodsBean.getRs_code())) {
            if (isPull) {
                data = collectGoodsBean.getData().getItems();
                parseData();
            } else {
                addMore(collectGoodsBean);
            }
        } else {
            Toast.makeText(mActivity, collectGoodsBean.getRs_msg(), Toast.LENGTH_SHORT).show();
            currentPager = prePager;
        }
    }

    private void addMore(CollectGoodsBean collectGoodsBean) {
        List<CollectGoodsBean.DataEntity.ItemsEntity> items = collectGoodsBean.getData().getItems();
        this.data.addAll(items);
        parseData();

    }

    public void parseData() {
        sortData();

        sinceId = data.get(data.size()-1).getId();

        adapter = new CollectionGoodsAdapter(mActivity, data);
        lv_mine_collection_goods.setAdapter(adapter);
    }

    private void sortData() {
        Collections.sort(data, new Comparator<CollectGoodsBean.DataEntity.ItemsEntity>() {
            @Override
            public int compare(CollectGoodsBean.DataEntity.ItemsEntity lhs, CollectGoodsBean.DataEntity.ItemsEntity rhs) {
                return lhs.getStatus() > rhs.getStatus() ? -1 : 0;
            }
        });
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

}
