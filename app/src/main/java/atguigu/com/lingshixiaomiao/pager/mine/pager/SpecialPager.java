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
import com.lanmang.lanmang_library.net.CallBack;
import com.lanmang.lanmang_library.net.NetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.CollSpecialAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CollSpecialBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/19.
 */
public class SpecialPager extends BasePager {

    public List<CollSpecialBean.DataEntity.ItemsEntity> data;
    private PullToRefreshListView lv_mine_coll_special;
    public CollSpecialAdapter adapter;
    private LinearLayout ll_loading;

    public SpecialPager(Activity mActivity) {
        super(mActivity);
        loadData();
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_collection_special_pager, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        lv_mine_coll_special = (PullToRefreshListView) v.findViewById(R.id.lv_mine_coll_special);
        refresh();
        ll_loading = (LinearLayout) v.findViewById(R.id.ll_loading);
    }

    private void refresh() {
        lv_mine_coll_special.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout loadingLayoutProxy = lv_mine_coll_special.getLoadingLayoutProxy(true, false);
        loadingLayoutProxy.setPullLabel("下拉小喵开始刷新~");
        loadingLayoutProxy.setRefreshingLabel("小喵正在刷新哦~");
        loadingLayoutProxy.setReleaseLabel("松手小喵开始刷新~");
        ILoadingLayout loadingLayoutProxy1 = lv_mine_coll_special.getLoadingLayoutProxy(false, true);
        loadingLayoutProxy1.setPullLabel("上拉小喵开始加载更多~");
        loadingLayoutProxy1.setRefreshingLabel("小喵正在加载更多哦~");
        loadingLayoutProxy1.setReleaseLabel("松手小喵开始加载更多~");

        lv_mine_coll_special.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

    @Override
    public void initData() {
        super.initData();
    }

    private int currentPager = 1;
    private int prePager = 1;
    private int sinceId = 0;
    private int pageSize = 4;
    private boolean isPull = true;

    private void loadData() {
        isPull = true;
        currentPager = 1;
        load();
    }

    private void loadMore() {
        isPull = false;
        prePager = currentPager++;
        load();
    }

    private void load() {
        showLoadingAnim();
        String[] u = Url.COLLECTION_SPECIAL_URL;
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String uid = loginBean.getData().getUid();
        String url = u[0] + uid + u[1] + currentPager + u[2] + pageSize + u[3] + sinceId;
        new JsonUtils().loadData(url, CollSpecialBean.class);
        //loadNetData(url);
    }

    private void loadNetData(String url) {
        NetUtils.linkForJson(url, CollSpecialBean.class, new CallBack<CollSpecialBean>() {

            @Override
            public void onSuccess(CollSpecialBean result) {
                success(result);
            }
        });
    }

    private void showLoadingAnim() {
        ll_loading.setVisibility(View.VISIBLE);
        ImageView iv_loading = (ImageView) ll_loading.findViewById(R.id.iv_loading);
        AnimationDrawable loading = (AnimationDrawable) iv_loading.getBackground();
        loading.start();
    }

    private void hindLoadingAnim() {
        ll_loading.setVisibility(View.GONE);
        lv_mine_coll_special.onRefreshComplete();
    }

    @Subscribe
    public void onEventMainThread(CollSpecialBean collSpecialBean) {
        success(collSpecialBean);
    }

    private void success(CollSpecialBean collSpecialBean) {
        hindLoadingAnim();
        if (Constants.SUCCESS.equals(collSpecialBean.getRs_code())) {
            if (isPull) {
                data = collSpecialBean.getData().getItems();
                sinceId = data.get(data.size() - 1).getId();
                loadPager();
            } else {
                List<CollSpecialBean.DataEntity.ItemsEntity> items = collSpecialBean.getData().getItems();
                data.addAll(items);
                adapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(mActivity, collSpecialBean.getRs_msg(), Toast.LENGTH_SHORT).show();
            currentPager = prePager;
        }
    }

    public void loadPager() {
        adapter = new CollSpecialAdapter(mActivity, data);
        lv_mine_coll_special.setAdapter(adapter);
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
