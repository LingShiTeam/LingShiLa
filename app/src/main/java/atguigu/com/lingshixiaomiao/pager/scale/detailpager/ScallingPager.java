package atguigu.com.lingshixiaomiao.pager.scale.detailpager;

import android.app.Activity;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.scale.base.ScaleBasePager;
import atguigu.com.lingshixiaomiao.pager.scale.bean.ScallingBean;
import atguigu.com.lingshixiaomiao.pager.scale.utils.TimeUtil;
import atguigu.com.lingshixiaomiao.pager.scale.utils.Url;

/**
 * Created by Administrator on 2016/4/10 0010.
 */
public class ScallingPager extends ScaleBasePager {

    private ListView lv_scalling;

    //热卖中lv的数据集合
    private List<ScallingBean.DataEntity.ItemsEntity> itemsEntities;


    public ScallingPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View rootView = View.inflate(mActivity, R.layout.scale_scalling_pager, null);
        lv_scalling = (ListView) rootView.findViewById(R.id.lv_scalling);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();

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

    /**
     * 解析json数据并绑定到视图
     *
     * @param result
     */
    private void processData(String result) {

        ScallingBean scallingBean = new Gson().fromJson(result, ScallingBean.class);
        itemsEntities = new ArrayList<>();

        //初始化lv数据集合
        itemsEntities = scallingBean.getData().getItems();

        lv_scalling.setAdapter(new MyLvAdapter());
    }

    private List<TextView> timeTvs;

    /**
     * lv的适配器
     */
    private class MyLvAdapter extends BaseAdapter {

        MyLvAdapter() {
            timeTvs = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return itemsEntities.size();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            Holder holder;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.scale_scalling_item, null);
                holder = new Holder();
                holder.iv_scale_item = (ImageView) convertView.findViewById(R.id.iv_scale_item);
                holder.tv_scale_item_discount = (TextView) convertView.findViewById(R.id.tv_scale_item_discount);
                holder.tv_scale_item_name = (TextView) convertView.findViewById(R.id.tv_scale_item_name);
                holder.tv_scale_item_time = (TextView) convertView.findViewById(R.id.tv_scale_item_time);
                holder.tv_scale_item_time.setTag(true);
                holder.tv_scale_item_currentprice = (TextView) convertView.findViewById(R.id.tv_scale_item_currentprice);
                holder.tv_scale_item_formerlyprice = (TextView) convertView.findViewById(R.id.tv_scale_item_formerlyprice);
                holder.tv_scale_item_toshopping = (TextView) convertView.findViewById(R.id.tv_scale_item_toshopping);
                holder.tv_scale_item_percentage = (TextView) convertView.findViewById(R.id.tv_scale_item_percentage);
                holder.pb_scale_item_progress = (ProgressBar) convertView.findViewById(R.id.pb_scale_item_progress);
                holder.tv_scale_item_count = (TextView) convertView.findViewById(R.id.tv_scale_item_count);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            ScallingBean.DataEntity.ItemsEntity itemsEntity = itemsEntities.get(position);
            x.image().bind(holder.iv_scale_item, itemsEntity.getImg().getImg_url());
            holder.tv_scale_item_discount.setText(itemsEntity.getTag().getTitle());
            holder.tv_scale_item_name.setText(itemsEntity.getTitle());

            //获取url时间戳
            long urlTime = itemsEntity.getTime() * 1000;

            //计算出剩余时间
            long leftTime =System.currentTimeMillis()- urlTime;
            //Log.e("TAG", "剩余时间：" + leftTime);

            //将当前的item的tv视图加入集合
            timeTvs.add(holder.tv_scale_item_time);

            //启动计时器
            new CountDownTimer(leftTime, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    String leftDate = TimeUtil.getLeftDate(millisUntilFinished);
                    //Log.e("TAG", "onTick() :" + leftDate);
                    timeTvs.get(position).setText(leftDate);
                }

                @Override
                public void onFinish() {

                }
            }.start();


            holder.tv_scale_item_currentprice.setText("￥" + String.valueOf(itemsEntity.getPrice().getCurrent()));
            holder.tv_scale_item_formerlyprice.setText("￥" + String.valueOf(itemsEntity.getPrice().getPrime()));

            //价格上加删除线
            holder.tv_scale_item_formerlyprice.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            holder.tv_scale_item_formerlyprice.getPaint().setAntiAlias(true);//抗锯齿
            holder.tv_scale_item_formerlyprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

            holder.tv_scale_item_toshopping.setOnClickListener(new MyOnclickListener());
            holder.tv_scale_item_percentage.setText("已抢" + itemsEntity.getSpecial_percentage() + "%");
            holder.pb_scale_item_progress.setMax(100);
            holder.pb_scale_item_progress.setProgress(itemsEntity.getSpecial_percentage());
            holder.tv_scale_item_count.setText("已抢" + String.valueOf(itemsEntity.getSpecial_num()) + "件");
            return convertView;
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
        private TextView tv_scale_item_toshopping;
        private TextView tv_scale_item_percentage;
        private ProgressBar pb_scale_item_progress;
        private TextView tv_scale_item_count;
    }

    /**
     * 点击监听
     */
    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}
