package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CollectGoodsBean;

/**
 * Created by lanmang on 2016/4/19.
 */
public class CollectionGoodsAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<CollectGoodsBean.DataEntity.ItemsEntity> data;
    private List<CollectGoodsBean.DataEntity.ItemsEntity> data0;
    private List<CollectGoodsBean.DataEntity.ItemsEntity> data1;

    public CollectionGoodsAdapter(Activity mActivity, List<CollectGoodsBean.DataEntity.ItemsEntity> data) {
        this.mActivity = mActivity;
        this.data = data;
        sortData();
    }

    private void sortData() {
        data0 = new ArrayList<>();
        data1 = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            int status = data.get(i).getStatus();
            if (status == 0) {
                data0.add(data.get(i));
            } else {
                data1.add(data.get(i));
            }
        }

    }

    @Override
    public int getCount() {
        int count = 0;
        if (data0.size() > 0) {
            count++;
        }
        if (data1.size() > 0) {
            count++;
        }
        return count;
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
            h = new ViewHolder();
            c = View.inflate(mActivity, R.layout.mine_collection_goods_item, null);

            h.title = (TextView) c.findViewById(R.id.tv_mine_collection_goods);
            h.gridView = (GridView) c.findViewById(R.id.gv_mine_collection_goods);
            h.image = (ImageView) c.findViewById(R.id.iv_mine_collection_goods);

            c.setTag(h);
        } else {
            h = (ViewHolder) c.getTag();
        }

        List<CollectGoodsBean.DataEntity.ItemsEntity> gridData;

        if (position == 0) {
            h.title.setText("有效商品");
            h.image.setEnabled(true);
            gridData = data0;
        } else {
            h.title.setText("无效商品");
            h.image.setEnabled(false);
            gridData = data1;
        }
        CollectionGoodsGridAdapter adapter = new CollectionGoodsGridAdapter(mActivity, gridData);
        h.gridView.setAdapter(adapter);

        return c;
    }


    class ViewHolder {
        GridView gridView;
        TextView title;
        ImageView image;
    }

}
