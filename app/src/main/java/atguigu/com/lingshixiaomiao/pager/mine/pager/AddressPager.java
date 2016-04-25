package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.application.GlobalVariables;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.AddressAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.AddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.ChangeAddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.DefaultAddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/10.
 */
public class AddressPager extends ContentBasePager {

    private RecyclerView rv_address_manage;
    private JsonUtils jsonUtils;
    public static AddressBean.DataEntity data;
    private AddressAdapter adapter;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public AddressPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_setting_address, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        rv_address_manage = (RecyclerView) v.findViewById(R.id.rv_address_manage);
    }

    @Override
    public String setTitle() {
        return "收货地址管理";
    }

    @Override
    public String setComplete() {
        return "新增";
    }

    @Override
    public void initData() {
        super.initData();
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String uid = loginBean.getData().getUid();
        String url = Url.MANAGE_ADDRESS_URL[0] + uid + Url.MANAGE_ADDRESS_URL[1];
        jsonUtils = new JsonUtils();
        jsonUtils.loadData(url, AddressBean.class);
    }

    @Override
    public void complete() {
        Intent intent = new Intent(mActivity, MineContentActivity.class);
        intent.putExtra("edit", false);
        intent.putExtra("pager", Constants.EDITADDRESS_PAGER);
        mActivity.startActivity(intent);
    }


    /**
     * 获取地址管理的联网返回值
     */
    @Subscribe
    public void onEventMainThread(AddressBean addressBean) {
        if (Constants.SUCCESS.equals(addressBean.getRs_code())) {
            data = addressBean.getData();
            setAdapter();
            setDefaultAddress();
        }
    }

    public void setDefaultAddress() {
        List<AddressBean.DataEntity.ItemsEntity> items = data.getItems();
        boolean hasDefault = false;
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getType() == 1) {
                    hasDefault = true;
                    AddressBean.DataEntity.ItemsEntity t = items.get(i);
                    DefaultAddressBean d = new DefaultAddressBean();
                    d.setCity(t.getCity());
                    d.setFull_add(t.getFull_add());
                    d.setId(t.getId());
                    d.setName(t.getName());
                    d.setPhone(t.getPhone());
                    d.setProper(t.getProper());
                    d.setProvince(t.getProvince());
                    d.setType(t.getType());
                    GlobalVariables.defaultAddressBean = d;
                }
            }
        }
        if (!hasDefault) {
            GlobalVariables.defaultAddressBean = null;
        }

        if (GlobalVariables.defaultAddressBean == null) {
            LogUtils.loge("默认地址为null");
        }else{
            LogUtils.loge(GlobalVariables.defaultAddressBean.toString());
        }

    }

    /**
     * 获取修改地址的返回值
     */
    @Subscribe
    public void onEventMainThread(ChangeAddressBean changeAddressBean) {
        setDefaultAddress();
        int id = changeAddressBean.getData().getId();
        if (id != 0) {
            return;
        }
        if (Constants.SUCCESS.equals(changeAddressBean.getRs_code())) {
            adapter.notifyDataSetChanged();
            Toast.makeText(mActivity, "地址修改成功", Toast.LENGTH_SHORT).show();
            LogUtils.loge("地址修改成功");
        } else {
            Toast.makeText(mActivity, "地址修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取修改地址的返回值
     */
    @Subscribe
    public void onEventMainThread(AddressBean.DataEntity data) {
        adapter.notifyDataSetChanged();
        setDefaultAddress();
    }

    private void setAdapter() {
        rv_address_manage.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new AddressAdapter(mActivity, data);
        rv_address_manage.setAdapter(adapter);
    }

    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
