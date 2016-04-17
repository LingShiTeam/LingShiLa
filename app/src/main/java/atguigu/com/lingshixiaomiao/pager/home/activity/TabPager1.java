package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.TabBasePager;
import atguigu.com.lingshixiaomiao.pager.home.bean.TabDataBean;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.view.XListView;

/**
 * Created by Liu_haiwei on 2016/4/16.
 * 侧滑菜单详情页面
 */
public class TabPager1 extends TabBasePager {

    private final String tabUrl;
    private TabDataBean tabDataBean;
    private MyTabAdapter adapter;
    private XListView xlistview;
    private ImageView widget_loading_pb;
    private AnimationDrawable ad;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param tabUrl
     */
    public TabPager1(Activity mActivity, String tabUrl) {
        super(mActivity);
        Log.e("TAG", "tabUrl == " + tabUrl);
        this.tabUrl = tabUrl;
        registerEventBus();
    }

    @Override
    public View initView() {
        View view = View.inflate(mContent, R.layout.tab_item_layout, null);
        xlistview = (XListView) view.findViewById(R.id.xlistview);
        widget_loading_pb = (ImageView) view.findViewById(R.id.widget_loading_pb);
        ad = (AnimationDrawable) widget_loading_pb.getBackground();
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        // 解析数据
        new JsonUtils().loadData(tabUrl, TabDataBean.class);
    }

    /**
     * 使用EventBus接收解析后的数据--TabDataBean
     *
     * @param tabDataBean
     */
    @Subscribe
    public void onEventMainThread(TabDataBean tabDataBean) {
        this.tabDataBean = tabDataBean;
        if (adapter == null) {
            adapter = new MyTabAdapter();
        }
        xlistview.setAdapter(new MyTabAdapter());
        adapter.notifyDataSetChanged();
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
            TextView textView = new TextView(mContent);
            textView.setText(tabDataBean.getData().getItems().get(position).getTitle());
            return textView;
        }
    }

    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 解注册EventBus
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
