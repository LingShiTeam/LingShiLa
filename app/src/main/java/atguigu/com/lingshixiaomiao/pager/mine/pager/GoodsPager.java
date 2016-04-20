package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    private ListView lv_mine_collection_goods;
    public CollectionGoodsAdapter adapter;
    public List<CollectGoodsBean.DataEntity.ItemsEntity> data;

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
        lv_mine_collection_goods = (ListView)v.findViewById(R.id.lv_mine_collection_goods);
    }

    @Override
    public void initData() {
        super.initData();

    }

    private int currentPager = 1;
    private int sinceId = 0;

    private void loadData() {
        LoginBean data = (LoginBean) LoginUtils.getInstance().getData();
        String uid = data.getData().getUid();
        String url = Url.COLLECTION_GOODS_URL[0] + uid + Url.COLLECTION_GOODS_URL[1] +
                currentPager + Url.COLLECTION_GOODS_URL[2] + sinceId + Url.COLLECTION_GOODS_URL[3];
        new JsonUtils().loadData(url, CollectGoodsBean.class);
    }

    /**
     * 获取收藏商品返回信息
     *
     * @param collectGoodsBean
     */

    private CollectGoodsBean collectGoodsBean;

    @Subscribe
    public void onEventMainThread(CollectGoodsBean collectGoodsBean) {
        if (Constants.SUCCESS.equals(collectGoodsBean.getRs_code())) {
            this.collectGoodsBean = collectGoodsBean;
            parseData();
        }else{
            Toast.makeText(mActivity, collectGoodsBean.getRs_msg(), Toast.LENGTH_SHORT).show();
        }
    }

    public void parseData() {
        data = collectGoodsBean.getData().getItems();
        adapter = new CollectionGoodsAdapter(mActivity, data);
        lv_mine_collection_goods.setAdapter(adapter);
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
