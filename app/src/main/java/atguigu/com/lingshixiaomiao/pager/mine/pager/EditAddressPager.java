package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;

/**
 * Created by lanmang on 2016/4/10.
 */
public class EditAddressPager extends ContentBasePager {

    private Bundle bundle;
    private String title;
    private String completeBtn;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param bundle
     */
    public EditAddressPager(Activity mActivity, Bundle bundle) {
        super(mActivity);
        this.bundle = bundle;
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_address_edit, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {

    }

    @Override
    public String setTitle() {
        return title;
    }

    @Override
    public String setComplete() {
        return completeBtn;
    }

    @Override
    public void initData() {
        super.initData();
        if (bundle != null) {
            title = "修改收货地址";
            completeBtn = "保存";



        }else{
            title = "新建收货地址";
            completeBtn = "完成";
        }
    }

}
