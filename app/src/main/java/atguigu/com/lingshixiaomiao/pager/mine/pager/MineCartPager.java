package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.MineCartAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartNotBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/10.
 */
public class MineCartPager extends ContentBasePager {

    private final String uid;
    private ListView lv_mine_cart_can_buy;
    private ListView lv_mine_cart_cannot_buy;
    private List<CartBean.DataEntity.ItemssEntity> canitemss;
    private List<CartNotBean.DataEntity.ItemssEntity> cannotitemss;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public MineCartPager(Activity mActivity) {
        super(mActivity);
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        uid = loginBean.getData().getUid();
        loadData(true);
        loadData(false);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_cart, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        lv_mine_cart_can_buy = (ListView) v.findViewById(R.id.lv_mine_cart_can_buy);
        lv_mine_cart_cannot_buy = (ListView) v.findViewById(R.id.lv_mine_cart_cannot_buy);

    }

    @Override
    public String setTitle() {
        return "购物车";
    }

    @Override
    public void initData() {
        super.initData();

    }

    private void loadData(final boolean canBuy) {

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
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtils.loge("onFinished");
            }
        });
    }

    private void parseJson(String json, boolean canBuy) {
        if (canBuy) {
            CartBean cartBean = new Gson().fromJson(json, CartBean.class);
            if (Constants.SUCCESS.equals(cartBean.getRs_code())) {
                canitemss = cartBean.getData().getItemss();
                LogUtils.loge("itemss = " + canitemss);
                addCanData();
            } else {
                Toast.makeText(mActivity, cartBean.getRs_msg(), Toast.LENGTH_SHORT).show();
            }
        }else{
            CartNotBean cartNotBean = new Gson().fromJson(json, CartNotBean.class);
            if (Constants.SUCCESS.equals(cartNotBean.getRs_code())) {
                cannotitemss = cartNotBean.getData().getItemss();
                LogUtils.loge("itemss = " + canitemss);
                addCanNotData();
            }
        }
    }

    private void addCanNotData() {
        MineCartAdapter cannotAdapter = new MineCartAdapter(mActivity, cannotitemss, false);
        lv_mine_cart_cannot_buy.setAdapter(cannotAdapter);
    }

    private void addCanData() {
        MineCartAdapter canAdapter = new MineCartAdapter(mActivity, canitemss);
        lv_mine_cart_can_buy.setAdapter(canAdapter);
    }

    @Override
    public String setComplete() {
        return "编辑";
    }
}
