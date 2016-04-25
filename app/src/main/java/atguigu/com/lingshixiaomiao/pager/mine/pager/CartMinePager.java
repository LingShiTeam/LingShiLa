package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.CartMineAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.AddCartBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartNotBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.DeleteCartBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/10.
 */
public class CartMinePager extends ContentBasePager implements View.OnClickListener {

    private TextView tv_mine_title_complete;
    private TextView tv_mine_cart2_jiesuan;
    private TextView tv_mine_cart_total_price;
    private RecyclerView lv_mine_cart2_bottom;
    public ImageView mine_cart2_check_all;
    private LinearLayout ll_mien_cart2_total_price;
    private LinearLayout ll_loading;
    private final String uid;
    private List<CartBean.DataEntity.ItemssEntity> itemss;
    private List<CartNotBean.DataEntity.ItemssEntity> cannotitems;
    private int load = 0;
    private CartMineAdapter adapter;
    private List<CartBean.DataEntity.ItemssEntity.ItemsEntity> items;
    private List<CartBean.DataEntity.ItemssEntity.ItemsEntity> buyItems;
    private SwipeRefreshLayout srl_mine_cart_refresh;


    /**
     * 构造方法
     *
     * @param mActivity
     */
    public CartMinePager(Activity mActivity) {
        super(mActivity);
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        uid = loginBean.getData().getUid();
        loadData();
    }

    private void loadData() {
        load = 0;
        loadData(true);
        loadData(false);
    }

    private void loadAdapter() {
        adapter = new CartMineAdapter(mActivity, itemss, cannotitems, this);
        lv_mine_cart2_bottom.setLayoutManager(new LinearLayoutManager(mActivity));
        lv_mine_cart2_bottom.setAdapter(adapter);
    }

    private void showLoadingAnim() {
        ll_loading.setVisibility(View.VISIBLE);
        ImageView iv_loading = (ImageView) ll_loading.findViewById(R.id.iv_loading);
        AnimationDrawable loading = (AnimationDrawable) iv_loading.getBackground();
        loading.start();
    }

    private void hindLoadingAnim() {
        ll_loading.setVisibility(View.GONE);
        srl_mine_cart_refresh.setRefreshing(false);
    }

    private void loadData(final boolean canBuy) {

        if (canBuy) {
            showLoadingAnim();
        }

        String type = null;

        if (canBuy) {
            type = Constants.CART_CAN_BUY;
        } else {
            type = Constants.CART_CANNOT_BUY;
        }

        String[] u = Url.CART_URL;
        String canBuyUrl = u[0] + uid + u[1] + type;
        x.http().get(new RequestParams(canBuyUrl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String json) {
                LogUtils.loge("onSuccess");
                LogUtils.loge("json = " + json);
                json = json.replaceFirst("items", "itemss");
                LogUtils.loge("json = " + json);
                parseJson(json, canBuy);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("onError");
                load++;
                if (load == 2) {
                    loadAdapter();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtils.loge("onFinished");
                if (load == 2) {
                    hindLoadingAnim();
                }
            }
        });
    }

    private void parseJson(String json, boolean canBuy) {
        if (canBuy) {
            CartBean cartBean = new Gson().fromJson(json, CartBean.class);
            if (Constants.SUCCESS.equals(cartBean.getRs_code())) {
                itemss = cartBean.getData().getItemss();
                items = itemss.get(0).getItems();
                LogUtils.loge("itemss = " + itemss);

            } else {
                Toast.makeText(mActivity, cartBean.getRs_msg(), Toast.LENGTH_SHORT).show();
            }
        } else {
            CartNotBean cartNotBean = new Gson().fromJson(json, CartNotBean.class);
            if (Constants.SUCCESS.equals(cartNotBean.getRs_code())) {
                cannotitems = cartNotBean.getData().getItemss();
                LogUtils.loge("cannotitems = " + cannotitems);
            }
        }
        load++;
        if (load == 2) {
            loadAdapter();
            hindLoadingAnim();
        }
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_cart_pager, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        tv_mine_title_complete = (TextView) mActivity.findViewById(R.id.tv_mine_title_complete);
        tv_mine_cart2_jiesuan = (TextView) v.findViewById(R.id.tv_mine_cart2_jiesuan);
        tv_mine_cart_total_price = (TextView) v.findViewById(R.id.tv_mine_cart_total_price);
        lv_mine_cart2_bottom = (RecyclerView) v.findViewById(R.id.lv_mine_cart2_bottom);
        ll_mien_cart2_total_price = (LinearLayout) v.findViewById(R.id.ll_mien_cart2_total_price);
        mine_cart2_check_all = (ImageView) v.findViewById(R.id.mine_cart2_check_all);
        ll_loading = (LinearLayout) v.findViewById(R.id.ll_loading);
        srl_mine_cart_refresh = (SwipeRefreshLayout) v.findViewById(R.id.srl_mine_cart_refresh);

        setListener();
    }

    private void setListener() {
        tv_mine_cart2_jiesuan.setOnClickListener(this);
        mine_cart2_check_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selected = !mine_cart2_check_all.isSelected();
                mine_cart2_check_all.setSelected(selected);
                adapter.selectAll(selected);
            }
        });

        srl_mine_cart_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void hindComplete() {
        tv_mine_title_complete.setText("编辑");
        tv_mine_cart2_jiesuan.setText("结算(" + adapter.count + ")");
        ll_mien_cart2_total_price.setVisibility(View.VISIBLE);
    }

    private void showComplete() {
        tv_mine_title_complete.setText("完成");
        tv_mine_cart2_jiesuan.setText("删除");
        ll_mien_cart2_total_price.setVisibility(View.INVISIBLE);
    }

    @Override
    public String setTitle() {
        return "购物车";
    }

    @Override
    public String setComplete() {
        return "编辑";
    }

    @Override
    public void initData() {
        super.initData();
    }


    private boolean isComplete = false;

    @Override
    public void complete() {
        isComplete = !isComplete;
        if (isComplete) {
            showComplete();
        } else {
            hindComplete();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_mine_cart2_jiesuan:
                List<CartBean.DataEntity.ItemssEntity.ItemsEntity> items = this.itemss.get(0).getItems();
                if (isComplete) {
                    Toast.makeText(mActivity, "开始删除", Toast.LENGTH_SHORT).show();
                    delete(items);
                    complete();
                } else {
                    Toast.makeText(mActivity, "开始结算", Toast.LENGTH_SHORT).show();
                    startPay();
                }
                break;
        }
    }

    private void delete(List<CartBean.DataEntity.ItemssEntity.ItemsEntity> items) {
        //删除
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isCheck()) {
                deletes.add(items.get(i).getId());
                if (stringBuffer.length() > 0) {
                    stringBuffer.append(",");
                }
                stringBuffer.append(items.get(i).getId());
            }
        }

        startDelete(stringBuffer);
    }

    private void startDelete(StringBuffer stringBuffer) {
        LogUtils.loge("删除购物车ID = " + stringBuffer);
        String[] u = Url.CART_DELETE_UTL;
        String url = u[0] + uid + u[1] + stringBuffer;
        new JsonUtils().loadData(url, DeleteCartBean.class);
    }

    private List<Integer> deletes = new ArrayList<>();

    @Subscribe
    public void onEventMainThread(DeleteCartBean deleteCartBean) {
        if (Constants.SUCCESS.equals(deleteCartBean.getRs_code())) {
            Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();

            for (int i = 0; i < items.size(); i++) {
                int id = items.get(i).getId();
                if (deletes.contains(id)) {
                    items.remove(i);
                }
            }
            adapter.compute();
            adapter.notifyDataSetChanged();


        } else {
            Toast.makeText(mActivity, deleteCartBean.getRs_msg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void onEventMainThread(AddCartBean addCartBean) {
        if (Constants.SUCCESS.equals(addCartBean.getRs_code())) {
            Toast.makeText(mActivity, "修改数量成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, addCartBean.getRs_msg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    //结算
    private void startPay() {
        buyItems = new ArrayList<>();
        if (items != null && items.size() > 0) {
            for(int i = 0; i < items.size(); i++) {
                if (items.get(i).isCheck()) {
                    buyItems.add(items.get(i));
                }
            }

            if (buyItems != null && buyItems.size() > 0) {
                LogUtils.loge("需要结算的商品 buyItems = " + buyItems);
                startBuy(buyItems, mActivity);
            }else{
                Toast.makeText(mActivity, "结算失败", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(mActivity, "购物车中没有商品", Toast.LENGTH_SHORT).show();
        }
    }

    public void setCount(int count) {
        if (!isComplete) {
            tv_mine_cart2_jiesuan.setText("结算(" + count + ")");
        }
    }

    public void setPrice(String price) {
        tv_mine_cart_total_price.setText("￥" + price);
    }

    public void setCheck(boolean isChecked) {
        mine_cart2_check_all.setSelected(isChecked);
    }

    public void addToCart(int num, int id) {
        String[] u = Url.ADD_CART_URL;
        String url = u[0] + uid + u[1] + id + u[2] + num;
        new JsonUtils().loadData(url, AddCartBean.class);
    }

    public void clearCannot() {
        if (cannotitems != null) {
            //删除
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < cannotitems.size(); i++) {
                deletes.add(cannotitems.get(i).getId());
                if (stringBuffer.length() > 0) {
                    stringBuffer.append(",");
                    stringBuffer.append(cannotitems.get(i).getId());
                }
            }
            startDelete(stringBuffer);
        }
    }

    /**
     * 开始结算
     * @param buyItems
     */
    private void startBuy(List<CartBean.DataEntity.ItemssEntity.ItemsEntity> buyItems, Context context) {

    }

}
