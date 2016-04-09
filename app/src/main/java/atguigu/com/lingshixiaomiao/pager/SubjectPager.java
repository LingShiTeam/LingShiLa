package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;

/**
 * Created by lanmang on 2016/4/8.
 * 专题页面
 */
public class SubjectPager extends BasePager {

    private TextView textView;

    public SubjectPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.subject_pager, null);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("专题 数据加载了");
        textView.setText("专题");
    }
}
