package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.mine.activity.SettingActivity;

/**
 * Created by lanmang on 2016/4/8.
 * 我的小喵页面
 */
public class MinePager extends BasePager implements View.OnClickListener{

    private ImageButton ib_mine_setting;

    public MinePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_pager, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        ib_mine_setting = (ImageButton)v.findViewById(R.id.ib_mine_setting);

        ib_mine_setting.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("我的小喵 数据加载了");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.ib_mine_setting:
                startActivity(SettingActivity.class);
                break;
        }
    }

    private void startActivity(Class clazz) {
        mActivity.startActivity(new Intent(mActivity, clazz));
    }
}
