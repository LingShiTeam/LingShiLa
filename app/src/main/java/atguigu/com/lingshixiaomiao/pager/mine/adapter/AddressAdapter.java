package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.bean.AddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.ChangeAddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/13.
 */
public class AddressAdapter extends RecyclerView.Adapter {

    private Context context;
    public AddressBean.DataEntity data;
    private final List<AddressBean.DataEntity.ItemsEntity> items;

    public AddressAdapter(Context context, AddressBean.DataEntity data) {
        this.context = context;
        items = data.getItems();
        this.data = data;
        sortItems();
    }

    private void sortItems() {
        Collections.sort(items, new Comparator<AddressBean.DataEntity.ItemsEntity>() {
            @Override
            public int compare(AddressBean.DataEntity.ItemsEntity lhs, AddressBean.DataEntity.ItemsEntity rhs) {
                return lhs.getType() > rhs.getType() ? -1 : 0;
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.mine_address_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddressBean.DataEntity.ItemsEntity item = items.get(position);
        ViewHolder h = (ViewHolder) holder;

        h.tv_mine_address_name.setText(item.getName());
        h.tv_mine_address_number.setText(item.getPhone());
        String address = item.getProvince() + " " + item.getCity() + " " + item.getProper() + " " + item.getFull_add();
        h.tv_mine_address.setText(address);
        if (Constants.DEFAULT_ADDRESS == item.getType()) {
            h.tv_mine_address_default.setSelected(true);
        } else {
            h.tv_mine_address_default.setSelected(false);
        }
        h.tv_mine_address_name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_mine_address_name;
        private TextView tv_mine_address_number;
        private TextView tv_mine_address;
        private TextView tv_mine_address_default;
        private TextView tv_mine_address_delete;
        private TextView tv_mine_address_edit;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_mine_address_name = (TextView) itemView.findViewById(R.id.tv_mine_address_name);
            tv_mine_address_number = (TextView) itemView.findViewById(R.id.tv_mine_address_number);
            tv_mine_address = (TextView) itemView.findViewById(R.id.tv_mine_address);
            tv_mine_address_default = (TextView) itemView.findViewById(R.id.tv_mine_address_default);
            tv_mine_address_delete = (TextView) itemView.findViewById(R.id.tv_mine_address_delete);
            tv_mine_address_edit = (TextView) itemView.findViewById(R.id.tv_mine_address_edit);

            tv_mine_address_default.setOnClickListener(this);
            tv_mine_address_delete.setOnClickListener(this);
            tv_mine_address_edit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_mine_address_default:
                    setDefaultAddress(getPosition());
                    break;
                case R.id.tv_mine_address_delete:
                    showDeleteDialog();
                    break;
                case R.id.tv_mine_address_edit:
                    editAddress();
                    break;
            }
        }

        private void showDeleteDialog() {
            new AlertDialog.Builder(context)
                    .setMessage("您确定是否删除该收货地址?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteAddress();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();

        }

        private void editAddress() {
            Intent intent = new Intent(context, MineContentActivity.class);
            Bundle bundle = new Bundle();
            AddressBean.DataEntity.ItemsEntity item = items.get(getPosition());

            bundle.putString("add_id", item.getId() + "");
            bundle.putString("name", item.getName());
            bundle.putString("phone", item.getPhone());
            bundle.putString("province", item.getProvince());
            bundle.putString("city", item.getCity());
            bundle.putString("proper", item.getProper());
            bundle.putString("detail", item.getFull_add());
            bundle.putInt("type", item.getType());
            bundle.putInt("position", getPosition());
            intent.putExtras(bundle);
            intent.putExtra("pager", Constants.EDITADDRESS_PAGER);
            intent.putExtra("edit", true);
            context.startActivity(intent);
        }

        private void deleteAddress() {

            if (LoginUtils.getInstance().isLogin()) {
                int position = getPosition();
                LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
                int opt = tv_mine_address_default.isSelected() ? 1 : 0;
                int id = items.get(position).getId();
                String url = Url.DELETE_ADDRESS_URL[0] + loginBean.getData().getUid() + Url.DELETE_ADDRESS_URL[1] + opt + Url.DELETE_ADDRESS_URL[2] + id;
                items.remove(position);
                //重置默认地址
                if (getItemCount() > 0 && opt == 1) {
                    setDefaultAddress(0);
                } else {
                    new JsonUtils().loadData(url, ChangeAddressBean.class);
                }
            } else {
                Toast.makeText(context, "请登录后修改", Toast.LENGTH_SHORT).show();
            }
        }

        private void setDefaultAddress(int position) {
            if (LoginUtils.getInstance().isLogin()) {
                if (!tv_mine_address_default.isSelected()) {
                    for (int i = 0; i < items.size(); i++) {
                        items.get(i).setType(0);
                    }
                    AddressBean.DataEntity.ItemsEntity item = items.get(position);
                    item.setType(1);
                    sortItems();
                    LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
                    String url = Url.CHANGE_DEFAULT_ADDRESS_URL[0] + loginBean.getData().getUid() + Url.CHANGE_DEFAULT_ADDRESS_URL[1] + item.getId();
                    new JsonUtils().loadData(url, ChangeAddressBean.class);
                }
            } else {
                Toast.makeText(context, "请登录后修改", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
