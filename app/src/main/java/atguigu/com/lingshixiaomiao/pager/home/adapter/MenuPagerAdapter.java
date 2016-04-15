package atguigu.com.lingshixiaomiao.pager.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Liu_haiwei on 2016/4/15.
 */
public class MenuPagerAdapter extends PagerAdapter {

    private final Context context;
    private final List<String> tabTitleLists;

    public MenuPagerAdapter(Context context, List<String> tabTitleLists) {
        this.context = context;
        this.tabTitleLists = tabTitleLists;
    }

    @Override
    public int getCount() {
        Log.e("TAG", "tabTitleLists = " + tabTitleLists.size());
        return tabTitleLists != null ? tabTitleLists.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView = new TextView(context);
        textView.setText("hahahhahah" + position);
        container.addView(textView);
        return textView;
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
