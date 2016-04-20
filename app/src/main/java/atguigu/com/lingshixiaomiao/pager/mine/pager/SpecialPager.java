package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

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
    private ListView lv_mine_coll_special;
    public CollSpecialAdapter adapter;

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
        lv_mine_coll_special = (ListView)v.findViewById(R.id.lv_mine_coll_special);
    }

    @Override
    public void initData() {
        super.initData();
    }

    private int currentPage = 1;
    private int sinceId = 0;

    private void loadData() {
        String[] u = Url.COLLECTION_SPECIAL_URL;
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String uid = loginBean.getData().getUid();
        String url = u[0] + uid + u[1] + currentPage + u[2] + sinceId;
        new JsonUtils().loadData(url, CollSpecialBean.class);
    }

    @Subscribe
    public void onEventMainThread(CollSpecialBean collSpecialBean) {
        if (Constants.SUCCESS.equals(collSpecialBean.getRs_code())) {
            data = collSpecialBean.getData().getItems();
            loadPager();
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
