package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.home.bean.TabDataBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.view.XListView;

/**
 * Created by Liu_haiwei on 2016/4/16.
 * 侧滑菜单详情页面
 */
public class TabPager extends BasePager {

    private XListView listview;
    private TabDataBean tabDataBean;
    private boolean tag = true;
    private MyTabAdapter adapter;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param tabUrl
     */
    public TabPager(Activity mActivity, String tabUrl) {
        super(mActivity);
        Log.e("TAG", "tabUrl == " + tabUrl);
        new JsonUtils().loadData(tabUrl, TabDataBean.class);

    }

    @Override
    public View initView() {
        listview = new XListView(mActivity);
        return listview;
    }

    @Override
    public void initData() {
        super.initData();

    }

    /**
     * 使用EventBus接收解析后的数据--TabDataBean
     *
     * @param tabDataBean
     */
    @Subscribe
    public void onEventMainThread(TabDataBean tabDataBean) {
        this.tabDataBean = tabDataBean;

        if (tag) {
            if(adapter == null) {
                adapter = new MyTabAdapter();

            }
            listview.setAdapter(new MyTabAdapter());

            tag = false;
        }


    }

    /**
     * xListView的适配器
     */
    class MyTabAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return tabDataBean != null ? tabDataBean.getData().getCount() : 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(mActivity);
            textView.setText(tabDataBean.getData().getItems().get(position).getTitle());
            return textView;
        }
    }

    /**
     * 注册EventBus
     */
    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 解注册EventBus
     */
    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
