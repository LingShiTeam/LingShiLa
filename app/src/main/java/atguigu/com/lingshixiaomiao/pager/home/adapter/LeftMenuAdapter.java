package atguigu.com.lingshixiaomiao.pager.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomeBean;

/**
 * Created by Liu_haiwei on 2016/4/14.
 * 侧滑菜单listview的适配器
 */
public class LeftMenuAdapter extends BaseAdapter {
    private final Activity mActivity;
    private final HomeBean homeBean;
    private final List<HomeBean.DataEntity.ClassifiesEntity> data;

    public LeftMenuAdapter(Activity mActivity, HomeBean homeBean) {
        this.mActivity = mActivity;
        this.homeBean = homeBean;
        data = homeBean.getData().getClassifies();
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mActivity, R.layout.item_drawer_classify_list, null);
            holder.item_drawer_category_icon = (ImageView) convertView.findViewById(R.id.item_drawer_category_icon);
            holder.item_drawer_category_title = (TextView) convertView.findViewById(R.id.item_drawer_category_title);
            holder.item_drawer_category_desc = (TextView) convertView.findViewById(R.id.item_drawer_category_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        HomeBean.DataEntity.ClassifiesEntity entity = data.get(position);

        x.image().bind(holder.item_drawer_category_icon,entity.getImg().getImg_url());
        holder.item_drawer_category_title.setText(entity.getTitle());
        holder.item_drawer_category_desc.setText(entity.getDesc());

        return convertView;
    }

    static class ViewHolder {
        ImageView item_drawer_category_icon;
        TextView item_drawer_category_title;
        TextView item_drawer_category_desc;

    }
}
