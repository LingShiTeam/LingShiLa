package atguigu.com.lingshixiaomiao.pager.home.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomePagerBean;

/**
 * Created by Liu_haiwei on 2016/4/10.
 * ListView的适配器
 */
public class ListViewAdapter extends BaseAdapter {

    private final Activity mActivity;
    private final List<HomePagerBean.DataEntity.ItemsEntity> data;
    // 置顶按钮
    private final ImageView iv_home_tiptop;
    private ImageOptions imageOption;

    public ListViewAdapter(Activity mActivity, List<HomePagerBean.DataEntity.ItemsEntity> data, ImageView iv_home_tiptop) {
        this.mActivity = mActivity;
        this.data = data;
        this.iv_home_tiptop = iv_home_tiptop;
        // xUtils的默认图片设置
        imageOption = new ImageOptions.Builder()
                //.setImageScaleType(ImageView.ScaleType.FIT_START)//等比例缩小到充满长/宽居中显示, 或原样显示
                .setLoadingDrawableId(R.drawable.default_home_banner_640_270)
                .setFailureDrawableId(R.drawable.default_home_banner_640_270)
                .setConfig(Bitmap.Config.ARGB_4444)
                .build();
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
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

        Log.e("tag", position + "");

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mActivity, R.layout.home_listview_common_item, null);

            holder.item_home_item_desc = (TextView) convertView.findViewById(R.id.item_home_item_desc);
            holder.item_home_item_img = (ImageView) convertView.findViewById(R.id.item_home_item_img);
            holder.item_home_item_price = (TextView) convertView.findViewById(R.id.item_home_item_price);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position >= 10) {
            iv_home_tiptop.setVisibility(View.VISIBLE);
        } else {
            iv_home_tiptop.setVisibility(View.GONE);
        }

        HomePagerBean.DataEntity.ItemsEntity itemsEntity = data.get(position);

        x.image().bind(holder.item_home_item_img, itemsEntity.getImg().getImg_url(), imageOption);
        holder.item_home_item_desc.setText(itemsEntity.getTitle());
        holder.item_home_item_price.setText("¥" + itemsEntity.getPrice().getCurrent());
        return convertView;
    }

    static class ViewHolder {

        ImageView item_home_item_img;
        TextView item_home_item_desc;
        TextView item_home_item_price;

    }

}
