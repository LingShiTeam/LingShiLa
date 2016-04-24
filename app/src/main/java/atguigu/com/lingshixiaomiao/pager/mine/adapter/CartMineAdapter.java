package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.x;

import java.text.DecimalFormat;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CartNotBean;
import atguigu.com.lingshixiaomiao.pager.mine.pager.CartMinePager;

/**
 * Created by lanmang on 2016/4/22.
 */
public class CartMineAdapter extends RecyclerView.Adapter {
    private Activity mActivity;
    private List<CartBean.DataEntity.ItemssEntity> canitems;
    private List<CartBean.DataEntity.ItemssEntity.ItemsEntity> items;
    private List<CartNotBean.DataEntity.ItemssEntity> cannotitems;
    private int size1 = 0;
    private int size2 = 0;
    private CartMinePager pager;
    private int delete = 0;
    private String price;
    public int count = 0;
    private int totalCount = 0;
    private boolean checkSingle = false;
    private AlertDialog dialog;
    private EditText iv_mine_cart2_dilaog_num;

    public CartMineAdapter(Activity mActivity, List<CartBean.DataEntity.ItemssEntity> canitems,
                           List<CartNotBean.DataEntity.ItemssEntity> cannotitems, CartMinePager pager) {
        this.mActivity = mActivity;
        this.canitems = canitems;
        this.cannotitems = cannotitems;
        this.pager = pager;
        compute();
    }

    public void compute() {
        if (cannotitems != null) {
            size2 = cannotitems.size();
        }

        if (canitems != null) {
            items = canitems.get(0).getItems();
            size1 = items.size();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder h = null;
        View inflate = null;
        switch (viewType) {
            case 11:
                inflate = View.inflate(mActivity, R.layout.mine_cart2_head1, null);
                h = new ViewHolderCanHead1(inflate);
                break;
            case 0:
                inflate = View.inflate(mActivity, R.layout.mine_cart_can_head, null);
                h = new ViewHolderCanHead(inflate);
                break;
            case 1:
                inflate = View.inflate(mActivity, R.layout.mine_cart2_item, null);
                h = new ViewHolderCan(inflate);
                break;
            case 2:
                inflate = View.inflate(mActivity, R.layout.mine_cart_can_bottom, null);
                h = new ViewHolderCanBottom(inflate);
                break;
            case 3:
                inflate = View.inflate(mActivity, R.layout.mine_cart2_cannot_item, null);
                h = new ViewHolderCannot(inflate);
                break;
            case 4:
                inflate = View.inflate(mActivity, R.layout.mine_cart_cannot_bottom, null);
                h = new ViewHolderTaoBao(inflate);
                break;
        }
        return h;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case 11:
                ViewHolderCanHead1 h11 = (ViewHolderCanHead1) holder;
                getCount();
                h11.tv_mine_cart2_head_total.setText("共" + totalCount + "件商品");
                break;
            case 0:
                ViewHolderCanHead h0 = (ViewHolderCanHead) holder;
                if (items != null && items.size() > 0) {
                    h0.ll_mine_cart2_can_head_check_all.setVisibility(View.VISIBLE);
                    h0.cb_mine_cart2_head_check_all.setSelected(canitems.get(0).isCheckAll());
                } else {
                    h0.ll_mine_cart2_can_head_check_all.setVisibility(View.GONE);
                }

                break;
            case 1:
                CartBean.DataEntity.ItemssEntity.ItemsEntity item = items.get(getRealPosition(position));
                ViewHolderCan h1 = (ViewHolderCan) holder;

                h1.cb_mine_car_item_center.setSelected(item.isCheck());

                h1.tv_mine_car_item_name.setText(item.getGoods_title());
                h1.tv_mine_car_item_kouwei.setText(item.getKinds());
                h1.tv_mine_cart_current_price.setText("￥" + item.getPrice().getCurrent() + "");
                h1.tv_mine_cart_old_price.setText("￥" + item.getPrice().getPrime() + "");
                h1.btn_mine_car_item_num.setText(item.getNum() + "");
                x.image().bind(h1.iv_mine_car_item, item.getImg().getImg_url());
                break;

            case 2:
                ViewHolderCanBottom h2 = (ViewHolderCanBottom) holder;
                if (items != null && items.size() > 0) {
                    h2.ll_mine_cart2_bottom.setVisibility(View.VISIBLE);
                    getCount();
                    h2.tv_mine_cart2_bottom_total.setText("共" + count + "件商品");
                    getPrice();
                    h2.tv_mine_cart2_bottom_price.setText("￥" + price);
                } else {
                    h2.ll_mine_cart2_bottom.setVisibility(View.GONE);
                }
                break;
            case 3:
                position = getRealPosition(position);
                CartNotBean.DataEntity.ItemssEntity itemnot = cannotitems.get(position);
                ViewHolderCannot h3 = (ViewHolderCannot) holder;

                if (position == 0) {
                    h3.ll_mine_cart2_mot_top.setVisibility(View.VISIBLE);
                } else {
                    h3.ll_mine_cart2_mot_top.setVisibility(View.GONE);
                }

                if (position == cannotitems.size() - 1) {
                    h3.tv_mine_cart2_cannot_clear.setVisibility(View.VISIBLE);
                } else {
                    h3.tv_mine_cart2_cannot_clear.setVisibility(View.GONE);
                }

                h3.tv_mine_car_item_name.setText(itemnot.getGoods_title());
                h3.tv_mine_car_item_kouwei.setText(itemnot.getKinds());
                h3.tv_mine_cart_current_price.setText("￥" + itemnot.getPrice().getCurrent() + "");
                h3.tv_mine_cart_old_price.setText("￥" + itemnot.getPrice().getPrime() + "");
                x.image().bind(h3.iv_mine_car_item, itemnot.getImg().getImg_url());
                break;

            case 4:
                //直接显示淘宝
                break;
        }
    }

    public int getRealPosition(int position) {

        if (position < 2) {
            return position;
        }

        if (position <= 2 + size1) {
            return position - 2;
        }

        if (position == getItemCount() - 1) {
            return getItemCount() - 1;
        }

        return (position - 3) - size1;
    }

    private void getPrice() {
        int price = 0;
        for (int i = 0; i < items.size(); i++) {
            CartBean.DataEntity.ItemssEntity.ItemsEntity item = items.get(i);
            if (item.isCheck()) {
                price += item.getNum() * item.getPrice().getCurrent();
            }
        }

        DecimalFormat df = new DecimalFormat("######0.00");
        this.price = df.format(price);

        pager.setPrice(this.price);

    }

    private void getCount() {
        count = 0;
        totalCount = 0;

        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                CartBean.DataEntity.ItemssEntity.ItemsEntity item = items.get(i);
                int num = item.getNum();
                totalCount += num;
                if (item.isCheck()) {
                    count += num;
                }
            }
        }
        pager.setCount(count);

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 11;
        }

        if (position == 1) {
            return 0;
        }

        if (position == getItemCount() - 1) {
            return 4;
        }

        if (position < size1 + 2) {
            return 1;
        }

        if (position == size1 + 2) {
            return 2;
        }

        return 3;
    }

    @Override
    public int getItemCount() {
        return 4 + size1 + size2;
    }

    public void selectAll(boolean isChecked) {
        if (canitems != null) {
            canitems.get(0).setCheckAll(isChecked);
            notifyItemChanged(1);
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setCheck(isChecked);
                notifyItemChanged(i + 2);
            }
            refresh();
        }
    }

    class ViewHolderCan extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView cb_mine_car_item_center;
        private FrameLayout fl_mine_image;
        private ImageView iv_mine_car_item;
        private TextView tv_mine_car_item_name;
        private TextView tv_mine_car_item_kouwei;
        private ImageView btn_mine_car_item_jian;
        private Button btn_mine_car_item_num;
        private ImageView btn_mine_car_item_add;
        private TextView tv_mine_cart_current_price;
        private TextView tv_mine_cart_old_price;

        public ViewHolderCan(View itemView) {
            super(itemView);
            //中间
            cb_mine_car_item_center = (ImageView) itemView.findViewById(R.id.cb_mine_car_item_center);
            fl_mine_image = (FrameLayout) itemView.findViewById(R.id.fl_mine_image);
            iv_mine_car_item = (ImageView) itemView.findViewById(R.id.iv_mine_car_item);
            tv_mine_car_item_name = (TextView) itemView.findViewById(R.id.tv_mine_car_item_name);
            tv_mine_car_item_kouwei = (TextView) itemView.findViewById(R.id.tv_mine_car_item_kouwei);
            btn_mine_car_item_jian = (ImageView) itemView.findViewById(R.id.btn_mine_car_item_jian);
            btn_mine_car_item_num = (Button) itemView.findViewById(R.id.btn_mine_car_item_num);
            btn_mine_car_item_add = (ImageView) itemView.findViewById(R.id.btn_mine_car_item_add);
            tv_mine_cart_current_price = (TextView) itemView.findViewById(R.id.tv_mine_cart_current_price);
            tv_mine_cart_old_price = (TextView) itemView.findViewById(R.id.tv_mine_cart_old_price);
            tv_mine_cart_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//删除线
            btn_mine_car_item_jian.setOnClickListener(this);
            btn_mine_car_item_add.setOnClickListener(this);
            btn_mine_car_item_num.setOnClickListener(this);

            cb_mine_car_item_center.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getRealPosition(getPosition());
                    CartBean.DataEntity.ItemssEntity.ItemsEntity item = items.get(position);
                    boolean selected = !cb_mine_car_item_center.isSelected();
                    item.setCheck(selected);
                    notifyItemChanged(getPosition());
                    if (!selected) {
                        canitems.get(0).setCheckAll(false);
                        notifyItemChanged(1);
                        pager.setCheck(false);
                    }else{
                        boolean isSelectAll = true;
                        for(int i = 0; i < items.size(); i++) {
                            if (!items.get(i).isCheck()) {
                                isSelectAll = false;
                                break;
                            }
                        }
                        if (isSelectAll) {
                            selectAll(true);
                            pager.setCheck(true);
                        }
                    }
                    refresh();
                }
            });
        }

        private int position;

        @Override
        public void onClick(View v) {
            int position = getRealPosition(getPosition());
            if (position < 0) {
                position = this.position;
            } else {
                this.position = position;
            }
            int num = items.get(position).getNum();
            int id = items.get(position).getId();
            int surplus_num = items.get(position).getSurplus_num();
            switch (v.getId()) {
                case R.id.btn_mine_car_item_jian:
                    num = num > 0 ? num - 1 : 0;
                    btn_mine_car_item_num.setText(num + "");
                    refresh();
                    pager.addToCart(num, id);
                    break;
                case R.id.btn_mine_car_item_add:
                    num = num < surplus_num ? num + 1 : surplus_num;
                    btn_mine_car_item_num.setText(num + "");
                    refresh();
                    pager.addToCart(num, id);
                    break;
                case R.id.btn_mine_car_item_num:
                    showChange(num, surplus_num);
                    break;
                case R.id.iv_mine_cart2_dilaog_jian:
                    num = num > 0 ? num - 1 : 0;
                    iv_mine_cart2_dilaog_num.setText(num + "");
                    break;
                case R.id.iv_mine_cart2_dilaog_add:
                    num = num < surplus_num ? num + 1 : surplus_num;
                    iv_mine_cart2_dilaog_num.setText(num + "");
                    break;
                case R.id.iv_mine_cart2_dilaog_queding:
                    String numStr = iv_mine_cart2_dilaog_num.getText().toString();
                    btn_mine_car_item_num.setText(numStr);
                    num = Integer.parseInt(numStr);
                    refresh();
                    pager.addToCart(num, id);
                case R.id.iv_mine_cart2_dilaog_quxiao:
                    dialog.dismiss();
                    break;
            }
            items.get(position).setNum(num);

        }

        private void showChange(final int num, final int surplus_num) {
            View inflate = View.inflate(mActivity, R.layout.mine_cart2_dialog, null);
            ImageView iv_mine_cart2_dilaog_jian = (ImageView) inflate.findViewById(R.id.iv_mine_cart2_dilaog_jian);
            iv_mine_cart2_dilaog_num = (EditText) inflate.findViewById(R.id.iv_mine_cart2_dilaog_num);
            ImageView iv_mine_cart2_dilaog_add = (ImageView) inflate.findViewById(R.id.iv_mine_cart2_dilaog_add);
            TextView iv_mine_cart2_dilaog_quxiao = (TextView) inflate.findViewById(R.id.iv_mine_cart2_dilaog_quxiao);
            TextView iv_mine_cart2_dilaog_queding = (TextView) inflate.findViewById(R.id.iv_mine_cart2_dilaog_queding);
            iv_mine_cart2_dilaog_num.setText(num + "");
            dialog = new AlertDialog.Builder(mActivity)
                    .setView(inflate)
                    .show();

            iv_mine_cart2_dilaog_jian.setOnClickListener(this);
            iv_mine_cart2_dilaog_add.setOnClickListener(this);
            iv_mine_cart2_dilaog_quxiao.setOnClickListener(this);
            iv_mine_cart2_dilaog_queding.setOnClickListener(this);


            iv_mine_cart2_dilaog_num.addTextChangedListener(new TextWatcher() {

                int MIN_MARK = 0;
                int MAX_MARK = surplus_num;

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s != null && !s.equals("")) {
                        int n = num;
                        try {
                            n = Integer.parseInt(s.toString());
                        } catch (NumberFormatException e) {
                            n = num;
                        }
                        if (n > MAX_MARK) {
                            s = String.valueOf(MAX_MARK);
                            iv_mine_cart2_dilaog_num.setText(s);
                        } else if (n < MIN_MARK) {
                            s = String.valueOf(MIN_MARK);
                            iv_mine_cart2_dilaog_num.setText(s);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null && !s.equals("")) {
                        int markVal = num;
                        try {
                            markVal = Integer.parseInt(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = num;
                        }
                        if (markVal > MAX_MARK) {
                            iv_mine_cart2_dilaog_num.setText(String.valueOf(MAX_MARK));
                        }
                        if (markVal < MIN_MARK) {
                            iv_mine_cart2_dilaog_num.setText(String.valueOf(MIN_MARK));
                        }
                    }
                }
            });

        }
    }

    private void refresh() {
        getCount();
        getPrice();
        notifyItemChanged(0);
        notifyItemChanged(items.size() + 2);
    }

    class ViewHolderCannot extends RecyclerView.ViewHolder {

        private FrameLayout fl_mine_image;
        private ImageView iv_mine_car_item;
        private TextView tv_mine_car_item_name;
        private TextView tv_mine_car_item_kouwei;
        private TextView tv_mine_cart_current_price;
        private TextView tv_mine_cart_old_price;
        private TextView tv_mine_cart2_cannot_clear;
        private LinearLayout ll_mine_cart2_mot_top;

        public ViewHolderCannot(View itemView) {
            super(itemView);
            fl_mine_image = (FrameLayout) itemView.findViewById(R.id.fl_mine_image);
            iv_mine_car_item = (ImageView) itemView.findViewById(R.id.iv_mine_car_item);
            tv_mine_car_item_name = (TextView) itemView.findViewById(R.id.tv_mine_car_item_name);
            tv_mine_car_item_kouwei = (TextView) itemView.findViewById(R.id.tv_mine_car_item_kouwei);
            tv_mine_cart_current_price = (TextView) itemView.findViewById(R.id.tv_mine_cart_current_price);
            tv_mine_cart_old_price = (TextView) itemView.findViewById(R.id.tv_mine_cart_old_price);
            tv_mine_cart2_cannot_clear = (TextView) itemView.findViewById(R.id.tv_mine_cart2_cannot_clear);
            ll_mine_cart2_mot_top = (LinearLayout) itemView.findViewById(R.id.ll_mine_cart2_mot_top);
            tv_mine_cart_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            tv_mine_cart2_cannot_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mActivity, "正在清空不可购买商品", Toast.LENGTH_SHORT).show();
                    pager.clearCannot();
                }
            });

        }
    }

    class ViewHolderTaoBao extends RecyclerView.ViewHolder {

        private LinearLayout ll_mine_cart2_not_bottom;

        public ViewHolderTaoBao(View itemView) {
            super(itemView);
            ll_mine_cart2_not_bottom = (LinearLayout) itemView.findViewById(R.id.ll_mine_cart2_not_bottom);
            ll_mine_cart2_not_bottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mActivity, "跳转到淘宝购物车", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ViewHolderCanHead extends RecyclerView.ViewHolder {

        private ImageView cb_mine_cart2_head_check_all;
        private LinearLayout ll_mine_cart2_can_head_check_all;

        public ViewHolderCanHead(View itemView) {
            super(itemView);
            //头部
            ll_mine_cart2_can_head_check_all = (LinearLayout) itemView.findViewById(R.id.ll_mine_cart2_can_head_check_all);
            cb_mine_cart2_head_check_all = (ImageView) itemView.findViewById(R.id.cb_mine_cart2_head_check_all);

            cb_mine_cart2_head_check_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean selected = !cb_mine_cart2_head_check_all.isSelected();
                    cb_mine_cart2_head_check_all.setSelected(selected);
                    pager.setCheck(selected);
                    for (int i = 0; i < items.size(); i++) {
                        items.get(i).setCheck(selected);
                        notifyItemChanged(i + 2);
                    }
                    refresh();
                }
            });
        }
    }

    class ViewHolderCanHead1 extends RecyclerView.ViewHolder {

        private TextView tv_mine_cart2_head_total;

        public ViewHolderCanHead1(View itemView) {
            super(itemView);
            //头部
            tv_mine_cart2_head_total = (TextView) itemView.findViewById(R.id.tv_mine_cart2_head_total);
        }
    }

    class ViewHolderCanBottom extends RecyclerView.ViewHolder {

        private RelativeLayout ll_mine_cart2_bottom;
        private TextView tv_mine_cart2_bottom_price;
        private TextView tv_mine_cart2_bottom_total;

        public ViewHolderCanBottom(View itemView) {
            super(itemView);
            //尾部
            tv_mine_cart2_bottom_total = (TextView) itemView.findViewById(R.id.tv_mine_cart2_bottom_total);
            ll_mine_cart2_bottom = (RelativeLayout) itemView.findViewById(R.id.ll_mine_cart2_bottom);
            tv_mine_cart2_bottom_price = (TextView) itemView.findViewById(R.id.tv_mine_cart2_bottom_price);
        }
    }
}

