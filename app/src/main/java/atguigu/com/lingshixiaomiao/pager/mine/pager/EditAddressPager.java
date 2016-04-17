package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.AddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.ChangeAddressBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CountryBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.ProvinceBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.StringUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/10.
 */
public class EditAddressPager extends ContentBasePager implements View.OnClickListener {

    private Bundle bundle;
    private String title;
    private String completeBtn;

    private EditText et_mine_edit_address_name;
    private EditText et_mine_edit_address_phone;
    private EditText et_mine_edit_address_address;
    private EditText et_mine_edit_address_detail;
    private TextView et_mine_edit_address_default;
    private LinearLayout ll_mine_address;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String proper;
    private String full_add;
    private int type;
    private String add_id;
    private AddressBean.DataEntity.ItemsEntity item;
    private TextView tv_choice_address;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param bundle
     * @param isEdit
     */
    public EditAddressPager(Activity mActivity, Bundle bundle, boolean isEdit) {
        super(mActivity);
        this.bundle = bundle;
        loadData(isEdit);
        loadCitiesData();
    }

    private void loadData(boolean isEdit) {
        if (isEdit) {
            title = "修改收货地址";
            completeBtn = "保存";

            add_id = bundle.getString("add_id");
            name = bundle.getString("name");
            phone = bundle.getString("phone");
            province = bundle.getString("province");
            city = bundle.getString("city");
            proper = bundle.getString("proper");
            full_add = bundle.getString("detail");
            int type = bundle.getInt("type");

            et_mine_edit_address_name.setText(name);
            et_mine_edit_address_phone.setText(phone);
            et_mine_edit_address_address.setText(province + " " + city + " " + proper);
            et_mine_edit_address_detail.setText(full_add);
            et_mine_edit_address_default.setSelected(type == 1 ? true : false);

        } else {
            title = "新建收货地址";
            completeBtn = "完成";
        }

        super.title = setTitle();
        super.complete = setComplete();
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_address_edit, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        et_mine_edit_address_name = (EditText) v.findViewById(R.id.et_mine_edit_address_name);
        et_mine_edit_address_phone = (EditText) v.findViewById(R.id.et_mine_edit_address_phone);
        et_mine_edit_address_address = (EditText) v.findViewById(R.id.et_mine_edit_address_address);
        et_mine_edit_address_detail = (EditText) v.findViewById(R.id.et_mine_edit_address_detail);
        et_mine_edit_address_default = (TextView) v.findViewById(R.id.et_mine_edit_address_default);
        ll_mine_address = (LinearLayout) v.findViewById(R.id.ll_mine_address);

        setListener();
    }

    private void setListener() {
        et_mine_edit_address_default.setOnClickListener(this);

        ll_mine_address.setOnClickListener(this);
    }

    @Override
    public String setTitle() {
        return title;
    }

    @Override
    public String setComplete() {
        return completeBtn;
    }

    private void editAddress() {
        if (LoginUtils.getInstance().isLogin()) {
            LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
            String uid = loginBean.getData().getUid();
            String[] u = Url.EDIT_ADDRESS_URL;

            name = et_mine_edit_address_name.getText().toString();
            phone = et_mine_edit_address_phone.getText().toString();
            full_add = et_mine_edit_address_detail.getText().toString();

            encodeAddress();

            String url = u[0] + uid + u[1] + add_id + u[2] + name + u[3] + phone
                    + u[4] + province + u[5] + city + u[6] + proper + u[7] + full_add + u[8] + type;

            new JsonUtils().loadData(url, ChangeAddressBean.class);
        } else {
            Toast.makeText(mActivity, "请联网后进行操作", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void complete() {
        if (LoginUtils.getInstance().isLogin()) {
            editAddress();
        } else {
            Toast.makeText(mActivity, "请登录后操作", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取修改地址的返回值
     */
    @Subscribe
    public void onEventMainThread(ChangeAddressBean changeAddressBean) {

        int editid = changeAddressBean.getData().getId();

        if (editid == 0) {
            return;
        }

        if (Constants.SUCCESS.equals(changeAddressBean.getRs_code())) {
            AddressBean.DataEntity data = AddressPager.data;
            List<AddressBean.DataEntity.ItemsEntity> items = data.getItems();

            for (int i = 0; i < items.size(); i++) {
                int id = items.get(i).getId();
                if (id == editid) {
                    item = items.get(i);
                }
            }

            if (item == null) {
                item = new AddressBean.DataEntity.ItemsEntity();
                item.setId(editid);
                items.add(item);
            }

            if (type == 1) {
                for (int i = 0; i < items.size(); i++) {
                    items.get(i).setType(0);
                }
                item.setType(1);
            } else {
                item.setType(0);
            }

            item.setName(name);
            item.setPhone(phone);
            decodeAddress();
            item.setProvince(province);
            item.setCity(city);
            item.setProper(proper);

            item.setFull_add(full_add);

            Collections.sort(items, new Comparator<AddressBean.DataEntity.ItemsEntity>() {
                @Override
                public int compare(AddressBean.DataEntity.ItemsEntity lhs, AddressBean.DataEntity.ItemsEntity rhs) {
                    return lhs.getType() > rhs.getType() ? -1 : 0;
                }
            });
            EventBus.getDefault().post(data);
        }
        mActivity.finish();
    }


    private void encodeAddress() {
        try {
            province = URLEncoder.encode(province, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            city = URLEncoder.encode(city, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            proper = URLEncoder.encode(proper, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void decodeAddress() {
        try {
            province = URLDecoder.decode(province, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            city = URLDecoder.decode(city, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            proper = URLDecoder.decode(proper, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_mine_edit_address_default:
                et_mine_edit_address_default.setSelected(!et_mine_edit_address_default.isSelected());
                type = et_mine_edit_address_default.isSelected() ? 1 : 0;
                break;
            case R.id.ll_mine_address:
                //点击弹出选项选择器
                pvOptions.show();
                //choiceAddress();
                break;
        }
    }

    private void choiceAddress() {
        View inflate = View.inflate(mActivity, R.layout.mine_choice_address, null);
        tv_choice_address = (TextView) inflate.findViewById(R.id.tv_choice_address);

        new AlertDialog.Builder(mActivity)
                .setView(inflate)
                .show();
    }

    private ArrayList<ProvinceBean> options1Items = new ArrayList<ProvinceBean>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();
    OptionsPickerView pvOptions;

    public void loadCitiesData() {
        try {
           /* InputStream is = mActivity.getAssets().open("cities.json");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            String citiesJson = baos.toString();
            baos.close();
            is.close();*/

            InputStream is = mActivity.getAssets().open("cities.json");
            int size = is.available();

            LogUtils.loge("size = " + size);

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            String citiesJson = StringUtils.replaceBlank(new String(buffer, "utf-8"));

            LogUtils.loge("citiesJson = " + citiesJson);
            //new JsonUtils().parseJson(citiesJson, CountryBean.class);
            countryBean =  new Gson().fromJson(citiesJson, CountryBean.class);
            LogUtils.loge("countryBEna = " + countryBean);
            setPicker();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CountryBean countryBean;

    @Subscribe
    public void onEventMainThread(CountryBean countryBean) {
        LogUtils.loge("countryBean = " + countryBean);
        this.countryBean = countryBean;
        //setPicker();
    }

    private void setPicker() {

        //选项选择器
        pvOptions = new OptionsPickerView(mActivity);


        List<CountryBean.CountryEntity.ProvinceEntity> province = countryBean.getCountry().getProvince();
        for (int i = 0; i < province.size(); i++) {
            //选项1
            CountryBean.CountryEntity.ProvinceEntity provinceEntity = province.get(i);
            options1Items.add(new ProvinceBean(i, provinceEntity.getName(), "", ""));

            //选项2
            List<CountryBean.CountryEntity.ProvinceEntity.CityEntity> city = provinceEntity.getCity();

            ArrayList<String> options2Items_01 = null;
            ArrayList<ArrayList<String>> options3Items_01 = null;

            for (int j = 0; j < city.size(); j++) {
                options2Items_01 = new ArrayList<String>();
                options2Items_01.add(city.get(j).getName());

                //选项3
                List<CountryBean.CountryEntity.ProvinceEntity.CityEntity.CountyEntity> county = city.get(j).getCounty();
                options3Items_01 = new ArrayList<ArrayList<String>>();
                ArrayList<String> options3Items_01_01 = null;
                for (int w = 0; w < county.size(); i++) {
                    options3Items_01_01 = new ArrayList<String>();
                    options3Items_01_01.add(county.get(w).getName());
                }
                options3Items_01.add(options3Items_01_01);

            }
            options2Items.add(options2Items_01);

            options3Items.add(options3Items_01);
        }

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, true, true);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                EditAddressPager.this.province = options1Items.get(options1).getPickerViewText();
                city = options2Items.get(options1).get(option2);
                proper = options3Items.get(options1).get(option2).get(options3);
                et_mine_edit_address_address.setText(EditAddressPager.this.province + " " + city + " " + proper);
            }
        });

    }
}
