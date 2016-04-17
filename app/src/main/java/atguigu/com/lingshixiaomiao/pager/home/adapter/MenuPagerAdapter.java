package atguigu.com.lingshixiaomiao.pager.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import atguigu.com.lingshixiaomiao.pager.home.TabBasePager;

/**
 * Created by Liu_haiwei on 2016/4/15.
 * 菜单详情页面viewpager的适配器
 */
public class MenuPagerAdapter extends PagerAdapter {

    private final Context context;
    private final List<String> tabTitleLists;
    private final List<TabBasePager> pagers;

    public MenuPagerAdapter(Context context, List<String> tabTitleLists, List<TabBasePager> pagers) {
        this.context = context;
        this.tabTitleLists = tabTitleLists;
        this.pagers =pagers;
    }

    @Override
    public int getCount() {
        //Log.e("TAG", "tabTitleLists = " + tabTitleLists.size());
        return tabTitleLists != null ? tabTitleLists.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.e("TAG", "position = " + position);
        TabBasePager tabBasePager = pagers.get(position);
        View rootView = tabBasePager.rootView;
        if(position == 0) {
            tabBasePager.initData();
        }
        container.addView(rootView);
        return rootView;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleLists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
