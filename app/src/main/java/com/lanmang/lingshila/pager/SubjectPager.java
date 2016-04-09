package com.lanmang.lingshila.pager;

import android.app.Activity;
import android.view.View;

import com.lanmang.lingshila.R;
import com.lanmang.lingshila.base.BasePager;
import com.lanmang.lingshila.utils.LogUtils;

import org.xutils.x;

/**
 * Created by lanmang on 2016/4/8.
 * 专题页面
 */
public class SubjectPager extends BasePager {


    public SubjectPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.subject_pager, null);
        x.view().inject(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("专题 数据加载了");
        //textView.setText("专题");
    }
}
