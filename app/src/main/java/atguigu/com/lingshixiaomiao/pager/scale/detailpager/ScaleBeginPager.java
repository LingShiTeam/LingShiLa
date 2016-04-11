package atguigu.com.lingshixiaomiao.pager.scale.detailpager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.pager.scale.base.ScaleBasePager;

/**
 * Created by Administrator on 2016/4/10 0010.
 */
public class ScaleBeginPager extends ScaleBasePager {

    private TextView textView;

    public ScaleBeginPager(Activity activity) {
        super(activity);
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
        LogUtils.loge("即将开始 数据加载了");
        textView.setText("即将开始");
    }
}
