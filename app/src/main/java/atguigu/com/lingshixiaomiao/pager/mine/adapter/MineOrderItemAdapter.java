package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.bean.OrderBean;

/**
 * Created by lanmang on 2016/4/21.
 */
public class MineOrderItemAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<OrderBean.DataEntity.ItemsEntity> items;

    public MineOrderItemAdapter(Activity mActivity, List<OrderBean.DataEntity.ItemsEntity> items) {
        this.mActivity = mActivity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
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
        OrderBean.DataEntity.ItemsEntity item = items.get(position);
        OrderBean.DataEntity.ItemsEntity.SingleEntity single = item.getSingle();

        if (c == null) {
            c = View.inflate(mActivity, R.layout.mine_order_item, null);
            h = new ViewHolder();

            h.tv_mine_order_title = (TextView) c.findViewById(R.id.tv_mine_order_title);
            h.tv_mine_order_status = (TextView) c.findViewById(R.id.tv_mine_order_status);
            h.fl_mine_image = (FrameLayout) c.findViewById(R.id.fl_mine_image);
            h.tv_mine_order_icon = (ImageView) c.findViewById(R.id.tv_mine_order_icon);
            h.tv_mine_order_goods_name = (TextView) c.findViewById(R.id.tv_mine_order_goods_name);
            h.tv_mine_order_taste = (TextView) c.findViewById(R.id.tv_mine_order_taste);
            h.tv_mine_order_price = (TextView) c.findViewById(R.id.tv_mine_order_price);
            h.tv_mine_order_count = (TextView) c.findViewById(R.id.tv_mine_order_count);
            h.tv_mine_order_count_des = (TextView) c.findViewById(R.id.tv_mine_order_count_des);
            h.tv_mine_order_freight = (TextView) c.findViewById(R.id.tv_mine_order_freight);
            h.tv_mine_order_real_price = (TextView) c.findViewById(R.id.tv_mine_order_real_price);
            h.tv_mine_order_delete = (TextView) c.findViewById(R.id.tv_mine_order_delete);
            h.tv_mine_order_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mActivity, "删除", Toast.LENGTH_SHORT).show();
                }
            });

            c.setTag(h);
        } else {
            h = (ViewHolder) c.getTag();
        }

        h.tv_mine_order_title.setText(item.getName());
        h.tv_mine_order_goods_name.setText(single.getGoods_title());
        h.tv_mine_order_taste.setText(single.getKinds());
        h.tv_mine_order_price.setText("￥ " + single.getPrice().getCurrent());
        h.tv_mine_order_count.setText(single.getNum() + "");
        h.tv_mine_order_count_des.setText("共" + item.getNum() + "件商品");
        h.tv_mine_order_real_price.setText("￥" + item.getFinal_sum());
        h.tv_mine_order_freight.setText("运费 : ￥" + item.getFreight());
        x.image().bind(h.tv_mine_order_icon, item.getSingle().getImg().getImg_url());

        return c;
    }

    class ViewHolder {
        TextView tv_mine_order_title;
        TextView tv_mine_order_status;
        FrameLayout fl_mine_image;
        ImageView tv_mine_order_icon;
        TextView tv_mine_order_goods_name;
        TextView tv_mine_order_taste;
        TextView tv_mine_order_price;
        TextView tv_mine_order_count;
        TextView tv_mine_order_count_des;
        TextView tv_mine_order_freight;
        TextView tv_mine_order_real_price;
        TextView tv_mine_order_delete;
    }


}
