package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import atguigu.com.lingshixiaomiao.base.BasePager;


/**
 * Created by lanmang on 2016/4/19.
 */
public class CollectionViewPagerAdapter extends PagerAdapter {

    private Activity mActivity;
    private List<BasePager> pagers;

    public CollectionViewPagerAdapter(Activity mActivity, List<BasePager> pagers) {
        this.mActivity = mActivity;
        this.pagers = pagers;
    }

    @Override
    public int getCount() {
        return pagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager basePager = pagers.get(position);
        View rootView = basePager.rootView;
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
