package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CollectGoodsBean;
import atguigu.com.lingshixiaomiao.pager.scale.activity.SnackInfomationActivity;

/**
 * Created by lanmang on 2016/4/19.
 */
public class CollectionGoodsGridAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<CollectGoodsBean.DataEntity.ItemsEntity> gridData;

    public CollectionGoodsGridAdapter(Activity mActivity, List<CollectGoodsBean.DataEntity.ItemsEntity> gridData) {
        this.mActivity = mActivity;
        this.gridData = gridData;
    }

    @Override
    public int getCount() {
        return gridData.size();
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
        final CollectGoodsBean.DataEntity.ItemsEntity data = gridData.get(position);
        final ViewHolder h;
        if (c == null) {
            h = new ViewHolder();

            c = View.inflate(mActivity, R.layout.mine_coll_grid_item, null);

            h.tv_mine_coll_image = (ImageView) c.findViewById(R.id.tv_mine_coll_image);
            h.tv_mine_coll_des = (TextView) c.findViewById(R.id.tv_mine_coll_des);
            h.tv_mine_coll_current_price = (TextView) c.findViewById(R.id.tv_mine_coll_current_price);
            h.tv_mine_coll_old_price = (TextView) c.findViewById(R.id.tv_mine_coll_old_price);
            h.tv_mine_coll_add = (ImageView) c.findViewById(R.id.tv_mine_coll_add);
            h.iv_mine_coll_goods_off = (ImageView) c.findViewById(R.id.iv_mine_coll_goods_off);
            h.cb_mine_coll_goods_del = (CheckBox) c.findViewById(R.id.cb_mine_coll_goods_del);
            h.ll_mine_coll_goods = (FrameLayout) c.findViewById(R.id.ll_mine_coll_goods);
            h.cb_mine_coll_goods_del.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.setChecked(isChecked);
                }
            });
            h.ll_mine_coll_goods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.isShowDelete()) {
                        h.cb_mine_coll_goods_del.setChecked(!h.cb_mine_coll_goods_del.isChecked());
                    } else {
                        loadDes(data);
                    }
                }
            });

            c.setTag(h);
        } else {
            h = (ViewHolder) c.getTag();
        }


        x.image().bind(h.tv_mine_coll_image, data.getImg().getImg_url());
        h.tv_mine_coll_des.setText(data.getTitle());
        h.tv_mine_coll_current_price.setText("￥ " + data.getPrice().getCurrent() + "");
        h.tv_mine_coll_old_price.setText(data.getPrice().getPrime() + "");
        h.tv_mine_coll_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (data.getStatus() != 0) {
            h.iv_mine_coll_goods_off.setVisibility(View.VISIBLE);
            h.tv_mine_coll_add.setClickable(false);
        } else {
            h.iv_mine_coll_goods_off.setVisibility(View.GONE);
            h.tv_mine_coll_add.setClickable(true);
        }

        if (data.isShowDelete()) {
            h.cb_mine_coll_goods_del.setVisibility(View.VISIBLE);
        } else {
            h.cb_mine_coll_goods_del.setVisibility(View.GONE);
        }

        return c;
    }

    private void loadDes(CollectGoodsBean.DataEntity.ItemsEntity data) {
        //获取当前食物的id
        int id = data.getId();
        Intent intent = new Intent(mActivity, SnackInfomationActivity.class);
        intent.putExtra("snack_id", id);
        mActivity.startActivity(intent);
    }

    class ViewHolder {
        ImageView tv_mine_coll_image;
        TextView tv_mine_coll_des;
        TextView tv_mine_coll_current_price;
        TextView tv_mine_coll_old_price;
        ImageView tv_mine_coll_add;
        ImageView iv_mine_coll_goods_off;
        CheckBox cb_mine_coll_goods_del;
        FrameLayout ll_mine_coll_goods;
    }

}
