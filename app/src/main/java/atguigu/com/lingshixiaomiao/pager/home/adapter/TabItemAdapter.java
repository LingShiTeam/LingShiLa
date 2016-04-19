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
import atguigu.com.lingshixiaomiao.pager.home.bean.TabDataBean;

/**
 * Created by Liu_haiwei on 2016/4/19.
 * TabGridView适配器
 */
public class TabItemAdapter extends BaseAdapter {
    private final Context mContent;
    private final TabDataBean tabDataBean;
    private final List<TabDataBean.DataEntity.ItemsEntity> dataLists;
    private ImageOptions imageOption;

    public TabItemAdapter(Context mContent, TabDataBean tabDataBean, List<TabDataBean.DataEntity.ItemsEntity> dataLists) {
        this.mContent = mContent;
        this.tabDataBean = tabDataBean;
        this.dataLists = dataLists;
        imageOption = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)//等比例缩小到充满长/宽居中显示, 或原样显示
                .setLoadingDrawableId(R.drawable.default_home_banner_640_270)
                .setFailureDrawableId(R.drawable.default_home_banner_640_270)
                .setConfig(Bitmap.Config.ARGB_4444)
                .build();
    }

    @Override
    public int getCount() {
        return dataLists != null ? dataLists.size() : 0;
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
            convertView = View.inflate(mContent, R.layout.item_goods, null);
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

        TabDataBean.DataEntity.ItemsEntity entity = dataLists.get(position);

        x.image().bind(holder.item_goods_img, entity.getImg().getImg_url(), imageOption);
        holder.item_goods_name.setText(entity.getTitle());
        holder.item_goods_cur_price.setText("¥" + entity.getPrice().getCurrent());
        holder.item_goods_ori_price.setText("¥" + entity.getPrice().getPrime());
        if (entity.getSold_num() < entity.getSurplus_num()) {
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
