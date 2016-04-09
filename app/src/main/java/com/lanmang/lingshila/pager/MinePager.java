package com.lanmang.lingshila.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lanmang.lingshila.base.BasePager;
import com.lanmang.lingshila.utils.LogUtils;

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
        LogUtils.loge("我的小喵 数据加载了");
        textView.setText("我的小喵");
    }
}
