package atguigu.com.lingshixiaomiao.pager.scale.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.x;

import java.text.DecimalFormat;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.scale.bean.ScaleCarBean;

/**
 * 购物车lv的适配器
 * <p/>
 * Created by Administrator on 2016/4/23 0023.
 */
public class CarItemAdapter extends BaseAdapter {

    private Context context;

    private List<ScaleCarBean.DataEntity.ItemssEntity.ItemsEntity> items;

    private TextView tvCount;

    private TextView tvXiaoJi;

    private Button btnCarJiesuan;

    private TextView tvCarHeji;

    private ImageView cb_car_ziying_quanxuan;

    private ImageView cbCarQuanxuan;

    public CarItemAdapter(Context context, List<ScaleCarBean.DataEntity.ItemssEntity.ItemsEntity> items,
                          TextView tvCount, TextView tvXiaoJi, TextView tvCarHeji, Button btnCarJiesuan,
                          ImageView cb_car_ziying_quanxuan, ImageView cbCarQuanxuan) {

        this.context = context;

        this.items = items;

        this.tvCount = tvCount;

        this.tvXiaoJi = tvXiaoJi;

        this.tvCarHeji = tvCarHeji;

        this.btnCarJiesuan = btnCarJiesuan;

        this.cb_car_ziying_quanxuan = cb_car_ziying_quanxuan;

        this.cbCarQuanxuan = cbCarQuanxuan;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        //得到当前item的数据对象
        final ScaleCarBean.DataEntity.ItemssEntity.ItemsEntity itemsEntity = items.get(position);

        final Holder holder;

        if (convertView == null) {

            convertView = View.inflate(context, R.layout.scale_item_car, null);

            holder = new Holder();

            holder.llCarItemZiying = (LinearLayout) convertView.findViewById(R.id.ll_car_item_ziying);
            holder.cbCarZiyingQuanxuan = (ImageView) convertView.findViewById(R.id.cb_car_ziying_quanxuan);
            holder.llCarItemBukemai = (LinearLayout) convertView.findViewById(R.id.ll_car_item_bukemai);
            holder.llCarItemZongji = (LinearLayout) convertView.findViewById(R.id.ll_car_item_zongji);
            holder.cbCarItem = (ImageView) convertView.findViewById(R.id.cb_car_item);
            holder.flMineImage = (FrameLayout) convertView.findViewById(R.id.fl_mine_image);
            holder.ivCarItem = (ImageView) convertView.findViewById(R.id.iv_car_item);
            holder.tvCarItemName = (TextView) convertView.findViewById(R.id.tv_car_item_name);
            holder.tvCarItemKouwei = (TextView) convertView.findViewById(R.id.tv_car_item_kouwei);
            holder.btnCarItemJian = (Button) convertView.findViewById(R.id.btn_car_item_jian);
            holder.btnCarItemShuliang = (Button) convertView.findViewById(R.id.btn_car_item_shuliang);
            holder.btnCarItemJia = (Button) convertView.findViewById(R.id.btn_car_item_jia);
            holder.tvCarItemCurrentprice = (TextView) convertView.findViewById(R.id.tv_car_item_currentprice);
            holder.tvCarItemTotalcount = (TextView) convertView.findViewById(R.id.tv_car_item_totalcount);
            holder.tvCarItemXiaoji = (TextView) convertView.findViewById(R.id.tv_car_item_xiaoji);
            holder.tvCarItemPreprice = (TextView) convertView.findViewById(R.id.tv_car_item_preprice);

            convertView.setTag(holder);
        } else {

            holder = (Holder) convertView.getTag();
        }

        //item中的视图设置各种监听
        setListener(itemsEntity, holder);

        //根据当前item的bean数据中的checked属性，手动设置cb的状态
        holder.cbCarItem.setSelected(itemsEntity.isChecked());

        String img_url = itemsEntity.getImg().getImg_url();
        x.image().bind(holder.ivCarItem, img_url);

        String goods_title = itemsEntity.getGoods_title();
        holder.tvCarItemName.setText(goods_title);

        holder.tvCarItemKouwei.setText(itemsEntity.getKinds());

        //初始化商品数量按钮
        holder.btnCarItemShuliang.setText(itemsEntity.getNum() + "");

        holder.tvCarItemCurrentprice.setText(itemsEntity.getPrice().getCurrent() + "");

        holder.tvCarItemPreprice.setText(itemsEntity.getPrice().getPrime() + "");

        //价格上加删除线
        holder.tvCarItemPreprice.getPaint().setAntiAlias(true);//抗锯齿
        holder.tvCarItemPreprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

        return convertView;
    }

    /**
     * 设置监听
     *
     * @param itemsEntity
     * @param holder
     */
    private void setListener(final ScaleCarBean.DataEntity.ItemssEntity.ItemsEntity itemsEntity, final Holder holder) {

        //item的cb的监听
        holder.cbCarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean selected = !holder.cbCarItem.isSelected();

                holder.cbCarItem.setSelected(selected);

                itemsEntity.setChecked(selected);

                setDataOfXiaoJiFooter();

                //判断全选cb是否需要改变状态
                int itemCount = 0;

                for (int i = 0; i < items.size(); i++) {

                    boolean checked = items.get(i).isChecked();

                    if (checked) {
                        itemCount++;
                    }
                }

                if (itemCount == items.size()) {

                    cb_car_ziying_quanxuan.setSelected(true);
                    cbCarQuanxuan.setSelected(true);
                } else {
                    cb_car_ziying_quanxuan.setSelected(false);
                    cbCarQuanxuan.setSelected(false);
                }
            }
        });

        //设置监听
        holder.btnCarItemShuliang.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

            }
        });

        holder.btnCarItemJian.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                String text = (String) holder.btnCarItemShuliang.getText();
                int count = Integer.parseInt(text);
                count--;
                if (count < 1) {
                    count = 1;
                }
                itemsEntity.setNum(count);
                holder.btnCarItemShuliang.setText(count + "");

                if (itemsEntity.isChecked()) {

                    setDataOfXiaoJiFooter();
                }
            }
        });

        /**
         * 当前item的cb的监听
         */
        holder.btnCarItemJia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String text = (String) holder.btnCarItemShuliang.getText();
                int count = Integer.parseInt(text);
                count++;
                if (count > itemsEntity.getSurplus_num()) {
                    count = itemsEntity.getSurplus_num();
                }
                itemsEntity.setNum(count);
                holder.btnCarItemShuliang.setText(count + "");

                if (itemsEntity.isChecked()) {

                    setDataOfXiaoJiFooter();
                }
            }
        });
    }

    /**
     * 计算并设置小计footer中的数据
     */
    public void setDataOfXiaoJiFooter() {

        int count = 0;
        double totalPrice = 0;

        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).isChecked()) {
                count += items.get(i).getNum();
                totalPrice += (items.get(i).getPrice().getCurrent()) * (items.get(i).getNum());
            }
        }


        tvCount.setText("共" + count + "件商品");
        btnCarJiesuan.setText("结算(" + count + ")");

        DecimalFormat df = new DecimalFormat("######0.00");
        String format = df.format(totalPrice);
        tvXiaoJi.setText("￥" + format);
        tvCarHeji.setText("￥" + format);
    }

    class Holder {

        private LinearLayout llCarItemZiying;
        private ImageView cbCarZiyingQuanxuan;
        private LinearLayout llCarItemBukemai;
        private ImageView cbCarItem;
        private FrameLayout flMineImage;
        private ImageView ivCarItem;
        private TextView tvCarItemName;
        private TextView tvCarItemKouwei;
        private Button btnCarItemJian;
        private Button btnCarItemShuliang;
        private Button btnCarItemJia;
        private TextView tvCarItemCurrentprice;
        private TextView tvCarItemPreprice;
        private TextView tvCarItemTotalcount;
        private TextView tvCarItemXiaoji;
        private LinearLayout llCarItemZongji;
    }
}
