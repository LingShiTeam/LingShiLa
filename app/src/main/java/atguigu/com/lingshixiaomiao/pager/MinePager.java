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
 * 我的小喵页面
 */
public class MinePager extends BasePager {

    private TextView textView;

    public MinePager(Activity mActivity) {
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
        LogUtils.loge("lalalal");
        LogUtils.loge("我的小喵 0哈哈哈 数据加载了 ");
        LogUtils.loge("olaoloalo");
        textView.setText("我的小喵");
    }
}
