package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.AddressAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.AddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.ChangeAddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CacheUtils;
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
    private AddressBean.DataEntity data;
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
        rv_address_manage = (RecyclerView)v.findViewById(R.id.rv_address_manage);
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

    }


    /**
     * 获取地址管理的联网返回值
     *
     */
    @Subscribe
    public void onEventMainThread(AddressBean addressBean) {
        if (Constants.SUCCESS.equals(addressBean.getRs_code())) {
            data = addressBean.getData();
            String address = jsonUtils.getJson();
            CacheUtils.setCache(CacheUtils.getSmallFile(mActivity, "address"), address);

            setAdapter();
        }
    }
    /**
     * 获取修改地址的返回值
     *
     */
    @Subscribe
    public void onEventMainThread(ChangeAddressBean changeAddressBean) {
        if(Constants.SUCCESS.equals(changeAddressBean.getRs_code())){
            Toast.makeText(mActivity, "地址修改成功", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(mActivity, "地址修改失败", Toast.LENGTH_SHORT).show();
        }
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
