package atguigu.com.lingshixiaomiao.pager.scale.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.scale.adapter.CarItemAdapter;
import atguigu.com.lingshixiaomiao.pager.scale.bean.ScaleCarBean;
import atguigu.com.lingshixiaomiao.pager.scale.utils.Url;

public class ShoppingCarActivity extends Activity {

    private ListView lvCar;
    private RelativeLayout rlCarJiesuanlan;
    private ImageView cbCarQuanxuan;
    private Button btnCarJiesuan;
    private LinearLayout llCarJiage;
    private TextView tvCarHeji;

    /**
     * 头
     */
    private LinearLayout llCarXiaomiaocar;
    private TextView tv_car_head_zongshu;
    private ImageView cb_car_ziying_quanxuan;

    /**
     * 尾
     */
    private TextView tvTaobaocarShangpinshu;
    private TextView tvTaobaocarXiaoji;
    private LinearLayout llCarTaobaocar;
    private TextView tvTaobaocarTblingshi;
    private TextView tv_car_item_totalcount;
    private TextView tv_car_item_xiaoji;

    /**
     * 购物车item数据集合
     */
    private List<ScaleCarBean.DataEntity.ItemssEntity.ItemsEntity> items;

    private CarItemAdapter carItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);

        initView();

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        getDataFromNet();
    }

    private void getDataFromNet() {

        //如果未登录
        if (!LoginUtils.getInstance().isLogin()) {
            Toast.makeText(ShoppingCarActivity.this, "请登录", Toast.LENGTH_SHORT).show();
            return;
        }

        //获取用户id
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String uid = loginBean.getData().getUid();

        //得到请求地址
        String url = Url.SHOPPING_CAR_0 + uid + Url.SHOPPING_CAR_1;

        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.e("TAG", "onSuccess:" + result);
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
     * 处理json数据
     *
     * @param result
     */
    private void processData(String result) {

        String s = result.replaceFirst("items", "itemss");

        ScaleCarBean scaleCarBean = new Gson().fromJson(s, ScaleCarBean.class);

        items = scaleCarBean.getData().getItemss().get(0).getItems();

        int num = 0;
        for (int i = 0; i < items.size(); i++) {
            num += items.get(i).getNum();
        }
        tv_car_head_zongshu.setText("共" + num + "件商品");

        //lv设置监听
        carItemAdapter = new CarItemAdapter(this, items, tv_car_item_totalcount, tv_car_item_xiaoji,
                tvCarHeji, btnCarJiesuan, cbCarQuanxuan, cb_car_ziying_quanxuan);
        lvCar.setAdapter(carItemAdapter);
    }

    /**
     * 初始化视图
     */
    private void initView() {

        lvCar = (ListView) findViewById(R.id.lv_car);
        rlCarJiesuanlan = (RelativeLayout) findViewById(R.id.rl_car_jiesuanlan);
        cbCarQuanxuan = (ImageView) findViewById(R.id.cb_car_quanxuan);
        btnCarJiesuan = (Button) findViewById(R.id.btn_car_jiesuan);
        llCarJiage = (LinearLayout) findViewById(R.id.ll_car_jiage);
        tvCarHeji = (TextView) findViewById(R.id.tv_car_heji);

        //lv加头加尾
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View headView = layoutInflater.inflate(R.layout.scale_head_xiaomiaocar, null);
        View headViews = layoutInflater.inflate(R.layout.scale_car_head_ziying, null);
        View footViews = layoutInflater.inflate(R.layout.scale_car_foot_zongji_bukemai, null);
        View footView = layoutInflater.inflate(R.layout.scale_foot_taobaocar, null);

        lvCar.addHeaderView(headView);
        lvCar.addHeaderView(headViews);
        lvCar.addFooterView(footViews);
        lvCar.addFooterView(footView);

        tv_car_head_zongshu = (TextView) headView.findViewById(R.id.tv_car_head_zongshu);
        cb_car_ziying_quanxuan = (ImageView) headViews.findViewById(R.id.cb_car_ziying_quanxuan);
        tv_car_item_totalcount = (TextView) footViews.findViewById(R.id.tv_car_item_totalcount);
        tv_car_item_xiaoji = (TextView) footViews.findViewById(R.id.tv_car_item_xiaoji);
        tvTaobaocarTblingshi = (TextView) footView.findViewById(R.id.tv_taobaocar_tblingshi);

        //设置监听
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {

        //自营全选cb监听
        cb_car_ziying_quanxuan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean selected = !cb_car_ziying_quanxuan.isSelected();

                cb_car_ziying_quanxuan.setSelected(selected);

                for (int i = 0; i < items.size(); i++) {

                    items.get(i).setChecked(selected);
                }

                cbCarQuanxuan.setSelected(selected);

                carItemAdapter.notifyDataSetChanged();

                carItemAdapter.setDataOfXiaoJiFooter();
            }
        });

        //全选cb点击监听
        cbCarQuanxuan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean selected = !cbCarQuanxuan.isSelected();

                cbCarQuanxuan.setSelected(selected);

                for (int i = 0; i < items.size(); i++) {

                    items.get(i).setChecked(selected);
                }

                cb_car_ziying_quanxuan.setSelected(selected);

                carItemAdapter.notifyDataSetChanged();

                carItemAdapter.setDataOfXiaoJiFooter();
            }
        });
    }
}
