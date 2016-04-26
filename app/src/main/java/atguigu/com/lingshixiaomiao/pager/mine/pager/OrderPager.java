package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.MineOrderAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;

/**
 * Created by lanmang on 2016/4/10.
 */
public class OrderPager extends ContentBasePager {

    private int position;
    private ViewPager mine_pager_orders;
    private TabLayout tl_mine_orders_title;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param bundle
     */
    public OrderPager(Activity mActivity, Bundle bundle) {
        super(mActivity);
        position = bundle.getInt("position");
        addPager();
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_orders_shop, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        mine_pager_orders = (ViewPager) v.findViewById(R.id.mine_pager_orders);
        tl_mine_orders_title = (TabLayout) v.findViewById(R.id.tl_mine_orders_title);
    }

    @Override
    public String setTitle() {
        return "我的订单";
    }

    @Override
    public void initData() {
        super.initData();

    }

    private void addPager() {
        MineOrderAdapter adapter = new MineOrderAdapter(mActivity);
        mine_pager_orders.setAdapter(adapter);
        mine_pager_orders.setCurrentItem(position);
        tl_mine_orders_title.setupWithViewPager(mine_pager_orders);
    }

}
