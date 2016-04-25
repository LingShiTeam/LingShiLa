package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import atguigu.com.lingshixiaomiao.pager.mine.pager.OrderItemPager;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;

/**
 * Created by lanmang on 2016/4/21.
 */
public class MineOrderAdapter extends PagerAdapter {

    private String[] titles = {"全部", "待付款", "待发货", "待收货", "待评价"};
    private Activity mActivity;


    public MineOrderAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String type = getType(position);
        OrderItemPager orderItemPager = new OrderItemPager(mActivity, type);
        View pager = orderItemPager.rootView;
        container.addView(pager);
        return pager;
    }

    @Nullable
    private String getType(int position) {
        String type = null;
        switch (position) {
            case 0:
                type = Constants.ORDER_TYPE_ALL;
                break;
            case 1:
                type = Constants.ORDER_TYPE_WAIT_PAY;
                break;
            case 2:
                type = Constants.ORDER_TYPE_WAIT_SEND;
                break;
            case 3:
                type = Constants.ORDER_TYPE_WAIT_RECEIVE;
                break;
            case 4:
                type = Constants.ORDER_TYPE_WAIT_COMMENT;
                break;
        }
        return type;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
