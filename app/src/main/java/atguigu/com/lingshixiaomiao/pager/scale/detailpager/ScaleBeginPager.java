package atguigu.com.lingshixiaomiao.pager.scale.detailpager;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.utils.NetWorkUtils;
import atguigu.com.lingshixiaomiao.pager.home.view.RefreshLayout;
import atguigu.com.lingshixiaomiao.pager.scale.base.ScaleBasePager;
import atguigu.com.lingshixiaomiao.pager.scale.bean.ScaleBeginBean;
import atguigu.com.lingshixiaomiao.pager.scale.utils.CacheUtil;
import atguigu.com.lingshixiaomiao.pager.scale.utils.TimeUtil;
import atguigu.com.lingshixiaomiao.pager.scale.utils.Url;

/**
 * Created by Administrator on 2016/4/10 0010.
 */
public class ScaleBeginPager extends ScaleBasePager {

    //存放url的数组
    private String[] urls = {Url.SCALE_BEGIN_URL_0, Url.SCALE_BEGIN_URL_1};

    //记录当前url下标
    private int urlPosition = 0;

    //判断是否为加载更多
    private boolean isLoadMore;

    private ListView lv_scale_begin;

    private RefreshLayout rl_scale_begin;

    private List<ScaleBeginBean.DataEntity.ItemsEntity> itemsEntities;

    private MyLvAdapter adapter;

    public ScaleBeginPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View rootView = View.inflate(mActivity, R.layout.scale_begin_pager, null);
        lv_scale_begin = (ListView) rootView.findViewById(R.id.lv_scale_begin);
        rl_scale_begin = (RefreshLayout) rootView.findViewById(R.id.rl_scale_begin);

        // 下拉刷新
        rl_scale_begin.setOnRefreshListener(new MyOnRefreshListener());
        // 上拉加载更多
        rl_scale_begin.setOnLoadListener(new MyOnLoadListener());
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();

        String savedJson = CacheUtil.getString(mActivity, Url.SCALE_BEGIN_URL_0);

        if (!TextUtils.isEmpty(savedJson)) {
            processData(savedJson);
        }

        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestParams params = new RequestParams(Url.SCALE_BEGIN_URL_0);
        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtil.putString(mActivity, Url.SCALE_BEGIN_URL_0, result);

                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void processData(String result) {

        ScaleBeginBean scaleBeginBean = new Gson().fromJson(result, ScaleBeginBean.class);

        if (!isLoadMore) {

            itemsEntities = new ArrayList<>();

            //初始化lv数据集合
            itemsEntities = scaleBeginBean.getData().getItems();

            adapter = new MyLvAdapter();
            lv_scale_begin.setAdapter(adapter);

        } else {

            itemsEntities.addAll(scaleBeginBean.getData().getItems());

            //刷新适配器
            adapter.notifyDataSetChanged();
        }
    }

    private class MyLvAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return itemsEntities.size();
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

            Holder holder;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.scale_begin_item, null);
                holder = new Holder();
                holder.iv_scale_item = (ImageView) convertView.findViewById(R.id.iv_scale_item);
                holder.tv_scale_item_discount = (TextView) convertView.findViewById(R.id.tv_scale_item_discount);
                holder.tv_scale_item_name = (TextView) convertView.findViewById(R.id.tv_scale_item_name);
                holder.tv_scale_item_currentprice = (TextView) convertView.findViewById(R.id.tv_scale_item_currentprice);
                holder.tv_scale_item_formerlyprice = (TextView) convertView.findViewById(R.id.tv_scale_item_formerlyprice);
                holder.tv_scale_item_percentage = (TextView) convertView.findViewById(R.id.tv_scale_item_percentage);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            ScaleBeginBean.DataEntity.ItemsEntity itemsEntity = itemsEntities.get(position);
            x.image().bind(holder.iv_scale_item, itemsEntity.getImg().getImg_url());
            holder.tv_scale_item_discount.setText(itemsEntity.getTag().getTitle());
            holder.tv_scale_item_name.setText(itemsEntity.getTitle());

            holder.tv_scale_item_currentprice.setText("￥" + String.valueOf(itemsEntity.getPrice().getCurrent()));
            holder.tv_scale_item_formerlyprice.setText("￥" + String.valueOf(itemsEntity.getPrice().getPrime()));

            //价格上加删除线
            holder.tv_scale_item_formerlyprice.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            holder.tv_scale_item_formerlyprice.getPaint().setAntiAlias(true);//抗锯齿
            holder.tv_scale_item_formerlyprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

            long time = itemsEntity.getTime();
            Log.e("TAG", "即将开始时间：" + time);
            String date = TimeUtil.getFormatData(String.valueOf(time * 1000));
            holder.tv_scale_item_percentage.setText(date);
            return convertView;
        }
    }

    class Holder {
        private ImageView iv_scale_item;
        private TextView tv_scale_item_discount;
        private TextView tv_scale_item_name;
        private TextView tv_scale_item_currentprice;
        private TextView tv_scale_item_formerlyprice;
        private TextView tv_scale_item_percentage;
    }

    private class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {

            if (!NetWorkUtils.isNetworkConnected()) {
                Toast.makeText(mActivity, "没有网络...", Toast.LENGTH_SHORT).show();
                rl_scale_begin.setRefreshing(false);
                return;
            }

            if (adapter != null) {
                getDataFromNet();
            }

            rl_scale_begin.postDelayed(new Runnable() {

                @Override
                public void run() {
                    rl_scale_begin.setRefreshing(false);
                }

            }, 2000);
        }
    }

    private class MyOnLoadListener implements RefreshLayout.OnLoadListener {
        @Override
        public void onLoad() {

            if (!NetWorkUtils.isNetworkConnected()) {

                Toast.makeText(mActivity, "没有网络...", Toast.LENGTH_SHORT).show();
                rl_scale_begin.setLoading(false);
                return;
            }

            //请求数据
            getMoreDataFromNet();
        }
    }

    private void getMoreDataFromNet() {

        //当前要访问的url在urls数组中的下标
        urlPosition++;

        if (urlPosition >= urls.length) {
            Toast.makeText(mActivity, "家底都被你掏光了,再去发现新大陆吧~", Toast.LENGTH_SHORT).show();
            rl_scale_begin.setLoading(false);
            return;
        }

        RequestParams params = new RequestParams(urls[urlPosition]);
        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                isLoadMore = true;

                processData(result);

                rl_scale_begin.setLoading(false);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_scale_begin.setLoading(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                rl_scale_begin.setLoading(false);
            }

            @Override
            public void onFinished() {
                rl_scale_begin.setLoading(false);
            }
        });
    }
}
