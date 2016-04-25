package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartNotBean;

/**
 * Created by lanmang on 2016/4/22.
 */
public class MineCartAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<CartBean.DataEntity.ItemssEntity> canitemss;
    private List<CartNotBean.DataEntity.ItemssEntity> cannotitemss;
    private boolean can = true;

    public MineCartAdapter(Activity mActivity, List<CartBean.DataEntity.ItemssEntity> canitemss) {
        this.mActivity = mActivity;
        this.canitemss = canitemss;
    }

    public MineCartAdapter(Activity mActivity, List<CartNotBean.DataEntity.ItemssEntity> cannotitemss, boolean can) {
        this.can = can;
        this.mActivity = mActivity;
        this.cannotitemss = cannotitemss;
    }

    @Override
    public int getCount() {
        if (can) {
            return canitemss.get(0).getItems().size();
        }else{
            return cannotitemss.size();
        }
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
    public View getView(int position, View c, ViewGroup parent) {
        ViewHolder h;

        if (c == null) {
            c = View.inflate(mActivity, R.layout.mine_cart_item, null);
            h = new ViewHolder();

            h.cb_mine_car_item = (CheckBox) c.findViewById(R.id.cb_mine_car_item);
            h.fl_mine_image = (FrameLayout) c.findViewById(R.id.fl_mine_image);
            h.iv_mine_car_item = (ImageView) c.findViewById(R.id.iv_mine_car_item);
            h.tv_mine_car_item_name = (TextView) c.findViewById(R.id.tv_mine_car_item_name);
            h.tv_mine_car_item_kouwei = (TextView) c.findViewById(R.id.tv_mine_car_item_kouwei);
            h.btn_mine_car_item_jian = (Button) c.findViewById(R.id.btn_mine_car_item_jian);
            h.btn_mine_car_item_num = (Button) c.findViewById(R.id.btn_mine_car_item_num);
            h.btn_mine_car_item_add = (Button) c.findViewById(R.id.btn_mine_car_item_add);
            h.tv_mine_cart_current_price = (TextView) c.findViewById(R.id.tv_mine_cart_current_price);
            h.tv_mine_cart_old_price = (TextView) c.findViewById(R.id.tv_mine_cart_old_price);

            c.setTag(h);
        } else {
            h = (ViewHolder) c.getTag();
        }
        if (can) {
            CartBean.DataEntity.ItemssEntity total = canitemss.get(0);
            CartBean.DataEntity.ItemssEntity.ItemsEntity item = total.getItems().get(position);
            h.tv_mine_car_item_name.setText(item.getGoods_title());
            h.tv_mine_car_item_kouwei.setText(item.getKinds());
            h.tv_mine_cart_current_price.setText("￥" + item.getPrice().getCurrent() + "");
            h.tv_mine_cart_old_price.setText("￥" + item.getPrice().getPrime() + "");
            h.btn_mine_car_item_num.setText(item.getNum() + "");
            x.image().bind(h.iv_mine_car_item, item.getImg().getImg_url());
        }else{
            CartNotBean.DataEntity.ItemssEntity item = cannotitemss.get(position);
            h.tv_mine_car_item_name.setText(item.getGoods_title());
            h.tv_mine_car_item_kouwei.setText(item.getKinds());
            h.tv_mine_cart_current_price.setText("￥" + item.getPrice().getCurrent() + "");
            h.tv_mine_cart_old_price.setText("￥" + item.getPrice().getPrime() + "");
            x.image().bind(h.iv_mine_car_item, item.getImg().getImg_url());
        }

        return c;
    }

    class ViewHolder {
        CheckBox cb_mine_car_item;
        FrameLayout fl_mine_image;
        ImageView iv_mine_car_item;
        TextView tv_mine_car_item_name;
        TextView tv_mine_car_item_kouwei;
        Button btn_mine_car_item_jian;
        Button btn_mine_car_item_num;
        Button btn_mine_car_item_add;
        TextView tv_mine_cart_current_price;
        TextView tv_mine_cart_old_price;
    }
}
