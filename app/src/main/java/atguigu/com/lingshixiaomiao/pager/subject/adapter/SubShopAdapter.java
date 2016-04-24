package atguigu.com.lingshixiaomiao.pager.subject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubTopShopBean;

/**
 * Created by CheungJhonny on 2016/4/20.
 */
public class SubShopAdapter extends BaseAdapter {

    private final SubTopShopBean shopBean;
    private final Context context;
    private List<SubTopShopBean.DataBean.ItemsBean> shopItems;
    private ImageOptions imageOptions;

    public SubShopAdapter(Context context, SubTopShopBean shopBean, List<SubTopShopBean.DataBean.ItemsBean> shopItems) {
        this.context = context;
        this.shopBean = shopBean;
        this.shopItems = shopItems;
        imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)//设置等比例缩小到充满长宽居中显示 或愿样显示
                .setLoadingDrawableId(R.drawable.default_home_banner_640_270)
                .setFailureDrawableId(R.drawable.default_home_banner_640_270)
                .setConfig(Bitmap.Config.ARGB_4444)
                .build();

    }



    @Override
    public int getCount() {
        return shopItems.size();
    }

    @Override
    public Object getItem(int position) {
        return shopItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_subject_shop, null);
            holder = new ViewHolder();

            holder.iv_shop_icon = (ImageView) convertView.findViewById(R.id.iv_shop_icon);
            holder.iv_shop_empty = (ImageView) convertView.findViewById(R.id.iv_shop_empty);
            holder.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            holder.tv_price_current = (TextView) convertView.findViewById(R.id.tv_price_current);
            holder.tv_price_prime = (TextView) convertView.findViewById(R.id.tv_price_prime);
            holder.add_subshop_cart = (ImageView) convertView.findViewById(R.id.add_subshop_cart);
            holder.add_subshop_unable_cart = (ImageView) convertView.findViewById(R.id.add_subshop_unable_cart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        //装载数据
        holder.tv_shop_name.setText(shopItems.get(position).getTitle());
        holder.tv_price_current.setText("￥ " + shopItems.get(position).getPrice().getCurrent());
        holder.tv_price_prime.setText("￥ " + shopItems.get(position).getPrice().getPrime());
        x.image().bind(holder.iv_shop_icon, shopItems.get(position).getImg().getImg_url(), imageOptions);
        //获取商品的剩余数量

        SubTopShopBean.DataBean.ItemsBean itemsBean = shopItems.get(position);
        if (0 == itemsBean.getSurplus_num()) {
            holder.iv_shop_empty.setVisibility(View.VISIBLE);
            holder.add_subshop_cart.setVisibility(View.GONE);
            holder.add_subshop_unable_cart.setVisibility(View.VISIBLE);
            holder.tv_shop_name.setTextColor(Color.GRAY);
            holder.tv_price_current.setTextColor(Color.GRAY);
        } else {
            holder.iv_shop_empty.setVisibility(View.GONE);
            holder.add_subshop_cart.setVisibility(View.VISIBLE);
            holder.add_subshop_unable_cart.setVisibility(View.GONE);
            holder.tv_shop_name.setTextColor(context.getResources().getColor(R.color.subject_shop));
            holder.tv_price_current.setTextColor(context.getResources().getColor(R.color.subject_shop));
        }
        return convertView;
    }


    class ViewHolder {
        ImageView iv_shop_icon; // 商品图片
        ImageView iv_shop_empty; // 商品不能出售状态

        TextView tv_shop_name; // 商品的介绍
        TextView tv_price_current; //商品现在的价格
        TextView tv_price_prime; // 商品原来的价格
        ImageView add_subshop_cart; // 商品可以放入购物车
        ImageView add_subshop_unable_cart; // 商品不可出售
    }

}
