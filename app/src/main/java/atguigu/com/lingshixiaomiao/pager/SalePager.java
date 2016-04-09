package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.LogUtils;


/**
 * Created by lanmang on 2016/4/8.
 * 特卖页面
 */
public class SalePager extends BasePager {

    private TextView textView;

    public SalePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        textView = new TextView(mActivity);
        textView.setTextColor(Color.RED);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("特卖 数据加载了");
        textView.setText("特卖");
    }
}
