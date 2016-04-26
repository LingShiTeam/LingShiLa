package atguigu.com.lingshixiaomiao.pager.mine.utils;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;

/**
 * Created by lanmang on 2016/4/25.
 */
public class CartUtils {

    private String uid;
    private List<CartBean.DataEntity.ItemssEntity> itemss;
    private List<CartBean.DataEntity.ItemssEntity.ItemsEntity> items;
    private int goodsNum = 0;

    private CartUtils() {
        if (LoginUtils.getInstance().isLogin()) {
            LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
            uid = loginBean.getData().getUid();
        }
    }

    public static CartUtils getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static CartUtils INSTANCE = new CartUtils();
    }


    public void checkGoodsNum() {

        String[] u = Url.CART_URL;
        String canBuyUrl = u[0] + uid + u[1] + Constants.CART_CAN_BUY;
        x.http().get(new RequestParams(canBuyUrl), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String json) {
                LogUtils.loge("onSuccess");
                LogUtils.loge("json = " + json);
                json = json.replaceFirst("items", "itemss");
                LogUtils.loge("json = " + json);
                parseJson(json);
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

    private void parseJson(String json) {
        CartBean cartBean = new Gson().fromJson(json, CartBean.class);
        if (Constants.SUCCESS.equals(cartBean.getRs_code())) {
            itemss = cartBean.getData().getItemss();
            if (itemss != null) {
                items = itemss.get(0).getItems();
                if (items != null) {
                    int num = 0;
                    for (int i = 0; i < items.size(); i++) {
                        num += items.get(i).getNum();
                    }
                    num = num > 99 ? 99 : num;
                    showNum(num);
                }
            }
        }

    }

    private void showNum(int num) {
        goodsNum = num;
        EventBus.getDefault().post(getInstance());
    }

    public int getGoodsNum() {
        return goodsNum;
    }
}
