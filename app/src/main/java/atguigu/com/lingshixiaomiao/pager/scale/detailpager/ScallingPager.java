package atguigu.com.lingshixiaomiao.pager.scale.detailpager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.scale.activity.SnackInfomationActivity;
import atguigu.com.lingshixiaomiao.pager.scale.base.ScaleBasePager;
import atguigu.com.lingshixiaomiao.pager.scale.bean.ScallToCarBean;
import atguigu.com.lingshixiaomiao.pager.scale.bean.ScallingBean;
import atguigu.com.lingshixiaomiao.pager.scale.utils.CacheUtil;
import atguigu.com.lingshixiaomiao.pager.scale.utils.TimeUtil;
import atguigu.com.lingshixiaomiao.pager.scale.utils.Url;

/**
 * Created by Administrator on 2016/4/10 0010.
 */
public class ScallingPager extends ScaleBasePager {

    private ListView lv_scalling;

    private RefreshLayout rl_scalling;

    //热卖中lv的数据集合
    private List<ScallingBean.DataEntity.ItemsEntity> itemsEntities;

    private MyLvAdapter adapter;

    //存放url的数组
    private String[] urls = {Url.SCALLING_URL_0, Url.SCALLING_URL_1, Url.SCALLING_URL_2};

    //记录当前url下标
    private int urlPosition = 0;

    //判断是否为加载更多
    private boolean isLoadMore;

    private Intent intent;

    public ScallingPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {

        View rootView = View.inflate(mActivity, R.layout.scale_scalling_pager, null);
        lv_scalling = (ListView) rootView.findViewById(R.id.lv_scalling);
        rl_scalling = (RefreshLayout) rootView.findViewById(R.id.rl_scalling);

        // 下拉刷新
        rl_scalling.setOnRefreshListener(new MyOnRefreshListener());
        // 上拉加载更多
        rl_scalling.setOnLoadListener(new MyOnLoadListener());

        lv_scalling.setOnItemClickListener(new MyOnItemClickListener());

        return rootView;
    }

    /**
     * 单机item的监听
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            intent = new Intent(mActivity, SnackInfomationActivity.class);

            //携带详情页面所需要的数据
            intent.putExtra("snack_id", itemsEntities.get(position).getId());
            intent.putExtra("image_url", itemsEntities.get(position).getImg().getImg_url());
            intent.putExtra("snack_title", itemsEntities.get(position).getTitle());

            //携带购物按钮上传所需的数据
            goToCarForDetail(position);
        }
    }

    /**
     * 初始化视图
     */
    @Override
    public void initData() {

        super.initData();

        String savedJson = CacheUtil.getString(mActivity, Url.SCALLING_URL_0);

        if (!TextUtils.isEmpty(savedJson)) {
            processData(savedJson);
        }

        getDataFromNet();
    }

    /**
     * 从网络获取热卖中lv的数据
     */
    private void getDataFromNet() {

        RequestParams params = new RequestParams(Url.SCALLING_URL_0);
        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                CacheUtil.putString(mActivity, Url.SCALLING_URL_0, result);

                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Toast.makeText(mActivity, "网络请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析json数据并绑定到视图
     *
     * @param result
     */
    private void processData(String result) {

        ScallingBean scallingBean = new Gson().fromJson(result, ScallingBean.class);


        if (!isLoadMore) {

            itemsEntities = new ArrayList<>();

            //初始化lv数据集合
            itemsEntities = scallingBean.getData().getItems();

            adapter = new MyLvAdapter();
            lv_scalling.setAdapter(adapter);

        } else {

            itemsEntities.addAll(scallingBean.getData().getItems());

            //刷新适配器
            adapter.notifyDataSetChanged();
        }

    }

    /**
     * lv的适配器
     */
    private class MyLvAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemsEntities.size();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            Holder holder;

            if (convertView == null) {

                holder = new Holder();
                convertView = View.inflate(mActivity, R.layout.scale_scalling_item, null);

                holder.iv_scale_item = (ImageView) convertView.findViewById(R.id.iv_scale_item);
                holder.tv_scale_item_discount = (TextView) convertView.findViewById(R.id.tv_scale_item_discount);
                holder.tv_scale_item_name = (TextView) convertView.findViewById(R.id.tv_scale_item_name);

                //得到倒计时tv视图
                holder.tv_scale_item_time = (TextView) convertView.findViewById(R.id.tv_scale_item_time);

                holder.tv_scale_item_currentprice = (TextView) convertView.findViewById(R.id.tv_scale_item_currentprice);
                holder.tv_scale_item_formerlyprice = (TextView) convertView.findViewById(R.id.tv_scale_item_formerlyprice);
                holder.tv_scale_item_toshopping2 = (TextView) convertView.findViewById(R.id.tv_scale_item_toshopping2);
                holder.tv_scale_item_percentage = (TextView) convertView.findViewById(R.id.tv_scale_item_percentage);
                holder.pb_scale_item_progress = (ProgressBar) convertView.findViewById(R.id.pb_scale_item_progress);
                holder.tv_scale_item_count = (TextView) convertView.findViewById(R.id.tv_scale_item_count);

                convertView.setTag(holder);
            } else {

                holder = (Holder) convertView.getTag();
            }

            //得到当前item的数据对象
            ScallingBean.DataEntity.ItemsEntity itemsEntity = itemsEntities.get(position);

            //item中的view设置监听
            setListener(position, holder);

            //设置倒计时
            setDownTimer(holder, itemsEntity);

            x.image().bind(holder.iv_scale_item, itemsEntity.getImg().getImg_url());
            holder.tv_scale_item_discount.setText(itemsEntity.getTag().getTitle());
            holder.tv_scale_item_name.setText(itemsEntity.getTitle());
            holder.tv_scale_item_currentprice.setText("￥" + String.valueOf(itemsEntity.getPrice().getCurrent()));
            holder.tv_scale_item_formerlyprice.setText("￥" + String.valueOf(itemsEntity.getPrice().getPrime()));

            //价格上加删除线
            holder.tv_scale_item_formerlyprice.getPaint().setAntiAlias(true);//抗锯齿
            holder.tv_scale_item_formerlyprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

            holder.tv_scale_item_percentage.setText("已抢" + itemsEntity.getSpecial_percentage() + "%");
            holder.pb_scale_item_progress.setMax(100);
            holder.pb_scale_item_progress.setProgress(itemsEntity.getSpecial_percentage());
            holder.tv_scale_item_count.setText("已抢" + String.valueOf(itemsEntity.getSpecial_num()) + "件");

            return convertView;
        }

        /**
         * 设置倒计时
         *
         * @param holder
         * @param itemsEntity
         */
        private void setDownTimer(Holder holder, ScallingBean.DataEntity.ItemsEntity itemsEntity) {

            CountDownTimer timer = (CountDownTimer) holder.tv_scale_item_time.getTag();

            //获取url时间戳
            long urlTime = ((long) (itemsEntity.getTime())) * 1000;

            Long leftTime = urlTime - System.currentTimeMillis();

            //先判断计时器是否已经置空，如果没有，就取消计时，并置空
            if (timer != null) {

                timer.cancel();
                timer = null;
            }

            final TextView textView = holder.tv_scale_item_time;

            timer = new CountDownTimer(leftTime, 100) {

                @Override
                public void onTick(long millisUntilFinished) {

                    String leftDate = TimeUtil.getFormatTime(millisUntilFinished);
                    textView.setText(leftDate);
                }

                @Override
                public void onFinish() {
                    cancel();
                }
            };

            timer.start();

            holder.tv_scale_item_time.setTag(timer);
        }

        /**
         * item中的view设置监听
         *
         * @param position
         * @param holder
         */
        private void setListener(final int position, Holder holder) {

            //购物按钮监听
            holder.tv_scale_item_toshopping2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("TAG", "onClick()");

                    //添加到购物车(上传数据到服务器)
                    goToCar(position);
                }
            });
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    class Holder {
        private ImageView iv_scale_item;
        private TextView tv_scale_item_discount;
        private TextView tv_scale_item_name;
        private TextView tv_scale_item_time;
        private TextView tv_scale_item_currentprice;
        private TextView tv_scale_item_formerlyprice;
        private TextView tv_scale_item_toshopping2;
        private TextView tv_scale_item_percentage;
        private ProgressBar pb_scale_item_progress;
        private TextView tv_scale_item_count;
    }

    /**
     * 添加购物车
     *
     * @param position
     */
    private void goToCar(int position) {

        //如果未登录
        if (!LoginUtils.getInstance().isLogin()) {

            Toast.makeText(mActivity, "请登录", Toast.LENGTH_SHORT).show();
            return;
        }

        //获取物品id
        final int good_id = itemsEntities.get(position).getId();

        //获取用户id
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        final String uid = loginBean.getData().getUid();

        //得到请求地址
        String url = Url.GOTOCAR_0 + uid + Url.GOTOCAR_1 + good_id;

        Log.e("TAG", url);

        //get请求上传数据
        RequestParams requestParams = new RequestParams(url);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                Log.e("TAG", "onSuccess():" + result);

                //通过get请求上传数据到服务器
                uploadGotoCarData(result, uid, good_id);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("TAG", "onError()");
            }

            @Override
            public void onCancelled(CancelledException cex) {

                Log.e("TAG", "onCancelled()");
            }

            @Override
            public void onFinished() {

                Log.e("TAG", "onFinished()");
            }
        });
    }

    /**
     * 得到详情页面添加购物车的请求信息
     *
     * @param position
     * @return
     */
    private void goToCarForDetail(int position) {

        //如果未登录
        if (!LoginUtils.getInstance().isLogin()) {

            Toast.makeText(mActivity, "请登录", Toast.LENGTH_SHORT).show();

            return;
        }

        //获取物品id
        int good_id = itemsEntities.get(position).getId();

        //获取用户id
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String uid = loginBean.getData().getUid();

        //得到请求地址
        String url = Url.GOTOCAR_0 + uid + Url.GOTOCAR_1 + good_id;

        Log.e("TAG", "goToCarForDetail()==========url:" + url);

        //get请求上传数据
        RequestParams requestParams = new RequestParams(url);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                Log.e("TAG", "=================onSuccess(): " + result);

                String s = result.replaceFirst("kinds", "kindss");

                intent.putExtra("uploadDataForDetail", s);


                Log.e("TAG", "=============intent.putExtra(): " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e("TAG", "onError()");
                Toast.makeText(mActivity, "添加购物车失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

                Log.e("TAG", "onCancelled()");
                Toast.makeText(mActivity, "添加购物车失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished() {

                Log.e("TAG", "onFinished()");
                mActivity.startActivity(intent);
            }
        });
    }

    /**
     * 上传购物数据到服务器
     *
     * @param result
     * @param uid
     * @param good_id
     */
    private void uploadGotoCarData(String result, String uid, int good_id) {

        String results = result.replaceFirst("kinds", "kindss");

        ScallToCarBean scallToCarBean = new Gson().fromJson(results, ScallToCarBean.class);

        int kind_id = scallToCarBean.getData().getKindss().get(0).getId();

        int subkind_id = scallToCarBean.getData().getKindss().get(0).getKinds().get(0).getId();

        String url_0 = Url.GOTOCARR_0 + uid + Url.GOTOCARR_1 + good_id + Url.GOTOCARR_2 + kind_id + Url.GOTOCARR_3 + subkind_id + Url.GOTOCARR_4;

        Log.e("TAG", "url_0==============" + url_0);

        RequestParams requestParamss = new RequestParams(url_0);

        x.http().get(requestParamss, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.e("TAG", "onSuccess()================:" + result);

                Toast.makeText(mActivity, "添加购物车成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError()================");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled()================");
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished()================");
            }
        });
    }

    /**
     * 下拉刷新监听器
     */
    private class MyOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {

            if (!NetWorkUtils.isNetworkConnected()) {
                Toast.makeText(mActivity, "没有网络...", Toast.LENGTH_SHORT).show();
                rl_scalling.setRefreshing(false);
                return;
            }

            if (adapter != null) {
                getDataFromNet();
            }

            rl_scalling.postDelayed(new Runnable() {

                @Override
                public void run() {
                    rl_scalling.setRefreshing(false);
                }

            }, 2000);
        }
    }

    /**
     * 上拉加载更多监听器
     */
    private class MyOnLoadListener implements RefreshLayout.OnLoadListener {

        @Override
        public void onLoad() {

            if (!NetWorkUtils.isNetworkConnected()) {

                Toast.makeText(mActivity, "没有网络...", Toast.LENGTH_SHORT).show();
                rl_scalling.setLoading(false);
                return;
            }

            //请求数据
            getMoreDataFromNet();
        }
    }

    /**
     * 上拉时从网络加载更多数据
     */
    private void getMoreDataFromNet() {

        //当前要访问的url在urls数组中的下标
        urlPosition++;

        if (urlPosition >= urls.length) {
            Toast.makeText(mActivity, "家底都被你掏光了,再去发现新大陆吧~", Toast.LENGTH_SHORT).show();
            rl_scalling.setLoading(false);
            return;
        }

        RequestParams params = new RequestParams(urls[urlPosition]);
        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                isLoadMore = true;

                processData(result);

                rl_scalling.setLoading(false);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                rl_scalling.setLoading(false);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                rl_scalling.setLoading(false);
            }

            @Override
            public void onFinished() {
                rl_scalling.setLoading(false);
            }
        });
    }
}
