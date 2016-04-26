package atguigu.com.lingshixiaomiao.pager.home.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.bean.ShoppingListBean;

/**
 * Created by Liu_haiwei on 2016/4/20.
 * 商品列表适配器
 */
public class ShoppingGridAdapter extends BaseAdapter {

    private final Context context;
    private final List<ShoppingListBean.DataEntity.ItemsEntity> dataList;
    private final ImageOptions imageOption;

    public ShoppingGridAdapter(Context context, List<ShoppingListBean.DataEntity.ItemsEntity> dataList) {
        this.context = context;
        this.dataList = dataList;
        imageOption = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)//等比例缩小到充满长/宽居中显示, 或原样显示
                .setLoadingDrawableId(R.drawable.default_home_banner_640_270)
                .setFailureDrawableId(R.drawable.default_home_banner_640_270)
                .setConfig(Bitmap.Config.ARGB_4444)
                .build();
    }

    @Override
    public int getCount() {
        return dataList != null ? dataList.size() : 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_goods, null);
            holder.item_goods_img = (ImageView) convertView.findViewById(R.id.item_goods_img);
            holder.item_goods_empty = (ImageView) convertView.findViewById(R.id.item_goods_empty);
            holder.item_goods_name = (TextView) convertView.findViewById(R.id.item_goods_name);
            holder.item_goods_cur_price = (TextView) convertView.findViewById(R.id.item_goods_cur_price);
            holder.item_goods_ori_price = (TextView) convertView.findViewById(R.id.item_goods_ori_price);
            holder.add_to_cart_2x = (ImageView) convertView.findViewById(R.id.add_to_cart_2x);
            holder.add_to_cart_unable_2x = (ImageView) convertView.findViewById(R.id.add_to_cart_unable_2x);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ShoppingListBean.DataEntity.ItemsEntity entity = dataList.get(position);

        x.image().bind(holder.item_goods_img, entity.getImg().getImg_url(), imageOption);
        holder.item_goods_name.setText(entity.getTitle());
        holder.item_goods_cur_price.setText("¥" + entity.getPrice().getCurrent());
        holder.item_goods_ori_price.setText("¥" + entity.getPrice().getPrime());
        if (entity.getSurplus_num() <= 0) {
            holder.item_goods_empty.setVisibility(View.GONE);
            holder.add_to_cart_2x.setVisibility(View.VISIBLE);
            holder.add_to_cart_unable_2x.setVisibility(View.GONE);
        } else {
            holder.item_goods_empty.setVisibility(View.VISIBLE);
            holder.add_to_cart_2x.setVisibility(View.GONE);
            holder.add_to_cart_unable_2x.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView item_goods_img;
        ImageView item_goods_empty;
        TextView item_goods_name;
        TextView item_goods_cur_price;
        TextView item_goods_ori_price;
        ImageView add_to_cart_2x;
        ImageView add_to_cart_unable_2x;
    }

}
