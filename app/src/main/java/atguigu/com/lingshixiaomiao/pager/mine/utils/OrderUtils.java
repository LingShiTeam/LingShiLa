package atguigu.com.lingshixiaomiao.pager.mine.utils;

import de.greenrobot.event.EventBus;

/**
 * Created by lanmang on 2016/4/25.
 */
public class OrderUtils {

    private OrderUtils(){

    }

    public static OrderUtils getInstance(){
        return Holder.INSTANCE;
    }

    public void checkOrderNum() {
        loadNum();
        EventBus.getDefault().post(getInstance());
    }

    private void loadNum() {
        for(int i = 1; i < 5; i++) {
            orderNums[i] = i;
        }
    }

    private static class Holder {
        public static OrderUtils INSTANCE = new OrderUtils();
    }


    private int[] orderNums = new int[5];

    public int[] getOrderNums() {
        return orderNums;
    }

    public void setOrderNums(int[] orderNums) {
        this.orderNums = orderNums;
    }


}
